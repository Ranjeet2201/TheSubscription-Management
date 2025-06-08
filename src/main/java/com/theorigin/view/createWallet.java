package main.java.com.theorigin.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import main.java.com.theorigin.controller.WalletController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class createWallet extends Application {

    int user_id;
    String email;

    public static void main(String[] args) {
        launch(args);
    }

    public createWallet(int user_id,String email){
        System.out.println("Userid in CreateWallet"+user_id);
        this.user_id = user_id;
        this.email = email;
    }

    @Override
    public void start(Stage primaryStage) {
        ImageView pancardImgView = new ImageView(new Image("file:D:/TheSubscription/TheOrigin/thesubscription/src/assets/images/pancard.png"));
        pancardImgView.setFitHeight(100);
        pancardImgView.setFitWidth(100);
        pancardImgView.setPreserveRatio(true);

        Label headLabel = createLabel("Enter KYC details");
        TextField nameField = createTextField("Enter Name as per PAN");
        TextField phoneField = createTextField("Phone Number");
        TextFormatter<Integer> phoneFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) { // Allows only numeric input
            return change;
        }
        return null;
        });
        phoneField.setTextFormatter(phoneFormatter);
        TextField panNumField = createTextField("PAN NO.");
        
        DatePicker datePicker = createDatePicker();
        datePicker.setPrefSize(350, 45);
        datePicker.setStyle("-fx-border-color: black; -fx-border-width: 2px");

        CheckBox agreeCheckBox = new CheckBox("I accept privacy policy and product's T&C's");

        Button submitButton = createButton("Submit");
        submitButton.setPrefSize(650, 45);
        submitButton.setDisable(true); // Initially disable the button

        submitButton.setOnAction(e -> {
            // Retrieve values from text fields and date picker
            String panName = nameField.getText();
            String panNum = panNumField.getText();
            String birthDate = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
            
            // Validate and parse mobile number
            int mobileNum = 0;
            try {
                mobileNum = Integer.parseInt(phoneField.getText());
                System.out.println("Correct phone number format.");
            } catch (NumberFormatException ex) {
                // Handle the exception, e.g., show an error message or log the error
                System.out.println("Invalid phone number format.");
                return; // Exit if parsing fails
            }
        
            // Call the walletCreator method with these values
            new WalletController(email).walletCreator(primaryStage, user_id, panName, mobileNum, panNum, birthDate);
        });
        

        // Add listeners to enable the submit button when all fields are filled and checkbox is selected
        nameField.textProperty().addListener((observable, oldValue, newValue) -> validateForm(nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton));
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> validateForm(nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton));
        panNumField.textProperty().addListener((observable, oldValue, newValue) -> validateForm(nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton));
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> validateForm(nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton));
        agreeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> validateForm(nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton));

        VBox kycFormBox = createVBox();
        kycFormBox.getChildren().addAll(headLabel, pancardImgView, nameField, phoneField, panNumField, datePicker, agreeCheckBox, submitButton);
        VBox.setMargin(submitButton, new Insets(20, 5, 0, 5));

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(kycFormBox);

        Scene sc = new Scene(root, 1300, 650);
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    Label createLabel(String str) {
        Label label = new Label(str);
        label.setStyle("-fx-font-size:45px;-fx-font-weight:bold;-fx-text-fill:black;");
        label.setMinSize(450, 45);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    TextField createTextField(String str) {
        TextField textField = new TextField();
        textField.setPromptText(str);
        textField.setPrefSize(650, 45);
        textField.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 25px; -fx-border-radius: 25px;");
        return textField;
    }

    DatePicker createDatePicker() {
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date of Birth"); // Set placeholder for DatePicker
        return datePicker;
    }

    Button createButton(String str) {
        Button button = new Button(str);
        button.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color: #0078D7; -fx-background-radius: 25px;");
        return button;
    }

    VBox createVBox() {
        VBox vbox = new VBox(20); 
        vbox.setMaxSize(700, 800); 
        vbox.setPrefSize(700, 800); 
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-padding: 20px; -fx-background-radius: 35px; -fx-border-radius: 35px;");  
        vbox.setAlignment(Pos.CENTER); 
        return vbox;
    }

    boolean isFormFilled(TextField nameField, TextField phoneField, TextField panNumField, DatePicker datePicker, CheckBox agreeCheckBox) {
        return !nameField.getText().trim().isEmpty() &&
               !phoneField.getText().trim().isEmpty() &&
               !panNumField.getText().trim().isEmpty() &&
               datePicker.getValue() != null &&
               agreeCheckBox.isSelected();
    }

    void validateForm(TextField nameField, TextField phoneField, TextField panNumField, DatePicker datePicker, CheckBox agreeCheckBox, Button submitButton) {
        boolean isFormValid = isFormFilled(nameField, phoneField, panNumField, datePicker, agreeCheckBox);
        submitButton.setDisable(!isFormValid);
    }
}
