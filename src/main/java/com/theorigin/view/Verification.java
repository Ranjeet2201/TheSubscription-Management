package main.java.com.theorigin.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Verification extends Application {

    Stage primaryStage;
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {

        // Set stage properties
        primaryStage.setTitle("Subscription Verification");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.setScene(initialize());
        // Show the stage
        primaryStage.show();

    }

    Scene initialize(){

        // VBox to hold all elements vertically
        VBox mainVBox = new VBox(15);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.TOP_CENTER);

        // Label for Upload Image
        Label uploadImageLabel = new Label("Upload Subscription Image (less than 62KB)");

        // Button for uploading image
        Button uploadImageButton = new Button("Upload Image");
        uploadImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                // Add logic to compress the image here
            }
        });

        // Label for Upload Login
        Label uploadLoginLabel = new Label("Upload Login");

        // TextField for Login
        TextField loginField = new TextField();
        loginField.setPromptText("Login");

        // Label for Upload Password
        Label uploadPasswordLabel = new Label("Upload Password");

        // PasswordField for Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Upload Button
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction(e -> {
            // Here, add the logic to handle the uploaded image, login, and password
            System.out.println("Image uploaded, Login: " + loginField.getText() + ", Password: " + passwordField.getText());
            // Close the stage after upload
            primaryStage.close();
        });

        // Adding elements to mainVBox
        mainVBox.getChildren().addAll(
            uploadImageLabel,
            uploadImageButton,
            uploadLoginLabel,
            loginField,
            uploadPasswordLabel,
            passwordField,
            uploadButton
        );

        // Set the scene
        Scene verificationScene = new Scene(mainVBox);
        return verificationScene;
        
    }

}
