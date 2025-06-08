package main.java.com.theorigin.view;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.controller.HostSideController;
import main.java.com.theorigin.view.HostForm;
import main.java.com.theorigin.view.Pools;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.MergerDao;
import main.java.com.theorigin.dao.MergerPoolRequest;

public class HostPage extends Application {
    private Timer timer;
    File selectedFile;
    Stage myStage;
    HostSideController hostSideControllerObj = new HostSideController();
    static Pools poolsObj = new Pools();
    Scene hostScene;
    Button serviceBtn;
    static int user_id;
    String email;
    String nameOfUser;

    public static void main(String[] args){
        launch(args);   
    }

    public HostPage(int user_id,String email){
        this.user_id = user_id;
        this.email = email;
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

    Scene initialize(Stage myStage) {
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
                System.out.println("back btn 1 clicked");
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

        Label title2 = new Label("THE HOST");
        title2.setFont(new Font(40));
        LinearGradient textGradient2 = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#17EAD9")),
                new Stop(1, Color.web("#6078EA"))
        );
        title2.setTextFill(textGradient2);
        
        Button backButton2 = new Button("BACK");
        backButton2.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA);" +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        backButton2.setPrefHeight(50);
        backButton2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("back btn 2 clicked");
                new AuthenticationController().navigateToHomepage(myStage, user_id,email);
            }
        });
        
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        
        HBox titleBox2 = new HBox(backButton2,spacer2, title2);
        titleBox2.setPrefHeight(50);
        titleBox2.setPrefWidth(1300);
        titleBox2.setAlignment(Pos.CENTER);
        titleBox2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
        


        String[][] imgUrlArray = new String[6][6];
        imgUrlArray[0] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/netflix.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/spotify.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/youtube.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/prime.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/disney.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/jiocinema1.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/sonyliv.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/discovery.png"            
        };
        imgUrlArray[1] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/office.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/chatgpt.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/linkedin.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/grammarly.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/canva.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/aws.png",
        };
        imgUrlArray[2] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/kfc.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/zomato.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/mcdonalds.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/zomato.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/mcdonalds.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/kfc.png",
        
        };
        imgUrlArray[3] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/core2web.jpeg",
        
        };
        imgUrlArray[4] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/essentials.png",
        
        };
        imgUrlArray[5] = new String[] {
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
            "file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/lifestyle.png",
        
        };


        String[][] btnTextsArray = new String[6][6];
        btnTextsArray[0] = new String[] {
            "Netflix",
            "Spotify",
            "Youtube",
            "Prime",
            "Disney+",
            "JioCinema",
            "SonyLiv",
            "Discovery+"
        };
        btnTextsArray[1] = new String[] {
            "Office-365",
            "Chat-GPT",
            "LinkedIn",
            "Grammerly",
            "Canva",
            "AWS"
        };
        btnTextsArray[2] = new String[] {
            "KFC",
            "Zomato",
            "Mac-D",
            "KFC",
            "Zomato",
            "Mac-D"
        };
        btnTextsArray[3] = new String[] {
            "Core2web",
            "Core2web",
            "Core2web",
            "Core2web",
            "Core2web",
            "Core2web"
        };
        btnTextsArray[4] = new String[] {
            "Netmeds",
            "Netmeds",
            "Netmeds",
            "Netmeds",
            "Netmeds",
            "Netmeds"
        };
        btnTextsArray[5] = new String[] {
            "Myntra",
            "Myntra",
            "Myntra",
            "Myntra",
            "Myntra",
            "Myntra"
        };


        VBox mainVBox = new VBox();
        //mainVBox.getChildren().add(titleBox);

        for(int i = 0;i<=5; i++){
            HBox serviceHBox = new HBox(20); // HBox with 20px spacing
            serviceHBox.setAlignment(Pos.CENTER);
            for(int j=0; j < imgUrlArray[i].length; j++){
                Image serviceImg = new Image(imgUrlArray[i][j]);
                ImageView serviceImageView = serviceImageView(serviceImg);
                String btnText = btnTextsArray[i][j];
                serviceBtn = createStyledButton(btnText);
                               
                VBox serviceVBox = new VBox();
                serviceVBox = serviceVBox(serviceImageView, serviceBtn);
                serviceVBox.setMinWidth(250);
                serviceHBox.getChildren().add(serviceVBox);
                serviceBtn.setOnAction(e -> {
                    new HostSideController().navigateToHostForm(myStage,user_id,btnText,email);
                    HostForm hostFormObj = new HostForm(user_id,btnText,email);
                    
                 });
            }
            serviceHBox.setStyle("-fx-border-color: gray; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-color: white; " +
                "-fx-background-radius: 10px;");

            ScrollPane hboxScrollPane = new ScrollPane(serviceHBox);
            hboxScrollPane.setFitToWidth(true); // Make sure HBox fits within the scroll pane
            hboxScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);                                    
            mainVBox.getChildren().addAll(hboxScrollPane);
        }
         ScrollPane scrollPane = new ScrollPane(mainVBox);
         scrollPane.setFitToWidth(true);



         VBox hostVBox = new VBox(10);
         hostVBox.getChildren().addAll(titleBox1,scrollPane);
        hostVBox.setAlignment(Pos.TOP_CENTER); // Ensure everything is centered
        hostVBox.setPadding(new Insets(20)); // Add some padding for better visibility

         // Create HBox and center it
        HBox centerHBox = new HBox(50); // 50px spacing between VBoxes
        centerHBox.setAlignment(Pos.CENTER);

        // Create left VBox with an ImageView button
        Image leftImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/host.png"); // Replace with your image path
        ImageView leftImageView = new ImageView(leftImage);
        leftImageView.setFitWidth(300);
        leftImageView.setFitHeight(300);

        // Make the image fully rounded
        Circle clip = new Circle(150, 150, 150); // Center x, y, and radius
        leftImageView.setClip(clip);

        Button leftImageButton = new Button();
        leftImageButton.setGraphic(leftImageView);
        leftImageButton.setOnAction(e->{
            //hostSideControllerObj.navigateToHostHistory(myStage); 
            hostScene = new Scene(hostHistoryTabs(myStage,email));
            myStage.setScene(hostScene);
// IMp to pass this stage as i want to continue with same stage just change the scene.
        });

        VBox leftVBox = new VBox(leftImageButton);
        leftVBox.setMinHeight(300);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setStyle("-fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 5px; -fx-border-color: #000000;");


        // Create right VBox with an ImageView button
        Image rightImage = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/newhost.png"); // Replace with your image path
        ImageView rightImageView = new ImageView(rightImage);
        rightImageView.setFitWidth(300);
        rightImageView.setFitHeight(300);

        // Make the image fully rounded
        Circle clipRight = new Circle(150, 150, 150); // Center x, y, and radius
        rightImageView.setClip(clipRight);

        Button rightImageButton = new Button();
        rightImageButton.setGraphic(rightImageView);
        rightImageButton.setOnAction(e->{
            hostScene = new Scene(hostVBox);
            myStage.setScene(hostScene);
        });

        VBox rightVBox = new VBox(rightImageButton);
        rightVBox.setMinHeight(300);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setStyle("-fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 5px; -fx-border-color: #000000;");

        // Add VBoxes to HBox
        centerHBox.getChildren().addAll(leftVBox, rightVBox);
        VBox finalVBox = new VBox(5,titleBox2,centerHBox);

        // Add HBox to the root layout
        hostScene = new Scene(finalVBox);


        return hostScene;
    }


    public static VBox hostHistoryTabs(Stage myStage,String email){

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


        int currentlyLoggedHostId = new HostSideDao().fetchHostIdFromUserId(user_id);
        System.out.println(currentlyLoggedHostId);

        TabPane tabPane = new TabPane();
        Tab livePoolsTab = new Tab("LIVE");
        livePoolsTab.setStyle("-fx-font-weight: bold;");
        Tab activePoolsTab = new Tab("Active-Pool's");
        activePoolsTab.setStyle("-fx-font-weight: bold;");
        Tab mergersTab = new Tab("Mergers");
        mergersTab.setStyle("-fx-font-weight: bold;");
        Tab deadPoolsTab = new Tab("Dead-Pool's");
        deadPoolsTab.setStyle("-fx-font-weight: bold;");
        Tab verificationPoolsTab = new Tab("Verification");
        verificationPoolsTab.setStyle("-fx-font-weight: bold;");
        
        HostSideController hostSideControllerObj = new HostSideController();
        //Tab Contents
        livePoolsTab.setContent(new Pools().createThisHostLivePools("Chat", currentlyLoggedHostId,email));
        activePoolsTab.setContent(new Pools().createThisHostActivePools("Merger",currentlyLoggedHostId,email));
        mergersTab.setContent(mergerMainPage(currentlyLoggedHostId,email));
        deadPoolsTab.setContent(hostSideControllerObj.showHostDeadPools(1));
        verificationPoolsTab.setContent(new Pools().createThisHostVerificationPools("Verify", currentlyLoggedHostId,user_id,email));


        
        // Change tab color to blue when selected
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            newTab.setStyle("-fx-background-color: #B8E2F2; -fx-font-weight: bold;");
            if (oldTab != null) {
                oldTab.setStyle("-fx-background-color: white; -fx-font-weight: bold;");
            }
        });      
        tabPane.getTabs().addAll(livePoolsTab,activePoolsTab,mergersTab,verificationPoolsTab,deadPoolsTab);
        tabPane.setMaxHeight(550);
        tabPane.setMaxWidth(900);

        VBox hostHistoryVBox = new VBox(5,titleBox3,tabPane);
        return hostHistoryVBox;  
    }

    public static TabPane mergerMainPage(int currentlyLoggedHostId,String email){
        TabPane tabPane = new TabPane();
        Tab sentMergerRequestTab = new Tab("Sent");
        sentMergerRequestTab.setStyle("-fx-font-weight: bold;");
        Tab receivedMergerRequestTab = new Tab("Received");
        receivedMergerRequestTab.setStyle("-fx-font-weight: bold;");
        
        // //Tab Contents
        // sentMergerRequestTab.setContent(poolsObj.createTwoBtnPools("Pending","Withdraw"));
        // receivedMergerRequestTab.setContent(poolsObj.createTwoBtnPools("Accept","Decline"));
        
        // Add listener for sentMergerRequestTab
        sentMergerRequestTab.setOnSelectionChanged(event -> {
            if (sentMergerRequestTab.isSelected()) {
                System.out.println("Selected Sent tab");
                // Fetch the list of sent merger requests (you can adjust this logic)
                ObservableList<MergerPoolRequest> sentRequests =  new MergerDao().fetchMergerRequests(currentlyLoggedHostId, true); // Replace with your method to fetch sent requests
                // Call createMergerPoolsUI with the fetched data and true for sent requests
                ScrollPane sentScrollPane = new Pools().createMergerPoolsUI(sentRequests, true,email);
                sentMergerRequestTab.setContent(sentScrollPane);
            }
        });

        // Add listener for receivedMergerRequestTab
        receivedMergerRequestTab.setOnSelectionChanged(event -> {
            if (receivedMergerRequestTab.isSelected()) {
                System.out.println("Selected Received tab");
                // Fetch the list of received merger requests (adjust as needed)
                ObservableList<MergerPoolRequest> receivedRequests =  new MergerDao().fetchMergerRequests(currentlyLoggedHostId, false); // Replace with your method to fetch received requests
                // Call createMergerPoolsUI with the fetched data and false for received requests
                ScrollPane receivedScrollPane = new Pools().createMergerPoolsUI(receivedRequests, false,email);
                receivedMergerRequestTab.setContent(receivedScrollPane);
            }
        });


        
        // Change tab color to blue when selected
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            newTab.setStyle("-fx-background-color: #B8E2F2; -fx-font-weight: bold;");
            if (oldTab != null) {
                oldTab.setStyle("-fx-background-color: white; -fx-font-weight: bold;");
            }
        });      
        tabPane.getTabs().addAll(sentMergerRequestTab,receivedMergerRequestTab);
        tabPane.setMaxHeight(700);
        tabPane.setMaxWidth(1360);
        return tabPane;  
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

    static ImageView serviceImageView(Image img){

        ImageView serviceImageView = new ImageView(img);
        serviceImageView.setFitWidth(150); // Set image width smaller than button width
        serviceImageView.setFitHeight(100);
        serviceImageView.setPreserveRatio(true); // Maintain the aspect ratio of the image
        return serviceImageView;
    }

    static VBox serviceVBox(ImageView imgV, Button btn){
        VBox serviceVBox = new VBox(20);
        serviceVBox.getChildren().addAll(imgV,btn);
        serviceVBox.setPrefWidth(300); // Set preferred width
        serviceVBox.setAlignment(Pos.CENTER);
        serviceVBox.setPadding(new Insets(10));

        return serviceVBox;
    }

    public void uploadVerificationImageButtonClicked(int pool_id) {
        HostSideDao hostSideDaoObj = new HostSideDao();
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
        HostSideDao.saveVerificationImage(pool_id, selectedFile);
    }


}


