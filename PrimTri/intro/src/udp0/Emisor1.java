package udp0;

import java.util.Scanner;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Emisor1 {

    public static void main(String[] args) {
        
        int puerto = 12345;
        Scanner sc = new Scanner(System.in);

        try(DatagramSocket socket = new DatagramSocket()){

            InetAddress destino = InetAddress.getLocalHost();

            System.out.println("Esta es tu conversacion con PacoGPT (press quit to leave)");
            String msg;

            do{
                msg = sc.nextLine();
                byte[] mensaje = msg.getBytes(StandardCharsets.UTF_8);

                //datos, longitud, ip, puerto
                DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);
                //enviamos el paquete por el socket udp
                socket.send(envio);

                System.out.println("Mensaje enviado con éxito");

            }while(!msg.equalsIgnoreCase("quit"));

            System.out.println("Conversación finalizada,");
        }
        catch(IOException e){
            System.err.println("Error al enviar: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            sc.close();
        }
    }
}

//chafalleiro, CREO, pero funciona