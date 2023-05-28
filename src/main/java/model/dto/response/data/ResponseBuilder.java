package model.dto.response.data;

import org.springframework.http.ResponseEntity;


public class ResponseBuilder {

    public static ResponseEntity<ApiResponse> buildResponse(int httpStatusCode, String message, Object object) {
        return new ApiResponse.ApiResponseBuilder<>(httpStatusCode, message).withData(object).build();
    }
}
