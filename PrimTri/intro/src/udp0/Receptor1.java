package udp0;

import java.util.Scanner;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Receptor1 {

    public static void main(String[] args) {

        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];
        Scanner sc = new Scanner(System.in);

        try(DatagramSocket socket = new DatagramSocket(puertoEscucha)){
            System.out.println("Esperando en el puerto: "+puertoEscucha);


            String paquete;

            do{
                DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibo);

                int byteRecivir = recibo.getLength();
                paquete = new String(recibo.getData(), 0, byteRecivir, StandardCharsets.UTF_8);

                System.out.println("Mensaje recibido: "+paquete);
                System.out.println("Puerto origen: "+recibo.getPort()+"\nIP de origen:  "+recibo.getAddress().getHostAddress());
                
            }while(!paquete.equalsIgnoreCase("quit"));

            socket.close();
        }
        catch(IOException e){
            System.err.println("Error al recibir el paquete: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            sc.close();
        }
    }
}

//chafalleiro, CREO, pero funciona