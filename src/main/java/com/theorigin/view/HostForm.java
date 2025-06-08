package main.java.com.theorigin.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.controller.HostSideController;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.service.EmailService;
import main.java.com.theorigin.view.Pools;

public class HostForm extends Application{

    int user_id;
    int service_id;
    private String serviceName;
    public static HBox subfinalLayout;
    static VBox logoVBox;
    static VBox mainVBox;
    static Scene hostFormScene;
    Button addToPoolButton;
    String nameOfUser;
    ComboBox<String> plansComboBox3;
    ComboBox<String> membersComboBox4;
    Map<String, Integer> planDetails;
    Boolean isPrivate;
    String poolType;
    String poolState;
    String email;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage myStage) throws Exception {
        myStage.setScene(initialize(myStage));
        myStage.setTitle("TheSubscription");
        myStage.setHeight(760);
        myStage.setWidth(1360);
        
        myStage.setMaximized(true);
        myStage.show();
        
    }
    public HostForm(int user_id,String btnText,String email){
        this.user_id = user_id;
        this.email = email;
        System.out.println("HostForm Constructor " + this.user_id);
        serviceName = btnText;
        
    }
    public Scene initialize(Stage myStage){
                Label title = new Label("THE HOST");
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

        Image backgroundImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(700);
        backgroundImageView.setFitWidth(700);
        VBox.setVgrow(backgroundImageView, Priority.ALWAYS);
        logoVBox = new VBox(backgroundImageView);
        logoVBox.setAlignment(Pos.CENTER);
        
           

        // Create Labels
        Label poolNameLabel = labels("Poolname");
        Label serviceLabel = labels("Service");
        Label planLabel = labels("Plan");
        Label membersLabel = labels("Members");

        TextField poolNameField = new TextField(serviceName + " Pool");
        poolNameField.setPrefWidth(400);
        poolNameField.textProperty().addListener((obs, oldText, newText) -> {
            boolean isPoolNameFilled = !newText.trim().isEmpty();
            addToPoolButton.setDisable(!isPoolNameFilled);
            
        });
        String poolNameText = poolNameField.getText();

        // Create ComboBoxes
        //ComboBox<String> serviceComboBox1 = ComboBox("Username", null, null, null, null);
        ComboBox<String> serviceComboBox2 = new ComboBox<>();
        serviceComboBox2.getItems().add(serviceName);
        plansComboBox3 = new ComboBox<>();
        membersComboBox4 = new ComboBox<>();


        //Business Login of Host Form
        service_id = new HostSideDao().fetchServiceId(serviceName);
        System.out.println(service_id);
        
        planDetails = new HostSideDao().fetchPlanDetails(service_id);
        System.out.println(planDetails.keySet());
        System.out.println(planDetails.values());

        // Populate plansComboBox3 and membersComboBox4
        plansComboBox3.getItems().addAll(planDetails.keySet());
        plansComboBox3.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateMembersComboBox();
        });
 
        //membersComboBox4.getItems().addAll(String.valueOf(planDetails.values()));

        VBox poolNameVBox = new VBox(10, poolNameLabel, poolNameField);
        poolNameVBox.setAlignment(Pos.CENTER);
        poolNameVBox.setMaxWidth(400);
        VBox serviceVBox = new VBox(10, serviceLabel,serviceComboBox2);
        serviceVBox.setAlignment(Pos.CENTER);
        VBox planVBox = new VBox(10, planLabel, plansComboBox3);
        planVBox.setAlignment(Pos.CENTER);
        VBox membersVBox = new VBox(10, membersLabel, membersComboBox4);
        membersVBox.setAlignment(Pos.CENTER);

        Label termsLabel = new Label("Terms and conditions... (Add your content here)");
        // Create VBox for terms and conditions
        VBox termsVBox = new VBox(10,termsLabel);
        termsVBox.setPadding(new Insets(10));
        termsVBox.setMaxWidth(350);

        
        CheckBox rentalCheckBox = new CheckBox("Rental");
        CheckBox standardCheckBox = new CheckBox("Standard");

        rentalCheckBox.setOnAction(e -> {
            if (rentalCheckBox.isSelected()){
                standardCheckBox.setSelected(false);
                termsLabel.setText("Rentals are limited to a specific time frame.\n" + 
                                        "No ownership rights are transferred with rentals.\n" + 
                                        "Rental access cannot be shared or resold.\n" +
                                        "Misuse may lead to immediate termination without refund.\n" +
                                        "Full payment is required upfront; no early termination refunds.");
                addToPoolButton.setText("Verification");
                addToPoolButton.setDisable(true);
            }
        });

        standardCheckBox.setOnAction(e -> {
            if (standardCheckBox.isSelected()){
                rentalCheckBox.setSelected(false);

                addToPoolButton.setText("Add Plan");
                addToPoolButton.setDisable(true);
            }
        });

        CheckBox privateCheckBox = new CheckBox("Private");
        CheckBox publicCheckBox = new CheckBox("Public");

        privateCheckBox.setOnAction(e -> {
            if(privateCheckBox.isSelected()){
                publicCheckBox.setSelected(false);
                showAlert("Private Pool", "Private pools are only visible to selected members...");
                addToPoolButton.setDisable(false);
            } else {
                addToPoolButton.setDisable(true);
            }
        });
        
        publicCheckBox.setOnAction(e -> {
            if(publicCheckBox.isSelected()){
                privateCheckBox.setSelected(false);
                showAlert("Public Pool", "Public pools are visible to all members...");
                addToPoolButton.setDisable(false);
            } else {
                addToPoolButton.setDisable(true);
            }
        });

                // Create Button and disable it initially
                addToPoolButton = Pools.createPoolButton("Add Plan");
                addToPoolButton.setDisable(true);
                addToPoolButton.setOnAction(e -> {
                    String planName = plansComboBox3.getValue();
                    int membersAllowed = Integer.parseInt(updateMembersComboBox());

                    System.out.println("HostForm  "+user_id);
                    String poolName = poolNameField.getText().trim();
                    if (!poolName.isEmpty()) {
                        // Add to pool logic here
                        System.out.println("Pool Name: " + poolName);
                    } else {
                        System.out.println("Please fill in the Pool Name.");
                    }                    

                    if(publicCheckBox.isSelected()){
                        isPrivate = false;
                    }
                    if(privateCheckBox.isSelected()){
                        isPrivate = true;
                    }
                    if(rentalCheckBox.isSelected()){
                        poolType = "Rental";
                        poolState = "Verification";
                        System.out.println("***************");
                        System.out.println(user_id);
                        
                        System.out.println(serviceName);
                        System.out.println(planName);
                        System.out.println(membersAllowed);
                        System.out.println(poolType);
                        System.out.println(poolState);
                        System.out.println(isPrivate);
                    }
                    if(standardCheckBox.isSelected()){
                        poolType="Standard";
                        poolState = "Active";
                        System.out.println(user_id);
                        System.out.println(serviceName);
                        System.out.println(planName);
                        System.out.println(membersAllowed);
                        System.out.println(poolType);
                        System.out.println(poolState);
                        System.out.println(isPrivate);
                    
                        new HostSideController().navigateToHostFormData(user_id,poolName,serviceName,planName,membersAllowed,poolType,poolState,isPrivate);
                        //new EmailService().sendEmail(email, "You are a Host!", "Thank you for being a Host,you will notified as members get added and once the pool the pool is complete you will notified to do verification.");
                    }
        

                    //new HostSideController().navigateToHostFormData(user_id,poolName,serviceName,planName,membersAllowed,poolType,poolState,isPrivate);
                    
                });
        
        

        // Place termsVBox inside a ScrollPane
        ScrollPane termsScrollPane = new ScrollPane(termsVBox);
        termsScrollPane.setPrefHeight(150); // Adjust height as needed
        //termsScrollPane.setPrefWidth(400);
        termsScrollPane.setFitToWidth(true);

        VBox termsWrapperVbox = new VBox(termsScrollPane);
        termsWrapperVbox.setAlignment(Pos.CENTER);
        termsWrapperVbox.setPadding(new Insets(5));
        

        
        
        HBox poolTypeVBox = new HBox(10, rentalCheckBox, standardCheckBox);
        poolTypeVBox.setAlignment(Pos.CENTER);
        
        HBox visibilityVBox = new HBox(10, privateCheckBox, publicCheckBox);
        visibilityVBox.setAlignment(Pos.CENTER);        


        // Create VBox for main layout
        mainVBox = new VBox(5, poolNameVBox,serviceVBox,planVBox,membersVBox, poolTypeVBox, termsWrapperVbox, visibilityVBox, addToPoolButton);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setStyle("-fx-border-color: black; " +
                  "-fx-border-width: 5px; " +
                  "-fx-border-radius: 15px; " +
                  "-fx-background-radius: 15px; " +
                  "-fx-background-color: white;");

        mainVBox.setMaxWidth(400);

        subfinalLayout = new HBox(20, logoVBox, mainVBox); // Adjust spacing as needed
        subfinalLayout.setAlignment(Pos.CENTER);
        subfinalLayout.setPadding(new Insets(5));

        VBox finalLayout = new VBox(10,titleBox1,subfinalLayout);
        finalLayout.setMaxHeight(650);
        mainVBox.setMaxWidth(Double.MAX_VALUE);
        logoVBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(mainVBox, Priority.ALWAYS);
        HBox.setHgrow(logoVBox, Priority.ALWAYS);
        
        hostFormScene = new Scene(finalLayout);
        return hostFormScene;

    }

    private String updateMembersComboBox() {
        // Get the selected plan
        String selectedPlan = plansComboBox3.getValue();
        
        // Clear membersComboBox4
        membersComboBox4.getItems().clear();

        // If a plan is selected, update membersComboBox4 with the corresponding value
        if (selectedPlan != null && planDetails.containsKey(selectedPlan)) {
            int membersAllowed = planDetails.get(selectedPlan);
            membersComboBox4.getItems().add(String.valueOf(membersAllowed));
            
            // Return the newly added item (assumes only one item is added)
            return String.valueOf(membersAllowed);
        }
        return "0";
    }

    static Label labels(String lbl){
        Label labels = new Label(lbl);
        labels.setPrefWidth(100);
        labels.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        return labels;
    }
    static ComboBox ComboBox(String ele1,String ele2,String ele3,String ele4,String ele5){
        ComboBox<String> serviceComboBox = new ComboBox<>();
        serviceComboBox.getItems().addAll(ele1, ele2, ele3, ele4, ele5);
        serviceComboBox.setPrefWidth(400);
        return serviceComboBox;
    }

    private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
    
}
