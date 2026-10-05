package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor11 {

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
            System.out.println("Buenas tardes, pedazo de CABEZABUQUE");
            System.out.println("\n.\n.\n.\n");
            int cont = 1;

            while(ciclo){
                String msgCli = in1.readUTF();
                System.out.println("Uhhhh bienvenido, RAMIRO (x"+cont+")");

                if(msgCli.equalsIgnoreCase("no")){
                    ciclo = false;
                    out1.writeUTF("ME DA IGUAL! !!\n𓀀 𓀁 𓀂 𓀃 𓀄 𓀅 𓀆 𓀇 𓀈 𓀉 𓀊 𓀋 𓀌 𓀍 𓀎 𓀏\n 𓀐 𓀑 𓀒 𓀓 𓀔 𓀕 𓀖 𓀗 𓀘 𓀙 𓀚 𓀛 𓀜 𓀝 𓀞 𓀟 𓀠 𓀡 𓀢 𓀣 𓀤\n 𓀥 𓀦 𓀧 𓀨 𓀩 𓀪 𓀫 𓀬 𓀭 𓀮 𓀯 𓀰 𓀱 𓀲 𓀳 𓀴 𓀵 𓀶 𓀷 𓀸 𓀹 𓀺 𓀻 𓀼 𓀽 𓀾 𓀿 𓁀 𓁁 \n𓁂 𓁃 𓁄 𓁅 𓁆 𓁇 𓁈 𓁉 𓁊 𓁋 𓁌 𓁍 𓁎 𓁏 𓁐");
                }
                else{
                    out1.writeUTF("??? que??\n No te escucho.. repite...");
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
}