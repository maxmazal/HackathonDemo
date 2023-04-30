package com.example.hackathondemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 420);
        stage.setScene(scene);
        stage.setTitle("Budget Application");
        stage.show();
    }

    public static ArrayList<BudgetData> budget = new ArrayList<>();

    public static void main(String[] args) {
        launch();

    }
}