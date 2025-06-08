package main.java.com.theorigin.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.com.theorigin.service.EmailService;

public class WalletDao extends DatabaseCredentials {

    String email;

    public WalletDao(String email){
        this.email = email;
    }

    public int getWalletId(int user_id){
        String query = "SELECT wallet_id from thesubwallet WHERE user_id =?";
        int wallet_id = 0;

        try(Connection connection = DriverManager.getConnection(URL,UserName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setInt(1,user_id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    wallet_id = resultSet.getInt("wallet_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet_id;
    }

    public double getTotalBalance(int user_id){
        String query = "SELECT total_balance from thesubwallet WHERE user_id =?";
        double totalBalance = 0.0;

        try(Connection connection = DriverManager.getConnection(URL,UserName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setInt(1,user_id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    totalBalance = resultSet.getDouble("total_balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBalance;
    } 
    public static boolean doesUserIdExist(int userId) {
        String query = "SELECT COUNT(*) AS user_count FROM thesubwallet WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertWalletData(int user_id, String panName, int mobileNum, String panNum, String birthDate) {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet resultSet = null;
        int total_balance = 0;
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Check if the user_id exists in user_info table
            String checkUserSql = "SELECT COUNT(*) FROM user_info WHERE user_id = ?";
            prepare = conn.prepareStatement(checkUserSql);
            prepare.setInt(1, user_id);
            resultSet = prepare.executeQuery();
            resultSet.next();
            boolean userExists = resultSet.getInt(1) > 0;
            
            if (!userExists) {
                System.out.println("User ID does not exist: " + user_id);
                return false;
            }
            
            // Insert new record into thesubwallet table
            String insertSql = "INSERT INTO thesubwallet (user_id, pan_name, mob_num, pan_num, dob,total_balance) VALUES (?, ?, ?, ?, ?,?)";
            prepare = conn.prepareStatement(insertSql);
            prepare.setInt(1, user_id);
            prepare.setString(2, panName);
            prepare.setInt(3, mobileNum);
            prepare.setString(4, panNum);
            prepare.setString(5, birthDate); // Ensure birthDate is in the format 'YYYY-MM-DD'
            prepare.setInt(6, total_balance);
            int affectedRows = prepare.executeUpdate();
            
            // Check if insertion was successful
            if (affectedRows > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record inserted successfully");
                alert.showAndWait();
                return true;
            } else {
                System.out.println("No rows affected.");
                return false;
            }
            
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
   

    public boolean insertTransactionData(int wallet_id, double transactionAmount, String transactionMode, String transactionType, Integer pool_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Insert new record into transactions table
            String insertSql = "INSERT INTO transactions (wallet_id, pool_id, transaction_amount, transaction_mode, transaction_type, transaction_time) VALUES (?, ?, ?, ?, ?, NOW())";
            prepare = conn.prepareStatement(insertSql);
            prepare.setInt(1, wallet_id);
            
            if (pool_id != null) {
                prepare.setInt(2, pool_id); // Set pool_id if not null
            } else {
                prepare.setNull(2, java.sql.Types.INTEGER); // Set pool_id as NULL if pool_id is null
            }
            
            prepare.setDouble(3, transactionAmount);
            prepare.setString(4, transactionMode);
            prepare.setString(5, transactionType);
    
            int affectedRows = prepare.executeUpdate();
            
            // Check if insertion was successful
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record inserted successfully");
                alert.showAndWait();
                return true;
            } else {
                System.out.println("No rows affected.");
                return false;
            }
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    

    public void updateTotalBalance(int user_id,double transactionAmount){
        
        try{
            // double currentBalance = getTotalBalance(user_id);

            // double newBalance = currentBalance + (transactionAmount);

            String updateQuery = "UPDATE thesubwallet SET total_balance = total_balance + ? WHERE user_id = ?";
                try(Connection connection = DriverManager.getConnection(URL,UserName,password);
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                
                updateStmt.setDouble(1,transactionAmount);
                updateStmt.setInt(2,user_id);

                int rowsAffected = updateStmt.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Total Balance Updated Successfully");
                } else {
                    System.out.println("Wallet ID not found");
                }
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean creditMoney(double amount,int user_id){
        int wallet_id = getWalletId(user_id);
        insertTransactionData(wallet_id, amount, "UPI", "Credit",null);
        updateTotalBalance(user_id, (+amount)); 
        //new EmailService().sendEmail(email, "Money Credit Successfully!", "(₹) "+ amount + "  from your Bank Account has been successfully credit to TheSubscription Wallet!");
        System.out.println("Money credited Sucessfully");
        return true;
        
    }
    public boolean purchaseDebitMoney(double amount,int user_id,int pool_id){
        int wallet_id = getWalletId(user_id);
        insertTransactionData(wallet_id, amount, "UPI", "Purchase",pool_id);
        updateTotalBalance(user_id, (-amount)); 
        //new EmailService().sendEmail(email, "Purchase Successful!", "(₹) "+ amount + "  from your TheSub Wallet has been successfully Debit!.");
        System.out.println("Purchase Sucessfully");
        return true;
    }
    public boolean redeemMoney(double amount,int user_id){
        int wallet_id = getWalletId(user_id);
        insertTransactionData(wallet_id, amount, "UPI", "Redeem",null);
        updateTotalBalance(user_id, -amount); 
        //new EmailService().sendEmail(email, "Money Redeem Successfully!", "(₹) "+ amount + "  from your TheSub Wallet has been successfully credit to your Bank Account");
        System.out.println("Money Redeemed Sucessfully");
        return true;
        
    }
    public boolean earningMoney(double amount,int user_id,int pool_id){
        int wallet_id = getWalletId(user_id);
        insertTransactionData(wallet_id, amount, "UPI", "Earning",pool_id);
        updateTotalBalance(user_id, (+amount)); 
        //new EmailService().sendEmail(email, "Earnings Added Successfully!", "(₹) "+ amount + "  is added to your TheSubscription Wallet! as your Host Earnings!");
        System.out.println("Earning Added Sucessfully");
        return true;
    }
    public boolean refundMoney(double amount,int user_id,int pool_id){
        int wallet_id = getWalletId(user_id);
        insertTransactionData(wallet_id, amount, "UPI", "Refund",pool_id);
        updateTotalBalance(user_id, (+amount)); 
        //new EmailService().sendEmail(email, "Refund Successful!", "(₹) "+ amount + "   has been successfully refunded to TheSubscription Wallet!");
        System.out.println("Refund Sucessfully");
        return true;
    }
    public Map<Integer,Double> getTranscationHistory(int user_id){
        //Bussiness logic 
        //Below just trail 
        Map<Integer,Double> transactionMap=new HashMap<>();
        transactionMap.put(1, 100.00);
        return transactionMap;
    }
    public Map<Integer,Double> getDebitsHistory(int user_id){
        //Bussiness logic 
        //Below just trail 
        Map<Integer,Double> debitsMap=new HashMap<>();
        debitsMap.put(1, 100.00);
        return debitsMap;
    }

    public Map<Integer,Double> getCreditHistory(int user_id){
        //Bussiness logic 
        //Below just trail 
        Map<Integer,Double> creditHistoryMap=new HashMap<>();
        creditHistoryMap.put(1, 100.00);
        creditHistoryMap.put(2, 200.00);
        creditHistoryMap.put(3, 300.00);
        creditHistoryMap.put(4, 500.00);
        return creditHistoryMap;
    }
    public Map<Integer,Double> getRefundHistory(int user_id){
        //Bussiness logic 
        //Below just trail 
        Map<Integer,Double> refundMap=new HashMap<>();
        refundMap.put(1, 100.00);
        return refundMap;
    }
}
