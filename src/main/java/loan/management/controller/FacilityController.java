package loan.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import loan.management.constant.ResponseMessageConstant;
import loan.management.controller.v1.APIV1Controller;
import loan.management.model.dto.response.data.ResponseBuilder;
import loan.management.service.facility.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import loan.management.model.dto.request.facility.FacilityRequest;

@RestController
@RequiredArgsConstructor
@APIV1Controller
public class FacilityController {

    private final FacilityService facilityService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ResponseMessageConstant.RETRIEVED_SUCCESSFULLY),
            @ApiResponse(responseCode = "401", description = ResponseMessageConstant.NOT_ENOUGH_PRIVILEGE, content = @Content(schema = @Schema(hidden = true)))
    })
    @Operation(description = "Create facility for applicant")
    @PostMapping("/facilities")
    public ResponseEntity registerCreditFacility(@RequestBody FacilityRequest facilityRequest) {
        facilityService.registerFacility(facilityRequest);
        return ResponseBuilder.buildResponse(HttpStatus.OK.value(), "Register new facility");
    }
}
