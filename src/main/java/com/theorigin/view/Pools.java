package main.java.com.theorigin.view;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import main.java.com.theorigin.controller.HostSideController;
import main.java.com.theorigin.dao.ActivePool;
import main.java.com.theorigin.dao.AdminDao;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.Pool;
import main.java.com.theorigin.dao.VerificationPool;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.dao.LivePool;
import main.java.com.theorigin.dao.MergerDao;
import main.java.com.theorigin.dao.MergerPoolList;
import main.java.com.theorigin.dao.MergerPoolRequest;

public class Pools extends Application {

    HostSideDao hostSideDaoObj = new HostSideDao();
    static Stage availableMergerPoolStage;
    public static Button oneBtnPoolButton;
    private Scene sc;
    static Stage myStage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        // Scene sc = new Scene(hostHistoryTabs(),1500,980);
        // primaryStage.setScene(sc);
        // primaryStage.show();
    }
    
    private static Circle createPoolImage(Image pooImage){
        Circle img=new Circle(50);
        img.setFill(new ImagePattern(pooImage));

        return img;
    }
    private static Label createPoolLabel(String poolName){
        Label poolNameLabel=new Label(poolName);
        poolNameLabel.setMinSize(400, 80);
        poolNameLabel.setStyle("-fx-font-size:45px;-fx-text-fill:black;-fx-font-weight:bold;"); 
        return poolNameLabel;
    }
    private static Label remainingDay(int days){
        Label remaingDayLabel=new Label("Pool will expire in : "+days+" days");
        remaingDayLabel.setMinSize(350, 50);
        remaingDayLabel.setStyle("-fx-font-size:25px;-fx-text-fill:red;-fx-font-weight:bold;");
        return remaingDayLabel;
    }
    private static ProgressBar createMemberCountProgressBar(){
        ProgressBar progressBar=new ProgressBar(20);
        progressBar.setStyle(" -fx-border-color: black; -fx-border-width: 2; -fx-accent: red;-fx-border-radius: 5;");
        progressBar.setMinSize(400, 40);
        return progressBar;
    }
    private static ProgressBar createRemainingDaysProgressBar(){
        ProgressBar progressBar=new ProgressBar(20);
        progressBar.setStyle(" -fx-border-color: black; -fx-border-width: 2;-fx-border-radius: 5;");
        progressBar.setMinSize(400, 40);
        return progressBar;
    }
    private static VBox createLabelProgessBox(ProgressBar pb,Label label){
        VBox vBox=new VBox(20,pb,label);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setMinSize(400, 150);
        return vBox;
    }
    
    //Using this in Host form also.
    public static Button createPoolButton(String text){
        Button chatButton=new Button(text);
        chatButton.setMinWidth(150);
        chatButton.setPrefHeight(25);

        if(text == "Accept"){
            chatButton.setStyle("-fx-background-color: linear-gradient(#46923c,#46923c); -fx-text-fill: black; -fx-font-weight: bold; " +
            "-fx-font-size: 16px;-fx-background-radius: 15px; -fx-border-radius: 5px; -fx-border-color: black;-fx-border-width: 5px;");
        }else if(text == "Decline"){
            chatButton.setStyle("-fx-background-color: linear-gradient(#de0a26,#de0a26); -fx-text-fill: black; -fx-font-weight: bold; " +
            "-fx-font-size: 16px;-fx-background-radius: 15px; -fx-border-radius: 5px; -fx-border-color: black;-fx-border-width: 5px;");
        }else{
            chatButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA); -fx-text-fill: black; -fx-font-weight: bold; " +
            "-fx-font-size: 16px;-fx-background-radius: 15px; -fx-border-radius: 5px; -fx-border-color: black;-fx-border-width: 5px;");
        }
        return chatButton;
    }
    
    private static HBox createPoolHBox(Circle img,Label poolName,VBox proLabelBox,Button chatButton){
        LinearGradient vboxGradient;
        vboxGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#17EAD9")),
                new Stop(1, Color.web("#6078EA")));

        HBox poolBox=new HBox(img,poolName,proLabelBox,chatButton);
        poolBox.setMinSize(700,200);
        poolBox.setAlignment(Pos.CENTER_LEFT);
        poolBox.setMargin(img, new Insets(0,100,0,50));
        poolBox.setMargin(chatButton, new Insets(10,5,10,30));
        poolBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px;");
        poolBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
        poolBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(10), new BorderWidths(10), null)));

        return poolBox;
    }

    public static VBox availablePoolsForMerger(String text){
        // Create the search bar (TextField) and set its preferred height
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        searchBar.setPrefHeight(100);

        //ScrollPane availableMergerPools = new ScrollPane(new Pools().createThisHostActivePools(text,1));
        ScrollPane availableMergerPools = new ScrollPane();
        VBox mainVBox = new VBox(10,searchBar,availableMergerPools);
        return mainVBox;
    }
    
    private static Alert alertPopup(String headerText,String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // alert.setTitle("Merger Request");
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.showAndWait();
        System.out.println("Merger Request Sent");
        // Scene sc = new Scene(HostPage.mergerMainPage(), 1360, 760);  // Corrected size to 1360x760
        // Stage newStage = new Stage();
        // newStage.setScene(sc);
        // newStage.show();        
        return alert;
    }

    public static  ScrollPane createThisHostLivePools(String text,int fetchedHostId,String email) {
        ScrollPane livePoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold HBox1 and HBox2
        poolMainVBox.setPadding(new Insets(10)); // Padding for main VBox
        poolMainVBox.setMaxWidth(700); // 75% of 1300px
        poolMainVBox.setMaxWidth(Double.MAX_VALUE);
 // Fetch the pool data        
        List<LivePool> pools = new HostSideDao().fetchThisHostLivePools(fetchedHostId);
    
        for (LivePool livePool : pools) {
    
            String poolType = livePool.getPoolType();
            String poolState = livePool.getPoolState();
            boolean isPrivate = livePool.isPrivate();
            int poolId = livePool.getPoolId();
            int currentMembers = livePool.getCurrentMembers();
            int membersAllowed = livePool.getMembersAllowed();
            int serviceId = livePool.getServiceId();
             
            // Apply the filtering conditions
            if ((poolState.equals("Live"))) {
                // VBox1: Contains the pool image
                VBox vbox1 = new VBox(10);
                vbox1.setPadding(new Insets(5));
                vbox1.setMinWidth(100);
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
                VBox vbox2 = new VBox(5);
                vbox2.setPadding(new Insets(5));
                vbox2.setMinWidth(100);
                HBox.setHgrow(vbox2, Priority.ALWAYS);
                Label poolName = new Label(livePool.getPoolName());
                poolName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label thisPoolId = new Label("Pool-id  "+String.valueOf(poolId)); // Assuming a hardcoded rating for now
                thisPoolId.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
                Label memberCount = new Label("🙋‍♂️   " +"("+ currentMembers + "/" + membersAllowed + ")");
                memberCount.setStyle("-fx-font-size: 14px;");
           
                vbox2.getChildren().addAll(poolName, thisPoolId, memberCount);

                // VBox3: Contains 2 HBoxes
                VBox vbox3 = new VBox(10);
                vbox3.setPadding(new Insets(5));
                vbox3.setMinWidth(400); // Set VBox width to 400px

                // HBox1: Contains one label and a progress bar
                VBox memberCountVbox = new VBox(10);
                memberCountVbox.setPadding(new Insets(5));
                Label label1 = new Label("Member Count");
                label1.setMinWidth(150);
                label1.setAlignment(Pos.CENTER_LEFT);
                label1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                label1.setMinWidth(50); // Set label width to 50px

                
                // Create and configure the progress bar
                ProgressBar progressBar1 = new ProgressBar((double) currentMembers / membersAllowed);
                progressBar1.setPrefWidth(350); // Set a preferred width for the progress bar
                memberCountVbox.getChildren().addAll(label1, progressBar1);

                // HBox2: Contains one label and a progress bar
                VBox daysVbox = new VBox(10);
                daysVbox.setPadding(new Insets(5));
                Label label2 = new Label(" Rmaining Day's");
                label2.setAlignment(Pos.CENTER_LEFT);
                label2.setMinWidth(150);
                label2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                label2.setMinWidth(50); // Set label width to 50px

                // Create and configure the progress bar
                ProgressBar poolProgressBar = new ProgressBar(0);  // Start progress at 0
                poolProgressBar.setPrefWidth(975); // Set a preferred width for the progress bar
                HBox.setHgrow(poolProgressBar, Priority.ALWAYS);

                // Total time for the progress bar to complete (5 minutes in milliseconds)
                double totalTime = 300000; // 5 minutes = 300,000 milliseconds
                long startTime = System.currentTimeMillis();

                // Create a Timeline to update the progress bar every second
                Timeline progressTimeline = new Timeline(
                    new KeyFrame(Duration.millis(1000), e -> {
                        // Calculate the elapsed time
                        double elapsedTime = (System.currentTimeMillis() - startTime);
                        // Update the progress bar based on elapsed time
                        poolProgressBar.setProgress(elapsedTime / totalTime);
                    })
                );

                // Set the duration of the timeline to match the timer (5 minutes)
                progressTimeline.setCycleCount((int) totalTime / 1000);  // Run for 300 seconds (5 minutes)
                progressTimeline.play();
                poolProgressBar.setPrefWidth(975); // Set a preferred width for the progress bar

                daysVbox.getChildren().addAll(label2, poolProgressBar);

                // Add HBoxes to VBox3
                vbox3.getChildren().addAll(memberCountVbox, daysVbox);

                // VBox4: Contains a button
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(100); // Set VBox width to 100px

                Button chatButton = new Button(text);
                chatButton.setPrefWidth(100); // Set button width to 100px
                chatButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                        "-fx-text-fill: black; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
                chatButton.setOnAction(e -> {
                    Scene chatScene = new Scene(new TheSubChat().initialize(myStage));
                    myStage.setScene(chatScene);
                    myStage.setHeight(760);
                    myStage.setWidth(1360);
                    myStage.show();     
                });
                

                // Add button to VBox4
                vbox4.getChildren().add(chatButton);


                // HBox1: Combine VBox1, VBox2, and VBox3
                HBox livePoolHbox = new HBox(10);
                livePoolHbox.setPadding(new Insets(10,10,10,40));
                livePoolHbox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);
                livePoolHbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                poolMainVBox.getChildren().addAll(livePoolHbox);
                livePoolHbox.setBorder((new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(10), new BorderWidths(2), null))));
            }

        }
        // ScrollPane to make the content scrollable
        livePoolScrollPane = new ScrollPane(poolMainVBox);
        livePoolScrollPane.setFitToWidth(true);
        return livePoolScrollPane;

    }

    
    public ScrollPane createThisHostActivePools(String text,int fetchedHostId,String email) {
        ScrollPane activePoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold all pools
        poolMainVBox.setPadding(new Insets(10)); // Padding for the main VBox
        poolMainVBox.setMaxWidth(975); // 75% of 1300px
    
        // Fetch the pool data
        
         List<ActivePool> pools = new HostSideDao().fetchThisHostActivePools(fetchedHostId);
         
    
        for (ActivePool activePool : pools) {
    
            String poolType = activePool.getPoolType1();
            String poolState = activePool.getPoolState1();
            boolean isPrivate = activePool.isPrivate1();
            int poolId = activePool.getPoolId1();
            int currentMembers = activePool.getCurrentMembers1();
            int membersAllowed = activePool.getMembersAllowed1();
            int serviceId = activePool.getServiceId1();

            
             
            // Apply the filtering conditions
            if (poolState.equals("Active") && (isPrivate == true || isPrivate == false)) {
    
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
                Label poolName = new Label(activePool.getPoolName1());
                poolName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label hostRating = new Label("Pool-id"+poolId); // Assuming a hardcoded rating for now
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
                int planId = activePool.getPlanId1();
                String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
//Fetch PLan Amount
                double planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
                int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
//Fetch Service Name
                String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(serviceId);
                Label priceLabel = new Label(planName); // Assuming a hardcoded price for now
                priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
                Button btn = new Button(text);
                btn.setPrefWidth(150);
                btn.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                        "-fx-text-fill: black; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
                Label privatePoolLabel = new Label("Public");
                if(activePool.isPrivate1()){
                    privatePoolLabel = new Label("Private");
                }
                Label poolTypeLabel = new Label(activePool.getPoolType1() + " " + privatePoolLabel.getText() + "  Pool");
                poolTypeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-font-weight: bold;");
                vbox3.getChildren().addAll(priceLabel, btn, poolTypeLabel);
    
                // VBox4: Contains host image (hardcoded for now)
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(190);
                HBox.setHgrow(vbox4, Priority.ALWAYS);
    
                int poolHostId = fetchedHostId;
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
                
                btn.setOnAction(e->{
                   
                    int requiredMembers = membersAllowed - currentMembers;
                    //VBox mergerOptionsVBox = new VBox(availablePoolsForMerger("SendRequest"));
                    ScrollPane mergerScrollPane = new ScrollPane(createThisPlanMergerPools("Request",planId,requiredMembers,fetchedHostId,poolId));
                    Scene mergerScene = new Scene(mergerScrollPane, 980, 700);
                    
                    
                    Stage mergerStage = new Stage();
                    mergerStage.setTitle("Merge Options");
                    mergerStage.setMinWidth(700);
                    mergerStage.setScene(mergerScene);
                    mergerStage.show();
                
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
        activePoolScrollPane = new ScrollPane(poolMainVBox);
        activePoolScrollPane.setMaxWidth(975); // 75% of 1300px
        activePoolScrollPane.setFitToWidth(true);
        return activePoolScrollPane;
    }
               


    
    public ScrollPane createThisHostVerificationPools(String text,int fetchedHostId,int user_id,String email) {

        ScrollPane verificationPoolScrollPane;
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
            int hostId = pool.getHostId();
            int currentMembers = pool.getCurrentMembers();
            int membersAllowed = pool.getMembersAllowed();
            int serviceId = pool.getServiceId();
            
            // Apply the filtering conditions
            if (hostId == fetchedHostId) {
    
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
                double planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
                int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
//Fetch Service Name
                String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(serviceId);
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
                    new HostPage(user_id,email).uploadVerificationImageButtonClicked(poolId);
                    
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
        verificationPoolScrollPane = new ScrollPane(poolMainVBox);
        verificationPoolScrollPane.setMaxWidth(975); // 75% of 1300px
        verificationPoolScrollPane.setFitToWidth(true);
        return verificationPoolScrollPane;
    }


    
    public static  ScrollPane createThisPlanMergerPools(String text,int fetchedPlanId,int requiredMembers,int mergeSenderHostId,int mergeSenderPoolId) {
        ScrollPane mergerPoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold HBox1 and HBox2
        poolMainVBox.setPadding(new Insets(10)); // Padding for main VBox
        poolMainVBox.setMaxWidth(700); // 75% of 1300px
        poolMainVBox.setMaxWidth(Double.MAX_VALUE);
 // Fetch the pool data        
        List<MergerPoolList> pools = new MergerDao().fetchThisPlanMergerPools(fetchedPlanId);
    
        for (MergerPoolList mergerPool : pools) {
    
            String poolType = mergerPool.getPoolType();
            String poolState = mergerPool.getPoolState();
            boolean isPrivate = mergerPool.isPrivate();
            int receiverPoolId = mergerPool.getPoolId();
            int currentMembers = mergerPool.getCurrentMembers();
            int membersAllowed = mergerPool.getMembersAllowed();
            int serviceId = mergerPool.getServiceId();
            int receiverHostId = mergerPool.getHostId();

            // Apply the filtering conditions
            if ( poolState.equals("Active") && currentMembers == requiredMembers && receiverHostId != mergeSenderHostId && !isPrivate) {

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
                Label poolName = new Label(mergerPool.getPoolName());
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
                
                String planName = new ExploreSideDao().fetchPlanNameFromPlanId(fetchedPlanId);
//Fetch PLan Amount
                double planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(fetchedPlanId);
//Fetch plan Duration
                int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(fetchedPlanId);
//Fetch Service Name
                String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(serviceId);
                Label priceLabel = new Label(planName); // Assuming a hardcoded price for now
                priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
                Button mergerButton = new Button(text);
                mergerButton.setPrefWidth(150);
                mergerButton.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                        "-fx-text-fill: black; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
                Label privatePoolLabel = new Label("Public");
                if(mergerPool.isPrivate()){
                    privatePoolLabel = new Label("Private");
                }
                Label poolTypeLabel = new Label(mergerPool.getPoolType() + " " + privatePoolLabel.getText() + "  Pool");
                poolTypeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-font-weight: bold;");
                vbox3.getChildren().addAll(priceLabel, mergerButton, poolTypeLabel);
    
                // VBox4: Contains host image (hardcoded for now)
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(190);
                HBox.setHgrow(vbox4, Priority.ALWAYS);
    
                int poolHostId = mergerPool.getHostId();
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
                
                mergerButton.setOnAction(e->{
                    new MergerDao().movePoolToMergerFromActive(receiverPoolId);
                    new ExploreSideDao().updateCurrentStateToNewState(receiverPoolId, "Active", "Merger");
                    new MergerDao().movePoolToMergerFromActive(mergeSenderPoolId);
                    new ExploreSideDao().updateCurrentStateToNewState(mergeSenderPoolId, "Active", "Merger");
                    System.out.println("Deletion from Active Done");

                    
                    new MergerDao().insertMergerPoolData(fetchedPlanId, mergeSenderPoolId, receiverPoolId, mergeSenderHostId, receiverHostId);
                    System.out.println("Insert to merger DOne");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Merger Rules");
                    alert.setHeaderText("Important Rules for Pool Merger");
                    alert.setContentText("1. Once merged, pools cannot be separated.\n" +
                                        "2. Only active pools are eligible for merging.\n" +
                                        "3. Both sender and receiver pools must have valid subscriptions.\n" +
                                        "4. Ensure proper communication with the other pool's host before proceeding.\n" +
                                        "5. Merging process is irreversible once confirmed.");

                    alert.showAndWait(); 
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
        mergerPoolScrollPane = new ScrollPane(poolMainVBox);
        mergerPoolScrollPane.setFitToWidth(true);
        return mergerPoolScrollPane;

    }


public static ScrollPane createMergerPoolsUI(ObservableList<MergerPoolRequest> requests, boolean isSent,String email) {
    ScrollPane activePoolScrollPane;
    VBox poolMainVBox = new VBox(20); // Main VBox to hold HBox1 and HBox2
    poolMainVBox.setPadding(new Insets(10)); // Padding for main VBox
    poolMainVBox.setMaxWidth(700); // 75% of 1300px
    poolMainVBox.setMaxWidth(Double.MAX_VALUE);

    for (MergerPoolRequest request : requests) {

        int planId = request.getPlanId();
        int senderPoolId = request.getSenderPoolId();
        int senderHostId = request.getSenderHostId();
        int receiverPoolId = request.getReceiverPoolId();
        int receiverHostId = request.getReceiverHostId();
        
        // VBox1: Contains the pool image
        VBox vbox1 = new VBox(10);
        vbox1.setPadding(new Insets(5));
        vbox1.setMinWidth(100);
        Image poolImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png");
        Circle poolImgCircle = new Circle(50);
        poolImgCircle.setFill(new javafx.scene.paint.ImagePattern(poolImg));
        vbox1.getChildren().add(poolImgCircle);

        // VBox2: Contains pool name, host rating, and member count
        VBox vbox2 = new VBox(5);
        vbox2.setPadding(new Insets(5));
        vbox2.setMinWidth(100);
        Label senderPoolIdLabel = new Label("S-Pool-id  "+String.valueOf(senderPoolId)); // Replace with actual pool name
        senderPoolIdLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
        Label senderHostIdLabel = new Label("S-Host-id  "+String.valueOf(senderHostId)); // Replace with actual host rating
        senderHostIdLabel.setStyle("-fx-font-size: 10px;-fx-font-weight: bold;");
        Label receiverPoolIdLabel = new Label("R-Pool-id  "+String.valueOf(receiverPoolId)); // Replace with actual member count
        receiverPoolIdLabel.setStyle("-fx-font-size: 10px;-fx-font-weight: bold;");
        Label receiverHostIdLabel = new Label("R-Host-id  "+String.valueOf(receiverHostId)); // Replace with actual member count
        receiverHostIdLabel.setStyle("-fx-font-size: 10px;-fx-font-weight: bold;");
        vbox2.getChildren().addAll(senderPoolIdLabel, senderHostIdLabel, receiverPoolIdLabel, receiverHostIdLabel);

        // VBox3: Contains 2 HBoxes
        VBox vbox3 = new VBox(10);
        vbox3.setPadding(new Insets(5));
        vbox3.setMinWidth(400); // Set VBox width to 400px

        // HBox1: Contains one label and a progress bar
        HBox hbox1 = new HBox(10);
        hbox1.setPadding(new Insets(5));
        Label label1 = new Label("Status: " + (isSent ? "Sent" : "Received")); // Example status
        label1.setMinWidth(50); // Set label width to 50px
        ProgressBar progressBar1 = new ProgressBar();
        progressBar1.setPrefWidth(350); // Set progress bar width to 350px
        hbox1.getChildren().addAll(label1, progressBar1);

        // HBox2: Contains one label and a progress bar (if needed, can be added similarly)


        String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
//Fetch PLan Amount
        double planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
        int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
//Fetch Service Name



        Button btn1 = new Button(isSent ? "Withdraw" : "Accept");
        Button btn2 = new Button(isSent ? "Approve" : "Decline");
        
        btn1.setPrefWidth(150); // Set button width to 100px
        btn1.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 14px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;");
        btn1.setOnAction(e -> {
            if (isSent) {
                alertPopup("Withdraw Merger Request", "Once you withdraw you can't send again!");
                new MergerDao().deleteMergerPoolEntry(planId, senderPoolId, receiverPoolId, senderHostId, receiverHostId);
                new HostSideDao().insertActivePoolsData(receiverPoolId);
                new HostSideDao().insertActivePoolsData(senderPoolId);
                new ExploreSideDao().updateCurrentStateToNewState(receiverPoolId, "Merger", "Active");
                new ExploreSideDao().updateCurrentStateToNewState(senderPoolId, "Merger", "Active");

            } else {
                alertPopup("Merger Accepted", "You Accepted the Merger");
                // Show terms and conditions or other necessary actions
                btn2.setDisable(false);
            }
        });

        
        btn2.setPrefWidth(150); // Set button width to 100px
        btn2.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 14px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;");
        btn2.setOnAction(e -> {
            if (isSent) {
                alertPopup("Pending Requested", "Your Merger Request is Pending!");
                btn2.setDisable(true);
                new Pools().mergerBillPopUp(planId,planName,planAmount,planDuration,senderPoolId,senderHostId,receiverPoolId,receiverHostId,email);
            } else {
                alertPopup("Merger Declined", "You Declined the Merger");
                new MergerDao().deleteMergerPoolEntry(planId, senderPoolId, receiverPoolId, senderHostId, receiverHostId);
                new HostSideDao().insertActivePoolsData(receiverHostId);
                new HostSideDao().insertActivePoolsData(senderHostId);
                new ExploreSideDao().updateCurrentStateToNewState(receiverPoolId, "Merger", "Active");
                new ExploreSideDao().updateCurrentStateToNewState(senderPoolId, "Merger", "Active");
            }
        });


        HBox btnHBox = new HBox(10,btn1,btn2);
        // Add HBoxes to VBox3
        vbox3.getChildren().addAll(hbox1,btnHBox);

            // VBox4: Contains host image (hardcoded for now)
            VBox vbox4 = new VBox(10);
            vbox4.setPadding(new Insets(5));
            vbox4.setMinWidth(190);
            HBox.setHgrow(vbox4, Priority.ALWAYS);

            int poolUserId;
            if (isSent) {
                poolUserId = new ExploreSideDao().fetchUserIdFromHostId(request.getReceiverHostId());
            } else {
                poolUserId = new ExploreSideDao().fetchUserIdFromHostId(request.getSenderHostId());
            }
            
            
            ImageView hostImgView = new ImageView();
            new ExploreSideDao().loadUserImage(poolUserId, hostImgView); // Load service image
            Circle hostClip = new Circle(50);
            hostClip.setCenterX(50);
            hostClip.setCenterY(50);
            hostImgView.setClip(hostClip);
            hostImgView.setFitWidth(100);
            hostImgView.setFitHeight(100);
            vbox4.getChildren().add(hostImgView);

        // HBox1: Combine VBox1, VBox2, and VBox3
        HBox activePoolHbox = new HBox(10);
        activePoolHbox.setPadding(new Insets(10,10,10,40));
        activePoolHbox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4);

        poolMainVBox.getChildren().addAll(activePoolHbox);
        activePoolHbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(10), new BorderWidths(2), null)));
    }

    // ScrollPane to make the content scrollable
    activePoolScrollPane = new ScrollPane(poolMainVBox);
    activePoolScrollPane.setFitToWidth(true);
    return activePoolScrollPane;
}


    public void mergerBillPopUp(int planId,String planName,double planAmount,int planDuration,int senderPoolId,int senderHostId,int receiverPoolId,int receiverHostId,String email) {
        Stage popupStage = new Stage();
        // popupStage.initModality(Modality.APPLICATION_MODAL);
        // popupStage.initOwner(myStage);
    
        // Image at the top
        ImageView imageView = new ImageView(new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/logo.png")); 
        imageView.setFitWidth(100); // Adjusted to fit within 400x400
        imageView.setFitHeight(100); // Adjusted to fit within 400x400
        imageView.setPreserveRatio(true);
        VBox imageViewBox = new VBox(imageView);
        imageViewBox.setPrefSize(100, 100);
    
        int user_id = new ExploreSideDao().fetchUserIdFromHostId(senderHostId);
        int service_id = new ExploreSideDao().fetchServiceIdFromPlanId(planId);
        // Labels
        Label billLabel = new Label("Billing Details:  "+ planName+" Pool");
        billLabel.setStyle("-fx-font-size:16px;-fx-font-weight:bold;-fx-text-fill:black;");

        Label multiLineLabel = new Label("You are nowa member of this new Pool .\nPlease contact new Host for Credentials.\nSubscription will be live once Verified");
        multiLineLabel.setStyle("-fx-font-size:14px; -fx-text-fill:black;");
        
    
        Label serviceNameLabel = new Label("Duration :  " + planDuration+" Days.");
        serviceNameLabel.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-text-fill:black;");
    
        Label servicePriceLabel = new Label("Price:   "+planAmount+"  (₹)");
        servicePriceLabel.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        double platformFee = (planAmount*0.10);
        Label feelLabel = new Label("Platform Fee (10%):  "+platformFee);
        feelLabel.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-text-fill:black;");
        
        Label theSubWalletBalanceLabel = new Label("TheSubWallet: " + new WalletDao(email).getTotalBalance(user_id));
        theSubWalletBalanceLabel.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-text-fill:black;");
    
        double netPurchaseAmount = (planAmount + platformFee);
        Button proceedToPayButton;
            if(new WalletDao(email).getTotalBalance(user_id)>=planAmount + platformFee){
                proceedToPayButton = new Button("Net Pay : 0"  );
                
                if(new MergerDao().mergePools(senderPoolId, receiverPoolId)){
                    System.out.println("Merge Pool Was A Success");
                }
                new MergerDao().deleteMergerPoolEntry(planId, senderPoolId, receiverPoolId, senderHostId, receiverHostId);
                new WalletDao(email).purchaseDebitMoney(netPurchaseAmount, user_id,senderPoolId);
                new HostSideDao().insertActivePoolsData(receiverPoolId);
                new ExploreSideDao().updateCurrentStateToNewState(receiverPoolId, "Merger", "Active");

                // if(currentMembers == membersAllowed){
                //     //new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, poolId);
                //     System.out.println("U r now goinf to veri state");
                //     new ExploreSideDao().movePoolToVerificationFromActive(pool_id);
                //     new ExploreSideDao().updateCurrentStateToNewState(pool_id, "Active", "Verification");
                    
                // }
                
                     
                
            } else {
                proceedToPayButton = new Button("Net Pay : " + ((planAmount+platformFee) - new WalletDao(email).getTotalBalance(user_id)));
                proceedToPayButton.setDisable(true);
            }
        
        proceedToPayButton.setStyle("-fx-font-size:16px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:10px;");
        proceedToPayButton.setPrefSize(150, 40);
        proceedToPayButton.setPadding(new Insets(5));
    
        proceedToPayButton.setOnAction(e -> {
            // Handle payment logic here if needed
            popupStage.close();
        });
    
        // VBox layout for the popup
        VBox popupVBox = new VBox(10, imageViewBox, billLabel, serviceNameLabel, servicePriceLabel, feelLabel, theSubWalletBalanceLabel, proceedToPayButton);
        popupVBox.setAlignment(Pos.CENTER);
        popupVBox.setPadding(new Insets(10)); // Reduced padding
        popupVBox.setSpacing(10); // Reduced spacing between elements
        popupVBox.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px;-fx-background-radius:10px;-fx-border-radius:10px;");
    
        Scene popupScene = new Scene(popupVBox, 400, 400);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    





}

