package org.kevin.backendcostos.dto;

import com.google.gson.Gson;

public record OkResponseDto(String message) {

    @Override
    public String toString() {
        Gson responseJson = new Gson();
        return responseJson.toJson(this);
    }
}
