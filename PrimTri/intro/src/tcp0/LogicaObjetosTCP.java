package tcp0;

import java.util.Scanner;

public class LogicaObjetosTCP {

    private static final Scanner sc = new Scanner(System.in);

    public static Persona pedirPersona(){
        Persona p = new Persona();

        System.out.print("Dame el nombre: ");
        p.setNombre(sc.nextLine());

        int edad = 0;
        boolean edadValida = false;
        while(!edadValida){
            System.out.print("Dame la edad: ");
            try {
                edad = Integer.parseInt(sc.nextLine().trim());
                edadValida = true;
            } catch (NumberFormatException e) {
                System.err.println("Introduce un número válido...");
            }
        }
        p.setEdad(edad);

        return p;
    }

    public static void modificarPersona(Persona p){
        //de momento solo voy a poner el nombre en mayus y edad+10
        if(p.getNombre() != null){
            p.setNombre(p.getNombre().toUpperCase());
        }
        p.setEdad(p.getEdad()+10);
    }

    public static int seguir(){
        while (true) {
            System.out.print("¿Quieres seguir introduciendo datos? (Y/n): ");
            String retorno = sc.nextLine().trim();

            if (retorno.isEmpty() || retorno.equalsIgnoreCase("y")) {
                return 0;
            }
            if (retorno.equalsIgnoreCase("n")) {
                return 1;
            }
            System.out.println("Entrada no válida. Escribe 'Y' para continuar o 'n' para salir.");
        }
    }
}