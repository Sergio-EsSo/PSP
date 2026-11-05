package udp0;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClienteFilas {

    public static void main(String[] args) {
        int port = 6000;
        String host = "localhost";

        try {
            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(host);
            Scanner sc = new Scanner(System.in);

            boolean continuarJugando = true;
            System.out.println("Esperando...");

            // Bucle principal para permitir jugar varias veces sin reiniciar el cliente
            while (continuarJugando) {
                Set<Integer> setNums = new HashSet<>();

                // Pedimos los 5 números del 1 al 50
                while (setNums.size() < 5) {
                    System.out.print("\nIntroduce " + (setNums.size() + 1) + " número(s) entre 1 y 50: ");
                    if (sc.hasNextInt()) {
                        int numero = sc.nextInt();
                        if (numero < 1 || numero > 50) {
                            System.out.println("ERROR: El número debe estar entre 1 y 50");
                        } else if (!setNums.add(numero)) {
                            System.out.println("ERROR: El número ya ha sido introducido.");
                        }
                    } else {
                        System.out.println("ERROR: Debes introducir un número entero válido");
                        sc.next();
                    }
                }

                System.out.println("===============");

                // Pedimos dos números más que son los soles del 1 al 12
                Set<Integer> setSoles = new HashSet<>();
                while (setSoles.size() < 2) {
                    System.out.print("Introduce " + (setSoles.size() + 1) + " sol(es) entre 1 y 12: ");
                    if (sc.hasNextInt()) {
                        int sol = sc.nextInt();
                        if (sol < 1 || sol > 12) {
                            System.out.println("ERROR: El sol debe estar entre 1 y 12");
                        } else if (!setSoles.add(sol)) {
                            System.out.println("ERROR: Ese sol ya ha sido introducido.");
                        }
                    } else {
                        System.out.println("ERROR: Debes introducir un número entero válido");
                        sc.next();
                    }
                }

                // Pasamos los datos mediante un array de bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(baos);

                // Escribe el tamaño y los 5 números
                out.writeInt(setNums.size());
                for (Integer num : setNums) {
                    out.writeInt(num);
                }
                // Escribe el tamaño los 2 soles
                out.writeInt(setSoles.size());
                for (Integer sol : setSoles) {
                    out.writeInt(sol);
                }
                out.flush();

                byte[] datosAEnviar = baos.toByteArray();

                // Se envia la info al servidor en bytes
                DatagramPacket paqueteEnvio = new DatagramPacket(datosAEnviar, datosAEnviar.length, direccionServidor, port);
                socketUDP.send(paqueteEnvio);

                // Recibimos respuesta del servidor
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socketUDP.receive(paqueteRecepcion);

                String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("\nRespuesta del servidor: " + respuesta);

                // Preguntamos si quiere echar otra jugada
                String respuestaUsuario = "";
                do{
                    System.out.print("\n¿Quieres seguir jugando? (si / no): ");
                    respuestaUsuario = sc.next().trim();

                    if(!respuestaUsuario.equalsIgnoreCase("si") && !respuestaUsuario.equalsIgnoreCase("no")){
                        System.out.println("Introduce una opción VALIDA, carallo");
                    }
                }while(!respuestaUsuario.equalsIgnoreCase("si") && !respuestaUsuario.equalsIgnoreCase("no"));

                byte[] bufferRes = respuestaUsuario.getBytes(StandardCharsets.UTF_8);
                DatagramPacket pRes = new DatagramPacket(bufferRes, bufferRes.length, direccionServidor, port);
                socketUDP.send(pRes);

                if(respuestaUsuario.toLowerCase().equalsIgnoreCase("no")){
                    System.out.println("Chao");
                    continuarJugando = false;
                }
            }
            sc.close();
            socketUDP.close();
            System.out.println("Conexión cerrada correctamente.");

        } catch (IOException e) {
            System.err.println("Error: "+e.getMessage());
        }
    }
}