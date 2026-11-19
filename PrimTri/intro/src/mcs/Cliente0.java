package mcs;

import java.io.*;
import java.net.*;

public class Cliente0 {
    public static void main(String[] args) throws Exception {
        // Se crea el socket multicast en el puerto
        int port = 12345;
        MulticastSocket ms = new MulticastSocket(port);
        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        // union del grupo
        ms.joinGroup(grupo);

        String msg = "";

        while (!msg.trim().equals("quit")) {
            byte[] buf = new byte[1000];
            // Recibe el paquete
            DatagramPacket paq = new DatagramPacket(buf, buf.length);
            ms.receive(paq);

            msg = new String(paq.getData());
            System.out.println("Recibo: " + msg.trim());
        }

        ms.leaveGroup(grupo);
        ms.close();
        System.out.println("Socket cerrado... EL ADMIN CORTÓ");
    }
}