/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cross
 */
public class Server {

    private ServerSocket serverSocket;

    private void criarServerSocket(int porta) {
        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException ex) {
            System.out.println("Erro ao criar conexão");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Socket esperarConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    private void tratarConexao(Socket socket) throws IOException {
        String msgClient = "";
        String msgServer = "";
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Scanner s = new Scanner(System.in);
//protocolo

            while (true) {
                msgClient = input.readUTF();
                System.out.println("-------------------------------------");
                System.out.println("Client[" + formato.format(new Date()) + "]:" + msgClient);
                System.out.println("-------------------------------------");
                System.out.print("Você:");

                msgServer = s.nextLine();

                if (msgServer.equalsIgnoreCase("sair")|| msgClient.equalsIgnoreCase("sair")) {
                    break;
                }

                output.writeUTF(msgServer);
                output.flush();

            }

            fecharSocket(socket);
            System.out.println("Cliente finalizado.");

            // input.close();
            //output.close();
        } catch (IOException e) {
            System.out.println("erro metodo tratar conexão");
        } finally {
            // System.out.println("fechando socket");
            // fecharSocket(socket);
        }
    }

    private void fecharSocket(Socket socket) throws IOException {
        socket.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Server server = new Server();
            String msg;
            System.out.println("Aguardando conexão...");
            server.criarServerSocket(5555);

            //permitir atender mais de um cliente
            Socket socket = server.esperarConexao();//protocolo
            System.out.println("Cliente conectado.");
            server.tratarConexao(socket);

        } catch (IOException io) {
            System.out.println("erro na main");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, io);
        }
    }

}
