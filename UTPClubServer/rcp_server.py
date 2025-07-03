from xmlrpc.server import SimpleXMLRPCServer
import mysql.connector
from datetime import datetime

conexion = mysql.connector.connect(
    host="161.132.45.205",
    user="admin",
    password="990324",
    database="UTPBDCentral"
)

cursor = conexion.cursor(dictionary=True)

def registrar_socio(id_socio, nombre_socio, tipo_membresia):
    try:
        cursor.execute("INSERT INTO Socios (id_socio, nombre_socio, tipo_membresia) VALUES (%s, %s, %s)",
                       (id_socio, nombre_socio, tipo_membresia))
        conexion.commit()
        return True
    except mysql.connector.Error as e:
        return f"Error al registrar socio: {e}"

def registrar_cabecera_consumo(id_local, id_socio, fecha_consumo, monto_total):
    try:
        cursor.execute("INSERT INTO CabecerasConsumo (id_local, id_socio, fecha_consumo, monto_total) VALUES (%s, %s, %s, %s)",
                       (id_local, id_socio, fecha_consumo, monto_total))
        conexion.commit()
        return True
    except mysql.connector.Error as e:
        return f"Error al registrar consumo: {e}"

def obtener_socios():
    try:
        cursor.execute("SELECT * FROM Socios")
        return cursor.fetchall()
    except mysql.connector.Error as e:
        return f"Error al obtener socios: {e}"

def obtener_cabeceras_consumo_por_local(id_local):
    try:
        cursor.execute("SELECT * FROM CabecerasConsumo WHERE id_local = %s", (id_local,))
        return cursor.fetchall()
    except mysql.connector.Error as e:
        return f"Error al obtener cabeceras: {e}"


server = SimpleXMLRPCServer(("0.0.0.0", 8000), allow_none=True)
print("Servidor RPC escuchando en el puerto 8000...")

server.register_function(registrar_socio, "registrarSocio")
server.register_function(registrar_cabecera_consumo, "registrarCabeceraConsumo")
server.register_function(obtener_socios, "obtenerSocios")
server.register_function(obtener_cabeceras_consumo_por_local, "obtenerCabecerasConsumoPorLocal")

server.serve_forever()
