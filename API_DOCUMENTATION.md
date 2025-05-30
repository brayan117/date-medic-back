# 📚 Documentación de la API - Sistema de Citas Médicas

## 📋 Tabla de Contenidos
- [🔗 Endpoints](#-endpoints)
  - [👥 Pacientes](#-pacientes)
  - [👨‍⚕️ Doctores](#️-doctores)
  - [📅 Citas](#-citas)
  - [📋 Historiales Médicos](#-historiales-médicos)
  - [👩‍💼 Secretarías](#-secretarías)
  - 

## 1. Pacientes

### Obtener todos los pacientes
**GET** `/api/patients`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "cc": 1234567890,
    "name": "Juan",
    "lastName": "Pérez",
    "gender": "M",
    "email": "juan.perez@example.com",
    "phone": "+571234567890",
    "dates": [],
    "historicalRecords": []
  }
]
```

### Obtener un paciente por ID
**GET** `/api/patients/{cc}`

**Parámetros de Ruta:**
- `cc` (requerido): Cédula del paciente (número)

**Respuesta Exitosa (200 OK):**
```json
{
  "cc": 1234567890,
  "name": "Juan",
  "lastName": "Pérez",
  "gender": "M",
  "email": "juan.perez@example.com",
  "phone": "+571234567890",
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ],
  "historicalRecords": [
    {
      "id": 1,
      "diagnosis": "Gripe común",
      "description": "Paciente con síntomas de gripe",
      "medical_exam": "Examen físico general",
      "prescription": "Reposo y acetaminofén",
      "ccPatient": 1234567890,
      "idDoctor": 1
    }
  ]
}
```

### Crear un nuevo paciente
**POST** `/api/patients`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "cc": 1234567890,
  "name": "Juan",
  "lastName": "Pérez",
  "gender": "M",
  "email": "juan.perez@example.com",
  "phone": "+571234567890"
}
```

**Respuesta Exitosa (200 OK):**
Devuelve el paciente creado con el mismo formato que el GET por ID.

### Eliminar un paciente
**DELETE** `/api/patients/{cc}`

**Parámetros de Ruta:**
- `cc` (requerido): Cédula del paciente a eliminar

**Respuesta Exitosa (204 No Content):**
Cuerpo vacío

## 2. Doctores

### Obtener todos los doctores
**GET** `/api/doctors`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "cc": 987654321,
    "name": "María",
    "lastName": "González",
    "email": "maria.gonzalez@example.com",
    "phone": "+57987654321",
    "historicalRecords": [],
    "dates": [],
    "idSpecialty": 1
  }
]
```

### Obtener un doctor por ID
**GET** `/api/doctors/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID del doctor

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "cc": 987654321,
  "name": "María",
  "lastName": "González",
  "email": "maria.gonzalez@example.com",
  "phone": "+57987654321",
  "historicalRecords": [
    {
      "id": 1,
      "diagnosis": "Gripe común",
      "description": "Paciente con síntomas de gripe",
      "medical_exam": "Examen físico general",
      "prescription": "Reposo y acetaminofén",
      "ccPatient": 1234567890,
      "idDoctor": 1
    }
  ],
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ],
  "idSpecialty": 1
}
```

### Obtener la especialidad de un doctor
**GET** `/api/doctors/{id}/specialty`

**Parámetros de Ruta:**
- `id` (requerido): ID del doctor

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "name": "Cardiología",
  "doctors": []
}
```

### Crear un nuevo doctor
**POST** `/api/doctors`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "cc": 987654321,
  "name": "María",
  "lastName": "González",
  "email": "maria.gonzalez@example.com",
  "phone": "+57987654321",
  "idSpecialty": 1
}
```

**Respuesta Exitosa (200 OK):**
Devuelve el doctor creado con el mismo formato que el GET por ID.

## 3. Citas

### Obtener todas las citas
**GET** `/api/dates`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "date": "2023-12-15",
    "hour": "14:30",
    "ccPatient": 1234567890,
    "idDoctor": 1,
    "idSecretariat": 1
  }
]
```

### Obtener una cita por ID
**GET** `/api/dates/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID de la cita

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "date": "2023-12-15",
  "hour": "14:30",
  "ccPatient": 1234567890,
  "idDoctor": 1,
  "idSecretariat": 1
}
```

### Crear una nueva cita
**POST** `/api/dates`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "date": "2023-12-15",
  "hour": "14:30",
  "ccPatient": 1234567890,
  "idDoctor": 1,
  "idSecretariat": 1
}
```

**Respuesta Exitosa (200 OK):**
Devuelve la cita creada con el mismo formato que el GET por ID.

### Actualizar una cita
**PUT** `/api/dates/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID de la cita a actualizar

**Cuerpo de la Solicitud (application/json):**
```json
{
  "date": "2023-12-16",
  "hour": "15:30",
  "ccPatient": 1234567890,
  "idDoctor": 1,
  "idSecretariat": 1
}
```

**Respuesta Exitosa (200 OK):**
Devuelve la cita actualizada con el mismo formato que el GET por ID.

## 4. Historiales Médicos

### Obtener todos los historiales médicos
**GET** `/api/medical-records`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "diagnosis": "Gripe común",
    "description": "Paciente con síntomas de gripe",
    "medical_exam": "Examen físico general",
    "prescription": "Reposo y acetaminofén",
    "ccPatient": 1234567890,
    "idDoctor": 1
  }
]
```

### Obtener un historial por ID
**GET** `/api/medical-records/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID del historial médico

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "diagnosis": "Gripe común",
  "description": "Paciente con síntomas de gripe",
  "medical_exam": "Examen físico general",
  "prescription": "Reposo y acetaminofén",
  "ccPatient": 1234567890,
  "idDoctor": 1
}
```

### Crear un nuevo historial médico
**POST** `/api/medical-records`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "diagnosis": "Gripe común",
  "description": "Paciente con síntomas de gripe",
  "medical_exam": "Examen físico general",
  "prescription": "Reposo y acetaminofén",
  "ccPatient": 1234567890,
  "idDoctor": 1
}
```

**Respuesta Exitosa (200 OK):**
Devuelve el historial médico creado con el mismo formato que el GET por ID.

### Actualizar un historial médico
**PUT** `/api/medical-records/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID del historial médico a actualizar

**Cuerpo de la Solicitud (application/json):**
```json
{
  "diagnosis": "Gripe común (actualizado)",
  "description": "Paciente con síntomas de gripe mejorando",
  "medical_exam": "Examen físico general",
  "prescription": "Reposo y acetaminofén cada 8 horas",
  "ccPatient": 1234567890,
  "idDoctor": 1
}
```

**Respuesta Exitosa (200 OK):**
Devuelve el historial médico actualizado con el mismo formato que el GET por ID.

## 5. Secretarías

### Obtener todas las secretarías
**GET** `/api/secretariats`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "cc": 5678901234,
    "name": "Ana",
    "lastName": "López",
    "email": "ana.lopez@example.com",
    "dates": []
  }
]
```

### Obtener una secretaría por ID
**GET** `/api/secretariats/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID de la secretaría

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "cc": 5678901234,
  "name": "Ana",
  "lastName": "López",
  "email": "ana.lopez@example.com",
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ]
}
```

### Crear una nueva secretaría
**POST** `/api/secretariats`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "cc": 5678901234,
  "name": "Ana",
  "lastName": "López",
  "email": "ana.lopez@example.com"
}
```

**Respuesta Exitosa (200 OK):**
Devuelve la secretaría creada con el mismo formato que el GET por ID.

## 6. Especialidades

### Obtener todas las especialidades
**GET** `/api/specialties`

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Cardiología",
    "doctors": []
  }
]
```

### Obtener una especialidad por ID
**GET** `/api/specialties/{id}`

**Parámetros de Ruta:**
- `id` (requerido): ID de la especialidad

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 1,
  "name": "Cardiología",
  "doctors": [
    {
      "id": 1,
      "cc": 987654321,
      "name": "María",
      "lastName": "González",
      "email": "maria.gonzalez@example.com",
      "phone": "+57987654321",
      "historicalRecords": [],
      "dates": [],
      "idSpecialty": 1
    }
  ]
}
```

### Crear una nueva especialidad
**POST** `/api/specialties`

**Cuerpo de la Solicitud (application/json):**
```json
{
  "name": "Neurología"
}
```

**Respuesta Exitosa (200 OK):**
Devuelve la especialidad creada con el mismo formato que el GET por ID.

## Notas Generales

### Códigos de Estado HTTP Comunes
- `200 OK`: La solicitud se completó exitosamente
- `201 Created`: Recurso creado exitosamente
- `204 No Content`: La solicitud se completó exitosamente pero no hay contenido para devolver
- `400 Bad Request`: La solicitud es inválida o faltan parámetros requeridos
- `404 Not Found`: El recurso solicitado no fue encontrado
- `500 Internal Server Error`: Error del servidor al procesar la solicitud

### Formatos
- **Fechas**: `YYYY-MM-DD` (ejemplo: 2023-12-15)
- **Horas**: Formato de 24 horas `HH:MM` (ejemplo: 14:30)
- **Identificadores**: Números enteros positivos (tipo `Long`)
- **Listas**: Devueltas como arreglos JSON, pueden estar vacías si no hay elementos

### Validaciones
- Los campos requeridos deben estar presentes en las solicitudes POST/PUT
- Los formatos de email y teléfono deben ser válidos
- Las fechas y horas deben ser futuras para las citas
- Las relaciones (ID de doctor, paciente, etc.) deben existir en la base de datos



#### Obtener un paciente por ID
**GET** `/api/patients/{cc}`
```json
{
  "cc": 1234567890,
  "name": "Juan",
  "lastName": "Pérez",
  "gender": "M",
  "email": "juan.perez@example.com",
  "phone": "+571234567890",
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ],
  "historicalRecords": [
    {
      "id": 1,
      "diagnosis": "Gripe común",
      "description": "Paciente con síntomas de gripe",
      "medical_exam": "Examen físico general",
      "prescription": "Reposo y acetaminofén",
      "ccPatient": 1234567890,
      "idDoctor": 1
    }
  ]
}
```

### 2. Doctores (`/api/doctors`)

#### Obtener un doctor por ID
**GET** `/api/doctors/{id}`
```json
{
  "id": 1,
  "cc": 987654321,
  "name": "María",
  "lastName": "González",
  "email": "maria.gonzalez@example.com",
  "phone": "+57987654321",
  "historicalRecords": [
    {
      "id": 1,
      "diagnosis": "Gripe común",
      "description": "Paciente con síntomas de gripe",
      "medical_exam": "Examen físico general",
      "prescription": "Reposo y acetaminofén",
      "ccPatient": 1234567890,
      "idDoctor": 1
    }
  ],
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ],
  "idSpecialty": 1
}
```

### 3. Citas (`/api/dates`)

#### Obtener una cita por ID
**GET** `/api/dates/{id}`
```json
{
  "id": 1,
  "date": "2023-12-15",
  "hour": "14:30",
  "ccPatient": 1234567890,
  "idDoctor": 1,
  "idSecretariat": 1
}
```

### 4. Historiales Médicos (`/api/medical-records`)

#### Obtener un historial por ID
**GET** `/api/medical-records/{id}`
```json
{
  "id": 1,
  "diagnosis": "Gripe común",
  "description": "Paciente con síntomas de gripe",
  "medical_exam": "Examen físico general",
  "prescription": "Reposo y acetaminofén",
  "ccPatient": 1234567890,
  "idDoctor": 1
}
```

### 5. Secretarías (`/api/secretariats`)

#### Obtener una secretaría por ID
**GET** `/api/secretariats/{id}`
```json
{
  "id": 1,
  "cc": 5678901234,
  "name": "Ana",
  "lastName": "López",
  "email": "ana.lopez@example.com",
  "dates": [
    {
      "id": 1,
      "date": "2023-12-15",
      "hour": "14:30",
      "ccPatient": 1234567890,
      "idDoctor": 1,
      "idSecretariat": 1
    }
  ]
}
```

### 6. Especialidades (`/api/specialties`)

#### Obtener una especialidad por ID
**GET** `/api/specialties/{id}`
```json
{
  "id": 1,
  "name": "Cardiología",
  "doctors": [
    {
      "id": 1,
      "cc": 987654321,
      "name": "María",
      "lastName": "González",
      "email": "maria.gonzalez@example.com",
      "phone": "+57987654321",
      "historicalRecords": [],
      "dates": [],
      "idSpecialty": 1
    }
  ]
}
```

## Notas
- Las fechas siguen el formato `YYYY-MM-DD`
- Las horas siguen el formato `HH:MM` en formato de 24 horas
- Los números de identificación (CC) son de tipo `Long`
- Las listas pueden estar vacías si no hay registros relacionados
