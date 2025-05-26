# Módulo 2 - DTOs y Validaciones


Entrega Módulo 2 - DTOs + Validaciones
Encargada: Adriana
Carpetas trabajadas: dto/, validation/ y resources/messages.properties

Estructura entregada

dto/
- AuthDTO.java: Credenciales de login con email y password
- UsuarioDTO.java: Datos de usuario: nombre, email, rol
- TecnicoDTO.java: Técnico: nombre, email, categorías soportadas (lista)
- TicketDTO.java: Título, descripción, urgencia, estado, técnico asignado
- FeedbackDTO.java: Evaluación de ticket (calificación y comentario)
- ReporteDTO.java: Filtros para generación de reportes

validation/
- TicketValidator.java: Si el ticket está RESUELTO debe tener técnico asignado
- TecnicoValidator.java: Evita categorías duplicadas en técnicos

resources/
- messages.properties: Textos personalizados para cada validación
- application.properties: Configura la carga del archivo de mensajes

Pruebas implementadas

ValidationSandbox.java:
- Prueba todos los DTOs del sistema con datos erróneos
- Llama a validaciones por anotación y validadores personalizados
- Imprime errores en consola agrupados por DTO

¿Cómo probar que todo funciona?

Ejecutar el siguiente comando desde la carpeta donde está el pom.xml:

mvn spring-boot:run "-Dspring-boot.run.main-class=com.sisST.soporte_tickets.dev.ValidationSandbox"

Salida esperada:

[authDTO]
- el email debe ser válido
- la contraseña debe tener entre 6 y 60 caracteres

[usuarioDTO]
- El nombre es obligatorio
- Debe ingresar un email válido
- El rol es obligatorio

[ticketDTO]
- La descripción es obligatoria
- Debe seleccionar una categoría
- Debe indicar el técnico que lo resolvió

[tecnicoDTO]
- El email del técnico es obligatorio
- No repitas categorías soportadas

[feedbackDTO]
- El ID del ticket es obligatorio
- La calificación máxima es 5

[reporteDTO]
- Sin errores

Estado de cumplimiento

- DTOs definidos correctamente: Completado
- Validaciones con anotaciones: Completado
- Validadores personalizados: Completado
- Pruebas manuales en sandbox: Completado
- Mensajes personalizados: Completado
- Pruebas de todos los DTOs: Completado

Mensaje sugerido para el equipo

Módulo 2: DTOs y Validaciones finalizado

Todos los DTOs del sistema están implementados con validaciones mediante anotaciones (@NotBlank, @Email, etc.). También se desarrollaron dos validadores personalizados:
- TicketValidator: para reglas cruzadas (estado vs técnico)
- TecnicoValidator: para detectar categorías repetidas

Se probó con un ValidationSandbox que ejecuta todas las validaciones y muestra los mensajes en consola.

Ejecutar prueba:
mvn spring-boot:run "-Dspring-boot.run.main-class=com.sisST.soporte_tickets.dev.ValidationSandbox"

Mensajes personalizados están en: resources/messages.properties

Todo listo para integrarse con servicios, controladores y excepciones.
