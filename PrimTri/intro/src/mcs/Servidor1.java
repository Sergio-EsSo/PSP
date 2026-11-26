package mcs;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Servidor1 {

    public static void main(String[] args) {

        int port = 12345;
        String ipGrupo = "225.0.0.1";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try (MulticastSocket ms = new MulticastSocket()){
            // flujo entrada
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isr);
            
            InetAddress grupo = InetAddress.getByName("225.0.0.1");

            String msg = "";

            while (true) {
                System.out.println("Datos a enviar al grupo: ");
                msg = in.readLine();
                if(msg.equalsIgnoreCase("quit")){
                    break;
                }

                DatagramPacket paq = new DatagramPacket(msg.getBytes(), msg.length(), grupo, port);
                ms.send(paq);

                msg = obtenerHoraActual(LocalDate.now());
                DatagramPacket paqH = new DatagramPacket(msg.getBytes(), msg.length(), grupo, port);
                ms.send(paqH);
            }

            ms.close();
            System.out.println("Socket CERRADO!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } /*catch (InterruptedException e){
            System.err.println("Servidor detenido.");
            //Thread.currentThread();
        }*/
    }

    public static String obtenerHoraActual(LocalDate fecha) {
        // Obtenemos la hora actual
        LocalTime horaActual = LocalTime.now();
        
        // Formateamos la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formatter);
        
        // Devolvemos una cadena con la fecha y la hora
        return "Fecha: " + fecha + ", Hora actual: " + horaFormateada;
    }
}