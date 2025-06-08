package main.java.com.theorigin.view;
import main.java.com.theorigin.controller.HomePageController;
import main.java.com.theorigin.dao.AuthenticationDao;
import javafx.application.Application;
import javafx.stage.Popup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HomePage extends Application {

        private String nameOfUser;
        private int user_id;
        String email;
        Stage primaryStage;
        HomePageController homePageControllerObj=new HomePageController();
        private int currentIndex=0;
    public static void main(String[] args) {
        launch(args);
    }

    public HomePage(int user_id,String email){
        this.user_id = user_id;
        this.email = email;
        System.out.println("Homepg Construc "+this.user_id);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        primaryStage.setTitle("TheSubscription");
        //Top Box content
        Label greetingLabel=new Label("Hello "+ new AuthenticationDao().fetchUserName());//Label with userName
        greetingLabel.setFont(new Font("Arial", 24));
        greetingLabel.setPrefSize(250, 40);
        greetingLabel.setStyle("-fx-text-fill:white");

        Circle circleView = new Circle(30);     //profileButton type Circle
        circleView.setStyle(
            "-fx-background-radius: 50%; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: white; " +
            "-fx-padding: 20px; "
        );
        circleView.setFill(Color.WHITE);

        
        ImageView notificationView=new ImageView(new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/notification.png"));
        notificationView.setFitHeight(40);
        notificationView.setFitWidth(40);
        Button notificationButton=new Button();
       // notificationButton.setStyle("-fx-background-color:transparent;");
        notificationButton.setGraphic(notificationView);
        

        HBox navBox = new HBox(20,greetingLabel,notificationButton,circleView);
        navBox.setPrefHeight(80);
        navBox.setPrefWidth(1300);
        navBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));

        //(Middle Box which slides images)creating sliding Images of various platforms
        Image[] images=new Image[]{new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/aws.png"),
        new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/netflix.png"),
        new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/prime.png"),
        new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/zomato.jpeg"),
        new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/sony.png")};

        ImageView middleImageView = new ImageView(images[currentIndex]);
        middleImageView.setFitWidth(1000);
        middleImageView.setFitHeight(250);
        middleImageView.setPreserveRatio(true);
        HBox imageSliderBox = new HBox(middleImageView);
        imageSliderBox.setPrefHeight(250);
        imageSliderBox.setPrefWidth(1000);
        imageSliderBox.setAlignment(Pos.CENTER); // Center align the content
        imageSliderBox.setPadding(new Insets(10, 15, 20, 15));
        //imageSliderBox.setMargin(notificationButton, new Insets(2,50,2,1150));
        //Timeline which changes images by changing index value also sets the image
        Timeline timeLine=new Timeline(new KeyFrame(Duration.seconds(1),event ->{currentIndex=(currentIndex+1)%(images.length);
        middleImageView.setImage(images[currentIndex]);}));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

        //Creating three Buttons Host,Explore,Chat
        //Host button
        Image hostImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/host.png");
        ImageView hostImgView = new ImageView(hostImg);
        hostImgView.setFitWidth(150);
        hostImgView.setFitHeight(120);
        hostImgView.setPreserveRatio(true);
        Button hostButton = new Button("HOST");
        hostButton.setPrefWidth(150);
        hostButton.setStyle("-fx-background-color:black;" +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        

        VBox hostVBox = new VBox(10, hostImgView, hostButton);
        hostVBox.setPrefWidth(150);
        hostVBox.setAlignment(Pos.CENTER);
        hostVBox.setPadding(new Insets(10));

        LinearGradient vboxGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("WHITE")),
                new Stop(1, Color.web("WHITE")));
        hostVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
        hostVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(3), new BorderWidths(3), null)));
        hostVBox.setOnMouseEntered(e -> hostVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null))));
        hostVBox.setOnMouseExited(e -> hostVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null))));//Host Over

        //Explore Button
        Image exploreImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/explore.png");
        ImageView explorView = new ImageView(exploreImg);
        explorView.setFitWidth(150);
        explorView.setFitHeight(120);
        explorView.setPreserveRatio(true);

        Button exploreButton = new Button("EXPLORE");
        exploreButton.setPrefWidth(150);
        exploreButton.setStyle("-fx-background-color: black;" +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        

        VBox exploreVBox = new VBox(10, explorView, exploreButton);
        exploreVBox.setPrefWidth(150);
        exploreVBox.setAlignment(Pos.CENTER);
        exploreVBox.setPadding(new Insets(10));
        exploreVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
        exploreVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(3), new BorderWidths(3), null)));
        exploreVBox.setOnMouseEntered(e -> exploreVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null))));
        exploreVBox.setOnMouseExited(e -> exploreVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null))));

        //Chat Section
        Image chatImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/chat.png");
        ImageView chatImgView = new ImageView(chatImg);
        chatImgView.setFitWidth(150);
        chatImgView.setFitHeight(120);
        chatImgView.setPreserveRatio(true);

        Button chatButton = new Button("CHAT");
        chatButton.setPrefWidth(150);
        chatButton.setStyle("-fx-background-color:black;" +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        

        VBox chatVBox = new VBox(10, chatImgView,chatButton);
        chatVBox.setPrefWidth(150);
        chatVBox.setAlignment(Pos.CENTER);
        chatVBox.setPadding(new Insets(10));
        //chatVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
        chatVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(3), new BorderWidths(3), null)));
        chatVBox.setOnMouseEntered(e -> chatVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null))));
        chatVBox.setOnMouseExited(e -> chatVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null))));

                //Private Section
                Image privateImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/private.png");
                ImageView privateImgView = new ImageView(privateImg);
                privateImgView.setFitWidth(150);
                privateImgView.setFitHeight(120);
                privateImgView.setPreserveRatio(true);
        
                Button privateButton = new Button("PRIVATE");
                privateButton.setPrefWidth(150);
                privateButton.setStyle("-fx-background-color:black;" +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 16px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 5px;");
                
        
                VBox privateVBox = new VBox(10, privateImgView,privateButton);
                privateVBox.setPrefWidth(150);
                privateVBox.setAlignment(Pos.CENTER);
                privateVBox.setPadding(new Insets(10));
                //chatVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
                privateVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        new CornerRadii(3), new BorderWidths(3), null)));
                privateVBox.setOnMouseEntered(e -> privateVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null))));
                privateVBox.setOnMouseExited(e -> privateVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null))));

        //Chat Section
        Image membershipImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/premium.png");
        ImageView membershipImgView = new ImageView(membershipImg);
        membershipImgView.setFitWidth(150);
        membershipImgView.setFitHeight(120);
        membershipImgView.setPreserveRatio(true);

        Button membershipButton = new Button("PREMIUM");
        membershipButton.setPrefWidth(150);
        membershipButton.setStyle("-fx-background-color:black;" +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 15px; " +
                "-fx-border-radius: 5px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 5px;");
        

        VBox membershipVBox = new VBox(10, membershipImgView,membershipButton);
        membershipVBox.setPrefWidth(150);
        membershipVBox.setAlignment(Pos.CENTER);
        membershipVBox.setPadding(new Insets(10));
        //chatVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));
        membershipVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(3), new BorderWidths(3), null)));
        membershipVBox.setOnMouseEntered(e -> membershipVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null))));
        membershipVBox.setOnMouseExited(e -> membershipVBox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null))));


        
       

        //Box at bottom which has the three main buttons chat explore and host
        
        HBox optionBox = new HBox(150,chatVBox, hostVBox, exploreVBox, privateVBox,membershipVBox);
        optionBox.setPrefHeight(370);
        optionBox.setPrefHeight(1300);
        optionBox.setSpacing(100);
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setPadding(new Insets(10));
        VBox leftVBox=new VBox(imageSliderBox,optionBox);
        VBox notificationBox=new VBox();
        notificationBox.setPrefSize(400,800);


        // Add HBoxes to GridPane
        root.add(navBox, 0, 0);
        root.add(imageSliderBox, 0, 1);
        root.add(optionBox, 0, 2);

        // Set constraints to make sure hb2 is centered and hb3 is at the bottom
        GridPane.setFillWidth(navBox, true);
        GridPane.setFillWidth(imageSliderBox, true);
        GridPane.setFillWidth(optionBox, true);

        GridPane.setVgrow(imageSliderBox, Priority.ALWAYS); // Allow hb2 to grow and push hb3 to the bottom

        LinearGradient sceneGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#17EAD9")),
                new Stop(1, Color.web("#6078EA")));
     //   root.setBackground(new Background(new BackgroundFill(sceneGradient, CornerRadii.EMPTY, null)));

        
        Popup profilePopUp=new Popup();
        VBox popUpContent=new VBox();
        popUpContent.setMaxSize(250, 250);
        //popUpContent.setStyle("-fx-background-color: linear-gradient(#17EAD9, #6078EA); -fx-background-radius: 10px;");
        popUpContent.setPadding(new Insets(40));
        Button profileButton=new Button("Profile");
        profileButton.setStyle("-fx-background-color:black;" +
        "-fx-text-fill: white; " +
        "-fx-font-weight: bold; " +
        "-fx-font-size: 12px; " +
        "-fx-background-radius: 15px; " +
        "-fx-border-radius: 5px; " +
        "-fx-border-color: black; " +
        "-fx-border-width: 5px;");
        Button memberButton=new Button("ShashiSir");
        memberButton.setStyle("-fx-background-color:black;" +
        "-fx-text-fill: white; " +
        "-fx-font-weight: bold; " +
        "-fx-font-size: 12px; " +
        "-fx-background-radius: 15px; " +
        "-fx-border-radius: 5px; " +
        "-fx-border-color: black; " +
        "-fx-border-width: 5px;");
        popUpContent.getChildren().addAll(profileButton,memberButton);
        profilePopUp.getContent().add(popUpContent);
        circleView.setOnMouseClicked(e->{
               if(profilePopUp.isShowing()){
                profilePopUp.hide();
               }else{
                profilePopUp.show(primaryStage);
                profilePopUp.setX(primaryStage.getX() + circleView.getLayoutX() + circleView.getRadius() -150);
                profilePopUp.setY(primaryStage.getY() + circleView.getLayoutY() + circleView.getRadius() + 40);
               } 
        });
        profileButton.setOnAction(e->{
                System.out.println("UserId at HomePage"+user_id);
                homePageControllerObj.navigateToProfilePage(primaryStage,user_id,email);
                profilePopUp.hide();
        });
        memberButton.setOnAction(e ->{
                homePageControllerObj.navigateToMemberPage(primaryStage);
                //profilePopUp.hide();
        });
        hostButton.setOnAction(e ->{
                homePageControllerObj.navigateToHostPage(primaryStage,user_id,email);
        });
        exploreButton.setOnAction(e -> {
                homePageControllerObj.navigateToExplorePage(primaryStage,user_id,email);
        });
        chatButton.setOnAction(e->{
                homePageControllerObj.navigateToChatPage(primaryStage);
        });
        privateButton.setOnAction(e->{
                primaryStage.setScene(new PrivatePoolView(user_id,primaryStage,email).privateScene());
                primaryStage.show();
        });
        membershipButton.setOnAction(e->{

        });
       /*  notificationButton.setOnAction(e -> {
                if (root.getChildren().contains(notificationBox)) {
                    root.getChildren().remove(notificationBox);
                } else {
                    root.add(notificationBox, 1, 1);
                }
            });
          */
        Popup notificationPopUp=new Popup();
        TheSubChat chatObj=new TheSubChat();
        ScrollPane notificationScrollPane=chatObj.createSmallPools();
        VBox notifiacationContent=new VBox(notificationScrollPane);
        notifiacationContent.setMaxSize(450, 850);  
        notificationPopUp.getContent().add(notifiacationContent);
        notificationButton.setOnAction(e ->{
                if(notificationPopUp.isShowing()){
                        notificationPopUp.hide();
                }else{
                notificationPopUp.show(primaryStage);
                notificationPopUp.setX(primaryStage.getX() + circleView.getLayoutX() + circleView.getRadius() -150);
                notificationPopUp.setY(primaryStage.getY() + circleView.getLayoutY() + circleView.getRadius() + 70);
                }
        });

        Scene sc = new Scene(root, 1300, 700);
        primaryStage.setScene(sc);
        primaryStage.show();
        


    }
    

}

    
    
    



    

    
    

    
    



