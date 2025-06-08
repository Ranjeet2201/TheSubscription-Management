package main.java.com.theorigin.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExploreSideDao extends DatabaseCredentials {

    public List<Pool> fetchThisServicePools(int serviceId) {
        List<Pool> poolList = new ArrayList<>();

        String query = "SELECT plan_id, host_id, pool_id, pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE service_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, serviceId);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    int planId = rs.getInt("plan_id");
                    int hostId = rs.getInt("host_id");
                    int poolId = rs.getInt("pool_id");
                    String poolName = rs.getString("pool_name");
                    int currentMembers = rs.getInt("current_members");
                    int membersAllowed = rs.getInt("members_allowed");
                    String poolType = rs.getString("pool_type");
                    String poolState = rs.getString("pool_state");
                    boolean isPrivate = rs.getBoolean("is_private");

                    Pool poolObj = new Pool(planId,hostId,poolId, poolName, currentMembers, membersAllowed, poolType, poolState, isPrivate);
                    poolList.add(poolObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        return poolList;
    }

    
    //This is from Profile Dao but here for now....Save UserImage Query
    public void saveUserImage(int user_id,File selectedFile) {
        if (selectedFile != null) {
            try (Connection conn = DriverManager.getConnection(URL, UserName, password);
                FileInputStream fis = new FileInputStream(selectedFile)) {

                String sql = "UPDATE user_info SET user_img = ? WHERE user_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setBinaryStream(1, fis, (int) selectedFile.length());
                preparedStatement.setInt(2, user_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Image saved to database successfully.");
                } else {
                    System.out.println("Failed to save image.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
    public void loadUserImage(int user_id, ImageView imageView) {
        try (Connection conn = DriverManager.getConnection(URL, UserName, password)) {
            String sql = "SELECT user_img FROM user_info WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("user_img");
                if (is != null) {
                    Image img = new Image(is);
                    imageView.setImage(img);
                } else {
                    System.out.println("No image found for user_id: " + user_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadServiceImage(int service_id, ImageView imageView) {
        try (Connection conn = DriverManager.getConnection(URL, UserName, password)) {
            String sql = "SELECT service_logo FROM services WHERE service_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, service_id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("service_logo");
                if (is != null) {
                    Image img = new Image(is);
                    imageView.setImage(img);
                    // Restrict the size of the ImageView
                    imageView.setFitWidth(200);  // Set the desired width
                    imageView.setFitHeight(200); // Set the desired height
                    imageView.setPreserveRatio(true); // Maintain the aspect ratio
                } else {
                    System.out.println("No image found for service_id: " + service_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public int fetchUserIdFromHostId(int host_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int fetchedUserId = -1;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT user_id FROM host_info WHERE host_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, host_id);  // Set the hostId
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedUserId = resultSet.getInt("user_id");  // Get the service_id
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("UserId  Not Found");
                alert.setHeaderText(null);
                alert.setContentText("UserID of  " + host_id + " not found.");
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

        return fetchedUserId;
    }

    
    public  String fetchPlanNameFromPlanId(int plan_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        String fetchedPlanName = "";
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT plan_name FROM plans WHERE plan_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, plan_id);  // Set the plan_id
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedPlanName = resultSet.getString("plan_name");  // Get the planName
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("PlanName  Not Found");
                alert.setHeaderText(null);
                alert.setContentText("PlanName of  " + plan_id + " not found.");
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

        return fetchedPlanName;
    }

    public  int fetchPlanAmountFromPlanId(int plan_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int fetchedPlanAmount = -1;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT amount FROM plans WHERE plan_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, plan_id);  // Set the plan_id
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedPlanAmount = resultSet.getInt("amount");  // Get the planName
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Amount  Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Amount of  " + plan_id + " not found.");
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

        return fetchedPlanAmount;
    }

    public  int fetchPlanDurationFromPlanId(int plan_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int fetchedPlanDuration = -1;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            
            String sql = "SELECT duration FROM plans WHERE plan_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, plan_id);  // Set the plan_id
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedPlanDuration = resultSet.getInt("duration");  // Get the planDuration
            } else {
                // Show alert if duraitonnot found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duration  Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Duration of  " + plan_id + " not found.");
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

        return fetchedPlanDuration;
    }

    public  int fetchServiceIdFromPlanId(int plan_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int fetchedServiceId = -1;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT service_id FROM plans WHERE plan_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, plan_id);  // Set the plan_id
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedServiceId = resultSet.getInt("service_id");  // Get the planName
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ServiceId Not Found");
                alert.setHeaderText(null);
                alert.setContentText("ServiceId of  " + plan_id + " not found.");
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

        return fetchedServiceId;
    }


    public  String fetchServiceNameFromServiceId(int service_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        String fetchedServiceName = "";
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // SQL query to fetch the service_id where service_name is given
            String sql = "SELECT service_name FROM services WHERE service_id = ?";
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, service_id);  // Set the plan_id
            
            ResultSet resultSet = prepare.executeQuery();
            
            // Check if there is any result
            if (resultSet.next()) {
                fetchedServiceName = resultSet.getString("service_name");  // Get the planName
            } else {
                // Show alert if service not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ServiceName  Not Found");
                alert.setHeaderText(null);
                alert.setContentText("ServiceName of  " + service_id + " not found.");
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

        return fetchedServiceName;
    }    


//Buying means ther is space in pool so this 2 together will always work....
    public static void insertMemberIntoPoolAndUpdateMemberCount(int user_id, int service_id, int pool_id) {
        Connection conn = null;
        PreparedStatement insertPrepare = null;
        PreparedStatement updatePrepare = null;
        int member_id = user_id;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Begin transaction
            conn.setAutoCommit(false);  // Disable auto-commit for transaction

            // SQL query to insert a new member into pool_members table
            String insertSQL = "INSERT INTO pool_members (service_id, pool_id, member_id) VALUES (?, ?, ?)";
            insertPrepare = conn.prepareStatement(insertSQL);

            // Set the values for the placeholders for pool_members table
            insertPrepare.setInt(1, service_id);  // service_id
            insertPrepare.setInt(2, pool_id);     // pool_id
            insertPrepare.setInt(3, member_id);   // member_id

            // Execute the insert into pool_members
            int rowsInserted = insertPrepare.executeUpdate();
            
            if (rowsInserted > 0) {
                // SQL query to update the current_member count in thepools table
                String updateSQL = "UPDATE thepools SET current_members = current_members + 1 WHERE pool_id = ?";
                updatePrepare = conn.prepareStatement(updateSQL);
                
                // Set the pool_id for the update query
                updatePrepare.setInt(1, pool_id);
                
                // Execute the update on thepools table
                int rowsUpdated = updatePrepare.executeUpdate();

                // Commit the transaction if both insert and update are successful
                if (rowsUpdated > 0) {
                    conn.commit();  // Commit transaction

                    // Show success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Member successfully added to pool and member count updated.");
                    alert.showAndWait();
                } else {
                    // If update fails, rollback the transaction
                    conn.rollback();

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to update member count in thepools.");
                    alert.showAndWait();
                }
            } else {
                // If insert fails, rollback the transaction
                conn.rollback();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert Failed");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add member to pool.");
                alert.showAndWait();
            }

        } catch (SQLException | ClassNotFoundException ex) {
            try {
                if (conn != null) {
                    conn.rollback();  // Rollback transaction on error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (insertPrepare != null) insertPrepare.close();
                if (updatePrepare != null) updatePrepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
// When Curr = Total Members then From active to Live for Standard pools Rental are already live...

    public boolean movePoolToVerificationFromActive(int poolId) {
        Connection conn = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement insertStmt = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);            
            // Begin transaction
            conn.setAutoCommit(false);

            // Delete from active_pools table
            String deleteQuery = "DELETE FROM active_pools WHERE pool_id = ?";
            deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, poolId);
            deleteStmt.executeUpdate();
            
            // Insert into verification_pools table
            String insertQuery = "INSERT INTO verification_pools (pool_id) VALUES (?)";
            insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, poolId);
            
            
            insertStmt.executeUpdate();

            // Commit transaction
            conn.commit();
            
            return true; // If both operations were successful
            
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback transaction if something goes wrong
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false; // If any operation failed
        } finally {
            // Close statements
            try {
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean movePoolToLiveFromVerification(int poolId,int duration) {
        Connection conn = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement insertStmt = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);            
            // Begin transaction
            conn.setAutoCommit(false);

            // Delete from active_pools table
            String deleteQuery = "DELETE FROM verification_pools WHERE pool_id = ?";
            deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, poolId);
            deleteStmt.executeUpdate();

        
            // SQL query for insertion
            String insertQuery = "INSERT INTO live_pools (pool_id, start_date, end_date) VALUES (?, ?, ?)";
            
            // Get the current date and calculate end date
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(duration);
            
                insertStmt = conn.prepareStatement(insertQuery);
                // Set parameters
                insertStmt.setInt(1, poolId);                // Set pool_id
                insertStmt.setDate(2, Date.valueOf(startDate));  // Set start_date as current date
                insertStmt.setDate(3, Date.valueOf(endDate));    // Set end_date as start_date + duration
            
            insertStmt.executeUpdate();

            // Commit transaction
            conn.commit();
            
            return true; // If both operations were successful
            
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback transaction if something goes wrong
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false; // If any operation failed
        } finally {
            // Close statements
            try {
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean movePoolToDeadFromLive(int poolId) {
        Connection conn = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement insertStmt = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);            
            // Begin transaction
            conn.setAutoCommit(false);

            // Delete from active_pools table
            String deleteQuery = "DELETE FROM live_pools WHERE pool_id = ?";
            deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, poolId);
            deleteStmt.executeUpdate();

        
            // SQL query for insertion
            String insertQuery = "INSERT INTO dead_pools (pool_id) VALUES (?)";
        
            
            insertStmt = conn.prepareStatement(insertQuery);
            // Set parameters
            insertStmt.setInt(1, poolId);                // Set pool_id
            
            insertStmt.executeUpdate();

            // Commit transaction
            conn.commit();
            
            return true; // If both operations were successful
            
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback transaction if something goes wrong
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false; // If any operation failed
        } finally {
            // Close statements
            try {
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateCurrentStateToNewState(int poolId, String currentState, String newState) {
        Connection conn = null;
        PreparedStatement updateStmt = null;
        boolean isUpdated = false;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Update the pool_state where it matches the current state and pool_id
            String updateQuery = "UPDATE thepools SET pool_state = ? WHERE pool_id = ? AND pool_state = ?";
            updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, newState);    // Set the new state
            updateStmt.setInt(2, poolId);         // Set the pool_id
            updateStmt.setString(3, currentState); // Set the current state
            
            int rowsUpdated = updateStmt.executeUpdate();
            
            // If at least one row is updated, set isUpdated to true
            isUpdated = rowsUpdated > 0;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (updateStmt != null) {
                    updateStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return isUpdated;
    }
    







    public boolean isUserMemberOfPool(int user_id, int pool_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        boolean isMember = false;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
    
            // SQL query to check if the user is already a member of the pool
            String sql = "SELECT COUNT(*) FROM pool_members WHERE pool_id = ? AND member_id = ?";
            prepare = conn.prepareStatement(sql);
    
            // Set the values for the placeholders (pool_id and user_id)
            prepare.setInt(1, pool_id);   // pool_id
            prepare.setInt(2, user_id);   // member_id (user_id)
    
            // Execute the query
            ResultSet resultSet = prepare.executeQuery();
    
            // If the count is greater than 0, it means the user is already a member of the pool
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                isMember = true;  // User is a member of the pool
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
    
        return isMember;  // Return true if user is a member, false otherwise
    }
    


    
}


