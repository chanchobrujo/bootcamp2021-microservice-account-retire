package com.everisbootcamp.accountretire.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("America/Lima"));

    public ResponseModel(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public Map<String, Object> getResponse() {
        Map<String, Object> response = new HashMap<>();

        response.put("message", this.getMessage());
        response.put("timestamp", this.getTimestamp());

        return response;
    }
}