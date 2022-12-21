package loan.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import loan.management.constant.ResponseMessageConstant;
import loan.management.model.dto.request.user.SortByUser;
import loan.management.model.dto.response.data.Data;
import loan.management.model.dto.response.data.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import loan.management.model.dto.response.user.UserResponses;
import loan.management.model.filter.RoleEnums;
import loan.management.model.filter.UserFilter;
import loan.management.service.user.UserService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @Operation(description = "Get User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ResponseMessageConstant.RETRIEVED_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE, content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/api/my-user")
    public ResponseEntity<DataResponse> getUser(
            @RequestParam(required = false,defaultValue = "") String userNameOrEmail ) {
       UserResponses user =  userService.getByEmailOrUsername(userNameOrEmail);
       Data data = Data.builder()
                .status(Objects.isNull(user) ?  HttpStatus.BAD_REQUEST.value() :  HttpStatus.OK.value())
                .isSuccess(Objects.isNull(user) ? false : true)
                .results(user)
                .build();

       return ResponseEntity.ok(DataResponse.builder().data(data).build());
    }

    @Operation(description = "Get User list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ResponseMessageConstant.RETRIEVED_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE, content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponses>> getUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "REQUEST_DATETIME") SortByUser sortBy,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction orderBy,
            @RequestParam(required = false, defaultValue = "USER") RoleEnums role,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromCreateDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toCreateDate
    ) {

        //create user filter
        UserFilter userFilter = UserFilter.builder()
                .page(page)
                .size(size)
                .sortByUser(sortBy)
                .orderBy(orderBy)
                .role(role)
                .fromRequestDate(fromCreateDate)
                .toRequestDate(toCreateDate)
                .build();

        return ResponseEntity.ok(userService.getUsers(userFilter));
    }
}
