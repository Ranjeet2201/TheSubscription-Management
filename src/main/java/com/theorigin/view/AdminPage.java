
package main.java.com.theorigin.view;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.com.theorigin.dao.AdminDao;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.Pool;
import main.java.com.theorigin.dao.VerificationPool;
import main.java.com.theorigin.dao.WalletDao;

public class AdminPage extends Application {

    Stage myStage;
    //AdminDao adminDaoObj = new AdminDao();
    private void initializeDaos(){
        
    }

    // Style for buttons
    private final String buttonStyle = "-fx-pref-width: 150px; -fx-pref-height: 40px; "
                                     + "-fx-font-size: 16px; -fx-padding: 5px; "
                                     + "-fx-border-radius: 5px; -fx-border-color: #ccc; "
                                     + "-fx-background-color: black; -fx-text-fill: white;";

    @Override
    public void start(Stage myStage) {
        initializeDaos();
        // Top HBox with 8 specific buttons
        HBox topHBox = new HBox(10); // spacing of 10 between buttons
        topHBox.setPadding(new Insets(10, 10, 10, 10)); // padding around the HBox

        Button topButton1 = new Button("Dashboard");
        Button topButton2 = new Button("Pools");
        Button topButton3 = new Button("Settings");
        Button topButton4 = new Button("Users");
        Button topButton5 = new Button("Notifications");
        Button topButton6 = new Button("Host");
        Button topButton7 = new Button("Service");
        Button topButton8 = new Button("About");

        topButton1.setStyle(buttonStyle);
        topButton2.setStyle(buttonStyle);
        topButton3.setStyle(buttonStyle);
        topButton4.setStyle(buttonStyle);
        topButton5.setStyle(buttonStyle);
        topButton6.setStyle(buttonStyle);
        topButton7.setStyle(buttonStyle);
        topButton8.setStyle(buttonStyle);

        topHBox.getChildren().addAll(topButton1, topButton2, topButton3, topButton4, topButton5, topButton6, topButton7, topButton8);
        topHBox.setStyle("-fx-background-color: white;");

        // Left VBox with 12 specific buttons
        VBox leftVBox = new VBox(10); // spacing of 10 between buttons
        leftVBox.setPadding(new Insets(10, 10, 10, 10)); // padding around the VBox
        leftVBox.setStyle("-fx-background-color: white;");

        Button leftButton1 = new Button("Verification");
        Button leftButton2 = new Button("Subscriptions");
        Button leftButton3 = new Button("Payments");
        Button leftButton4 = new Button("Feedback");
        Button leftButton5 = new Button("Security");
        Button leftButton6 = new Button("Logs");
        Button leftButton7 = new Button("Admin Tools");
        Button leftButton8 = new Button("Support");
        Button leftButton9 = new Button("Debarred");
        Button leftButton10 = new Button("Analytics");
        Button leftButton11 = new Button("Refund");
        Button leftButton12 = new Button("Report");

        leftButton1.setStyle(buttonStyle);
        leftButton2.setStyle(buttonStyle);
        leftButton3.setStyle(buttonStyle);
        leftButton4.setStyle(buttonStyle);
        leftButton5.setStyle(buttonStyle);
        leftButton6.setStyle(buttonStyle);
        leftButton7.setStyle(buttonStyle);
        leftButton8.setStyle(buttonStyle);
        leftButton9.setStyle(buttonStyle);
        leftButton10.setStyle(buttonStyle);
        leftButton11.setStyle(buttonStyle);
        leftButton12.setStyle(buttonStyle);

        leftVBox.getChildren().addAll(leftButton1, leftButton2, leftButton3, leftButton4, leftButton5, leftButton6, leftButton7, leftButton8, leftButton9, leftButton10, leftButton11, leftButton12);

        // Center Pane (where scenes will be switched)
        StackPane centerPane = new StackPane();
        VBox mainDashboardVBox = createDashboardView(); // Create the dashboard view
        centerPane.getChildren().add(mainDashboardVBox); // Default view is the Dashboard

        centerPane.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 2px;");

        ScrollPane centerScrollPane = new ScrollPane(centerPane);


        // BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topHBox);
        borderPane.setLeft(leftVBox);
        borderPane.setCenter(centerScrollPane);

        // Set button actions for left buttons to change the center pane
        leftButton1.setOnAction(e -> {
            centerPane.getChildren().clear();
            AdminDao adminDaoObj = new AdminDao();
            ScrollPane scrollPane = createAllVerificationPools("Verify");
            centerPane.getChildren().add(scrollPane);
        });

        leftButton2.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Subscriptions Page View"));
        });

        leftButton3.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Payments Page View"));
        });

        leftButton4.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Feedback Page View"));
        });

        leftButton5.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Security Page View"));
        });

        leftButton6.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Logs Page View"));
        });

        leftButton7.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Admin Tools Page View"));
        });

        leftButton8.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Support Page View"));
        });

        leftButton9.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Debarred Page View"));
        });

        leftButton10.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Analytics Page View"));
        });

        leftButton11.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Refund Page View"));
        });

        leftButton12.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Report Page View"));
        });

        // Set button actions for top buttons to change the center pane
        topButton1.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(mainDashboardVBox); // Show the dashboard
        });

        topButton2.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Pools View"));
        });

        topButton3.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Settings View"));
        });

        topButton4.setOnAction(e -> {
            centerPane.getChildren().clear();
            
            centerPane.getChildren().add(new Button("Users View"));
        });

        topButton5.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Notifications View"));
        });

        topButton6.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Host View"));
        });

        topButton7.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("Service View"));
        });

        topButton8.setOnAction(e -> {
            centerPane.getChildren().clear();
            centerPane.getChildren().add(new Button("About View"));
        });

        // Create the scene and set the stage
        Scene scene = new Scene(borderPane, 1300, 700);
        myStage.setTitle("Admin Page");
        myStage.setScene(scene);
        myStage.show();
    }

    // Method to create the dashboard view
    private VBox createDashboardView() {
        VBox mainDashboardVBox = new VBox(20); // spacing of 20 between large HBoxes
        mainDashboardVBox.setPadding(new Insets(20));
        mainDashboardVBox.setAlignment(Pos.CENTER);

        int buttonWidth = 150; // Width of buttons
        int buttonHeight = 40; // Height of buttons

        for (int i = 0; i < 5; i++) { // 5 large HBoxes for rows
            HBox largeHBox = new HBox(10); // spacing of 10 between small HBoxes
            largeHBox.setAlignment(Pos.CENTER);
            largeHBox.setPadding(new Insets(10, 0, 10, 0)); // Padding to add space around the HBox

            for (int j = 0; j < 5; j++) { // 5 small HBoxes per row
                HBox smallHBox = createSmallDashboardHBox("Daily\nActive\nUsers", (1000 + (i * 5) + j) + "", buttonWidth, buttonHeight);
                smallHBox.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-radius: 5px; -fx-border-color: #ccc;");
                largeHBox.getChildren().add(smallHBox);
            }

            mainDashboardVBox.getChildren().add(largeHBox);
        }

        return mainDashboardVBox;
    }

    // Method to create a small HBox with a title and a number
    private HBox createSmallDashboardHBox(String title, String number, int buttonWidth, int buttonHeight) {
        HBox smallHBox = new HBox();
        smallHBox.setAlignment(Pos.CENTER);
        smallHBox.setPadding(new Insets(10));
        smallHBox.setSpacing(10);
        smallHBox.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        smallHBox.setPrefWidth(buttonWidth); // Set width to match the buttons

        VBox textVBox = new VBox();
        textVBox.setAlignment(Pos.CENTER);
        textVBox.setPadding(new Insets(10));

        Text titleText = new Text(title);
        titleText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: white;");

        Text numberText = new Text(number);
        numberText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: white;");

        textVBox.getChildren().addAll(titleText, numberText);

        smallHBox.getChildren().add(textVBox);

        return smallHBox;
    }


    public ScrollPane createAllVerificationPools(String text) {
        ScrollPane allVerificationPoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold all pools
        poolMainVBox.setPadding(new Insets(10)); // Padding for the main VBox
        poolMainVBox.setMaxWidth(975); // 75% of 1300px
    
        // Fetch the pool data
        
         List<VerificationPool> pools = new AdminDao().fetchAllPoolsInVerificationState();
    
        for (VerificationPool pool : pools) {
    
            String poolType = pool.getPoolType();
            String poolState = pool.getPoolState();
            boolean isPrivate = pool.isPrivate();
            int poolId = pool.getPoolId();
            int currentMembers = pool.getCurrentMembers();
            int membersAllowed = pool.getMembersAllowed();
            int serviceId = pool.getServiceId();

//Fetch Plan-Id for PlanName
            int planId = pool.getPlanId();
            String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
//Fetch PLan Amount
            int planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
            int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
//Fetch Service Name
            String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(serviceId);

            

            if(currentMembers == membersAllowed){
                //new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, poolId);
                new ExploreSideDao().movePoolToVerificationFromActive(poolId);
                new ExploreSideDao().updateCurrentStateToNewState(poolId, "Active", "Verification");
                
            }
            
            // Apply the filtering conditions
            if ((poolType.equals("Standard") && poolState.equals("Verification")) ||
                (poolType.equals("Rental") && poolState.equals("Verification"))) {
    
                // VBox1: Contains the pool image
                VBox vbox1 = new VBox(10);
                vbox1.setPadding(new Insets(5));
                vbox1.setMinWidth(190);
                HBox.setHgrow(vbox1, Priority.ALWAYS);
    
                ImageView serviceImgView = new ImageView();
                new ExploreSideDao().loadServiceImage(serviceId, serviceImgView); // Load service image
                Circle clip = new Circle(50);
                clip.setCenterX(50);
                clip.setCenterY(50);
                serviceImgView.setClip(clip);
                serviceImgView.setFitWidth(100);
                serviceImgView.setFitHeight(100);
                vbox1.getChildren().add(serviceImgView);
    
                // VBox2: Contains pool name, host rating, and member count
                VBox vbox2 = new VBox(20);
                vbox2.setPadding(new Insets(5));
                vbox2.setMinWidth(190);
                HBox.setHgrow(vbox2, Priority.ALWAYS);
                Label poolName = new Label(pool.getPoolName());
                poolName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label hostRating = new Label("Host Rating: 4.5⭐(342)"); // Assuming a hardcoded rating for now
                hostRating.setStyle("-fx-font-size: 14px;");
                Label memberCount = new Label("🙋‍♂️ " +"("+ currentMembers + "/" + membersAllowed + ")");
                memberCount.setStyle("-fx-font-size: 14px;");
                vbox2.getChildren().addAll(poolName, hostRating, memberCount);
    
                // VBox3: Contains price label and Buy button
                VBox vbox3 = new VBox(20);
                vbox3.setPadding(new Insets(5));
                vbox3.setMinWidth(190);
                HBox.setHgrow(vbox3, Priority.ALWAYS);
                Label priceLabel = new Label(planName); // Assuming a hardcoded price for now
                priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
                Button verifyButton = new Button(text);
                verifyButton.setPrefWidth(150);
                verifyButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                        "-fx-text-fill: black; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
                Label privatePoolLabel = new Label("Public");
                if(pool.isPrivate()){
                    privatePoolLabel = new Label("Private");
                }
                Label poolTypeLabel = new Label(pool.getPoolType() + " " + privatePoolLabel.getText() + "  Pool");
                poolTypeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-font-weight: bold;");
                vbox3.getChildren().addAll(priceLabel, verifyButton, poolTypeLabel);
    
                // VBox4: Contains host image (hardcoded for now)
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(190);
                HBox.setHgrow(vbox4, Priority.ALWAYS);
    
                int poolHostId = pool.getHostId();
                int poolUserId = new ExploreSideDao().fetchUserIdFromHostId(poolHostId);
                
                ImageView hostImgView = new ImageView();
                new ExploreSideDao().loadUserImage(poolUserId, hostImgView); // Load service image
                Circle hostClip = new Circle(50);
                hostClip.setCenterX(50);
                hostClip.setCenterY(50);
                hostImgView.setClip(hostClip);
                hostImgView.setFitWidth(100);
                hostImgView.setFitHeight(100);
                vbox4.getChildren().add(hostImgView);
    
                // HBox1: Combine VBox1, VBox2, VBox3, VBox4
                HBox hbox1 = new HBox(20);
                hbox1.setPadding(new Insets(10, 10, 10, 40));
                hbox1.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
    
    
                // HBox2: Contains the progress bar
                HBox hbox2 = new HBox(10); // Added spacing between progress bar and label
                hbox2.setPadding(new Insets(5, 5, 5, 5));

                // Create and configure the progress bar
                ProgressBar poolProgressBar = new ProgressBar((double) currentMembers / membersAllowed);
                poolProgressBar.setPrefWidth(975); // Set a preferred width for the progress bar
                HBox.setHgrow(poolProgressBar, Priority.ALWAYS);
                // Add both components to the HBox
                hbox2.getChildren().addAll(poolProgressBar);

                verifyButton.setOnAction(e->{
                    VerificationPopup(poolId,planId,membersAllowed,poolUserId);
                    
                });


                // Combine HBox1 and HBox2 into a single VBox
                VBox onePoolVbox = new VBox(10, hbox1, hbox2);
                onePoolVbox.setMaxWidth(975); // 75% of 1300px
                onePoolVbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        new CornerRadii(10), new BorderWidths(2), null)));
                onePoolVbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                poolMainVBox.getChildren().add(onePoolVbox);
                
            }
        }
    
        // ScrollPane to make the content scrollable
        allVerificationPoolScrollPane = new ScrollPane(poolMainVBox);
        allVerificationPoolScrollPane.setMaxWidth(975); // 75% of 1300px
        allVerificationPoolScrollPane.setFitToWidth(true);
        return allVerificationPoolScrollPane;
    }

    private void VerificationPopup(int pool_id,int planId,int membersAllowed,int user_id) {
        Stage verificaitonStage = new Stage();
        Scene verificationScene;
        // Create an ImageView with an example image
        ImageView verificationImgView = new ImageView();
        new AdminDao().loadVerificationImage(pool_id, verificationImgView); // Load service image
        
        //vbox1.getChildren().add(verificationImgView);


//Fetch PLan Amount
        int planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
        int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
        int amountToBePaidToHost = (membersAllowed - 1) * planAmount;

        // Create buttons
        Button approveButton = new Button("Approve");

        approveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        approveButton.setOnAction(e->{
            new ExploreSideDao().movePoolToLiveFromVerification(pool_id, planDuration);
            new ExploreSideDao().updateCurrentStateToNewState(pool_id, "Verification", "Live");
            new HostSideDao().poolLiveTimerStart(pool_id);
            
            verificaitonStage.close();

        });
        Button declineButton = new Button("Decline");
        declineButton.setOnAction(e->{
            verificaitonStage.close();
        });
        declineButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        // Create HBox for buttons
        HBox buttonBox = new HBox(10, approveButton, declineButton);

        // Create VBox for the whole scene
        VBox vbox = new VBox(10,verificationImgView, buttonBox);
        verificationScene = new Scene(vbox,400,300);

        verificaitonStage.setScene(verificationScene);
        verificaitonStage.show();
        verificaitonStage.showAndWait();
    }




    public static void main(String[] args) {
        launch(args);
    }
}


