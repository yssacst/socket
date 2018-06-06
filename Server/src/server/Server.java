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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cross
 */
public class Server {

    
    private ServerSocket serverSocket;
    
    private void criarServerSocket(int porta){
        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException ex) {
            System.out.println("Erro ao criar conexão");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Socket esperarConexao() throws IOException{
       Socket socket =  serverSocket.accept();
       return socket;
    }
    
    
    private void tratarConexao(Socket socket) throws IOException{
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Scanner s = new Scanner(System.in);
//protocolo

            String msg = input.readUTF();
        
            System.out.println("Mensagem recebida...");
        System.out.println("Mensagem recebida:"+msg);
        
            //output.writeUTF(s.next());
            output.writeUTF("Hello World");
            output.flush();
      
      
      
            input.close();
            output.close();
        }catch(IOException e){
            System.out.println("erro metodo tratar conexão");
        }finally{
            System.out.println("fechando socket");
            fecharSocket(socket);
        }
    }
    
    private void fecharSocket(Socket socket) throws IOException{
        socket.close();        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Server server = new Server();
            System.out.println("Aguardando conexão...");
            server.criarServerSocket(5555);
            
            while(true){//permitir atender mais de um cliente
            Socket socket = server.esperarConexao();//protocolo
            System.out.println("Cliente conectado.");
            server.tratarConexao(socket);
            System.out.println("Cliente finalizado.");
            }
        }catch(IOException io){
            System.out.println("erro na main");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, io);           
        }
    }
    
}
