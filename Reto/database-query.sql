-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS empleo_gestor;
USE empleo_gestor;

-- Tabla de administradores
CREATE TABLE administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de empresas
CREATE TABLE empresas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla de categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla de tipos de contrato
CREATE TABLE tipos_contrato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

-- Tabla de usuarios (candidatos)
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_nacimiento DATE,
    password VARCHAR(100) NOT NULL,
    curriculum_path VARCHAR(255),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla de vacantes
CREATE TABLE vacantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    categoria_id INT NOT NULL,
    tipo_contrato_id INT,
    salario DECIMAL(10,2),
    ubicacion VARCHAR(100),
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre DATE,
    estado ENUM('CREADA', 'ASIGNADA', 'CANCELADA') DEFAULT 'CREADA',
    FOREIGN KEY (empresa_id) REFERENCES empresas(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (tipo_contrato_id) REFERENCES tipos_contrato(id)
);

-- Tabla de solicitudes
CREATE TABLE solicitudes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vacante_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE', 'REVISION', 'RECHAZADA', 'ADJUDICADA') DEFAULT 'PENDIENTE',
    mensaje TEXT,
    FOREIGN KEY (vacante_id) REFERENCES vacantes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    UNIQUE KEY (vacante_id, usuario_id)
);

-- Datos iniciales
INSERT INTO administradores (username, password, nombre, email) 
VALUES ('admin', 'admin123', 'Administrador Principal', 'admin@empleogestor.com');

INSERT INTO tipos_contrato (nombre, descripcion) VALUES
('Tiempo completo', 'Jornada completa de 40 horas semanales'),
('Medio tiempo', 'Jornada parcial de 20-30 horas semanales'),
('Por proyecto', 'Contrato temporal para un proyecto específico'),
('Prácticas', 'Para estudiantes en formación'),
('Autónomo', 'Trabajo por cuenta propia');

INSERT INTO categorias (nombre, descripcion) VALUES 
('Tecnología', 'Puestos relacionados con tecnología e informática'),
('Administración', 'Puestos administrativos y de oficina'),
('Ventas', 'Puestos relacionados con ventas y atención al cliente'),
('Salud', 'Sector sanitario y de salud'),
('Educación', 'Sector educativo y formación'),
('Construcción', 'Sector de la construcción y obra civil');

INSERT INTO empresas (nombre, email, telefono, direccion) VALUES
('Tech Solutions', 'info@techsolutions.com', '912345678', 'Calle Tecnológica 123, Madrid'),
('AdministraCorp', 'contacto@administracorp.com', '934567890', 'Avenida Central 45, Barcelona'),
('Ventas Globales', 'ventas@globales.com', '956789012', 'Plaza Comercial 67, Valencia');

INSERT INTO usuarios (nombre, email, telefono, direccion, fecha_nacimiento, password) VALUES
('Juan Pérez', 'juan@example.com', '623456789', 'Calle Falsa 123, Madrid', '1990-05-15', 'juan123'),
('María García', 'maria@example.com', '678901234', 'Avenida Siempreviva 742, Barcelona', '1985-08-22', 'maria123'),
('Carlos López', 'carlos@example.com', '612345678', 'Plaza Mayor 5, Valencia', '1992-11-30', 'carlos123');

INSERT INTO vacantes (empresa_id, titulo, descripcion, categoria_id, tipo_contrato_id, salario, ubicacion, fecha_cierre) VALUES
(1, 'Desarrollador Java', 'Buscamos desarrollador Java con experiencia en Spring Boot', 1, 1, 35000.00, 'Madrid', '2023-12-31'),
(1, 'Diseñador UX/UI', 'Diseñador de interfaces con experiencia en Figma', 1, 2, 28000.00, 'Remoto', '2023-11-30'),
(2, 'Asistente Administrativo', 'Gestión documental y apoyo administrativo', 2, 1, 22000.00, 'Barcelona', '2023-10-15'),
(3, 'Comercial Sector Tecnológico', 'Ventas de soluciones tecnológicas a empresas', 3, 1, 25000.00, 'Valencia', '2023-12-15');

INSERT INTO solicitudes (vacante_id, usuario_id, estado, mensaje) VALUES
(1, 1, 'PENDIENTE', 'Me interesa la posición, tengo 3 años de experiencia con Java'),
(1, 2, 'REVISION', 'Soy desarrolladora full-stack con experiencia en Java y Spring'),
(3, 3, 'PENDIENTE', 'Busco oportunidad en el sector administrativo');