package udp0;

import java.util.Set;

public class LogicaFilas {

    // Métodos para la lógica de comprobación
    public static int aciertosNums(Set<Integer> setNums, Set<Integer> setClienteNums) {
        // Verifica los aciertos de los nums
        int aciertos = 0;
        for (Integer n : setNums) {
            if (setClienteNums.contains(n)) {
                aciertos++;
            }
        }
        return aciertos;
    }

    public static int aciertosSoles(Set<Integer> setSoles, Set<Integer> setClienteSoles){
        // Verifica los aciertos de los soles
        int aciertos = 0;
        for (Integer s : setSoles) {
            if (setClienteSoles.contains(s)) {
                aciertos++;
            }
        }
        return aciertos;
    }

    public static String respuesta(Set<Integer> setNums, Set<Integer> setCliNums, Set<Integer> setSoles, Set<Integer> setCliSoles){

        int nums = aciertosNums(setNums, setCliNums);
        int soles = aciertosSoles(setSoles, setCliSoles);

        String resNum = "", resSol = "";

        // Lógica de premios basada en Eurojacobo
        if (nums >= 3 || (nums == 2 && soles > 0) || (nums == 1 && soles == 2)){
            resNum = "Has acertado "+nums+" números";
            if (soles == 2){
                resSol = " y 2 soles!";
            } else if (soles == 1){
                resSol = " y 1 sol.";
            } else{
                resSol = ".";
            }
            String respuesta = resNum.concat(resSol);
            if (nums == 5 && soles == 2) return respuesta.concat(" FELICIDADES, has ganado un año gratis de la CUOTA DE CEBEM !!!!!");
            else if (nums == 5 && soles <= 1) return respuesta.concat(" Has ganado un bucket de pollo del KFC!");
            else return respuesta;
        }
        else{
            return "No has ganado NADA...";
        }
    }
}