package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorPerfecto {

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
            int cont = 1;

            while(ciclo){
                String msgCli = in1.readUTF();
                int numero = Integer.parseInt(msgCli.trim()); //cambio lo que recibo a un numero para no complicarme la vida

                System.out.println("Bienvenido al servidor (x"+cont+")");

                if(numero == 0){
                    ciclo = false; // se corta si ingreso 0
                }
                else{
                    out1.writeUTF("Es perfecto? "+logicaPerfecto.esPerfecto((numero))); //devuelvo el codigo
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
        catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
} //se podria mejorar pero funciona