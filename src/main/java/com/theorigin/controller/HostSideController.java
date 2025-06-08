package main.java.com.theorigin.controller;

import main.java.com.theorigin.service.HostSideService;
import main.java.com.theorigin.view.HostForm;
import main.java.com.theorigin.view.HostPage;
import main.java.com.theorigin.view.Pools;
import main.java.com.theorigin.view.SubscriptionPlatforms;
import main.java.com.theorigin.view.TheSubChat;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class HostSideController {
    //Pools poolsObj = new Pools();
    //HostPage hostPageObj = new HostPage();
    HostSideService hostSideServiceObj = new HostSideService();
    int host_id = 1;
    int service_id = 3;
    
    public void navigateToHostExplorePage(Stage myStage) {
        System.out.println("This is Host Explore Page");
        
    }

    public void navigateToHostForm(Stage myStage,int user_id,String btnText,String email){
        System.out.println("HostController " + user_id);
        myStage.setScene(new HostForm(user_id,btnText,email).initialize(myStage));

    }
    public void navigateToHostFormData(int user_id,String poolNameText,String serviceName,String planName,int membersAllowed,String poolType,String poolState,Boolean isPrivate ){
        System.out.println(user_id);
        new HostSideService().hostFormService(user_id,poolNameText,serviceName,planName,membersAllowed,poolType,poolState,isPrivate);
    }

    public void navigateToChat(Stage myStage){
        System.out.println("Navigate to Chat");
        Scene chatScene = new Scene(new TheSubChat().initialize(myStage));
        myStage = new Stage();
        myStage.setScene(chatScene);
        myStage.show();
    }
    // public void navigateToHostHistory(Stage myStage){
    //     System.out.println("This is HOstHistory PAge.");
    //     Scene hostScene = new Scene(HostPage.hostHistoryTabs(myStage));
    //     myStage.setScene(hostScene);
    //     myStage.show();
    // }
    public void navigateToAvailabelPoolsForMerger(Stage myStage){
        System.out.println("This is availabel Pools for merger.");
        Scene availablePoolsforMergerScene = new Scene(Pools.availablePoolsForMerger("SendRequest"), 1000, 500);
        myStage = new Stage();
        myStage.setScene(availablePoolsforMergerScene);
        myStage.show();
    }
    // public Node showHostLivePools(int host_id){
    //     ScrollPane livePoolsScrollPane = Pools.createLivePools("Chat");
    //     hostSideServiceObj.fetchHostLivePools(host_id);
    //     System.out.println("This is Live Pools.");
    //     return livePoolsScrollPane;
    // }
    // public Node showUserLivePools(int host_id){
    //     ScrollPane livePoolsScrollPane = new SubscriptionPlatforms(1,1).createBuyPools("Chat");
    //     hostSideServiceObj.fetchHostLivePools(host_id);
    //     System.out.println("This is Live Pools.");
    //     return livePoolsScrollPane;
    // }
    public Node showHostActivePools(int host_id){
        ScrollPane activePoolsScrollPane = new ScrollPane();
        hostSideServiceObj.fetchHostActivePools(host_id);
        System.out.println("This is Active Pools.");
        return activePoolsScrollPane;
    }
    // public Node showHostMergerPools(int host_id){
    //     TabPane mergerPoolsTabPane = HostPage.mergerMainPage();
    //     hostSideServiceObj.fetchHostMergerPools(host_id);
    //     System.out.println("This is Merger Pools.");
    //     return mergerPoolsTabPane;
    // }

    // public Node showHostVerificationPools(int host_id){
    //     ScrollPane verificationPoolsScrollPane = new Pools().createTwoBtnPools("Verify","Cancel");
    //     hostSideServiceObj.fetchHostVerificationPools(host_id);
    //     System.out.println("This is Verification pool.");
    //     return verificationPoolsScrollPane;
    // }
    public Node showHostDeadPools(int host_id){
        hostSideServiceObj.fetchHostDeadPools(host_id);
        System.out.println("This is DeaDpool.");
        return null;
    }

    

    public void addToPool(){
        
    }
    

}
