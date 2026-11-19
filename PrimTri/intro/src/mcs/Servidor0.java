package mcs;

import java.io.*;
import java.net.*;

public class Servidor0 {
    public static void main(String[] args) throws Exception {
        // Flujo entrada
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // creacion socket
        MulticastSocket ms = new MulticastSocket();
        int port = 12345; 
        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        String msg = "";

        while (!msg.trim().equals("quit")) {
            System.out.print("Datos a enviar al grupo: ");
            msg = in.readLine();

            // enviar el paquete
            DatagramPacket paq = new DatagramPacket(
                    msg.getBytes(), msg.length(), grupo, port);
            ms.send(paq);
        }

        ms.close();
        System.out.println("Socket CERRADO!!!! Has mandado a tomar por culo a varios,");
    }
}