package main.java.com.theorigin.dao;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class AdminDao extends DatabaseCredentials {
    VerificationPool verificationPoolObj;
        

    // Fetch all pool_ids from the verification_pools table
    
    public List<VerificationPool> fetchAllPoolsInVerificationState() {
        System.out.println("Fetching all pools with pool_state as 'verification'");
        List<VerificationPool> poolVerificationList = new ArrayList<>();
    
        String query = "SELECT service_id, plan_id, host_id,pool_id, pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE pool_state = 'Verification' ";
    
        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {
    
            while (rs.next()) {
                int serviceId = rs.getInt("service_id");
                int planId = rs.getInt("plan_id");
                int hostId = rs.getInt("host_id");
                int poolId = rs.getInt("pool_id");
                String poolName = rs.getString("pool_name");
                int currentMembers = rs.getInt("current_members");
                int membersAllowed = rs.getInt("members_allowed");
                String poolType = rs.getString("pool_type");
                String poolState = rs.getString("pool_state");
                boolean isPrivate = rs.getBoolean("is_private");
    
                verificationPoolObj = new VerificationPool(serviceId, planId, hostId,  poolId, poolName, currentMembers, membersAllowed, poolType, poolState,isPrivate);
                poolVerificationList.add(verificationPoolObj);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }
    
        return poolVerificationList;
    }

    public void loadVerificationImage(int pool_id, ImageView imageView) {
        try (Connection conn = DriverManager.getConnection(URL, UserName, password)) {
            String sql = "SELECT img_ver FROM verification_pools WHERE pool_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, pool_id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("img_ver");
                if (is != null) {
                    Image img = new Image(is);
                    imageView.setImage(img);
                    // Restrict the size of the ImageView
                    imageView.setFitWidth(200);  // Set the desired width
                    imageView.setFitHeight(200); // Set the desired height
                    imageView.setPreserveRatio(true); // Maintain the aspect ratio
                } else {
                    System.out.println("No image found for pool_id: " + pool_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }



}

