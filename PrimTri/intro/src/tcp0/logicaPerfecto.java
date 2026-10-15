package tcp0; //cortesia de @gemini porque estoy perezoso

public class logicaPerfecto { 
    
    // es perfecto si el sumatorio de los divisores da el numero
    // ejemplo: 28 = 1 + 2 + 4 + 7 + 14 ;; 28 / 14 = 2, 28 / 7 = 4, 28 / 4 = 7, 28 / 2 = 14, etc.

    public static boolean esPerfecto(int numero) {
        if (numero <= 1) { //si es negativo o 0, NO ES PERFECTO (pero no usamos esto en el ejercicio)
            return false; // porque pedimos un entero POSITIVO
        }
        int sumaDivisores = 0; //inicializamos el sumatorio de los divisores

        for (int i = 1; i <= numero / 2; i++) { // hasta la mitad porque siempre es asi (lolazo)
            if (numero % i == 0) {
                sumaDivisores += i; // si el resto da 0 es porque es divisor, asi que se AÑADE
            }
        }

        return sumaDivisores == numero; //devuelve true o false si el sumatorio de divisores da igual al n umero
    }
}