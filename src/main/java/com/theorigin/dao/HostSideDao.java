package main.java.com.theorigin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;

//Import for saving Image in Databse.
import java.io.FileInputStream;
import java.io.InputStream;
//IMports to load user Img from database
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.com.theorigin.service.EmailService;

public class HostSideDao extends DatabaseCredentials{

        private Timer timer;
        ActivePool activePoolObj;
        LivePool livePoolObj;

        //int memberAllowed;
        //String planName;

        
        public int fetchHostIdFromUserId(int user_id) {
            Connection conn = null;
            PreparedStatement prepare = null;
            int fetchedHostId = -1;
            
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish connection
                conn = DriverManager.getConnection(URL, UserName, password);
                
                // SQL query to fetch the service_id where service_name is given
                String sql = "SELECT host_id FROM host_info WHERE user_id = ?";
                prepare = conn.prepareStatement(sql);
                prepare.setInt(1, user_id);  // Set the hostId
                
                ResultSet resultSet = prepare.executeQuery();
                
                // Check if there is any result
                if (resultSet.next()) {
                    fetchedHostId = resultSet.getInt("host_id");  // Get the service_id
                } else {
                    // Show alert if service not found
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("HostId  Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("HostID of  " + user_id + " not found.");
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
    
            return fetchedHostId;
        }
    

        public int fetchServiceId(String serviceName) {
            Connection conn = null;
            PreparedStatement prepare = null;
            int fetchedServiceId = -1;
            
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish connection
                conn = DriverManager.getConnection(URL, UserName, password);
                
                // SQL query to fetch the service_id where service_name is given
                String sql = "SELECT service_id FROM services WHERE service_name = ?";
                prepare = conn.prepareStatement(sql);
                prepare.setString(1, serviceName);  // Set the service name
                
                ResultSet resultSet = prepare.executeQuery();
                
                // Check if there is any result
                if (resultSet.next()) {
                    fetchedServiceId = resultSet.getInt("service_id");  // Get the service_id
                } else {
                    // Show alert if service not found
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Service Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Service with name " + serviceName + " not found.");
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

        public int fetchPlanId(String planName) {
            Connection conn = null;
            PreparedStatement prepare = null;
            int fetchedPlanId = -1;
            
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish connection
                conn = DriverManager.getConnection(URL, UserName, password);
                
                // SQL query to fetch the service_id where plan_name is given
                String sql = "SELECT plan_id FROM plans WHERE plan_name = ?";
                prepare = conn.prepareStatement(sql);
                prepare.setString(1, planName);  // Set the plan name
                
                ResultSet resultSet = prepare.executeQuery();
                
                // Check if there is any result
                if (resultSet.next()) {
                    fetchedPlanId = resultSet.getInt("plan_id");  // Get the plan_id
                } else {
                    // Show alert if plan not found
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("PlanId Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Plan with name " + planName + " not found.");
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
    
            return fetchedPlanId;
        }

        public Map<String, Integer> fetchPlanDetails(int serviceId) {
            Connection conn = null;
            PreparedStatement prepare = null;
            Map<String, Integer> planDetailsMap = new HashMap<>();

            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish connection
                conn = DriverManager.getConnection(URL, UserName, password);
                
                // SQL query to fetch plan_name and member_allowed where service_id is given
                String sql = "SELECT plan_name, member_allowed FROM plans WHERE service_id = ?";
                prepare = conn.prepareStatement(sql);
                prepare.setInt(1, serviceId);  // Set the service_id
                
                ResultSet resultSet = prepare.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    String planName = resultSet.getString("plan_name");
                    int memberAllowed = resultSet.getInt("member_allowed");
                    
                    // Add plan name and member allowed to the map
                    planDetailsMap.put(planName, memberAllowed);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                // Handle exceptions
                System.out.println("Error: " + ex.getMessage());
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return planDetailsMap;
    }

    // public Map<String, Integer> getPlanDetails(int serviceId) {
    //     return fetchPlanDetails(serviceId);
    // }
    


    public void getHostLivePools(int host_id){
        System.out.println("This is Live Pools.");
        
    }
    public void getHostActivePools(int host_id){
        System.out.println("This is Active Pools.");
    
    }
    // // Fetch all pool_ids from the thepools table where host_id = ?
    // public static List<Integer> fetchHostActivePoolIds(int hostId) {
    //     System.out.println("Inside Fetch Host Active PoolIDs");
    //     List<Integer> poolIds = new ArrayList<>(); // Use a list to store multiple pool_ids
    //     String query = "SELECT pool_id FROM thepools WHERE host_id=?";

    //     try (Connection conn = DriverManager.getConnection(URL, UserName, password);
    //         PreparedStatement stmt = conn.prepareStatement(query)) {

    //         // Bind the host_id to the query
    //         stmt.setInt(1, hostId);

    //         // Execute the query
    //         try (ResultSet rs = stmt.executeQuery()) {
    //             // Loop through the result set and add each pool_id to the list
    //             while (rs.next()) {
    //                 int poolId = rs.getInt("pool_id");
    //                 poolIds.add(poolId);  // Add each pool_id to the list
    //                 System.out.println("Pool ID: " + poolId); // You can keep this for debugging
    //             }
    //         }

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }

    //     return poolIds; // Return the list of pool_ids
    // }
        
    public List<ActivePool> fetchThisHostActivePools(int hostId) {
        
        System.out.println("Inside Fetch Host Active Poolssssss");
        List<ActivePool> poolActiveList = new ArrayList<>();

        String query = "SELECT service_id,plan_id, pool_id,pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE host_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, hostId);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    int serviceId = rs.getInt("service_id");
                    int planId = rs.getInt("plan_id");
                    int poolId = rs.getInt("pool_id");
                    String poolName = rs.getString("pool_name");
                    int currentMembers = rs.getInt("current_members");
                    int membersAllowed = rs.getInt("members_allowed");
                    String poolType = rs.getString("pool_type");
                    String poolState = rs.getString("pool_state");
                    boolean isPrivate = rs.getBoolean("is_private");

                    activePoolObj = new ActivePool(serviceId,planId,poolId,poolName, currentMembers, membersAllowed, poolType, poolState, isPrivate);
                    poolActiveList.add(activePoolObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        return poolActiveList;
    }

    public List<LivePool> fetchThisHostLivePools(int hostId) {
        
        System.out.println("Inside Fetch Host Live Poolssssss");
        List<LivePool> poolLiveList = new ArrayList<>();

        String query = "SELECT service_id,plan_id, pool_id,pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE host_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, hostId);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    int serviceId = rs.getInt("service_id");
                    int planId = rs.getInt("plan_id");
                    int poolId = rs.getInt("pool_id");
                    String poolName = rs.getString("pool_name");
                    int currentMembers = rs.getInt("current_members");
                    int membersAllowed = rs.getInt("members_allowed");
                    String poolType = rs.getString("pool_type");
                    String poolState = rs.getString("pool_state");
                    boolean isPrivate = rs.getBoolean("is_private");

                    livePoolObj = new LivePool(serviceId,planId,poolId,poolName, currentMembers, membersAllowed, poolType, poolState, isPrivate);
                    poolLiveList.add(livePoolObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        return poolLiveList;
    }

    public static  void saveVerificationImage(int pool_id,File selectedFile) {
        if (selectedFile != null) {
            try (Connection conn = DriverManager.getConnection(URL, UserName, password);
                FileInputStream fis = new FileInputStream(selectedFile)) {

                String sql = "UPDATE verification_pools SET img_ver = ? WHERE pool_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setBinaryStream(1, fis, (int) selectedFile.length());
                preparedStatement.setInt(2, pool_id);

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

    public void getHostMergerPools(int host_id){
        System.out.println("This is Merger Pools.");
        
    }
    public void getHostDeadPools(int host_id){
        System.out.println("This is DeaDpool.");
        
    }
    public void getHostVerificationPools(int host_id){
        System.out.println("This is DeaDpool.");
        
    }

    public void getThisService(int service_id){
        System.out.println("This is service.");
    }    

    public void setThisPool(){
        System.out.println("Set this pool.");
    }

    //HOST FORM DAO
    public int insertHostInfoData(int user_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet resultSet = null;
        int host_id = -1;
    
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
                return -1;
            }
    
            // Check if the user_id already exists in host_info table
            String checkHostSql = "SELECT host_id FROM host_info WHERE user_id = ?";
            prepare = conn.prepareStatement(checkHostSql);
            prepare.setInt(1, user_id);
            resultSet = prepare.executeQuery();
    
            if (resultSet.next()) {
                // User exists in host_info, update the hosted_pools
                host_id = resultSet.getInt("host_id");
                String updateSql = "UPDATE host_info SET hosted_pools = hosted_pools + 1 WHERE user_id = ?";
                prepare = conn.prepareStatement(updateSql);
                prepare.setInt(1, user_id);
                int affectedRows = prepare.executeUpdate();
    
                // Check if update was successful
                if (affectedRows > 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("HostInfo updated successfully");
                    alert.showAndWait();
                    return host_id;
                } else {
                    System.out.println("No rows updated.");
                    return -1;
                }
            } else {
                // User does not exist in host_info, insert new record
                String insertSql = "INSERT INTO host_info (user_id, hosted_pools, earning, isDebarr, rating) VALUES (?, ?, ?, ?, ?)";
                prepare = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
                prepare.setInt(1, user_id);
                prepare.setInt(2, 1); // Initial hosted_pools
                prepare.setDouble(3, 0.00); // Initial earnings
                prepare.setBoolean(4, false); // is_debarr
                prepare.setDouble(5, 0.0); // Initial rating
                
                int affectedRows = prepare.executeUpdate();
    
                // Check if insertion was successful
                if (affectedRows > 0) {
                    resultSet = prepare.getGeneratedKeys();
                    if (resultSet.next()) {
                        host_id = resultSet.getInt(1);
    
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("HostInfo inserted successfully");
                        alert.showAndWait();
                        return host_id;
                    }
                } else {
                    System.out.println("No rows inserted.");
                    return -1;
                }
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
            return -1;
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    

    

    public int insertPoolsData(int service_id,int plan_id,int host_id,String poolName,int currentMember,int memberAllowed,String poolType,String poolState,Boolean isPrivate) {
        Connection conn = null;
        PreparedStatement prepare = null;
        int pool_id;
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Insert new record into transactions table
            String insertSql = "INSERT INTO thepools (service_id, plan_id ,host_id ,pool_name, current_members, members_allowed, pool_type, pool_state, is_private) VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?)";
            prepare = conn.prepareStatement(insertSql,PreparedStatement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS);
            prepare.setInt(1, service_id);
            prepare.setInt(2, plan_id);
            prepare.setInt(3, host_id);
            prepare.setString(4, poolName);
            prepare.setInt(5, currentMember);
            prepare.setInt(6, memberAllowed);
            prepare.setString(7, poolType);
            prepare.setString(8, poolState);
            prepare.setBoolean(9, isPrivate);
            int affectedRows = prepare.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = prepare.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pool_id = generatedKeys.getInt(1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Pools added successfully. Pool ID: " + pool_id);
                    alert.showAndWait();
                    return pool_id;
                }
            } else {
                System.out.println("No rows affected.");
            }
            return -1;            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
            return -1;
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean insertPoolMembersData(int service_id,int pool_id,int user_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Insert new record into transactions table
            String insertSql = "INSERT INTO pool_members (service_id,pool_id,member_id) VALUES (?, ?, ?)";
            prepare = conn.prepareStatement(insertSql);
            prepare.setInt(1, service_id);
            prepare.setInt(2, pool_id);
            prepare.setInt(3, user_id);
            int affectedRows = prepare.executeUpdate();
            
            // Check if insertion was successful
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Pool Members Inserted successfully");
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
    
    public boolean insertActivePoolsData(int pool_id) {
        Connection conn = null;
        PreparedStatement prepare = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Insert new record into transactions table
            String insertSql = "INSERT INTO active_pools (pool_id) VALUES (?)";
            prepare = conn.prepareStatement(insertSql);
            prepare.setInt(1, pool_id);
            int affectedRows = prepare.executeUpdate();
            
            // Check if insertion was successful
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Active Pool Inserted successfully");
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
    
    public void poolLiveTimerStart(int pool_id) {
        
        // Initialize the Timer
        timer = new Timer();

        // Schedule the TimerTask to run after 5 minutes
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Call the methods after the timer completes
                new ExploreSideDao().movePoolToDeadFromLive(pool_id);
                new ExploreSideDao().updateCurrentStateToNewState(pool_id, "Live", "Dead");
                
            }
        }, 300000);  // 5 minutes in milliseconds
    }

   





}
