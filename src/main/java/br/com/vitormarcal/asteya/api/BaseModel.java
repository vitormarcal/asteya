package br.com.vitormarcal.asteya.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseModel {

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime disabledAt;
}
