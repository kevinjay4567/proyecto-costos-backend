package org.kevin.backendcostos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class BalanceResponse {

    private String message;

    private Map<String, String> errors;

    public BalanceResponse(String message) {
        this.message = message;
    }
}
