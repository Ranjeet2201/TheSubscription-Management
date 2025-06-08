package main.java.com.theorigin.controller;

import main.java.com.theorigin.view.HomePage;
import main.java.com.theorigin.view.LoginPage;
import main.java.com.theorigin.view.SignupPage;

import javafx.stage.Stage;

public class AuthenticationController {


    public void navigateToLoginPage(Stage myStage){
        LoginPage loginPageObj = new LoginPage();
        loginPageObj.start(myStage);
    }
    public void navigateToSignupPage(Stage myStage){
        SignupPage SignupPageObj = new SignupPage();
        SignupPageObj.start(myStage);
    }

    public void navigateToHomepage(Stage myStage, int user_id,String email) {
        HomePage homePageObj = new HomePage(user_id,email);
        homePageObj.start(myStage);
    }

}
