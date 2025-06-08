package main.java.com.theorigin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MergerDao extends DatabaseCredentials {
 
            
    public List<MergerPoolList> fetchThisPlanMergerPools(int plan_id) {
        
        System.out.println("Inside Fetch Merger Pools");
        List<MergerPoolList> poolMergerList = new ArrayList<>();

        String query = "SELECT service_id,pool_id,host_id,pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE plan_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, plan_id);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    int serviceId = rs.getInt("service_id");
                    int hostId = rs.getInt("host_id");
                    int poolId = rs.getInt("pool_id");
                    String poolName = rs.getString("pool_name");
                    int currentMembers = rs.getInt("current_members");
                    int membersAllowed = rs.getInt("members_allowed");
                    String poolType = rs.getString("pool_type");
                    String poolState = rs.getString("pool_state");
                    boolean isPrivate = rs.getBoolean("is_private");

                    MergerPoolList mergerPoolListObj = new MergerPoolList(serviceId,hostId,poolId,poolName, currentMembers, membersAllowed, poolType, poolState, isPrivate);
                    poolMergerList.add(mergerPoolListObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        return poolMergerList;
    }

    // Method to insert data into merger_pools table
    public void insertMergerPoolData(int planId, int senderPoolId, int receiverPoolId, int senderHostId, int receiverHostId) {
        String insertSQL = "INSERT INTO merger_pools (plan_id, sender_poolid, receiver_poolid, sender_hostid, receiver_hostid) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL,UserName,password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Set values for each placeholder (?)
            pstmt.setInt(1, planId);
            pstmt.setInt(2, senderPoolId);
            pstmt.setInt(3, receiverPoolId);
            pstmt.setInt(4, senderHostId);
            pstmt.setInt(5, receiverHostId);

            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Merger Insertion successful!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public boolean movePoolToMergerFromActive(int poolId) {
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

    public static ObservableList<MergerPoolRequest> fetchMergerRequests(int loggedInHostId, boolean isSent) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        

        ObservableList<MergerPoolRequest> requests = FXCollections.observableArrayList();
        String sql = isSent ?
            "SELECT * FROM merger_pools WHERE sender_hostid = ?" :
            "SELECT * FROM merger_pools WHERE receiver_hostid = ?";

        try {
            
            // Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);            
            // Begin transaction
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, loggedInHostId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int planId = rs.getInt("plan_id");
                int senderPoolId = rs.getInt("sender_poolid");
                int receiverPoolId = rs.getInt("receiver_poolid");
                int senderHostId = rs.getInt("sender_hostid");
                int receiverHostId = rs.getInt("receiver_hostid");
                
                requests.add(new MergerPoolRequest(planId, senderPoolId, receiverPoolId, senderHostId, receiverHostId));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

     // Method to delete an entry based on passed parameters
     public void deleteMergerPoolEntry(int planId, int senderPoolId, int receiverPoolId, int senderHostId, int receiverHostId) {
        String sql = "DELETE FROM merger_pools WHERE plan_id = ? AND sender_poolid = ? AND receiver_poolid = ? AND sender_hostid = ? AND receiver_hostid = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters dynamically
            pstmt.setInt(1, planId);
            pstmt.setInt(2, senderPoolId);
            pstmt.setInt(3, receiverPoolId);
            pstmt.setInt(4, senderHostId);
            pstmt.setInt(5, receiverHostId);

            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean mergePools(int senderPoolId, int receiverPoolId) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet resultSet = null;
    
        try {
            // Step 1: Establish connection
            conn = DriverManager.getConnection(URL, UserName, password);
            
            // Begin transaction
            conn.setAutoCommit(false);
    
            // Step 2: Update pool_id for all members in senderPoolId to receiverPoolId
            String updatePoolMembersQuery = "UPDATE pool_members SET pool_id = ? WHERE pool_id = ?";
            pstmt1 = conn.prepareStatement(updatePoolMembersQuery);
            pstmt1.setInt(1, receiverPoolId); // Set the new pool_id (receiverPoolId)
            pstmt1.setInt(2, senderPoolId);   // Set the condition pool_id (senderPoolId)
            int updatedRows = pstmt1.executeUpdate();
    
            // Step 3: Count the number of members now associated with receiverPoolId
            String countMembersQuery = "SELECT COUNT(member_id) AS member_count FROM pool_members WHERE pool_id = ?";
            pstmt2 = conn.prepareStatement(countMembersQuery);
            pstmt2.setInt(1, receiverPoolId);
            resultSet = pstmt2.executeQuery();
    
            int memberCount = 0;
            if (resultSet.next()) {
                memberCount = resultSet.getInt("member_count");
            }
    
            // Step 4: Update the member count in thepools table for receiverPoolId
            String updatePoolCountQuery = "UPDATE thepools SET current_members = ? WHERE pool_id = ?";
            pstmt3 = conn.prepareStatement(updatePoolCountQuery);
            pstmt3.setInt(1, memberCount); // Set the new member count
            pstmt3.setInt(2, receiverPoolId); // Set the pool_id to update
            int updatedCountRows = pstmt3.executeUpdate();
    
            // Commit transaction
            conn.commit();
    
            // Check if all updates were successful
            if (updatedRows > 0 && updatedCountRows > 0) {
                return true; // All operations were successful
            } else {
                return false; // Something went wrong
            }
    
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Rollback in case of error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false; // Exception occurred
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (pstmt3 != null) pstmt3.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    



}

