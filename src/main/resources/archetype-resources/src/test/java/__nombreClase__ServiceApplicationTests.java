package ${package};

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Prueba de integracion: levanta el contexto completo y se conecta a Oracle.
 * Solo se ejecuta cuando existe la variable DB_URL (en CI se omite).
 */
@SpringBootTest
@EnabledIfEnvironmentVariable(named = "DB_URL", matches = ".+")
class ${nombreClase}ServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
