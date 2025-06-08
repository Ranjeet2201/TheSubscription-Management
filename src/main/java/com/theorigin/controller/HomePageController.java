package main.java.com.theorigin.controller;
import javafx.stage.Stage;
import main.java.com.theorigin.view.ProfilePage;
import main.java.com.theorigin.view.SubscriptionPlatforms;
import main.java.com.theorigin.view.Explore;
//import com.theorigin.view.Explore;
import main.java.com.theorigin.view.HostPage;
import main.java.com.theorigin.view.TheSubChat;

public class HomePageController {
    public void navigateToProfilePage(Stage secondaryStage,int user_id,String email){
        System.out.println("In profilePage");
        
        ProfilePage profilePageObj=new ProfilePage(user_id,email);
        profilePageObj.start(secondaryStage);
    }
    public void navigateToMemberPage(Stage secondaryStage){
        System.out.println("In MemberShipPage");
    }
    public  void navigateToHostPage(Stage secondaryStage,int user_id,String email){
        System.out.println("HomePgController" + user_id);
        HostPage hostPageObj = new HostPage(user_id,email);
        hostPageObj.start(secondaryStage);
    }
    public  void navigateToExplorePage(Stage secondaryStage,int user_id,String email){
        System.out.println("In Explore Page");
        Explore exploreObj = new Explore(user_id,email);
        exploreObj.start(secondaryStage);
    }
    public  void navigateToChatPage(Stage secondaryStage){
        System.out.println("In chat Section");
        TheSubChat theSubChatObj=new TheSubChat();
        theSubChatObj.start(secondaryStage);
    }
    void notificationDirector(){
        System.out.println("redirect to  the notification sender");
    }
}
