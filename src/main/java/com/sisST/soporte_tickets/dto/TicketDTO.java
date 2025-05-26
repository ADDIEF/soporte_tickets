package com.sisST.soporte_tickets.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/* dto/TicketDTO.java */
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor

@Valid
public class TicketDTO {

    public enum Urgencia { BAJA, MEDIA, ALTA, CRITICA }
    public enum Estado   { ABIERTO, EN_PROCESO, RESUELTO, CERRADO }

    private Long id;

    @NotBlank @Size(max = 120)
    private String titulo;

    @NotBlank @Size(max = 2_000)
    private String descripcion;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Urgencia urgencia;

    private Long tecnicoAsignadoId;

    @NotNull
    private Estado estado;

    @PastOrPresent
    private LocalDateTime fechaCreacion;

    @FutureOrPresent
    private LocalDateTime fechaResolucion;

    private List<String> adjuntos;
}
