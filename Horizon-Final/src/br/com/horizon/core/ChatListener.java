package br.com.horizon.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        ClientChat.out.println(ClientChat.textField.getText());
        ClientChat.textField.setText("");

    }
}
