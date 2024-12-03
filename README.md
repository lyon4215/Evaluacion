Aplicación API RESTful de Usuarios
Descripción del proyecto
Esta aplicación proporciona una API RESTful para gestionar el registro de usuarios, incluyendo la validación de datos, generación de tokens de acceso, y persistencia en una base de datos en memoria (H2). La aplicación sigue los principios de arquitectura REST y está construida con Spring Boot.

Cómo probar la API

Pre-requisitos
Tener instalado:
Java 17+
Maven
Un cliente API como Postman o cURL.
Clonar el repositorio:
bash
Copiar código
git clone https://github.com/lyon4215/evaluacion.git
cd tu-repositorio
Iniciar la aplicación
Compila y ejecuta el proyecto:
bash
Copiar código
mvn spring-boot:run
Accede a la consola de H2 para verificar los datos persistidos (opcional):
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Usuario: sa
Contraseña: password
Probar los endpoints

1. Registrar usuario
2. 
URL: POST /api/usuarios/registro
JSON de entrada:
json

{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "password.123",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}


Posibles respuestas:
Éxito (200):
json
{
    "usuario": {
        "id": "bfd6a6fa-b387-4dba-a9d3-50404abc1905",
        "name": "Juan Rodriguez",
        "email": "juanr@dodriguon.com",
        "password": "password.123",
        "created": "2024-12-03T19:36:48.31061",
        "modified": "2024-12-03T19:36:48.31061",
        "lastLogin": "2024-12-03T19:36:48.31061",
        "token": "ba6040d9-3f5f-49c3-b25b-7381a4bf7027",
        "phones": [
            {
                "id": 1,
                "number": "12345678",
                "citycode": "1",
                "contrycode": "57"
            },
            {
                "id": 2,
                "number": "123456788",
                "citycode": "1",
                "contrycode": "57"
            }
        ],
        "active": true
    },
    "mensaje": "Usuario registrado exitosamente"
}


Error (400):
json

{
    "mensaje": "El correo ya esta registrado"
}
URL: POST /api/usuarios/registro
JSON de entrada:

{
  "name": "Juan Rodriguez",
  "email": "juanr@dodrigjuon.com",
  "password": "password",
  "phones": [
    {
      "number": "12345678",
      "citycode": "1",
      "contrycode": "57"
    },
    {
      "number": "123456788",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}


Error(400)
{
    "detalles": {
        "password": "La contraseña debe tener al menos 8 caracteres, incluir al menos una letra, un número y un carácter especial."
    },
    "mensaje": "Error de validación"
}
