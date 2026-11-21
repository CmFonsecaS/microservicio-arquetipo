# Arquetipo Maven: foro-microservicio-arquetipo

## Sistema de Foros en Línea

**Asignatura:** Desarrollo Full Stack III (DSY2205) · **Institución:** Duoc UC

Plantilla Maven (`maven-archetype`) para crear los microservicios Spring Boot del Sistema de Foros en
Línea con la misma estructura, configuración y forma de despliegue. Se construyó a partir de los
microservicios `usuarios-service` y `administracion-service`, y con ella se generó `foro-service`.

## ¿Qué genera?

```
<servicio>/
├── .github/workflows/publish-ecr.yml   Pruebas + imagen en ECR + despliegue en EC2
├── ec2/                                 Scripts de despliegue (SSM) y service.env
├── Dockerfile                           Build multi-etapa (JDK 21 -> JRE 21, usuario sin privilegios)
├── .env.example / .gitignore / .dockerignore
├── pom.xml                              Spring Boot 4.1.2, Java 21, JPA, Security JWT, Oracle, Actuator
└── src/main/java/<paquete>/
    ├── <NombreClase>ServiceApplication.java
    ├── config/SecurityConfig.java       JWT HS256 compartido, roles desde el claim "role", CORS
    ├── exception/                       RecursoNoEncontrado (404), Conflicto (409), AccesoDenegado (403)
    │                                    y GlobalExceptionHandler (errores en JSON)
    ├── controller/ service/ repository/ model/ dto/   Capas vacías documentadas
    └── src/main/resources/application.properties      Oracle + JWT por variables de entorno
```

## Parámetros

| Propiedad | Descripción | Valor por defecto |
|---|---|---|
| `groupId` | Grupo Maven | `cl.duoc` |
| `artifactId` | Nombre del microservicio | — |
| `package` | Paquete Java base | `groupId` |
| `nombreClase` | Prefijo de la clase principal (`Foro` → `ForoServiceApplication`) | — |
| `puerto` | Puerto HTTP | `8080` |
| `descripcion` | Descripción del proyecto | `Microservicio del Sistema de Foros en Linea` |

## Uso

1. Instalar el arquetipo en el repositorio Maven local:

   ```powershell
   .\mvnw.cmd clean install
   ```

2. Generar un microservicio nuevo:

   ```powershell
   .\mvnw.cmd archetype:generate `
     "-DarchetypeGroupId=cl.duoc" `
     "-DarchetypeArtifactId=foro-microservicio-arquetipo" `
     "-DarchetypeVersion=1.0.0" `
     "-DgroupId=cl.duoc" `
     "-DartifactId=foro-service" `
     "-Dpackage=cl.duoc.foro" `
     "-DnombreClase=Foro" `
     "-Dpuerto=8083" `
     "-DinteractiveMode=false"
   ```

3. Implementar las entidades, repositorios, servicios y controladores en las capas generadas y
   definir las reglas de acceso en `SecurityConfig`.

## Patrones aplicados en la plantilla

- **Arquitectura en capas (MVC para APIs REST):** Controller → Service → Repository → Model.
- **Service Layer:** cada servicio se define como interfaz y su implementación (`...ServiceImpl`).
- **Repository:** acceso a datos mediante Spring Data JPA.
- **DTO + Mapper:** la API nunca expone las entidades JPA directamente.
- **Singleton:** los componentes Spring (`@Service`, `@Repository`, `@Configuration`) tienen una única instancia.
- **Manejo centralizado de errores:** `@RestControllerAdvice` con respuestas JSON uniformes.
