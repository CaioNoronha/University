package br.com.horizon.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    public static ArrayList<String> userNames = new ArrayList<String>();
    public static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    public static void main(String[] args) throws IOException {

        System.out.println("Esperando por clientes...");

        ServerSocket ss = new ServerSocket(9807);

        while(true) {

            Socket soc = ss.accept();
            System.out.println("Conexao estabelecida!");

            ChatHandlerJob handler = new ChatHandlerJob(soc);

            handler.start();

        }

    }
}
