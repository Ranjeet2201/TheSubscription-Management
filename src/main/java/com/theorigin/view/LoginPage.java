package main.java.com.theorigin.view;

import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.dao.ActivePool;
import main.java.com.theorigin.dao.AuthenticationDao;
import main.java.com.theorigin.dao.DatabaseCredentials;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HomePageDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.Pool;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.service.AuthenticationService;
import main.java.com.theorigin.service.EmailService;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LoginPage extends Application{
    Stage myStage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage myStage)  {
        myStage.setScene(initialize(myStage));
        myStage.setTitle("TheSubscription");
        myStage.setHeight(700);
        myStage.setWidth(1300);
        
        myStage.setMaximized(true);
        myStage.show();        

    }
    Scene initialize(Stage myStage){
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-size:34px;-fx-font-weight:bold");
        
        // Load background image

        Image backgroundImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(700);
        backgroundImageView.setFitWidth(700);
        
        StackPane vboxForImage = new StackPane(backgroundImageView);
        vboxForImage.setMaxSize(200, 200);
        vboxForImage.setStyle("-fx-background-radius: 100px; -fx-background-color: white;");

        //Load login page image
        
        Image loginPageImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/thesublogin.png");
        ImageView loginPageImageView = new ImageView(loginPageImage);
        loginPageImageView.setFitHeight(200);
        loginPageImageView.setFitWidth(200);
        

        Rectangle clip1 = new Rectangle(loginPageImageView.getFitWidth(), loginPageImageView.getFitHeight());
        clip1.setArcWidth(200);
        clip1.setArcHeight(200);
        loginPageImageView.setClip(clip1);

        // Optional: Adding a circular border
        Circle circleBorder1 = new Circle(150, 150, 100); // CenterX, CenterY, Radius
        circleBorder1.setStroke(Color.BLACK); // Border color
        circleBorder1.setFill(Color.TRANSPARENT); // No fill, just border
        circleBorder1.setStrokeWidth(5); // Border thickness

        StackPane loginImagePane = new StackPane(loginPageImageView, circleBorder1);
        loginImagePane.setMaxSize(200, 200);
        loginImagePane.setStyle("-fx-background-radius: 100px; -fx-background-color: white;");

        // Ensure the StackPane itself is circular
        loginImagePane.setShape(new Circle(100)); // Radius of 100 (same as background radius)

        // Login UI components
        TextField userNameField = new TextField();
        userNameField.setMaxSize(400, 40);
        userNameField.setPromptText("Username");
        userNameField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxSize(400, 40);
        passwordField.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");
       
        Image googleIcon = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/googlelogin.png"); // Update with your image path
        ImageView googleImageView = new ImageView(googleIcon);
        googleImageView.setFitHeight(30); // Adjust image size
        googleImageView.setFitWidth(30);
        
        Button loginGoogleBtn = new Button("Continue with Google.");
        loginGoogleBtn.setMaxSize(400, 40);
        loginGoogleBtn.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        HBox googleBox = new HBox(10); // Horizontal box for image and button
        googleBox.getChildren().addAll(googleImageView, loginGoogleBtn);
        googleBox.setAlignment(Pos.CENTER_LEFT); // Align items to the left
        googleBox.setMaxSize(400, 40);
        googleBox.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc; -fx-background-color: white;");


        Image facebookIcon = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/facebooklogin.png"); // Update with your image path
        ImageView facebookImageView = new ImageView(facebookIcon);
        facebookImageView.setFitHeight(30); // Adjust image size
        facebookImageView.setFitWidth(30);

        Button loginFacebookBtn = new Button("Continue with Facebook.");
        loginFacebookBtn.setMaxSize(400, 40);
        loginFacebookBtn.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-font-size: 16px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        HBox facebookBox = new HBox(10); // Horizontal box for image and button
        facebookBox.getChildren().addAll(facebookImageView, loginFacebookBtn);
        facebookBox.setAlignment(Pos.CENTER_LEFT); // Align items to the left
        facebookBox.setMaxSize(400, 40);
        facebookBox.setStyle("-fx-pref-width: 300px; -fx-pref-height: 40px; -fx-padding: 5px; -fx-border-radius: 5px; -fx-border-color: #ccc; -fx-background-color: white;");


        Button loginButton = createStyledButton("Login");
        loginButton.setOnAction(e->{
            String username = userNameField.getText();
            String password = passwordField.getText();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            
            int user_id = new AuthenticationDao().authenticateUser(username,password);
            String nameOfUser = new AuthenticationDao().fetchUserName();
            System.out.println(user_id);
            if(user_id != -1){
                String email = new AuthenticationDao().fetchMailId(username);
                System.err.println(email);
//Initialize All DAO's
                initializeDaos();
                System.out.println(new WalletDao(email).doesUserIdExist(user_id));
                new AuthenticationController().navigateToHomepage(myStage,user_id,email);

// For NOW no need to send mail after every login.
// Check if the email is not null
                if (email != null && !email.isEmpty()) {
                    //new EmailService().sendEmail(email, "Welcome!", "Thank you for Logging-In.");
                } else {
                    System.out.println("Error: Email is null or empty");
                }

                
            } else {
                System.out.println("Some Error occured");
            }
            userNameField.clear();
            passwordField.clear();
        });
        
        Button signupButton = createStyledButton("SignUp");
        signupButton.setOnAction(e->{
            AuthenticationController authenticationControllerObj = new AuthenticationController();
            authenticationControllerObj.navigateToSignupPage(myStage);
        });
        
        HBox loginSignupBox = new HBox(10);
        loginSignupBox.getChildren().addAll(loginButton,signupButton);
        loginSignupBox.setAlignment(Pos.CENTER);
        loginSignupBox.setPrefWidth(700);
        loginSignupBox.setLayoutX(0);
       

        VBox loginBox = new VBox(20);
        loginBox.setPrefSize(700, 700);
        //loginBox.setStyle("-fx-background-color:#B8E2F2; -fx-background-radius: 0px; -fx-padding: 0px;");
        LinearGradient loginboxGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("Black")),
                new Stop(1, Color.web("Black")));
        loginBox.setBackground(new Background(new BackgroundFill(loginboxGradient, CornerRadii.EMPTY, null)));
        loginBox.getChildren().addAll(loginImagePane,loginLabel, userNameField, passwordField, loginSignupBox, googleBox,facebookBox);
        loginBox.setAlignment(Pos.CENTER); // Align content in center
        loginBox.setMargin(loginSignupBox, new Insets(10,0,0,0));
        // Combine background image VBox and login VBox
        HBox mainHBox = new HBox(30);
        mainHBox.getChildren().addAll(vboxForImage, loginBox);
        mainHBox.setPadding(new Insets(50));
        mainHBox.setAlignment(Pos.CENTER); // Align content in center

        // StackPane to hold everything
        GridPane root = new GridPane();
        root.add(vboxForImage,0,0);
        root.add(loginBox,1,0);

        Scene loginScene = new Scene(root);

        return loginScene;
    }

    public Button createStyledButton(String text){
        Button btn = new Button(text);
        btn.setPrefWidth(200);
        btn.setStyle(("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
        "-fx-text-fill: black; " +
        "-fx-font-weight: bold; " +
        "-fx-font-size: 16px; " +
        "-fx-background-radius: 10px; " +
        "-fx-border-radius: 5px; " +
        "-fx-border-color: black; " +
        "-fx-border-width: 5px;"));

        return btn;
    }

    public static void initializeDaos() {
        DatabaseCredentials databaseCredentialsObj = new DatabaseCredentials();
        AuthenticationDao authenticationDaoObj = new AuthenticationDao();
        ExploreSideDao exploreSideDaoObj = new ExploreSideDao();
        HomePageDao homePageDaoObj = new HomePageDao();
        HostSideDao hostSideDaoObj = new HostSideDao();
        //WalletDao walletDaoObj = new WalletDao();
        // ActivePool activePoolObj = new ActivePool(0, 0, 0, STYLESHEET_CASPIAN, 0, 0, STYLESHEET_MODENA, STYLESHEET_CASPIAN, false);
        // Pool poolObj = new Pool(0, 0, 0, STYLESHEET_CASPIAN, 0, 0, STYLESHEET_MODENA, STYLESHEET_CASPIAN, false);
        System.out.println("All DAO's are initialised");
    }
    
}


