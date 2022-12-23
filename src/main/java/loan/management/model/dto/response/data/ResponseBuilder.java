package loan.management.model.dto.response.data;

import org.springframework.http.ResponseEntity;


public class ResponseBuilder {

    public static ResponseEntity<ApiResponse> buildResponse(int httpStatusCode, String message) {
        return new ApiResponse.ApiResponseBuilder<>(httpStatusCode, message).build();
    }
}
