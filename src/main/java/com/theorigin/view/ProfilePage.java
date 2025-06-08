package main.java.com.theorigin.view;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.controller.HostSideController;
import main.java.com.theorigin.controller.ProfilePageController;
import main.java.com.theorigin.dao.AdminDao;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.UserPool;
import main.java.com.theorigin.dao.UserSideDao;
import main.java.com.theorigin.dao.VerificationPool;
import main.java.com.theorigin.service.WalletService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.scene.text.Font;
//Image Upload imports
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.util.Duration;
import java.util.List;

public class ProfilePage extends Application {

    Scene profileScene;
    //Selecting UserImg
    private File selectedFile;
    ProfilePageController profilePageControllerObj=new ProfilePageController();
    public static Button userPoolButton;
    public static boolean hostBtnClicked = false;
    int user_id;
    String email;
    String nameOfUser;

    public static void main(String[] args) {
        launch(args);
    }

    public ProfilePage(int user_id,String email){
        this.user_id = user_id;
        this.email = email;   
    }

    @Override
    public void start(Stage myStage) {

        
                Label title = new Label("Profile");
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




        ScrollPane sp;
        // Profile Info VBox
        LinearGradient sceneGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.web("#17EAD9")),
        new Stop(1, Color.web("#6078EA")));
        //*****************All About leftSide VBox(Starts here)*****************************
        ImageView profileImgView = new ImageView();

        //Circle profileImgView=new Circle(75);
        // profileImgView.setStyle(
        //     "-fx-background-radius: 50%; " +
        //     "-fx-text-fill: white; " +
        //     "-fx-background-color: white; " +
        //     "-fx-padding: 20px; "
        // );
        //Fetch Profile IMage of user...
        new ExploreSideDao().loadUserImage(user_id, profileImgView);
        profileImgView.setFitWidth(200);
        profileImgView.setFitHeight(200);
        
        Label userNameLabel=new Label("");
        userNameLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");
        userNameLabel.setMinSize(100,40);
        // userNameLabel.setAlignment(Pos.BASELINE_CENTER);
        VBox profileVBox=new VBox(profileImgView,userNameLabel);
        profileVBox.setMinSize(300, 300);
        profileVBox.setMargin(profileImgView, new Insets(50,20,10,90));
        profileVBox.setMargin(userNameLabel, new Insets(10,10,10,110));

        Button updateProfileButton=createStyledButton("Upload-Img");

        //updateProfileButton.setMinSize(300, 50);
        
        Button favButton=createStyledButton("❤️"+"Favorite");
        //favButton.setMinSize(300, 50);

        Button dashBoardButton=createStyledButton("DashBoard");
        //dashBoardButton.setMinSize(300, 50);
        
        Button logoutButton= createStyledButton("LogOut");
        //logoutButton.setMinSize(300, 50);
        
        VBox profileInfoVBox = new VBox();
        profileInfoVBox.setMinSize(400, 600);
        profileInfoVBox.setStyle("-fx-border-color:black;-fx-border-width:5px;-fx-padding:20px;-fx-background-radius:35px;-fx-border-radius:35px;");
        //profileInfoVBox.setBackground(new Background(new BackgroundFill(sceneGradient, CornerRadii.EMPTY, null)));
        profileInfoVBox.getChildren().addAll(profileVBox,updateProfileButton,favButton,dashBoardButton,logoutButton);
        profileInfoVBox.setMargin(updateProfileButton, new Insets(10,10,10,70));
        profileInfoVBox.setMargin(favButton, new Insets(10,10,10,70));
        profileInfoVBox.setMargin(dashBoardButton, new Insets(10,10,10,70));
        profileInfoVBox.setMargin(logoutButton, new Insets(10,10,10,70));
        //******************leftside VBox ends here********************************
        Label hostNameLabel=new Label("Host");
        hostNameLabel.setStyle("-fx-font-size:40px;-fx-font-weight:bold;-fx-text-fill:black;");
        hostNameLabel.setMinSize(300,40);
        hostNameLabel.setAlignment(Pos.CENTER);
        Circle activeHostPoolCount=new Circle(100);
        activeHostPoolCount.setStyle(
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: white; " +
            "-fx-padding: 5px; "
        );

        Label hostedPoolNumberLabel = new Label("10"); 
        hostedPoolNumberLabel.setTextFill(Color.WHITE);
        hostedPoolNumberLabel.setStyle("-fx-font-size: 40px;");
        StackPane hostStackPane=new StackPane(activeHostPoolCount,hostedPoolNumberLabel);
        hostStackPane.setAlignment(Pos.CENTER);
        VBox hostVBox=new VBox(hostNameLabel,hostStackPane);
        hostVBox.setMinSize(350, 250);
        hostVBox.setAlignment(Pos.CENTER);
        Button hostedPoolButton= createStyledButton("HOST");
        hostedPoolButton.setGraphic(hostVBox);
        //hostedPoolButton.setMinSize(550, 300);
        

        //user
        Label userLabel=new Label("User");
        userLabel.setStyle("-fx-font-size:40px;-fx-font-weight:bold;-fx-text-fill:black;");
        userLabel.setMinSize(300,40);
        userLabel.setAlignment(Pos.CENTER);
        Circle activeUserPoolCount=new Circle(100);
        activeUserPoolCount.setStyle(
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: white; " +
            "-fx-padding: 5px; "
        );

        Label userPoolNumberLabel = new Label("60"); 
        userPoolNumberLabel.setTextFill(Color.WHITE);
        userPoolNumberLabel.setStyle("-fx-font-size: 40px;");
        StackPane userStackPane=new StackPane(activeUserPoolCount,userPoolNumberLabel);
        userStackPane.setAlignment(Pos.CENTER);
        VBox userVBox=new VBox(userLabel,userStackPane);
        userVBox.setMinSize(300, 250);
        userVBox.setAlignment(Pos.CENTER);
        Button userPoolButton= createStyledButton("USER");
        userPoolButton.setGraphic(userVBox);
        //userPoolButton.setMinSize(550, 300);
        

        HBox upperHBox=new HBox(hostedPoolButton,userPoolButton);
        upperHBox.setMargin(hostedPoolButton, new Insets(30,20,10,40));
        upperHBox.setMargin(userPoolButton, new Insets(30,20,10,40));
        upperHBox.setMinSize(550, 325);
        upperHBox.setStyle("-fx-background-color:white");
        // upperHBox.setBackground(new Background(new BackgroundFill(sceneGradient, CornerRadii.EMPTY, null)));

        //LowerHbox
        Label myProfileLabel=new Label("MyProfile");
        myProfileLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");
        //myProfileLabel.setMinSize(300,40);
        myProfileLabel.setAlignment(Pos.CENTER);
        Circle ratingCircle=new Circle(100);
        ratingCircle.setStyle(
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: white; " +
            "-fx-padding: 5px; "
        );

        VBox myProfileVBox=new VBox(myProfileLabel,ratingCircle);
        myProfileVBox.setMinSize(350, 250);
        myProfileVBox.setAlignment(Pos.CENTER);
        Button myRatingButton= createStyledButton("Rating");
        myRatingButton.setGraphic(myProfileVBox);
        //myRatingButton.setMinSize(550, 300);
       

        //Wallet 
        Label myWalletLabel=new Label("MyWallet");
        myProfileLabel.setStyle("-fx-font-size:30px;-fx-font-weight:bold;-fx-text-fill:black;");
        //myProfileLabel.setMinSize(300,40);
        myProfileLabel.setAlignment(Pos.CENTER);
        Image walletImg=new Image("file:D:/TheSubscription/TheOrigin/thesubscription/src/assets/images/wallet.png");
        ImageView walletImgView=new ImageView(walletImg);
        walletImgView.setFitHeight(150);
        walletImgView.setFitWidth(100);
        walletImgView.setPreserveRatio(true);
        VBox walletVBox=new VBox(myWalletLabel,walletImgView);
        walletVBox.setMinSize(300, 250);
        walletVBox.setAlignment(Pos.CENTER);
        Button walletButton= createStyledButton("Wallet");
        walletButton.setGraphic(walletVBox);
    
        HBox lowerHBox=new HBox(myRatingButton,walletButton);
        lowerHBox.setMinSize(550, 325);
        lowerHBox.setStyle("-fx-background-color:white");
        //lowerHBox.setBackground(new Background(new BackgroundFill(sceneGradient, CornerRadii.EMPTY, null)));
        lowerHBox.setMargin(myRatingButton, new Insets(30,20,10,40));
        lowerHBox.setMargin(walletButton, new Insets(30,20,10,40));
        // Activities Record VBox with a background color for visibility
        VBox activitiesRecordVBox = new VBox(upperHBox,lowerHBox);
        activitiesRecordVBox.setMinWidth(700); // Set a minimum width
        activitiesRecordVBox.setStyle("-fx-background-color: lightgray;");

        // Main Layout HBox
        HBox subLayoutBox = new HBox();
        subLayoutBox.getChildren().addAll(profileInfoVBox, activitiesRecordVBox);
        subLayoutBox.setPadding(new Insets(10));
        VBox mainLayoutBox = new VBox();
        mainLayoutBox.getChildren().addAll(titleBox1, subLayoutBox);
        mainLayoutBox.setPadding(new Insets(10));
        
        // Set up the scene and stage
        profileScene = new Scene(mainLayoutBox, 1300, 650);
        myStage.setScene(profileScene);
        myStage.setTitle("TheSuscription");
        myStage.show();

        updateProfileButton.setOnAction(e ->{
            uploadImageButtonClicked();
            
        });
        favButton.setOnAction(e ->{
            profilePageControllerObj.favorite();
        });
        dashBoardButton.setOnAction(e ->{
            // mainLayoutBox.getChildren().remove(new HostPage().hostHistoryTabs());
            mainLayoutBox.getChildren().add(activitiesRecordVBox);

        });
        logoutButton.setOnAction(e->{
            new AuthenticationController().navigateToLoginPage(myStage);
            
        });
        hostedPoolButton.setOnAction(e ->{


            Scene hostScene = new Scene(new HostPage(user_id,email).hostHistoryTabs(myStage,email));
            myStage.setScene(hostScene);
            myStage.setMinHeight(700);
            myStage.setMinWidth(1300);
            myStage.show();

        });
        userPoolButton.setOnAction(e ->{

            Scene hostScene = new Scene(userHistoryVbox(myStage,user_id));
            myStage.setScene(hostScene);
            myStage.setMinHeight(700);
            myStage.setMinWidth(1300);
            myStage.show();
        });
        myRatingButton.setOnAction(e ->{
            profilePageControllerObj.yourRatings();
        });
        walletButton.setOnAction(e ->{
            profilePageControllerObj.navigateToWallet(myStage,user_id,email);
            
        });
    }

    public VBox userHistoryVbox(Stage myStage,int user_id){

        Label title3 = new Label("THE HOST");
        title3.setFont(new Font(40));
        LinearGradient textGradient3 = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#17EAD9")),
                new Stop(1, Color.web("#6078EA"))
        );
        title3.setTextFill(textGradient3);
        
        Button backButton3 = new Button("BACK");
        backButton3.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        backButton3.setPrefHeight(50);
        backButton3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("back btn 3 clicked");
                new AuthenticationController().navigateToHomepage(myStage, user_id,email);
            }
        });
        
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        
        HBox titleBox3 = new HBox(backButton3,spacer3, title3);
        titleBox3.setPrefHeight(50);
        titleBox3.setPrefWidth(1300);
        titleBox3.setAlignment(Pos.CENTER);
        titleBox3.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));

        TabPane tabPane = new TabPane();
        Tab livePoolsTab = new Tab("LIVE");
        livePoolsTab.setStyle("-fx-font-weight: bold;");
        Tab deadPoolsTab = new Tab("Ded");
        deadPoolsTab.setStyle("-fx-font-weight: bold;");
        
        HostSideController hostSideControllerObj = new HostSideController();
        
        //Tab Contents
        livePoolsTab.setContent(createThisUserLivePools("Chat",  user_id));
        deadPoolsTab.setContent(hostSideControllerObj.showHostDeadPools(1));


        
        // Change tab color to blue when selected
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            newTab.setStyle("-fx-background-color: #B8E2F2; -fx-font-weight: bold;");
            if (oldTab != null) {
                oldTab.setStyle("-fx-background-color: white; -fx-font-weight: bold;");
            }
        });      
        tabPane.getTabs().addAll(livePoolsTab,deadPoolsTab);
        tabPane.setMaxHeight(550);
        tabPane.setMaxWidth(900);

        VBox userHistoryVBox = new VBox(titleBox3,tabPane);
        return userHistoryVBox;
        
    }

    static Button createStyledButton(String text){
        Button btn = new Button(text);
        btn.setPrefWidth(200);
        btn.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("HOST");
                // app.navigateToTheHost(user_id);
            }
        });
        return btn;
    }
    
    public void uploadImageButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // The selected file can now be used
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
        }
        //Save img query is in HostSideDao there is no profile DAO for now
        profilePageControllerObj.navigateToSaveUserImg(user_id,selectedFile);
    }

    public ScrollPane createThisUserLivePools(String text,int user_id) {

        ScrollPane liveUserPoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold all pools
        poolMainVBox.setPadding(new Insets(10)); // Padding for the main VBox
        poolMainVBox.setMaxWidth(975); // 75% of 1300px
    
            
        
        List<UserPool> userPools = new UserSideDao().fetchPoolsForUser(user_id);
        
        // Display the fetched pools
        for (UserPool userPool : userPools) {

            String poolType = userPool.getPoolType();
            String poolState = userPool.getPoolState();
            boolean isPrivate = userPool.isPrivate();
            int poolId = userPool.getPoolId();
            int hostId = userPool.getHostId();
            int currentMembers = userPool.getCurrentMembers();
            int membersAllowed = userPool.getMembersAllowed();
            int serviceId = userPool.getServiceId();

            if ((poolState.equals("Live"))) {
    
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
                Label poolName = new Label(userPool.getPoolName());
                poolName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                Label thisPoolId = new Label("Pool-id  "+String.valueOf(poolId)); // Assuming a hardcoded rating for now
                thisPoolId.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
                Label thisHostId = new Label("🙋‍♂️ "+ "Host-id  "+ String.valueOf(hostId));
                thisHostId.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
                vbox2.getChildren().addAll(poolName, thisPoolId, thisHostId);
    
                // VBox3: Contains price label and Buy button
                VBox vbox3 = new VBox(20);
                vbox3.setPadding(new Insets(5));
                vbox3.setMinWidth(190);
                HBox.setHgrow(vbox3, Priority.ALWAYS);
    //Fetch Plan-Id for PlanName
                int planId = userPool.getPlanId();
                String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
    //Fetch PLan Amount
                int planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
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
                if(userPool.isPrivate()){
                    privatePoolLabel = new Label("Private");
                }
                Label poolTypeLabel = new Label(userPool.getPoolType() + " " + privatePoolLabel.getText() + "  Pool");
                poolTypeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-font-weight: bold;");
                vbox3.getChildren().addAll(priceLabel, verifyButton, poolTypeLabel);
    
                // VBox4: Contains host image (hardcoded for now)
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(190);
                HBox.setHgrow(vbox4, Priority.ALWAYS);
    
                int poolHostId = userPool.getHostId();
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
         liveUserPoolScrollPane = new ScrollPane(poolMainVBox);
         liveUserPoolScrollPane.setMaxWidth(975); // 75% of 1300px
         liveUserPoolScrollPane.setFitToWidth(true);
         return liveUserPoolScrollPane;
    }        
    
        













}
