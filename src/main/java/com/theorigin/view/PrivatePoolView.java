package main.java.com.theorigin.view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.Pool;
import main.java.com.theorigin.dao.PrivatePool;
import main.java.com.theorigin.dao.PrivatePoolDao;

public class PrivatePoolView {

    int user_id;
    String email;
    Stage myStage;
    PrivatePoolView(int user_id,Stage myStage,String email){
        this.user_id = user_id;
        this.myStage = myStage;
        this.email = email;
    }
    

    
    // Function to return a private scene with a PasswordField and a submit button
    public Scene privateScene() {


        VBox layout = new VBox(20); // Layout with vertical gap
        layout.setAlignment(Pos.CENTER); // Center all elements
        layout.setStyle("-fx-background-color: white;"); // Set background color
        layout.setPadding(new Insets(20, 0, 0, 0)); // Add padding to the top
        
        // Load login page image
        Image loginPageImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png");
        ImageView loginPageImageView = new ImageView(loginPageImage);
        loginPageImageView.setFitHeight(200);
        loginPageImageView.setFitWidth(200);

        // Circular clip for the image
        Rectangle clip1 = new Rectangle(loginPageImageView.getFitWidth(), loginPageImageView.getFitHeight());
        clip1.setArcWidth(200);
        clip1.setArcHeight(200);
        loginPageImageView.setClip(clip1);

        // Optional: Adding a circular border
        Circle circleBorder1 = new Circle(100); // Radius
        circleBorder1.setStroke(Color.BLACK); // Border color
        circleBorder1.setFill(Color.TRANSPARENT); // No fill, just border
        circleBorder1.setStrokeWidth(5); // Border thickness

        StackPane loginImagePane = new StackPane(loginPageImageView, circleBorder1);
        loginImagePane.setMaxSize(200, 200);
        loginImagePane.setStyle("-fx-background-radius: 100px; -fx-background-color: white;");

        // Ensure the StackPane itself is circular
        loginImagePane.setShape(new Circle(100)); // Radius of 100 (same as background radius)

        // Password field for pool key
        PasswordField poolKey = new PasswordField();
        poolKey.setPromptText("Enter Pool Key");
        poolKey.setMaxWidth(200); // Set max width
        poolKey.setStyle("-fx-font-size: 14px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setMaxWidth(200); // Set max width
        submitButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: BLACK; -fx-text-fill: white; -fx-border-radius: 5px;");

        // Set action for submit button
        submitButton.setOnAction(e -> {
            String poolId = poolKey.getText();
            int finalPoolId = Integer.parseInt(poolId);
            
            Boolean poolExists = new PrivatePoolDao().doesPoolExist(finalPoolId);
            System.out.println(poolExists);
            if (poolExists) {

                // Once submit is clicked, load the scrollpane with private pools
                ScrollPane privateScrollPane = new ScrollPane(createPrivateBuyPools("BUY",finalPoolId));
                Scene privateScene = new Scene(privateScrollPane);
                System.out.println("Till Here Private ScrollPane woks");
                myStage.setScene(privateScene);
                myStage.show();
            } else {
                // Handle empty input
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter a valid pool key.");
                alert.showAndWait();
            }
        });

        // Add all elements to layout
        layout.getChildren().addAll(loginImagePane, poolKey, submitButton);

        // Create a scene of 1300x600px
        return new Scene(layout, 1300, 600);
    }

        
    public ScrollPane createPrivateBuyPools(String text,int poolId) {
        ScrollPane buyPoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold all pools
        poolMainVBox.setPadding(new Insets(10)); // Padding for the main VBox
        poolMainVBox.setMaxWidth(975); // 75% of 1300px
    
        // Fetch the pool data
        
         List<PrivatePool> pools = new PrivatePoolDao().fetchThisPrivatePools(poolId);
    
        for (PrivatePool pool : pools) {
    
            String poolType = pool.getPoolType();
            String poolState = pool.getPoolState();
            boolean isPrivate = pool.isPrivate();
            int hostId = pool.getHostId();
            int serviceId = pool.getServiceId();
            int currentMembers = pool.getCurrentMembers();
            int membersAllowed = pool.getMembersAllowed();

            if(currentMembers == membersAllowed){
                //new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, poolId);
                System.out.println("U r now goinf to veri state");
                new ExploreSideDao().movePoolToVerificationFromActive(poolId);
                new ExploreSideDao().updateCurrentStateToNewState(poolId, "Active", "Verification");
                
            }
            
            
            // Apply the filtering conditions
            if ((poolType.equals("Standard") && poolState.equals("Active") && isPrivate) ||
                (poolType.equals("Rental") && poolState.equals("Live") && isPrivate)) {
    
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
//Fetch Plan-Id for PlanName
                int planId = pool.getPlanId();
                String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
//Fetch PLan Amount
                int planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
                int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
//Fetch Service Name
                String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(serviceId);
                Label priceLabel = new Label(planName); // Assuming a hardcoded price for now
                priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
                Button buyButton = new Button(text);
                buyButton.setPrefWidth(150);
                buyButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
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
                vbox3.getChildren().addAll(priceLabel, buyButton, poolTypeLabel);
    
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
                if (user_id == poolUserId || currentMembers == membersAllowed || new ExploreSideDao().isUserMemberOfPool(user_id, poolId)) {
                    buyButton.setDisable(true);
                    buyButton.setStyle("-fx-background-color: red;" +
                    "-fx-text-fill: black; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 14px; " +
                    "-fx-background-radius: 15px; " +
                    "-fx-border-radius: 5px; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2px;");}    

                buyButton.setOnAction(e->{
                    new SubscriptionPlatforms(user_id,serviceId,email).BillPopUp(myStage,poolType,serviceName,planAmount,planDuration,user_id,serviceId,poolId,currentMembers,membersAllowed,email);
                    
                    
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
        buyPoolScrollPane = new ScrollPane(poolMainVBox);
        buyPoolScrollPane.setMaxWidth(975); // 75% of 1300px
        buyPoolScrollPane.setFitToWidth(true);
        return buyPoolScrollPane;
    }

}
