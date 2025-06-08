package main.java.com.theorigin.service;

import main.java.com.theorigin.controller.AuthenticationController;
import main.java.com.theorigin.dao.AuthenticationDao;
import main.java.com.theorigin.view.LoginPage;
import javafx.stage.Stage;

public class AuthenticationService {
    public void signUpUserData(String name,String username,String email,String mobileNo,String password,Stage myStage){

        AuthenticationDao authenticationDaoObj = new AuthenticationDao();
        int id = authenticationDaoObj.insertUserData(name,username,email,mobileNo,password);
        if (id != -1) {
            AuthenticationController authenticationControllerObj = new AuthenticationController();
            authenticationControllerObj.navigateToLoginPage(myStage);
            
            // Check if the email is not null
            if (email != null && !email.isEmpty()) {
                //new EmailService().sendEmail(email, "Welcome!", "Thank you for signing up.");
            } else {
                System.out.println("Error: Email is null or empty");
            }
   
        }
        System.out.println("insertData Dao Called");
        
    }   

    public int authenticateAndFetchUser(String username,String password){
        System.out.println("Entered auth Service");
        int fetched_id = new AuthenticationDao().authenticateUser(username,password);
        return fetched_id;
    }

    
}
