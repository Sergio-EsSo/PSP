package udp0;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class ServidorFilas {

    private static final Set<Integer> GANADORES_NUMS = Set.of(5, 12, 23, 34, 45);
    private static final Set<Integer> GANADORES_SOLES = Set.of(3, 9);

    public static void main(String[] args) {

        try {
            int puerto = 6000;
            DatagramSocket socketUDP = new DatagramSocket(puerto);
            System.out.println("Servidor Eurojackpot escuchando en el puerto " + puerto);
            boolean bucle = true;

            while (bucle) {
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                
                System.out.println("Esperando apuesta de Eurojackpot...");
                socketUDP.receive(paqueteRecepcion);
                System.out.println("Cliente conectado desde: " + paqueteRecepcion.getAddress());

                // Convierte los bytes recibidos a numeros enteros
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                DataInputStream in = new DataInputStream(bais);

                int numSize = in.readInt();
                Set<Integer> setClienteNums = new HashSet<>();
                for (int i = 0; i < numSize; i++) {
                    setClienteNums.add(in.readInt());
                }

                int solSize = in.readInt();
                Set<Integer> setClienteSoles = new HashSet<>();
                for (int i = 0; i < solSize; i++) {
                    setClienteSoles.add(in.readInt());
                }

                System.out.println("Números recibidos ["+numSize+"]: "+setClienteNums);
                System.out.println("Soles recibidos ["+solSize+"]: "+setClienteSoles);
                System.out.println("Comprobando jugada...");

                // ============
                // Llamada al método para comprobar la jugada con la combinación fija
                String respuesta = LogicaFilas.respuesta(GANADORES_NUMS, setClienteNums, GANADORES_SOLES, setClienteSoles); 
                byte[] datosRespuesta = respuesta.getBytes();

                InetAddress direccionCliente = paqueteRecepcion.getAddress();
                int puertoCliente = paqueteRecepcion.getPort();
                
                DatagramPacket paqueteEnvio = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccionCliente, puertoCliente);
                socketUDP.send(paqueteEnvio);
                System.out.println("Respuesta enviada.");

                // ============
                //logica de salida del bucle por parte del servidor
                byte[] bufferVerificacion = new byte[1024];
                DatagramPacket paqueteVerif = new DatagramPacket(bufferVerificacion, bufferVerificacion.length);
                socketUDP.receive(paqueteVerif);

                String confirmacion = new String(paqueteVerif.getData(), 0, paqueteVerif.getLength(), StandardCharsets.UTF_8).trim();
                System.out.println("Verificación del cliente: "+confirmacion);

                if(confirmacion.toLowerCase().equalsIgnoreCase("no")){
                    System.out.println("El cliente esta hasta el culo. SE VA!!");
                    bucle = false;
                }
            }

            System.out.println("Servidor finalizado correctamente.");
            socketUDP.close();
            
        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }
}