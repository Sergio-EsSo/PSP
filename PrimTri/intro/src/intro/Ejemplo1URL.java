package intro;

import java.net.*;

public class Ejemplo1URL {
    public static void main(String[] args) {
        URL url;
        try {
            System.out.println("Constructor simple para una URL:");
            url = new URL("http://docs.oracle.com/");
            Visualizar(url);

            System.out.println("Otro constructor simple para una URL:");
            url = new URL("http://localhost/PFC/gest/cli_gestion.php?S=3");
            Visualizar(url);

            System.out.println("Const.  para protocolo +URL +directorio:");
            url = new URL("http", "docs.oracle.com", "/javase/7");
            Visualizar(url);

            System.out.println("Constructor para protocolo + URL + puerto + directorio:");
            url = new URL("http", "docs.oracle.com", 80, "/javase/7");
            Visualizar(url);

            System.out.println("Constructor para un objeto URL y un directorio:");
            URL urlBase = new URL("http://docs.oracle.com/");
            url = new URL(urlBase, "/javase/7/docs/api/java/net/URL.html");
            Visualizar(url);

        } catch (MalformedURLException e) { 
            System.out.println(e);
        } // filtra las URL para verificar que existen y saca una excepcion en el caso de que alguna no exista
    }

    private static void Visualizar(URL url) {
        System.out.println("\tURL completa: " + url.toString()); // muestra la URL == direccion unica, COMPLETA y especifica de un recurso en internet
        System.out.println("\tgetProtocol(): " + url.getProtocol()); // muestra el protocolo de red (ej: http) == reglas de counicacion entre el navegador y el servidor
        System.out.println("\tgetHost(): " + url.getHost()); // devuelve el dominio == nombre del sitio web
        System.out.println("\tgetPort(): " + url.getPort()); // devuelve el puerto (-1 si no hay) == punto de conexion del servidor
        System.out.println("\tgetFile(): " + url.getFile()); // devuelve el archivo que viene despues del dominio == parte especifica de la URL
        System.out.println("\tgetUserInfo(): " + url.getUserInfo()); // informacion del usuario si EXISTE en la URL == contraseñas y usuario y tal o algo
        System.out.println("\tgetPath(): " + url.getPath()); // devuelve el archivo sin los parametros de consulta == ubicacion EXACTA del archivo/recurso/algo en el sitio web
        System.out.println("\tgetAuthority(): " + url.getAuthority()); // devuelte el Host y el puerto si EXISTE == nombre del sitio web y la conexion que tiene con el servidor
        System.out.println("\tgetQuery(): " + url.getQuery()); // deuvelve los paramteros de consulta == despues de '?' contiene parametros clave=valor (S=3) separados con & (S=3&N=0)
        System.out.println("==================================================");
    } // recibe el objeto URL e imprime por pantalla la URL TROCEADA
} 