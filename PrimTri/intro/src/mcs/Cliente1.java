package mcs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Cliente1 {

    public static void main(String[] args) {
        
        int port = 12345;
        String ipGrupo = "225.0.0.1";

        try(MulticastSocket ms = new MulticastSocket(port)){
            InetAddress grupo = InetAddress.getByName(ipGrupo);

            ms.joinGroup(grupo);
            String msg = "";

            while(!msg.trim().equals("quit")){
                byte[] buf = new byte[1024];
                DatagramPacket paq = new DatagramPacket(buf, buf.length);
                ms.receive(paq);

                msg = new String(paq.getData());
                System.out.println("La hora es: "+msg.trim());
            }

            ms.leaveGroup(grupo);
            ms.close();
            System.out.println("Chao, ");
        }
        catch(IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    private static NetworkInterface obtenerInterfazActiva() throws SocketException{
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface ni = interfaces.nextElement();

            if(ni.isUp() && ni.isLoopback() && ni.isVirtual() && ni.supportsMulticast()){
                return ni;
            }
        }
        return null;
    }
}