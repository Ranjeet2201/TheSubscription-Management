package main.java.com.theorigin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AuthenticationDao extends DatabaseCredentials{
    // static String nameOfUser;
    // static final String URL = "jdbc:mysql://localhost:3306/thesubscription_prototype";
    // static final String UserName = "root"; 
    // static final String password = "RANvb@22";

    public int insertUserData(String name,String username,String email,String mobileNo,String pass){
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet generatedKey = null;
        
        int generatedUserId = -1;
        try {
            System.out.println("In Fun");
          //  Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,UserName,password);
            System.out.println("In insert User Function");
            String sql = "INSERT INTO user_info(name,username,email,mobile_no,password) VALUES(?,?,?,?,?)";
            prepare = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            prepare.setString(1, name);
            prepare.setString(2, username);
            prepare.setString(3, email);
            prepare.setString(4, mobileNo);
            prepare.setString(5, pass);
            System.out.println("Query 1 Success");
            prepare.executeUpdate();
            System.out.println("Query Successfully executed");
            generatedKey = prepare.getGeneratedKeys();
            if(generatedKey.next()){
                generatedUserId = generatedKey.getInt(1);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record inserted successfully");
                alert.showAndWait();
                
 
            }
        } catch (SQLException  ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error : " + ex.getMessage());
        } finally {
            try {
                if(prepare != null) prepare.close();
                if(conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return generatedUserId;
    }

    public int authenticateUser(String username, String passWord) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int fetched_id = -1;
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, UserName, password);
            String sql = "SELECT * FROM user_info WHERE username = ? AND password = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, passWord);
            ResultSet resultSet = prepare.executeQuery();
            
            if (resultSet.next()) {  // Check if there is any result
                fetched_id = resultSet.getInt("user_id");
                nameOfUser = resultSet.getString("username");
                System.out.println(nameOfUser);
                // Show success alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Login Successfully");
                alert.showAndWait();
                
            } else {
                // Show invalid credentials alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Credentials");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Username or Password");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Show error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("error : " + ex.getMessage());
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return fetched_id;
    }

    public String fetchUserName(){
        return nameOfUser;
    }

    public String fetchMailId(String username) {
        Connection conn = null;
        PreparedStatement prepare = null;
        String fetchedMailId = "";
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT email FROM user_info WHERE username = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, username);  // Set the service name
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedMailId = resultSet.getString("email");  // Get the service_id
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mail Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Mail with name " + username + " not found.");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return fetchedMailId;
    }

    
}
