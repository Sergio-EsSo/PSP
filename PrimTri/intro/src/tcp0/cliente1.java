package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class cliente1 {

    public static void main(String[] args) {

        try{
            String Host = "localhost";
            int Puerto = 6000;

            Socket Cliente = new Socket(Host, Puerto);

            InetAddress i = Cliente.getInetAddress();
            System.out.println("Puerto local: "+Cliente.getLocalPort());
            System.out.println("Puerto remoto: "+Cliente.getPort());
            System.out.println("Host remoto: "+i.getHostName().toString());
            System.out.println("IP Host remoto: "+i.getHostAddress().toString());

            //IS y OS
            OutputStream os = Cliente.getOutputStream();
            InputStream is = Cliente.getInputStream();

            //flujos para enviar/recibir datos
            DataOutputStream out = new DataOutputStream(os);
            DataInputStream in = new DataInputStream(is);

            //mensaje para servidor
            out.writeUTF("Hola caracola!! !");

            //leer mensaje del servidor
            String mensajeServer = in.readUTF();
            System.out.println("Servidor: "+mensajeServer);

            out.close();
            in.close();
            Cliente.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}