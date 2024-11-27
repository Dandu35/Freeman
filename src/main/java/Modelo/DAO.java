package Modelo;

import Entidades.Jugador;
import Log.Log;

import java.io.File;
import java.sql.*;

/**
 * Clase que maneja el acceso a la base de datos y las operaciones relacionadas.
 * @author Mario Puente, David Andueza
 */
public class DAO {
    public static final String ruta = "bdfreeman.db";
    private static Log log = new Log();

    /**
     * Método principal utilizado para probar las funcionalidades de la clase DAO.
     *
     * @param args los argumentos de la línea de comandos
     * @throws SQLException si ocurre algún error relacionado con la base de datos
     */
    public static void main(String[] args) throws SQLException {
        DAO database = new DAO();
        borrarBaseDatos();
    }

    /**
     * Crea la base de datos y la tabla "jugadores".
     */
    public static void crearDB() {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:" + ruta);

            // Crear la sentencia SQLito para crear la tabla jugadores
            String createTableQuery = "CREATE TABLE jugadores(" +
                    "id INTEGER PRIMARY KEY, " +
                    "nombre TEXT, " +
                    "vida INTEGER NOT NULL, " +
                    "mundo INTEGER NOT NULL" +
                    ")";

            // Crear la declaración
            statement = connection.createStatement();

            // Ejecutar la sentencia SQLito para crear la tabla
            statement.executeUpdate(createTableQuery);

            System.out.println("La base de datos y la tabla se han creado exitosamente.");
        } catch (SQLException e) {
            //TODO: revisar
            log.logError(e.getMessage());
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión y la declaración
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //TODO: revisar
                log.logError(e.getMessage());
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene la ruta de la base de datos.
     *
     * @return la ruta de la base de datos
     */
    public String getRuta() {
        return this.ruta;
    }

    /**
     * Borra la base de datos.
     */
    public static void borrarBaseDatos() {
        File archivoBD = new File(ruta);
        if (archivoBD.exists()) {
            archivoBD.delete();
            System.out.println("La base de datos se ha borrado correctamente.");
        } else {
            System.out.println("La base de datos no existe en la ruta especificada.");
        }
    }

    /**
     * Inserta o actualiza los datos de un jugador en la base de datos.
     *
     * @param id     el ID del jugador
     * @param nombre el nombre del jugador
     * @param vida   la cantidad de vidas del jugador
     * @param mundo  el mundo del jugador
     */
    public void insertarDatos(int id, String nombre, int vida, int mundo) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:" + ruta);

            // Actualizar los datos del jugador si existe en la tabla
            String updateQuery = "UPDATE jugadores SET nombre = ?, vida = ?, mundo = ? WHERE id = ?";
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, nombre);
            statement.setInt(2, vida);
            statement.setInt(3, mundo);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Se ha actualizado la fila con ID " + id);
            } else {
                // Si no existe una fila con el ID dado, insertar una nueva fila
                String insertQuery = "INSERT INTO jugadores (id, nombre, vida, mundo) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(insertQuery);
                statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setInt(3, vida);
                statement.setInt(4, mundo);
                statement.executeUpdate();
                System.out.println("Se ha insertado una nueva fila con ID " + id);
            }
        } catch (SQLException e) {
            //TODO: revisar
            log.logError(e.getMessage());
            System.out.println("Error al insertar/actualizar los datos en la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión y la declaración
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //TODO: revisar
                log.logError(e.getMessage());
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }


    /**
     * Inserta ejemplos de datos en la base de datos.
     */
    public static void insertarEjemplosDatos() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:" + ruta);

            // Eliminar todas las filas existentes en la tabla jugadores
            String deleteQuery = "DELETE FROM jugadores";
            statement = connection.prepareStatement(deleteQuery);
            statement.executeUpdate();

            // Inserción 1
            String insertQuery1 = "INSERT INTO jugadores (id, nombre, vida, mundo) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(insertQuery1);
            statement.setInt(1, 1);
            statement.setString(2, "Jugador1");
            statement.setInt(3, 2);
            statement.setInt(4, 1);
            statement.executeUpdate();

            // Inserción 2
            String insertQuery2 = "INSERT INTO jugadores (id, nombre, vida, mundo) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(insertQuery2);
            statement.setInt(1, 2);
            statement.setString(2, "Jugador2");
            statement.setInt(3, 3);
            statement.setInt(4, 2);
            statement.executeUpdate();

            // Inserción 3
            String insertQuery3 = "INSERT INTO jugadores (id, nombre, vida, mundo) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(insertQuery3);
            statement.setInt(1, 3);
            statement.setString(2, "Jugador3");
            statement.setInt(3, 1);
            statement.setInt(4, 1);
            statement.executeUpdate();

            System.out.println("Los datos se han insertado correctamente en la base de datos.");
        } catch (SQLException e) {
            //TODO: revisar
            log.logError(e.getMessage());
            System.out.println("Error al insertar los datos en la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión y la declaración
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //TODO: revisar
                log.logError(e.getMessage());
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Consulta un jugador en la base de datos utilizando su ID.
     *
     * @param id ID del jugador a consultar.
     * @return Jugador consultado.
     */
    public Jugador consultarJugador(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:" + ruta);

            // Consulta: Obtener los valores de la tabla jugadores cuando el ID es 1
            String query = "SELECT * FROM jugadores WHERE id = " + id;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int vida = resultSet.getInt("vida");
                int mundo = resultSet.getInt("mundo");

                Jugador jugador = new Jugador(400, 640, vida, 3, "MaskDudeAndar", "andar", nombre, mundo);
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Vida: " + vida + ", Mundo: " + mundo);
                return jugador;
            }
        } catch (SQLException e) {
            //TODO: revisar
            log.logError(e.getMessage());
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar la conexión, la declaración y el conjunto de resultados
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //TODO: revisar
                log.logError(e.getMessage());
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return null;
    }
}
