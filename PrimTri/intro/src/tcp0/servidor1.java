package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor1 {

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

            //leer cliente 1 y responder
            System.out.println("Mensaje del cliente 1.... "+in1.readUTF());
            out1.writeUTF("Pero madre mía buenas tardes, CLIENTE 1!");

            /*
            //aceptar cliente 2
            Socket cliente2 = Servidor.accept();
            System.out.println("Cliente2 conectado desde... "+cliente2.getInetAddress());

            //flujos cliente 2
            DataInputStream in2 = new DataInputStream(cliente2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(cliente2.getOutputStream());

            //leer cliente 2 y responder
            System.out.println("Mensaje del cliente 2....."+in2.readUTF());
            out2.writeUTF("Hola.. cliente 2... no me caes muy bien.");
            */

            cliente1.close();
            //cliente2.close();
            Servidor.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}