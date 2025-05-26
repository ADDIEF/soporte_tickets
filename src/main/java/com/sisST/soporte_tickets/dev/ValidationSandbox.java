package com.sisST.soporte_tickets.dev;

import com.sisST.soporte_tickets.SoporteTicketsApplication;
import com.sisST.soporte_tickets.dto.*;
import com.sisST.soporte_tickets.validation.TicketValidator;
import com.sisST.soporte_tickets.validation.TecnicoValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;

public class ValidationSandbox {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SoporteTicketsApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        Validator beanValidator = ctx.getBean(Validator.class);

        /* === DTO: AuthDTO === */
        AuthDTO auth = AuthDTO.builder()
                .email("bademail")
                .password("123")
                .build();
        validateAndPrint(beanValidator, auth, "authDTO");

        /* === DTO: UsuarioDTO === */
        UsuarioDTO user = UsuarioDTO.builder()
                .nombre("")
                .email("user@")
                .rol("")
                .build();
        validateAndPrint(beanValidator, user, "usuarioDTO");

        /* === DTO: TicketDTO === */
        TicketDTO ticket = TicketDTO.builder()
                .titulo("Sin impresora")
                .descripcion("")
                .categoriaId(null)
                .urgencia(TicketDTO.Urgencia.ALTA)
                .estado(TicketDTO.Estado.RESUELTO)
                .build();

        Errors ticketErrors = new BeanPropertyBindingResult(ticket, "ticketDTO");
        for (ConstraintViolation<TicketDTO> v : beanValidator.validate(ticket)) {
            String field = v.getPropertyPath().toString();
            ticketErrors.rejectValue(field, "validation", v.getMessage());
        }
        ctx.getBean(TicketValidator.class).validate(ticket, ticketErrors);
        printResult("ticketDTO", ticketErrors);

        /* === DTO: TecnicoDTO === */
        TecnicoDTO tecnico = TecnicoDTO.builder()
                .nombre(" ")
                .email("noemail")
                .categoriasSoportadas(List.of(1L, 1L))
                .build();
        Errors tecErrors = new BeanPropertyBindingResult(tecnico, "tecnicoDTO");
        beanValidator.validate(tecnico).forEach(v -> tecErrors.rejectValue(
                v.getPropertyPath().toString(), "validation", v.getMessage()));
        ctx.getBean(TecnicoValidator.class).validate(tecnico, tecErrors);
        printResult("tecnicoDTO", tecErrors);

        /* === DTO: FeedbackDTO === */
        FeedbackDTO feedback = FeedbackDTO.builder()
                .ticketId(null)
                .calificacion(7)
                .comentario("OK")
                .build();
        validateAndPrint(beanValidator, feedback, "feedbackDTO");

        /* === DTO: ReporteDTO === */
        ReporteDTO reporte = ReporteDTO.builder()
                .estado(null)
                .urgencia(null)
                .tecnicoId(null)
                .categoriaId(null)
                .build();
        validateAndPrint(beanValidator, reporte, "reporteDTO");

        ctx.close();
    }

    private static void validateAndPrint(Validator validator, Object dto, String name) {
        Errors errors = new BeanPropertyBindingResult(dto, name);
        validator.validate(dto).forEach(v -> {
            String field = v.getPropertyPath().toString();
            if (field != null && !field.isBlank()) {
                errors.rejectValue(field, "validation", v.getMessage());
            } else {
                errors.reject("validation", v.getMessage());
            }
        });
        printResult(name, errors);
    }

    private static void printResult(String name, Errors errors) {
        System.out.println("\n[" + name + "]");
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e ->
                    System.out.println("• " + e.getDefaultMessage()));
        } else {
            System.out.println("✔ Sin errores");
        }
    }
}
