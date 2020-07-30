package br.com.horizon.core;

import br.com.horizon.utils.View;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ClientChat {
    static JFrame chatWindow = new JFrame("Aplicacao de Chat");
    static JTextArea chatArea = new JTextArea(22, 45);
    public static JTextField textField = new JTextField(45);
    static JLabel blankLabel = new JLabel("          ");
    static JButton sendButton = new JButton("Enviar!");


    static BufferedReader in;
    public static PrintWriter out;

    ClientChat() {

        chatWindow.setLayout(new FlowLayout());

        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);

        chatWindow.setSize(475, 500);
        chatWindow.setVisible(true);

        chatArea.setEditable(false);

        sendButton.addActionListener(new ChatListener());
        textField.addActionListener(new ChatListener());

    }

    public void startChat() throws Exception {

        String ipAddress = View.inputString("Horizon Chat", "Digite o endereço de IP:");

        Socket soc = new Socket(ipAddress, 9807);

        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

        out = new PrintWriter(soc.getOutputStream(), true);

        ClientChat.chatWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    soc.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                super.windowClosing(e);
                ClientChat.chatArea.setText("");
            }
        });

        while(true) {

            String serverMsg = "";

            try {
                serverMsg = in.readLine();
            } catch(IOException ioException) {
                if(soc.isClosed()) {
                    break;
                }
                ioException.printStackTrace();
            }

            if (serverMsg.contentEquals("NAMEREQUIRED")) {

                String userName = View.inputString("Digite um nome unico:", "Nome eh obrigatorio!");
                out.println(userName);
                chatWindow.setTitle("Aplicacao de Chat - Logado como: " + userName);
            } else if (serverMsg.contentEquals("NAMEACCEPTED")) {

                System.out.println(serverMsg);
                textField.setEditable(true);

            } else {
                chatArea.append(serverMsg + "\n");
            }

        }

    }

}


