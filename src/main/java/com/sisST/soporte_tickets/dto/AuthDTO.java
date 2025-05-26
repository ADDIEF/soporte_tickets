package com.sisST.soporte_tickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* dto/AuthDTO.java */
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder

public class AuthDTO {
    @Email 
    @NotBlank 
    private String email;

    @NotBlank 
    @Size(min = 6,max = 60) 
    private String password;
}
