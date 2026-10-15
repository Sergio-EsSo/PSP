package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class clientePerfecto {

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
                System.out.println("Introduce un número (ENTERO, POSITIVO) -- (introduce 0 para cortar): ");
                String msg = sc.nextLine();
                int numero = Integer.parseInt(msg.trim());

                if(numero == 0){
                    ciclo = false; // se corta si ingreso 0
                }
                else if(numero < 0){
                    System.out.println("Dame un número POSITIVO");
                }
                else{
                    out.writeUTF(msg);
                    System.out.println(in.readUTF());
                    System.out.println("-----"); //repito el bucle hasta salir
                }
            }

            System.out.println("Chao!");

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