package main.java.com.theorigin.controller;
import java.io.File;

import javafx.stage.Stage;
import main.java.com.theorigin.dao.ExploreSideDao;
import main.java.com.theorigin.dao.HostSideDao;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.view.TheSubWallet;
import main.java.com.theorigin.view.createWallet;
public class ProfilePageController {
    public void updateProfile(){

    }
    public void favorite(){

    }
    public void logout(Stage primaryStage){

    }
    public void allHostedPools(){

    }
    public void allBuyedPools(){

    }
    public void yourRatings(){

    }
    public void navigateToWallet(Stage myStage,int user_id,String email){
        System.out.println("Userid in Wallet"+user_id);
        if(new WalletDao(email).doesUserIdExist(user_id)){
            new TheSubWallet(user_id,email).start(myStage);
        }else{
            new createWallet(user_id,email).start(myStage);
        }
    }
    public void navigateToSaveUserImg(int user_id,File selectedFile){
        new ExploreSideDao().saveUserImage(user_id, selectedFile);
    }
}
