package org.kevin.backendcostos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class DatosPersonaResponse {

    private String message;

    private Map<String, String> errors;

    public DatosPersonaResponse(String message) {
        this.message = message;
    }
}
