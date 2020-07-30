package br.com.horizon.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatHandlerJob extends Thread {

    //Attributes
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;

    //Initializer
    public ChatHandlerJob(Socket socket) throws IOException {
        setSocket(socket);
    }

    //Getters e Setters
    private Socket getSocket() {
        return socket;
    }

    private void setSocket(Socket socket) {
        this.socket = socket;
    }

    private BufferedReader getIn() {
        return in;
    }

    private void setIn(BufferedReader in) {
        this.in = in;
    }

    private PrintWriter getOut() {
        return out;
    }

    private void setOut(PrintWriter out) {
        this.out = out;
    }

    private String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    /** Metodos CORE da Thread */
    @Override
    public void run() {
        super.run();

        try {

            setIn(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));

            setOut(new PrintWriter(getSocket().getOutputStream(), true));

            getOut().println("NAMEREQUIRED");


            setUserName(getIn().readLine());


            if (getName() == null) {
                return;
            }


            if (!ChatServer.userNames.contains(getUserName())) {

                ChatServer.userNames.add(getUserName());

                getOut().println("NAMEACCEPTED");

                ChatServer.printWriters.add(getOut());
            }

            /** Envio de Mensagens para todos os clientes!!! */

            while (true) {

                // Recebemos a mensagem vinda do Cliente!
                String clientMessage = in.readLine();

                // Verificamos se ela nao eh nula
                if (clientMessage == null) {
                    return;
                }

                // Se nao for nula, vamos enviar para todos os clientes essa mensagem
                for (PrintWriter writer : ChatServer.printWriters) {
                    writer.println(getUserName() + ": " + clientMessage); // Formatamos a mensagem a ser enviada pros clientes
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
