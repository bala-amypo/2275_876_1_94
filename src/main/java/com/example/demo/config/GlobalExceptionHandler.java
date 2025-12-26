// package com.example.demo.config;

// import com.example.demo.dto.ApiResponse;
// import com.example.demo.exception.BadRequestException;
// import com.example.demo.exception.ResourceNotFoundException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {
    
//     @ExceptionHandler(BadRequestException.class)
//     public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException ex) {
//         return ResponseEntity.badRequest()
//             .body(new ApiResponse(false, ex.getMessage()));
//     }
    
//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<ApiResponse> handleNotFound(ResourceNotFoundException ex) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//             .body(new ApiResponse(false, ex.getMessage()));
//     }
    
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//             .body(new ApiResponse(false, "Internal server error"));
//     }
// }



