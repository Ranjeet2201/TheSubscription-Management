package main.java.com.theorigin.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.WalletController;
import main.java.com.theorigin.service.EmailService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WalletPopUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int user_id = 0;
        //Button to trigger the popup
        Button openPopupButton = new Button("Open Wallet PopUp");
        //openPopupButton.setOnAction(e -> showWalletPopUp(primaryStage,user_id,email));

        StackPane root = new StackPane(openPopupButton);
        Scene scene = new Scene(root, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void showWalletPopUp(Stage owner,int user_id,String email) {
        // Create the popup stage
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(owner);
    
        // Image at the top
        ImageView imageView = new ImageView(new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/rupee.png")); 
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        VBox imageViewBox = new VBox(imageView);
        imageViewBox.setPrefSize(150, 150);
    
        // Label
        Label amountLabel = new Label("Enter Amount to be added:");
        amountLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        // TextField for amount input
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setPrefSize(200, 50);
        amountField.setStyle("-fx-font-size:24px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
        
        // HBox for predefined amounts
        Button plus100 = createAmountButton("+100", amountField);
        Button plus200 = createAmountButton("+200", amountField);
        Button plus500 = createAmountButton("+500", amountField);
        Button plus1000 = createAmountButton("+1000", amountField);
        HBox amountBox = new HBox(15, plus100, plus200, plus500, plus1000); // Increase spacing between buttons
        amountBox.setAlignment(Pos.CENTER);
        
        // ComboBox for payment modes
        ComboBox<String> paymentModeBox = new ComboBox<>();
        paymentModeBox.getItems().addAll("UPI", "Cards", "NetBanking");
        paymentModeBox.setPromptText("Pay using");
        paymentModeBox.setPrefSize(250, 50);
        paymentModeBox.setStyle("-fx-font-size:16px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
        
        // Button to add money
        Button addMoneyButton = new Button("Add Money ₹0");
        addMoneyButton.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
        addMoneyButton.setPrefSize(250, 50);
        addMoneyButton.setPadding(new Insets(10));
        
    
        // Update button text based on the amount entered
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            String amountText = newValue.isEmpty() ? "0" : newValue;
            addMoneyButton.setText("Add Money ₹" + amountText);
        });

         // Enable button when a payment mode is selected
         paymentModeBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                addMoneyButton.setDisable(false); // Enable button
            } else {
                addMoneyButton.setDisable(true); // Disable button
            }
        });
        addMoneyButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            if(new WalletController(email).creditMoneyToSubWallet(amount,user_id)){
                //new EmailService().sendEmail(email, "Credit Amount", "Amount has been successfully Credited to your TheSubscription Wallet!.");
                // Create an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Amount Credit to wallet successfully.");
                alert.showAndWait();
                popupStage.close();
            } else {
                // Create an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Amount Credit Failed.");
                alert.showAndWait();
                popupStage.close();
            }
        });
    
        // HBox for payment mode and add money button
        HBox bottomBox = new HBox(15, paymentModeBox, addMoneyButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10, 0, 10, 0)); // Add padding around the bottom box
    
        // VBox layout for the popup
        VBox popupVBox = new VBox(20, imageViewBox, amountLabel, amountField, amountBox, bottomBox);
        popupVBox.setAlignment(Pos.CENTER);
        popupVBox.setPadding(new Insets(20, 20, 20, 20)); // Add padding to the VBox
        popupVBox.setSpacing(15); // Ensure spacing between each element
        popupVBox.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px;-fx-background-radius:20px;-fx-border-radius:20px;");
        popupVBox.setMargin(imageViewBox, new Insets(10, 0, 10, 175));
        popupVBox.setMargin(amountLabel, new Insets(10, 0, 20, 20));
        popupVBox.setMargin(amountField, new Insets(10, 20, 10, 50));
        popupVBox.setMargin(amountBox, new Insets(10, 0, 50, 20));
        
        Scene popupScene = new Scene(popupVBox, 500, 600);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    
    static Button createAmountButton(String text, TextField amountField) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:10px;");
        button.setPrefSize(200, 50); // Increase size of the buttons
        button.setOnAction(e -> {
            int currentAmount = Integer.parseInt(amountField.getText().isEmpty() ? "0" : amountField.getText());
            currentAmount += Integer.parseInt(text.replace("+", ""));
            amountField.setText(String.valueOf(currentAmount));
        });
        return button;
    }

    static void redeemWalletPopUp(Stage owner,int user_id,String email) {
        // Create the popup stage
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(owner);
    
        // Image at the top
        ImageView imageView = new ImageView(new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/rupee.png")); 
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        VBox imageViewBox = new VBox(imageView);
        imageViewBox.setPrefSize(150, 150);
    
        // Label
        Label amountLabel = new Label("Enter Amount to Redeem:");
        amountLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        // TextField for amount input
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setPrefSize(200, 50);
        amountField.setStyle("-fx-font-size:24px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
        
        // HBox for predefined amounts
        Button plus100 = createAmountButton("+100", amountField);
        Button plus200 = createAmountButton("+200", amountField);
        Button plus500 = createAmountButton("+500", amountField);
        Button plus1000 = createAmountButton("+1000", amountField);
        HBox amountBox = new HBox(15, plus100, plus200, plus500, plus1000); // Increase spacing between buttons
        amountBox.setAlignment(Pos.CENTER);
        
        // ComboBox for payment modes
        ComboBox<String> paymentModeBox = new ComboBox<>();
        paymentModeBox.getItems().addAll("UPI", "Cards", "NetBanking");
        paymentModeBox.setPromptText("Redeem using");
        paymentModeBox.setPrefSize(250, 50);
        paymentModeBox.setStyle("-fx-font-size:16px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
        
        // Button to add money
        Button redeemMoneyButton = new Button("Redeem Money ₹0");
        redeemMoneyButton.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
        redeemMoneyButton.setPrefSize(250, 50);
        redeemMoneyButton.setPadding(new Insets(10));
        
    
        // Update button text based on the amount entered
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            String amountText = newValue.isEmpty() ? "0" : newValue;
            redeemMoneyButton.setText("Redeem Money ₹" + amountText);
        });

         // Enable button when a payment mode is selected
         paymentModeBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                redeemMoneyButton.setDisable(false); // Enable button
            } else {
                redeemMoneyButton.setDisable(true); // Disable button
            }
        });
        redeemMoneyButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            if(new WalletController(email).redeemMoneyFromSubWallet(amount,user_id)){
                //new EmailService().sendEmail(email, "Redeem Amount", "Amount has been successfully transfered to your Bank Account.");
                // Create an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Amount Redeemed from wallet successfully.");
                alert.showAndWait();
                popupStage.close();
            } else {
                // Create an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failer");
                alert.setHeaderText(null);
                alert.setContentText("Amount Redeemed Failed.");
                alert.showAndWait();
                popupStage.close();
            }
        
        });
    
        // HBox for payment mode and add money button
        HBox bottomBox = new HBox(15, paymentModeBox, redeemMoneyButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10, 0, 10, 0)); // Add padding around the bottom box
    
        // VBox layout for the popup
        VBox popupVBox = new VBox(20, imageViewBox, amountLabel, amountField, amountBox, bottomBox);
        popupVBox.setAlignment(Pos.CENTER);
        popupVBox.setPadding(new Insets(20, 20, 20, 20)); // Add padding to the VBox
        popupVBox.setSpacing(15); // Ensure spacing between each element
        popupVBox.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px;-fx-background-radius:20px;-fx-border-radius:20px;");
        popupVBox.setMargin(imageViewBox, new Insets(10, 0, 10, 175));
        popupVBox.setMargin(amountLabel, new Insets(10, 0, 20, 20));
        popupVBox.setMargin(amountField, new Insets(10, 20, 10, 50));
        popupVBox.setMargin(amountBox, new Insets(10, 0, 50, 20));
        
        Scene popupScene = new Scene(popupVBox, 500, 600);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }


    public void BillPopUp(Stage owner) {
        
        Stage popupStage = new Stage();
        // popupStage.initModality(Modality.APPLICATION_MODAL);
        // popupStage.initOwner(owner);
    
        // Image at the top
        ImageView imageView = new ImageView(new Image("file:D:/TheSubscription/TheOrigin/thesubscription/src/assets/images/logo (1).png")); 
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        VBox imageViewBox = new VBox(imageView);
        imageViewBox.setPrefSize(200, 200);
    
        // Label
        Label billLabel = new Label("Billing Details:");
        billLabel.setStyle("-fx-font-size:40px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        Label serviceNameLabel=new Label("Service : "+"Netflix");
        serviceNameLabel.setStyle("-fx-font-size:25px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        Label servicePriceLabel=new Label("Service Price: ₹165");
        servicePriceLabel.setStyle("-fx-font-size:25px;-fx-font-weight:bold;-fx-text-fill:black;");

        Label feelLabel=new Label("Fee(10%): 16.5");
        feelLabel.setStyle("-fx-font-size:25px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        Label theSubWalletBalanceLabel=new Label("TheSubscription Wallet: "+"199.0");
        theSubWalletBalanceLabel.setStyle("-fx-font-size:25px;-fx-font-weight:bold;-fx-text-fill:black;");

        Button proceedToPayButton = new Button("Pay");
        proceedToPayButton.setStyle("-fx-font-size:25px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
        proceedToPayButton.setPrefSize(250, 50);
        proceedToPayButton.setPadding(new Insets(10));
    
       

        proceedToPayButton.setOnAction(e -> {
            popupStage.close();
        });
    
        // HBox for payment mode and add money button
        HBox bottomBox = new HBox(proceedToPayButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20, 0, 20, 0)); // Add padding around the bottom box
    
        // VBox layout for the popup
        VBox popupVBox = new VBox(20, imageViewBox, billLabel,serviceNameLabel ,servicePriceLabel,feelLabel,theSubWalletBalanceLabel,bottomBox);
        popupVBox.setAlignment(Pos.CENTER);
        popupVBox.setPadding(new Insets(30, 20, 30, 20)); // Add padding to the VBox
        popupVBox.setSpacing(20); // Ensure spacing between each element
        popupVBox.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px;-fx-background-radius:20px;-fx-border-radius:20px;");
        popupVBox.setMargin(imageViewBox, new Insets(20, 0, 20, 160));
        popupVBox.setMargin(billLabel, new Insets(10, 0, 20, 20));
        popupVBox.setMargin(serviceNameLabel, new Insets(10, 0, 20, 20));
        popupVBox.setMargin(servicePriceLabel, new Insets(10, 0, 20, 20));
        popupVBox.setMargin(feelLabel, new Insets(10, 10, 20, 20));
        popupVBox.setMargin(theSubWalletBalanceLabel, new Insets(10, 0, 100, 20));
 
        Scene popupScene = new Scene(popupVBox, 600, 900);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

}

