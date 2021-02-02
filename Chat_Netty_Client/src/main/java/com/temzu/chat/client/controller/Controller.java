package com.temzu.chat.client.controller;

import com.temzu.chat.client.network.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    private int PORT = 8189;
    private String HOST = "localhost";

    private Network network;


    @FXML
    private TextField msgField;

    @FXML
    private TextArea mainArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new Network(PORT, HOST);
    }

    public void sendMsgAction(ActionEvent actionEvent) {
        network.sendMessage(msgField.getText());
        msgField.clear();
        msgField.requestFocus();
    }
}
