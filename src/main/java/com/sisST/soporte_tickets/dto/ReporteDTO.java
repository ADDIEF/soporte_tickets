package com.sisST.soporte_tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/* dto/ReporteDTO.java */
/** DTO genérico para filtros de reportes */
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor

public class ReporteDTO {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private TicketDTO.Estado estado;
    private TicketDTO.Urgencia urgencia;
    private Long tecnicoId;
    private Long categoriaId;
}

