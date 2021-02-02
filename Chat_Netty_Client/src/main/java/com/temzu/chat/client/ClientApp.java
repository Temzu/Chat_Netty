package com.temzu.chat.client;

import com.temzu.chat.client.network.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientApp extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(ClientApp.class);


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
        primaryStage.setTitle("Chat client");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
