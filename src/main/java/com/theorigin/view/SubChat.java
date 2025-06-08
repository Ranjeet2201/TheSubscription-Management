// package com.theorigin.view;

// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.stage.Stage;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.layout.Border;
// import javafx.scene.layout.BorderStroke;
// import javafx.scene.layout.BorderStrokeStyle;
// import javafx.scene.layout.BorderWidths;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.CycleMethod;
// import javafx.scene.paint.ImagePattern;
// import javafx.scene.paint.LinearGradient;
// import javafx.scene.paint.Stop;
// import javafx.scene.shape.Circle;

// public class SubChat extends Application {

//     private ChatClient chatClientObj;
//     VBox chatVBox;
//     HBox mainChatLayoutBox;
//     Scene chatScene;
//     private VBox currentChatBox; // Reference to the currently displayed chatBox
//     int counter =0;

//     public static void main(String[] args)throws Exception {
//         launch(args);
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setScene(initialize());
//         primaryStage.setTitle("TheSubscription");
//         primaryStage.setHeight(650);
//         primaryStage.setWidth(1300);
        
//         primaryStage.setMaximized(true);
//         primaryStage.show();

//     }

//     Scene initialize(){
//         // Initialize the main layout container
//         mainChatLayoutBox = new HBox();
//         HBox chooseBox=new HBox(30);
//         Button hostButton=HostPage.createStyledButton("Host");
//         Button memberButton=HostPage.createStyledButton("Member");
//         chooseBox.setMargin(hostButton, new Insets(5,5,5,20));
//         chooseBox.setMargin(memberButton, new Insets(5,5,5,10));
//         chooseBox.getChildren().addAll(hostButton,memberButton);

//         ScrollPane chatPoolsScrollPane = new ScrollPane(createChatPools());

//         //chatVBox = new VBox(chatLabel);
//         chatVBox.setPrefSize(900, 650);

//         VBox leftPoolsVBox = new VBox();
//         leftPoolsVBox.getChildren().addAll(chooseBox,chatPoolsScrollPane);
//         // Add components to main layout
//         mainChatLayoutBox.getChildren().addAll(leftPoolsVBox, chatVBox);
//         mainChatLayoutBox.setSpacing(10);
//         chatScene=new Scene(mainChatLayoutBox, 1300, 650);
        
//        return chatScene;

//     }

//     public  ScrollPane createChatPools() {
//         VBox poolMainVBox = new VBox(12);
//         poolMainVBox.setMaxWidth(300);

//         for (int i = 0; i < 20; i++) {
//             Image poolImg = new Image("file:E:/TheSubscription(Prototype1)/thesubscription/src/main/resources/images/aws.png");
//             Circle poolImgCircle = new Circle(30);
//             poolImgCircle.setFill(new ImagePattern(poolImg));
            
//             Label poolNameLabel = new Label("RB Pool");
//             poolNameLabel.setMinSize(50, 60);
//             poolNameLabel.setStyle("-fx-font-size:15px;-fx-text-fill:black;-fx-font-weight:bold;");

//             HBox poolHBox = new HBox(10);
//             poolHBox.getChildren().addAll(poolImgCircle,poolNameLabel);
//             poolHBox.setPrefSize(250, 100);
//             poolHBox.setAlignment(Pos.CENTER_LEFT);
//             poolHBox.setMargin(poolImgCircle, new Insets(0, 20, 0, 10));
//             poolHBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px; -fx-background-color: white;");
//             poolHBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
//                     new CornerRadii(10), new BorderWidths(10), null)));

//             Button poolHboxBtn = new Button();
//             poolHboxBtn.setGraphic(poolHBox);
//             poolHboxBtn.setStyle("-fx-background-color: transparent;");

//             poolMainVBox.getChildren().add(poolHboxBtn);
//             poolHboxBtn.setOnAction(e -> {
             
//             });
//         }

//         ScrollPane poolScrollPane = new ScrollPane(poolMainVBox);

//         poolScrollPane.setFitToWidth(true); // Ensure it fits to width
//         poolScrollPane.setPrefViewportHeight(500);
//         return poolScrollPane;
//     }
//     public Scene getScene(){
//         return chatScene;
//     }
// }

// class chatBox {
//     Circle imgIcon;
//     Label poolName;

//     chatBox(Circle imgIcon, Label poolName) {
//         this.imgIcon = new Circle(imgIcon.getRadius(), imgIcon.getFill());
//         this.poolName = new Label(poolName.getText());
//         this.poolName.setStyle(poolName.getStyle());
        
//     }

//     VBox createChatBox() {
//         Button reportButton=createReportButton();
//         HBox topHBox = createTopBox(imgIcon, poolName,reportButton);
//         VBox mainTextChatBox = new VBox();
//         mainTextChatBox.setPrefSize(1000, 500);
//         TextField chatTextField=createTextField();
//         chatTextField.setText("Enter message here");
//         chatTextField.setPrefSize(900, 50);
//         Button sendButton=createSendButton();
//         HBox msgBox=createHBox(chatTextField, sendButton);
//         msgBox.setPrefSize(1000, 150);
//         msgBox.setAlignment(Pos.CENTER);
//         VBox chatBox = new VBox(topHBox, mainTextChatBox,msgBox);
//         return chatBox;
//     }

//     HBox createTopBox(Circle imgIcon, Label poolName, Button reportButton) {
//         HBox chatTopBox = new HBox();
//        // chatTopBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px; -fx-background-color: white;");
//         imgIcon.setRadius(50);
//         poolName.setPrefSize(50, 60);
//         poolName.setStyle("-fx-font-size:20px;-fx-text-fill:black;-fx-font-weight:bold;");
//         reportButton.setText("Report");
//         chatTopBox.getChildren().addAll(imgIcon, poolName, reportButton);
//         chatTopBox.setMargin(poolName, new Insets(5,100,5,100));
//         chatTopBox.setMargin(reportButton, new Insets(5,5,5,230));
//         return chatTopBox;
//     }
//     Button createReportButton(){
//         Button reportButton=new Button("Report");
//         reportButton.setStyle("-fx-font-size:15px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
//         reportButton.setPrefSize(150, 40);
//         return reportButton;
//     }
//     TextField createTextField(){
//         TextField chatField = new TextField();
//         chatField.setPromptText("Enter amount");
//         chatField.setPrefSize(950, 70);
//         chatField.setStyle("-fx-font-size:24px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
//         return chatField;
//     }
//     Button createSendButton(){
//         Button sendButton=new Button("send");
//         sendButton.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
//         sendButton.setPrefSize(120, 60);
//         return sendButton;
//     }
//     HBox createHBox(TextField msgField,Button sendButton){
//         HBox msgBox=new HBox(msgField,sendButton);
//         msgBox.setPrefSize(1000, 200);
//         return msgBox;
//     }
// }
// class HostChatBox {
//     private Circle imgIcon;
//     private Label poolName;

//     public HostChatBox(Circle imgIcon, Label poolName) {
//         this.imgIcon = new Circle(imgIcon.getRadius(), imgIcon.getFill());
//         this.poolName = new Label(poolName.getText());
//         this.poolName.setStyle(poolName.getStyle());
//     }

//     public VBox createChatBox() {
//         Button reportButton = createReportButton();
//         HBox topHBox = createTopBox(imgIcon, poolName, reportButton);
        
//         VBox mainTextChatBox = new VBox();
//         mainTextChatBox.setPrefSize(1000, 400); // Adjust height to fit below components
        
//         TextField chatTextField = createTextField();
//         chatTextField.setPrefSize(800, 50);
//         Button sendButton = createSendButton();
//         HBox msgBox = createHBox(chatTextField, sendButton);
//         msgBox.setAlignment(Pos.CENTER);
//         msgBox.setPrefSize(1000, 150);
//         HBox memberBox = createMemberBox(4);
        
//         VBox chatBox = new VBox(20); // Add spacing between elements
//         chatBox.setPadding(new Insets(10));
//         chatBox.getChildren().addAll(topHBox, memberBox, mainTextChatBox, msgBox);
        
//         return chatBox;
//     }

//     private HBox createTopBox(Circle imgIcon, Label poolName, Button reportButton) {
//         HBox chatTopBox = new HBox(20); // Add spacing between children
//         chatTopBox.setPadding(new Insets(10));
//         chatTopBox.setAlignment(Pos.CENTER_LEFT);
        
//         imgIcon.setRadius(30); // Adjust radius for better fit
//         poolName.setPrefSize(300, 40); // Adjust size to fit the text
//         poolName.setStyle("-fx-font-size:24px;-fx-text-fill:black;-fx-font-weight:bold;");
        
//         chatTopBox.getChildren().addAll(imgIcon, poolName, reportButton);
//         HBox.setMargin(poolName, new Insets(0, 0, 0, 20)); // Add margin between elements
//         HBox.setMargin(reportButton, new Insets(0, 0, 0, 200));
        
//         return chatTopBox;
//     }

//     private Button createReportButton() {
//         Button reportButton = new Button("Report");
//         reportButton.setStyle("-fx-font-size:18px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
//         reportButton.setPrefSize(100, 40);
//         return reportButton;
//     }

//     private TextField createTextField() {
//         TextField chatField = new TextField();
//         chatField.setPromptText("Enter message here");
//         chatField.setPrefSize(800, 50); // Adjust to fit the layout
//         chatField.setStyle("-fx-font-size:18px;-fx-border-color:black;-fx-border-radius:10px;-fx-background-radius:10px;");
//         return chatField;
//     }

//     private Button createSendButton() {
//         Button sendButton = new Button("Send");
//         sendButton.setStyle("-fx-font-size:18px;-fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:20px;");
//         sendButton.setPrefSize(100, 50);
//         return sendButton;
//     }

//     private HBox createHBox(TextField msgField, Button sendButton) {
//         HBox msgBox = new HBox(10); // Add spacing between children
//         msgBox.getChildren().addAll(msgField, sendButton);
//         return msgBox;
//     }

//     private HBox createMemberBox(int count) {
//         HBox memberHolder = new HBox(20); // Add spacing between members
//         memberHolder.setAlignment(Pos.CENTER_LEFT);
//         for (int i = 0; i < count; i++) {
//             Circle memberIcon = new Circle(20, Color.BLUE); // Create a new Circle for each member
//             Label memberName = new Label("Member " + (i + 1)); // Example member name
//             memberName.setStyle("-fx-font-size:16px;");
//             VBox memberVBox = createMemberVBox(memberIcon, memberName);
//             memberHolder.getChildren().add(memberVBox);
//         }
//         return memberHolder;
//     }

//     private VBox createMemberVBox(Circle imgIcon, Label memberName) {
//         VBox memberVBox = new VBox(5); // Add spacing between icon and label
//         memberVBox.setAlignment(Pos.CENTER);
//         memberVBox.getChildren().addAll(imgIcon, memberName);
//         return memberVBox;
//     }
   
// }