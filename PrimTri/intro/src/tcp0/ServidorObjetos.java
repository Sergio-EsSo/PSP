package tcp0;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServidorObjetos {

    public static void main(String[] args) {
        
        int puerto = 6000;
        System.out.println("Esperando al Cliente...");

        try (
            ServerSocket server = new ServerSocket(puerto);
            Socket cliente = server.accept();
            //flujos
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        ){
            oos.flush();
            System.out.println("Conectado al Cliente!");

            //se reciben los objetos desde el cliente
            @SuppressWarnings("unchecked")
            List<Persona> listaServer = (List<Persona>) ois.readObject();

            System.out.println("Recibo: ");
            for(Persona p : listaServer){
                System.out.println("---");
                System.out.println(p.toString());
            }

            //se modifican (logica de objetos)
            System.out.println("\n\nModifico:\n---");
            for(Persona p : listaServer){
                LogicaObjetosTCP.modificarPersona(p);
                System.out.println(p.toString());
                System.out.println("-----");
            }

            //se envian
            oos.reset();
            oos.writeObject(listaServer);
            oos.flush();
            System.out.println("Modificaciones enviadas al cliente!");

            //cerrar
            oos.close();
            ois.close();
            cliente.close();
            server.close();
            
        } catch (IOException e) {
            System.err.println("No se ha podido conectar con el Cliente..."+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha encontrado la clase Persona: " + e.getMessage());
        }
    }
}