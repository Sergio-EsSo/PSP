package tcp0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor0 {

    public static void main(String[] args) {

        try{
            int Puerto = 6000;
            ServerSocket Servidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en "+Servidor.getLocalPort());

            Socket cliente1 = Servidor.accept();
            Socket cliente2 = Servidor.accept();

            Servidor.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}