package tcp0;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteObjetos {

    public static void main(String[] args) {
        
        String host = "localhost";
        int port = 6000;
        List<Persona> lista = new ArrayList<>();
        
        System.out.println("Esperando al Servidor...");

        try (
            Socket cliente = new Socket(host, port);
            //flujos
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        ) {
            oos.flush();

            //se meten los objetos en una lista (logica de objetos)
            int sr = 0;
            while(sr == 0) { 
                Persona p = LogicaObjetosTCP.pedirPersona();
                lista.add(p);
                sr = LogicaObjetosTCP.seguir();
            }

            //se envian al servidor usando serializable:
            oos.writeObject(lista);
            oos.flush();

            //se reciben modificados
            @SuppressWarnings("unchecked")
            List<Persona> recibo = (List<Persona>) ois.readObject();

            System.out.println("\n\nNueva lista: \n---");
            for(Persona p : recibo){
                System.out.println(p.toString());
                System.out.println("---");
            }

            //cerrar
            ois.close();
            oos.close();
            cliente.close();
            
        } catch (IOException e) {
            System.err.println("No se ha podido conectar con el Servidor..."+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha encontrado la clase Persona: " + e.getMessage());
        }
    }
}