package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import model.dto.request.user.UserLoginDto;
import model.dto.request.user.UserSignUpDto;
import model.dto.response.data.ApiResponseData;
import model.dto.response.data.Data;
import model.dto.response.data.DataResponse;
import model.dto.response.data.ResponseBuilder;
import model.dto.response.data.ApiResponse.ApiResponseBuilder;
import model.dto.response.user.JwtAuthenticationResponse;
import model.dto.response.user.UserDto;
import model.dto.response.user.UserResponses;
import model.entity.user.Role;
import model.entity.user.User;
import security.JwtTokenProvider;
import service.user.UserService;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import constant.AuthConstants;
import constant.ResponseMessageConstant;
import constant.RoleConstants;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;


    @Operation(description = "Sign in user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = ResponseMessageConstant.CREATE_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE)
    })
    @RequestMapping(value = "/api/auth/sign-in", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto userLogin){
    	Boolean isExistEmail = userService.existsByEmail(userLogin.getEmail());

        if(!isExistEmail) {
            Data data = Data.builder()
                    .message(AuthConstants.USERNAME_OR_PASWORD_NO_EXIST)
                    .isSuccess(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();

            return new ResponseEntity(DataResponse.builder().data(data), HttpStatus.BAD_REQUEST);
        }

    	UserResponses userResponse = userService.getByEmailOrUsername(userLogin.getEmail());
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        
        return ResponseBuilder.buildResponse(200, "login success", JwtAuthenticationResponse.builder().token(jwt)
        		.user(userResponse).build());
    }

    @Operation(description = "Sign up user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = ResponseMessageConstant.CREATE_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE)
    })
    @PostMapping(value="/api/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpDto userSignUp){

        if(userService.existsByEmail(userSignUp.getEmail())) {
            return ResponseEntity.badRequest().body(ApiResponseData.builder().success(false).message(AuthConstants.EMAIL_USER_EXIST).build());
        }

        // Creating user's account
        User user = User.builder()
        		.firstName(userSignUp.getFirstName())
        		.lastName(userSignUp.getLastName())
                .password(passwordEncoder.encode(userSignUp.getPassword()))
                .email(userSignUp.getEmail())
                .build();

        User result = userService.save(user);
        UserDto userDto = UserDto.builder()
        		 .id(result.getId())
        		 .firstName(result.getFirstName())
        		 .lastName(result.getLastName())
        		 .email(result.getEmail())
        		 .displayName(String.join(result.getFirstName()," ", result.getLastName()))
        		 .build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getFirstName() + result.getLastName()).toUri();

        	return ResponseBuilder.buildResponse(200, "register success", userDto);
    }
}
