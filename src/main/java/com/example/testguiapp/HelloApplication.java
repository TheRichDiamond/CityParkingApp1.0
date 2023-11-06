package com.example.testguiapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
        public void start(Stage primaryStage){

        // create window with title "Login"
        primaryStage.setTitle("Login");
        primaryStage.setResizable(true);
        primaryStage.minHeightProperty().setValue(800);
        primaryStage.minWidthProperty().setValue(800);

        primaryStage.show();

        // create grid pane to hold all the elements

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // create scene and add grid pane to it
        Scene welcomescene = new Scene(grid, 300, 275);
        primaryStage.setScene(welcomescene);

        Text welcomescenetitle = new Text("Login");
        welcomescenetitle.setFont(Font.font("Tahoma", 20));
        grid.add(welcomescenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName,0,1);

        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);

        Label pw = new Label("Password:");
        grid.add(pw,0,2);

        TextField pwBox = new TextField();
        grid.add(pwBox,1,2);

        Button loginbtn = new Button("Sign in");
        grid.add(loginbtn,1,4);

        Button registerbtn = new Button("Register");
        grid.add(registerbtn,1,5);


        registerbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                if (username != null && password != null && !username.isEmpty() && !password.isEmpty()){
                    String user = username;
                    String pass = password;
                    welcomescenetitle.setText("You Are Now Registered!");
                } else {
                    welcomescenetitle.setText("Please Enter a Username and Password!");
                }
            }
        });

        // Create scene for after login

        GridPane afterloging = new GridPane();
        afterloging.setAlignment(Pos.CENTER);
        afterloging.setHgap(10);
        afterloging.setVgap(10);
        afterloging.setPadding(new Insets(25,25,25,25));

        Scene afterloginsc = new Scene(afterloging, 300, 275);
        Text afterlogintitle = new Text("Welcome to City Parking Monitor!");
        afterloging.add(afterlogintitle, 0, 0, 2, 1);


        Button logoutbtn = new Button("Logout");
        afterloging.add(logoutbtn,1,4);

        logoutbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(welcomescene);
                Text logouttxt = new Text("You have been logged out!");
                logouttxt.setFont(Font.font("Tahoma", 20));
                // add a logout message under all the other elements
                grid.add(logouttxt, 0, 6, 2, 1);


            }
        });

        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userTextField.getText();
                String password = pwBox.getText();

                if (username.equals("admin") && password.equals("admin")){
                    welcomescenetitle.setText("Welcome to City Parking Monitor!");

                    primaryStage.setScene(afterloginsc);

                } else {
                    welcomescenetitle.setText("Wrong username or password!");
                }
            }
        });

        Label searchParking = new Label("Search Parking:");
        afterloging.add(searchParking,0,1);
        TextField searchParkingBox = new TextField();
        afterloging.add(searchParkingBox,1,1);

        Button searchbtn = new Button("Search");
        afterloging.add(searchbtn,0,3);

        searchbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String search = searchParkingBox.getText(); // get the text from the search box

                // Specify the path to your .txt file
                File file = new File("parking_spotDB.txt");

                // Check if the file exists, if not, create it
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains(search)) {


                            Text searchresult = new Text(line);
                            afterloging.add(searchresult, 0, 5, 2, 1);


                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public static void main(String[] args) {
        launch();
    }
}