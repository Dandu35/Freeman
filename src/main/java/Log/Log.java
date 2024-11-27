package Log;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La clase Log se utiliza para realizar el registro de errores en un archivo de registro.
 * Proporciona métodos para registrar errores y leer el contenido del archivo de registro.
 * @author Mario Puente, David Andueza
 */
public class Log {

    private final String logFileName = "src/main/java/Log/errorLog.log";
    private DateFormat dateFormat;

    /**
     * Constructor de la clase Log.
     * Inicializa el formato de fecha utilizado para registrar la fecha y hora del error.
     */
    public Log() {
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * Método principal que se ejecuta al invocar la clase desde la línea de comandos.
     * Lee el contenido del archivo de registro y lo imprime en la consola.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        leerLog();
    }

    /**
     * Registra un error en el archivo de registro.
     *
     * @param errorMessage El mensaje de error a registrar.
     */
    public void logError(String errorMessage) {
        try {
            FileWriter fileWriter = new FileWriter(logFileName, true); // "true" para agregar al archivo existente
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(getFechaHora() + " - " + errorMessage);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee el contenido del archivo de registro y lo imprime en la consola.
     */
    public static void leerLog() {
        try {
            FileReader fileReader = new FileReader("src/main/java/Log/errorLog.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la fecha y hora actual formateada según el formato establecido.
     *
     * @return La fecha y hora actual formateada.
     */
    private String getFechaHora() {
        Date date = new Date();
        return dateFormat.format(date);
    }
}