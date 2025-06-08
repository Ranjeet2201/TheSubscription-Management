package main.java.com.theorigin.view;



import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.service.AuthenticationService;
import main.java.com.theorigin.dao.AuthenticationDao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SignupPage extends Application{    
    Stage myStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage) {
        
        myStage.setScene(initialize(myStage));
        myStage.setTitle("TheSubscription");
        myStage.setHeight(760);
        myStage.setWidth(1360);
        
        myStage.setMaximized(true);
        myStage.show();


    }
     
    Scene initialize(Stage myStage){
        Label signupLabel = new Label("SignUp");
        signupLabel.setStyle("-fx-font-size:34px;-fx-font-weight:bold");

        // Load background image
        Image backgroundImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(700);
        backgroundImageView.setFitWidth(700);

        // Create VBox for image
        StackPane vboxForImage = new StackPane(backgroundImageView);
        vboxForImage.setStyle("-fx-background-radius: 0px; -fx-background-color: #B8E2F2; -fx-padding: 0px;");

        Image signupPageImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/thesubsignup.png");
        ImageView signupPageImageView = new ImageView(signupPageImage);
        signupPageImageView.setFitHeight(200);
        signupPageImageView.setFitWidth(200);
        

        Rectangle clip1 = new Rectangle(signupPageImageView.getFitWidth(), signupPageImageView.getFitHeight());
        clip1.setArcWidth(200);
        clip1.setArcHeight(200);
        signupPageImageView.setClip(clip1);

        // Optional: Adding a circular border
        Circle circleBorder1 = new Circle(150, 150, 100); // CenterX, CenterY, Radius
        circleBorder1.setStroke(Color.BLACK); // Border color
        circleBorder1.setFill(Color.TRANSPARENT); // No fill, just border
        circleBorder1.setStrokeWidth(5); // Border thickness

        StackPane signupImagePane = new StackPane(signupPageImageView, circleBorder1);
        signupImagePane.setMaxSize(200, 200);
        signupImagePane.setStyle("-fx-background-radius: 100px; -fx-background-color: white;");

        // Ensure the StackPane itself is circular
        signupImagePane.setShape(new Circle(100)); // Radius of 100 (same as background radius)


        TextField nameField = new TextField();
        nameField.setMaxSize(400, 40);
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        TextField userNameField = new TextField();
        userNameField.setMaxSize(400, 40);
        userNameField.setPromptText("Username");
        userNameField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        TextField emailField = new TextField();
        emailField.setMaxSize(400, 40);
        emailField.setPromptText("Email");
        emailField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        TextField mobileNoField = new TextField();
        mobileNoField.setMaxSize(400, 40);
        mobileNoField.setPromptText("MobileNo");
        mobileNoField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxSize(400, 40);
        passwordField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");
       
        Button signUpButton = new Button("Sign Up");
        signUpButton.setMaxSize(200, 40); // Half the width of the PasswordField
        signUpButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc; -fx-background-color: Black; -fx-text-fill: white;");

        signUpButton.setOnAction(e -> {
            String name = nameField.getText();
            String username = userNameField.getText();
            String email = emailField.getText();
            String mobileNo = mobileNoField.getText();
            String password = passwordField.getText();

            // System.out.println("Username: " + username);
            // System.out.println("Password: " + password);
            // int id = insertUser(username, password);
            // if (id != -1) {
            //     app.navigateToLoginpage();
            // }
            AuthenticationService authenticationServiceObj = new AuthenticationService();
            authenticationServiceObj.signUpUserData(name,username,email,mobileNo,password,myStage);
            
            userNameField.clear();
            passwordField.clear();
            emailField.clear();
            mobileNoField.clear();
            nameField.clear();
        });

        Button loginButton = new Button("Login");
        loginButton.setMaxSize(200, 40); // Half the width of the PasswordField
        loginButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc; -fx-background-color: Black; -fx-text-fill: white;");
        loginButton.setOnAction(e -> {
            new AuthenticationController().navigateToLoginPage(myStage);
        });
        Label nameLabel = new Label("Name:");
        Label userNameLabel = new Label("Username:");
        Label emailLabel = new Label("E-mail:");
        Label mobileNoLabel = new Label("Mobile No:");
        Label passwordLabel = new Label("Password:");

        nameLabel.setPrefWidth(100);
        userNameLabel.setPrefWidth(100);
        emailLabel.setPrefWidth(100);
        mobileNoLabel.setPrefWidth(100);
        passwordLabel.setPrefWidth(100);        
        
        // Create HBoxes to hold each label and text field
        HBox nameHBox = new HBox(10, nameLabel, nameField);
        HBox userNameHBox = new HBox(10, userNameLabel, userNameField);
        HBox emailHBox = new HBox(10, emailLabel, emailField);
        HBox mobileNoHBox = new HBox(10, mobileNoLabel, mobileNoField);
        HBox passwordHBox = new HBox(10, passwordLabel, passwordField);        

        nameHBox.setStyle("-fx-padding: 5px;");
        userNameHBox.setStyle("-fx-padding: 5px;");
        emailHBox.setStyle("-fx-padding: 5px;");
        mobileNoHBox.setStyle("-fx-padding: 5px;");
        passwordHBox.setStyle("-fx-padding: 5px;");        
        
        // Create HBox to hold the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setMaxSize(300, 40);
        buttonBox.getChildren().addAll(signUpButton, loginButton);
        buttonBox.setStyle("-fx-padding: 0px;");
        

        VBox loginBox = new VBox(20);
        loginBox.setPrefSize(700, 700);
        loginBox.setStyle("-fx-background-color:Black; -fx-background-radius: 20px; -fx-padding: 20px;");
        
        loginBox.getChildren().addAll(signupImagePane,signupLabel,nameHBox,userNameHBox,emailHBox,mobileNoHBox,passwordHBox,buttonBox);
        loginBox.setAlignment(Pos.CENTER);

        HBox mainHBox = new HBox(30);
        mainHBox.getChildren().addAll(vboxForImage, loginBox);
        mainHBox.setPadding(new Insets(50));
        mainHBox.setAlignment(Pos.CENTER);

        GridPane root = new GridPane();
        root.add(vboxForImage, 0, 0);
        root.add(loginBox, 1, 0);

        Scene signUpScene = new Scene(root);
        return signUpScene;
    }
}
