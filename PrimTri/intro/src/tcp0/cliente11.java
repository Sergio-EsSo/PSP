package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class cliente11 {

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

            //flujers
            OutputStream os = Cliente.getOutputStream();
            InputStream is = Cliente.getInputStream();
            DataOutputStream out = new DataOutputStream(os);
            DataInputStream in = new DataInputStream(is);

            Scanner sc = new Scanner (System.in);
            boolean ciclo = true;

            while(ciclo){
                System.out.println("Crees en el destino?\n........ O no?");
                String msg = sc.nextLine();

                out.writeUTF(msg);
                System.out.println(in.readUTF());

                if(msg.equalsIgnoreCase("no")){
                    ciclo = false;
                }
            }

            //chafalleiro pero entiendo más o menos cómo hacerlo

            sc.close();
            out.close();
            in.close();
            Cliente.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}