Sistema RPC de Registro de Consumos - UTPClub
Este proyecto implementa un sistema cliente-servidor usando llamadas RPC (Remote Procedure Call) para registrar y consultar consumos realizados por socios en locales afiliados.

Arquitectura
Servidor Python: conecta con MySQL (UTPBDCentral)

Cliente Java Swing: conecta con SQLite (UTPBDLocal)

Comunicación vía XML-RPC

Contenido del Entregable
1. Servidor Python
Archivo servidor_rpc.py

Conecta con la base de datos MySQL

Métodos expuestos:

registrarSocio

registrarCabeceraConsumo

obtenerSocios

obtenerCabecerasConsumoPorLocal

Esquema MySQL (UTPBDCentral):

sql
Copiar
Editar
CREATE TABLE Socios (
  id_socio VARCHAR(20) PRIMARY KEY,
  nombre_socio VARCHAR(100),
  tipo_membresia VARCHAR(50)
);

CREATE TABLE CabecerasConsumo (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_local VARCHAR(20),
  id_socio VARCHAR(20),
  fecha_consumo DATE,
  monto_total DECIMAL(10,2)
);
Ejecución:

bash
Copiar
Editar
pip install mysql-connector-python
python servidor_rpc.py
2. Cliente Java
Código Java con interfaz Swing

Guarda consumos detallados en SQLite local

Envía cabeceras al servidor RPC

Esquema SQLite (UTPBDLocal):

sql
Copiar
Editar
CREATE TABLE ConsumosDetalle (
  id_socio TEXT,
  fecha_consumo TEXT,
  id_producto_servicio TEXT,
  descripcion TEXT,
  cantidad INTEGER,
  precio_unitario REAL,
  subtotal REAL
);
Ejecución:

bash
Copiar
Editar
mvn clean compile
mvn exec:java -Dexec.mainClass="com.utp.utpclubclient.UTPClubCliente_inicio"
Para cambiar el id_local, modifíquelo en el constructor o clase de inicio según el local que se desea representar.