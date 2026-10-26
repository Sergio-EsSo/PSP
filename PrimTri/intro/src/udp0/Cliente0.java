package udp0;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cliente0 {

    public static void main(String[] args) throws UnknownHostException {
        
        int port = 12345;
        InetAddress destino = InetAddress.getLocalHost();

        byte[] mensaje = new byte[1024];
        String saludo = "Enviando saludos desde el cliente";

        //conversion de string a byte
        mensaje = saludo.getBytes();

        //construyo el datagrama para enviar
        DatagramPacket envio = new DatagramPacket(mensaje, port);
    }
}