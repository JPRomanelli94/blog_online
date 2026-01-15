# Blog Online API — Spring Boot & Spring Security

REST API para un sistema de blog con autenticación JWT, roles y permisos.  
Proyecto integrador desarrollado utilizando Spring Boot y Spring Security.

---

## 🚀 Tecnologías

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL / H2
- Maven
- Lombok

---

## 🔐 Seguridad

- Autenticación JWT
- Filtro personalizado JwtTokenValidator
- Roles:
  - ADMIN
  - AUTHOR
  - USER
- Permisos:
  - CREATE
  - READ
  - UPDATE
  - DELETE
- Protección de endpoints con @PreAuthorize

---

## 📌 Funcionalidades

- Login con JWT
- Creación y gestión de usuarios
- Gestión de autores y posts
- Control de acceso basado en roles
- Relación Author ↔ Post (OneToMany / ManyToOne)

---

## ⚙️ Configuración

### Clonar repositorio

```bash
git clone https://github.com/TU_USUARIO/blog-online-api.git
```

---

### Ejecutar la aplicación

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

La API quedará disponible en:

http://localhost:8080

---

## 🧪 Testing con Postman

---

### 🔑 Login

Endpoint:

POST /auth/login

Body:

```json
{
  "username": "admin",
  "password": "1234"
}
```

Response:

```json
{
  "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

---

### 📌 Usar token JWT

Agregar header:

Authorization: Bearer TU_TOKEN

---

### ✍ Crear Post (ejemplo)

Endpoint:

POST /post

Body:

```json
{
  "content_post": "Primer post",
  "date_post": "2026-01-15",
  "id_author": {
    "id_author": 1
  }
}
```

## 👨‍💻 Autor

Juan Pablo Romanelli  
Proyecto Integrador — Spring Boot & Spring Security