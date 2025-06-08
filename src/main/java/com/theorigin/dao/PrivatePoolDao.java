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


public class PrivatePoolDao extends DatabaseCredentials{
    


    public static boolean doesPoolExist(int poolId) {
        System.out.println("Enter poolExixst Part");
        String query = "SELECT EXISTS (SELECT 1 FROM thepools WHERE pool_id = ?) AS pool_exists";
        
        try (Connection connection = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, poolId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("pool_exists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<PrivatePool> fetchThisPrivatePools(int poolId) {
        
        System.out.println("Inside Fetch Host Active Poolssssss");
        List<PrivatePool> privatePoolList = new ArrayList<>();

        String query = "SELECT service_id,plan_id, host_id,pool_name, current_members, members_allowed, pool_type, pool_state, is_private FROM thepools WHERE pool_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, poolId);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    int serviceId = rs.getInt("service_id");
                    int planId = rs.getInt("plan_id");
                    int hostId = rs.getInt("host_id");
                    String poolName = rs.getString("pool_name");
                    int currentMembers = rs.getInt("current_members");
                    int membersAllowed = rs.getInt("members_allowed");
                    String poolType = rs.getString("pool_type");
                    String poolState = rs.getString("pool_state");
                    boolean isPrivate = rs.getBoolean("is_private");

                    PrivatePool privatePoolObj = new PrivatePool(serviceId,planId,hostId,poolName, currentMembers, membersAllowed, poolType, poolState, isPrivate);
                    privatePoolList.add(privatePoolObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        return privatePoolList;
    }



}
