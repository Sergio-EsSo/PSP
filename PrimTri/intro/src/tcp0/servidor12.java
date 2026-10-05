package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor12 {

    public static void main(String[] args) {

        try{
            int Puerto = 6000;
            ServerSocket Servidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en "+Servidor.getLocalPort());

            //aceptar cliente 1
            Socket cliente1 = Servidor.accept();
            System.out.println("Cliente1 conectado desde... "+cliente1.getInetAddress());

            //flujos cliente 1
            DataInputStream in1 = new DataInputStream(cliente1.getInputStream());
            DataOutputStream out1 = new DataOutputStream(cliente1.getOutputStream());

            //declaracion del BOOEAN
            boolean ciclo = true;
            System.out.println("---------\n");
            System.out.println("Buenas tardes, esto es una camara de eco");
            System.out.println("\n.\n.\n.\n");
            int cont = 1;

            while(ciclo){
                String msgCli = in1.readUTF();
                System.out.println("Uhhhh bienvenido, RAMIRO (x"+cont+")");

                if(msgCli.equalsIgnoreCase("no") || msgCli.equalsIgnoreCase("quit")){
                    ciclo = false;
                    out1.writeUTF("Chao........................... Me lo estaba pasando GUAY, oye..");
                }
                else{
                    //devuelve en mayusculas lo que reciba del cliente
                    out1.writeUTF(ecoUpperCase(msgCli));
                    cont++;
                }
            }

            in1.close();
            out1.close();
            cliente1.close();
            Servidor.close();
            System.out.println("Venga chao, PESCAO");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String ecoUpperCase(String cadena){
        return cadena.toUpperCase();
    }
}