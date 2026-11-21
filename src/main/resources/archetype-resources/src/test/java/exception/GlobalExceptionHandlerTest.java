package ${package}.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void recursoNoEncontradoDevuelve404() {
        ResponseEntity<Map<String, Object>> respuesta =
                handler.noEncontrado(new RecursoNoEncontradoException("No existe"));

        assertEquals(404, respuesta.getStatusCode().value());
        assertEquals("No existe", respuesta.getBody().get("message"));
    }

    @Test
    void conflictoDevuelve409() {
        ResponseEntity<Map<String, Object>> respuesta =
                handler.conflicto(new ConflictoException("Duplicado"));

        assertEquals(409, respuesta.getStatusCode().value());
    }

    @Test
    void accesoDenegadoDevuelve403() {
        ResponseEntity<Map<String, Object>> respuesta =
                handler.accesoDenegado(new AccesoDenegadoException("Sin permiso"));

        assertEquals(403, respuesta.getStatusCode().value());
    }
}
