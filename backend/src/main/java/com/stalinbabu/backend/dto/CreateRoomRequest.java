package com.stalinbabu.backend.dto;

import jakarta.validation.constraints.NotNull;

public class CreateRoomRequest {

    @NotNull
    private PreferredColor preferredColor;

    public CreateRoomRequest() {
    }

    public PreferredColor getPreferredColor() {
        return preferredColor;
    }

    public void setPreferredColor(PreferredColor preferredColor) {
        this.preferredColor = preferredColor;
    }

}