/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author cross
 */
public class Client2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          try {
            Socket socket = new Socket("localhost", 5555);

            //criar strreans entrada saida
            ObjectOutputStream OutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Scanner s = new Scanner(System.in);
            String msgClient = "";
            String msgServer = "";

            while (!msgClient.equalsIgnoreCase("sair")) {
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
                System.out.println("Server: " + msgServer);
                System.out.println("-------------------------------------");

            }
            /*inputStream.close();
                    OutputStream.close();
                    socket.close();*/

        } catch (IOException e) {
            System.out.println("problema main cliente");
        }
    }
    
}
