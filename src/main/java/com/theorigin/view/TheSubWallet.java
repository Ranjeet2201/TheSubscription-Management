package main.java.com.theorigin.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.dao.WalletDao;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class TheSubWallet extends Application {

    int user_id;
    String email;
    public static void main(String[] args) {
        launch(args);
    }

    public TheSubWallet(int user_id,String email){
        this.user_id = user_id;
        this.email = email;
    }

    @Override
    public void start(Stage myStage) {

        
                Label title = new Label("Wallet");
                title.setFont(new Font(40));
                LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#17EAD9")),
                new Stop(1, Color.web("#6078EA"))
                );
                title.setTextFill(textGradient);
        
        Button backButton = new Button("BACK");
        backButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        backButton.setPrefHeight(50);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("back btn clicked");
                new AuthenticationController().navigateToHomepage(myStage, user_id,email);
            }
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox titleBox1 = new HBox(backButton,spacer, title);
        titleBox1.setPrefHeight(50);
        titleBox1.setPrefWidth(1300);
        titleBox1.setAlignment(Pos.CENTER);
        titleBox1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));


        
        Label thesubLabel = new Label("TheSubWallet Balance: ");
        thesubLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");

        Label amountLabel = new Label(String.valueOf(new WalletDao(email).getTotalBalance(user_id)));
        amountLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");

        
        HBox descriptionHBox = createHeadHBox(thesubLabel, amountLabel);

        Button addMoney = createButton("Add Money                       ₹ +");
        addMoney.setPrefSize(350, 80); 
        Button redeemMoney = createButton("Redeem Money                       ₹ -");
        redeemMoney.setPrefSize(350, 80); 

        
        VBox upperVBox = createUpperVBox(descriptionHBox, addMoney,redeemMoney);

        
        Button allTransactionButton = createButton("All Transactions");
        allTransactionButton.setMaxWidth(1200);
        Button creditButton = createButton("Credit");
        Button debitButton = createButton("Debit");
        Button refundButton = createButton("Refund");

        
        HBox buttonBox = createHBox(allTransactionButton, creditButton, debitButton, refundButton);

        
        VBox transactionShowerBox = createTransactionShowerVBox();

        
        VBox mainVBox = new VBox(20); 
        mainVBox.getChildren().addAll(upperVBox, buttonBox, transactionShowerBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setMaxSize(600,400);
        ImageView theSubimgView=new ImageView(new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png"));
        VBox logoVBox=createTransactionShowerVBox();
        theSubimgView.setFitHeight(500);
        theSubimgView.setFitWidth(400);
        logoVBox.setPrefSize(500, 650);
        logoVBox.getChildren().add(theSubimgView);
         
        HBox mainHBox=new HBox(logoVBox,mainVBox);
        mainHBox.setPrefHeight(650);
        mainHBox.setSpacing(30);
        VBox finalVBox=new VBox(titleBox1,mainHBox);
        finalVBox.setPrefHeight(650);
        finalVBox.setSpacing(30);

        

        
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(finalVBox);

        
        Scene sc = new Scene(root, 1800, 900);
        myStage.setScene(sc);
        myStage.show();

        addMoney.setOnAction(e ->{
            WalletPopUp.showWalletPopUp(myStage,user_id,email);
        });
        redeemMoney.setOnAction(e ->{
            WalletPopUp.redeemWalletPopUp(myStage,user_id,email);
        });
    }

    
    Button createButton(String str) {
        Button button = new Button(str);
        button.setPrefSize(250, 40); 
        button.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:25px;");
        return button;
    }

    
    private HBox createHBox(Button... buttons) {
        HBox hbox = new HBox(buttons);
        hbox.setMaxWidth(600);
        hbox.setMaxHeight(150);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10); 
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    
    VBox createUpperVBox(HBox descriptionBox, Button addMoneyButton,Button redeemMoneyButton) {
        VBox vbox = new VBox(20, descriptionBox, addMoneyButton,redeemMoneyButton); 
        vbox.setMaxSize(600, 200);
        vbox.setPrefSize(600, 150);
        vbox.setStyle("-fx-border-color:black;-fx-border-width:5px;-fx-padding:20px;-fx-background-radius:35px;-fx-border-radius:35px;");
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    
    private HBox createHeadHBox(Label mainLabel, Label amountLabel) {
        HBox hbox = new HBox(mainLabel, amountLabel);
        hbox.setMaxWidth(900);
        hbox.setMaxHeight(200);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50); 
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    
    VBox createTransactionShowerVBox() {
        VBox vbox = new VBox();
        vbox.setMaxSize(600, 300);
        vbox.setPrefSize(600, 300);
        vbox.setStyle(
            "-fx-border-color:black;" +         // Set the border color to black
            "-fx-border-width:3px;" +           // Set the border width to 3px
            "-fx-padding:20px;" +               // Add padding inside the border
            "-fx-background-radius:15px;" +     // Round the corners of the background
            "-fx-border-radius:15px;"           // Round the corners of the border
        );
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
