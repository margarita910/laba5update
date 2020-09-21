package Server;

import laba.commands.*;
import laba.com.company.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public Server() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6982);

        System.out.println("Server started!");

        Socket socket = serverSocket.accept();
    }

    private Command receiveData(){
        try(ServerSocket server = new ServerSocket(5665)){
            Socket client = server.accept();
            Command receiveCommand;
            byte[] buffer = new byte[65536];
            InputStream in = client.getInputStream();
            System.out.println("Ожидаем данные");
            while (true){
                in.read(buffer);

            }


        }
        catch (IOException e){

        }


    }

    private static ArrayList<String> executeCommand(Command command) throws IOException, InvalidScriptException, CommandExecutionException {
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        return invoker.executeCommand();
    }
}
