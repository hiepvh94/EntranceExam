package loan.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import loan.management.model.dto.request.user.UserLoginDto;
import loan.management.model.dto.request.user.UserSignUpDto;
import loan.management.model.dto.response.data.ApiResponseData;
import loan.management.model.dto.response.data.Data;
import loan.management.model.dto.response.data.DataResponse;
import loan.management.model.dto.response.user.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import loan.management.constant.AuthConstants;
import loan.management.constant.ResponseMessageConstant;
import loan.management.constant.RoleConstants;
import loan.management.model.entity.user.Role;
import loan.management.model.entity.user.User;
import loan.management.security.JwtTokenProvider;
import loan.management.service.role.RoleService;
import loan.management.service.user.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;


    @Operation(description = "Sign in user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = ResponseMessageConstant.CREATE_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE)
    })
    @RequestMapping(value = "/api/auth/sign-in", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto userLogin){
        Boolean isExistUsername = userService.existsByUsername(userLogin.getUsernameOrEmail());
        Boolean isExistEmail = userService.existsByEmail(userLogin.getUsernameOrEmail());
//        trinhvo97
        if(!(isExistUsername || isExistEmail)) {
            Data data = Data.builder()
                    .message(AuthConstants.USERNAME_OR_PASWORD_NO_EXIST)
                    .isSuccess(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();

            return new ResponseEntity(DataResponse.builder().data(data), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsernameOrEmail(),
                        userLogin.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().accessToken(jwt).tokenType("Bearer").build());
    }

    @Operation(description = "Sign up user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = ResponseMessageConstant.CREATE_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE)
    })
    @PostMapping(value="/api/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpDto userSignUp){

        if(userService.existsByUsername(userSignUp.getUsername())) {
            return ResponseEntity.badRequest().body(ApiResponseData.builder().success(false).message(AuthConstants.USERNAME_USER_EXIST).build());
        }

        if(userService.existsByEmail(userSignUp.getEmail())) {
            return ResponseEntity.badRequest().body(ApiResponseData.builder().success(false).message(AuthConstants.EMAIL_USER_EXIST).build());
        }

        Role userRole = roleService.getRoleByName(RoleConstants.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        // Creating user's account
        User user = User.builder()
                .userName(userSignUp.getUsername())
                .password(passwordEncoder.encode(userSignUp.getPassword()))
                .fullName(userSignUp.getFullName())
                .email(userSignUp.getEmail())
                .mobile(userSignUp.getMobile())
                .roles(roles)
                .build();

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponseData(true, "User registered successfully"));
    }
}
