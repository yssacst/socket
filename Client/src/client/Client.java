/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author cross
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
         1.estabelecer conexao
         2. trocar mensagens
         */
        //qual a maquina e qual porta
        try {
            Socket socket = new Socket("localhost", 5555);

            //criar strreans entrada saida
            ObjectOutputStream OutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Scanner s = new Scanner(System.in);
            String msgClient = "";
            String msgServer = "";
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

            while (true) {
                System.out.print("Você:");
                msgClient = s.nextLine();
                OutputStream.writeUTF(msgClient);
                OutputStream.flush();

                if (msgClient.equalsIgnoreCase("sair")) {
                    break;

                }
                // System.out.println("Mensagem enviada.");
                // System.out.println("Aguardando Resposta ..");
                msgServer = inputStream.readUTF();
                System.out.println("-------------------------------------");
                System.out.println("Server:[" + formato.format(new Date()) + "]:" + msgServer);
                System.out.println("-------------------------------------");

            }

            /*inputStream.close();
             OutputStream.close();
             socket.close();*/
        } catch (IOException e) {
            System.out.println("Fim de papo");
        }
    }

}
