package main.java.com.theorigin.controller;

import main.java.com.theorigin.view.SubscriptionPlatforms;

import javafx.stage.Stage;

public class ExplorePageController {
    public void navigateToThisSubscriptionPlatform(Stage primaryStage,int user_id,int service_id,String email){
        System.out.println("Going to Subscription PLatform");
        SubscriptionPlatforms subscriptionPlatformsObj = new SubscriptionPlatforms(user_id,service_id,email);
        subscriptionPlatformsObj.start(primaryStage);
    }




    
}
