package tcp0;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class cliente0 {

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

            Cliente.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}