package com.sisST.soporte_tickets.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* dto/FeedbackDTO.java */
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor

public class FeedbackDTO {
    @NotNull 
    private Long ticketId;

    @Min(1) 
    @Max(5) 
    private Integer calificacion;
    
    @Size(max = 500) 
    private String comentario;
}
