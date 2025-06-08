
package main.java.com.theorigin.view;

//Image Upload imports
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.controller.ExplorePageController;
import main.java.com.theorigin.controller.HostSideController;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.view.HostForm;
import main.java.com.theorigin.view.Pools;

public class Explore extends Application {
    Stage myStage;
    HostSideController hostSideControllerObj = new HostSideController();
    static Pools poolsObj = new Pools();
    Scene hostScene;
    Button serviceBtn;
    int user_id;
    private File selectedFile;
    String email;

    public static void main(String[] args){
        launch(args);   
    }

    public Explore(int user_id,String email){
        this.user_id = user_id;
        this.email = email;
    }

    @Override
    public void start(Stage myStage)  {
         myStage.setScene(initialize(myStage));
         myStage.setTitle("TheSubscription");
         myStage.setHeight(760);
         myStage.setWidth(1360);
         
         myStage.setMaximized(true);
         myStage.show();

    }

    Scene initialize(Stage myStage){
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
        
        HBox titleBox = new HBox(backButton,spacer, title);
        titleBox.setPrefHeight(50);
        titleBox.setPrefWidth(1300);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
        


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
        
        for(int i = 0;i<=5; i++){
            HBox serviceHBox = new HBox(20); // HBox with 20px spacing
            serviceHBox.setAlignment(Pos.CENTER);
            for(int j=0; j < imgUrlArray[i].length; j++){
                Image serviceImg = new Image(imgUrlArray[i][j]);
                ImageView serviceImageView = serviceImageView(serviceImg);
                String btnText = btnTextsArray[i][j];
                serviceBtn = createStyledButton(btnText);
                serviceBtn.setOnAction(e -> {
                    int service_id = new HostSideDao().fetchServiceId(btnText);
                    
                    ExplorePageController explorePageControllerObj = new ExplorePageController();
                    explorePageControllerObj.navigateToThisSubscriptionPlatform(myStage,user_id,service_id,email);
                 });
                               
                VBox serviceVBox = new VBox();
                serviceVBox = serviceVBox(serviceImageView, serviceBtn);
                serviceVBox.setMinWidth(250);
                serviceHBox.getChildren().add(serviceVBox);
                
            }
            serviceHBox.setStyle("-fx-border-color: gray; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-color: white; " +
                "-fx-background-radius: 10px;");

            ScrollPane hboxScrollPane = new ScrollPane(serviceHBox);
            hboxScrollPane.setFitToWidth(true); // Make sure HBox fits within the scroll pane
            hboxScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);                                    
            mainVBox.getChildren().add(hboxScrollPane);
        }
        
         ScrollPane scrollPane = new ScrollPane(mainVBox);
         scrollPane.setFitToWidth(true);



         VBox hostVBox = new VBox(10,titleBox,scrollPane);
         Scene scene = new Scene(hostVBox);
         return scene;   
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

}


