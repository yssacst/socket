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

            String msg = "Hello server";

            OutputStream.writeUTF(msg);
            OutputStream.flush();

            System.out.println("Mensagem enviada ");

            msg = inputStream.readUTF();

            System.out.println("Resposta: " + msg);

            inputStream.close();
            OutputStream.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("problema main cliente");
        }
    }

}
