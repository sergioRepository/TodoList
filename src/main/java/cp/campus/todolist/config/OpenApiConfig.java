package cp.campus.todolist.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API DE PEDIDOS",
                version = "1.0.0",
                description = """
                        Gstion de categorias, produtos y pedidos.
                        Los pedidos obtienen precios del catalogo y
                        calculan sus importes finales.
                        """
        )
)
public class OpenApiConfig {
}
