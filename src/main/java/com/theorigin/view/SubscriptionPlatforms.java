

package main.java.com.theorigin.view;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.dao.AdminDao;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.Pool;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.service.EmailService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;




public class SubscriptionPlatforms extends Application {
    static Scene subscriptionPlatformScene;
    static VBox mainLayoutBox;
    int user_id;
    String email;
    int service_id;
    ImageView serviceImgView;
    Stage myStage;
    


    public static void main(String[] args) {
        launch(args);
    }

    public SubscriptionPlatforms(int user_id,int service_id,String email){
        this.user_id = user_id;
        this.email = email;
        this.service_id = service_id;
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

                Label title = new Label("Services");
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





//Direct Call to EXPLORE DAO
        ImageView serviceImgView = new ImageView();

        // Load service image using the method
        new ExploreSideDao().loadServiceImage(service_id, serviceImgView);
        Circle clip = new Circle(50);
        clip.setCenterX(50);
        clip.setCenterY(50);
        serviceImgView.setClip(clip);
        serviceImgView.setFitWidth(100);
        serviceImgView.setFitHeight(100);
        
        // Create Netflix VBox
        VBox serviceVBox = createVBox(serviceImgView);
        // Pools Layout
        ScrollPane poolsScrollPane = createBuyPools("Buy",email);

        HBox finalServiceHBox = new HBox(5,titleBox1,serviceVBox,poolsScrollPane);
        // Main Layout
        mainLayoutBox = new VBox();
        mainLayoutBox.setSpacing(40);
        mainLayoutBox.getChildren().addAll(titleBox1,finalServiceHBox);
        mainLayoutBox.setBorder((new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        new CornerRadii(10), new BorderWidths(2), null))));
        HBox.setHgrow(poolsScrollPane, Priority.ALWAYS);

        subscriptionPlatformScene = new Scene(mainLayoutBox, 1300, 600);
        return subscriptionPlatformScene;
    }

    private ImageView createImgView(Image img) {
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(150);
        imgView.setFitWidth(75);
        imgView.setPreserveRatio(true);
        return imgView;
    }

    private VBox createVBox(ImageView imgView) {
        VBox vbox = new VBox(20, imgView);
        vbox.setPrefWidth(325); // 25% of 1300px
        vbox.setPrefHeight(600);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        LinearGradient vboxGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("WHITE")),
                new Stop(1, Color.web("WHITE")));
        vbox.setBackground(new Background(new BackgroundFill(vboxGradient, CornerRadii.EMPTY, null)));

        // Border
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(5), null)));

        return vbox;
    }
    
    public ScrollPane createBuyPools(String text,String email) {
        ScrollPane buyPoolScrollPane;
        VBox poolMainVBox = new VBox(20); // Main VBox to hold all pools
        poolMainVBox.setPadding(new Insets(10)); // Padding for the main VBox
        poolMainVBox.setMaxWidth(975); // 75% of 1300px
    
        // Fetch the pool data
        
         List<Pool> pools = new ExploreSideDao().fetchThisServicePools(service_id);
    
        for (Pool buyPool : pools) {
    
            String poolType = buyPool.getPoolType();
            String poolState = buyPool.getPoolState();
            boolean isPrivate = buyPool.isPrivate();
            int poolId = buyPool.getPoolId();
            int currentMembers = buyPool.getCurrentMembers();
            int membersAllowed = buyPool.getMembersAllowed();
            //Fetch Plan-Id for PlanName
            int planId = buyPool.getPlanId();
            String planName = new ExploreSideDao().fetchPlanNameFromPlanId(planId);
//Fetch PLan Amount
            double planAmount = new ExploreSideDao().fetchPlanAmountFromPlanId(planId);
//Fetch plan Duration
            int planDuration = new ExploreSideDao().fetchPlanDurationFromPlanId(planId);
            double earningAmount = (membersAllowed-1) * planAmount;

            if(currentMembers == membersAllowed){
                //new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, poolId);
                System.out.println("U r now goinf to veri state");
                new ExploreSideDao().movePoolToVerificationFromActive(poolId);
                new ExploreSideDao().updateCurrentStateToNewState(poolId, "Active", "Verification");
                //new EmailService().sendEmail(email, "Verification", "Your Pool is complete, please verify your pool to make it Live!!!");
            }
            if(poolState.equals("Live")){
                //new EmailService().sendEmail(email, "Live Pool", "Your Pool is Live u will get the Earning's!!! once pool duraiton is complete.");
            }
            if(poolState.equals("Dead")){
                //new EmailService().sendEmail(email, "Pool Ended", "Your Pool is Ended u will get the Earning's!!! Shortly.....");
                int thisPoolHostId = buyPool.getHostId();
                int thisHostUserId = new ExploreSideDao().fetchUserIdFromHostId(thisPoolHostId);
                new WalletDao(email).earningMoney(earningAmount, thisHostUserId, poolId);   
            }
            
            
            // Apply the filtering conditions
            if ((poolType.equals("Standard") && poolState.equals("Active") && !isPrivate) ||
                (poolType.equals("Rental") && poolState.equals("Live") && !isPrivate)) {
    
                // VBox1: Contains the pool image
                VBox vbox1 = new VBox(10);
                vbox1.setPadding(new Insets(5));
                vbox1.setMinWidth(190);
                HBox.setHgrow(vbox1, Priority.ALWAYS);
    
                ImageView serviceImgView = new ImageView();
                new ExploreSideDao().loadServiceImage(service_id, serviceImgView); // Load service image
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
                Label poolName = new Label(buyPool.getPoolName());
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

//Fetch Service Name
                String serviceName = new ExploreSideDao().fetchServiceNameFromServiceId(service_id);
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
                if(buyPool.isPrivate()){
                    privatePoolLabel = new Label("Private");
                }
                Label poolTypeLabel = new Label(buyPool.getPoolType() + " " + privatePoolLabel.getText() + "  Pool");
                poolTypeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-font-weight: bold;");
                vbox3.getChildren().addAll(priceLabel, buyButton, poolTypeLabel);
    
                // VBox4: Contains host image (hardcoded for now)
                VBox vbox4 = new VBox(10);
                vbox4.setPadding(new Insets(5));
                vbox4.setMinWidth(190);
                HBox.setHgrow(vbox4, Priority.ALWAYS);
    
                int poolHostId = buyPool.getHostId();
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
                    BillPopUp(myStage,poolType,serviceName,planAmount,planDuration,user_id,service_id,poolId,currentMembers,membersAllowed,email);
                    
                    
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

    public void BillPopUp(Stage myStage,String poolType,String serviceName,double planAmount,int planDuration,int user_id,int service_id,int pool_id,int currentMembers,int membersAllowed,String email) {
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
    
        // Labels
        Label billLabel = new Label("Billing Details:  "+ serviceName+" Pool");
        billLabel.setStyle("-fx-font-size:16px;-fx-font-weight:bold;-fx-text-fill:black;");

        Label multiLineLabel = new Label("You will be informed once the pool is Live.\nStandard pool are Live when pool is complete.\nThen your subscription starts.\nElse go for Rental Pool's amd start using Directly.");
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
                new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, pool_id);   
                new WalletDao(email).purchaseDebitMoney(netPurchaseAmount, user_id,pool_id);
                //new EmailService().sendEmail(email, "Welcome!", "Thank you for purchasing from TheSubscription! You will be notified when the Pool is Live.");
                if(currentMembers == membersAllowed){
                    //new ExploreSideDao().insertMemberIntoPoolAndUpdateMemberCount(user_id, service_id, poolId);
                    System.out.println("U r now goinf to veri state");
                    new ExploreSideDao().movePoolToVerificationFromActive(pool_id);
                    new ExploreSideDao().updateCurrentStateToNewState(pool_id, "Active", "Verification");
                    //new EmailService().sendEmail(email, "Verification", "Your Pool is complete, please verify your pool to make it Live!!!");
                }
                
                     
                
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
