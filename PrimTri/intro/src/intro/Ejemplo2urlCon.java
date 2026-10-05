package intro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ejemplo2urlCon {
    public static void main(String[] args) {
        
        try { 
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost/vernombre.php");
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true);

            //String cadena = null; //"nombre=Marisa&apellidos=Gomez";

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el nombre: "); //introduzco y guardo nombre
            String nombre = sc.nextLine();
            System.out.println("Introduce el apellido: "); //introduzco y guardo apellidos
            String apellidos = sc.nextLine();
            //construyo esta vaina (loca)
            String cadena = "nombre=" + nombre + "&apellidos=" + apellidos;
            System.out.println("Cadena por output hacia servidor: "+cadena); //esto se pasa al php y sale como sale en el php (guau/wow)
            sc.close();

            //ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter (conexion.getOutputStream());
            output.write(cadena);
            output.close(); //cerrar flujo

            //LEER DE LA URL
            BufferedReader reader = new BufferedReader (new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close(); //cerrar flujo

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    } //main
} //Ejemplo2urlCon