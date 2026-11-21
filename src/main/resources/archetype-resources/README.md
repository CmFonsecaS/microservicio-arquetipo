# Microservicio del Sistema de Foros en Línea

Proyecto generado con el arquetipo `cl.duoc:foro-microservicio-arquetipo:1.0.0`.

## Estructura por capas

| Paquete | Responsabilidad |
|---|---|
| `controller` | Endpoints REST (JSON) |
| `service` | Interfaz + implementación con la lógica de negocio (patrón Service Layer) |
| `repository` | Acceso a Oracle con Spring Data JPA (patrón Repository) |
| `model` | Entidades JPA |
| `dto` | Objetos de entrada/salida y Mapper (patrón DTO) |
| `config` | Seguridad JWT, roles y CORS |
| `exception` | Excepciones de negocio y `GlobalExceptionHandler` (errores en JSON) |

## Configuración

Variables de entorno requeridas (ver `.env.example`): `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`.

## Ejecutar

```powershell
.\mvnw.cmd spring-boot:run
```

Con Docker (el wallet se monta en `/app/wallet`):

```bash
docker build -t mi-servicio .
docker run --env-file .env -v "$(pwd)/wallet:/app/wallet:ro" -p 8080:8080 mi-servicio
```

## Despliegue en AWS

El workflow `.github/workflows/publish-ecr.yml` ejecuta las pruebas, publica la imagen en ECR
(`foro-online/<servicio>`) y la despliega en EC2 mediante Systems Manager. El nombre, puerto y ruta
de prueba del servicio se configuran en `ec2/service.env`.
