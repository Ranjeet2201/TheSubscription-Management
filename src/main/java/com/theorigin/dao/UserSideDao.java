package main.java.com.theorigin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.theorigin.dao.UserPool;

public class UserSideDao extends DatabaseCredentials{
    

        public List<UserPool> fetchPoolsForUser(int userId) {
        List<UserPool> pools = new ArrayList<>();
        
        String query = "SELECT tp.* " +
                       "FROM thepools tp " +
                       "JOIN pool_members pm ON tp.pool_id = pm.pool_id " +
                       "WHERE pm.member_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserPool pool = new UserPool();
                pool.setServiceId(rs.getInt("service_id"));
                pool.setPlanId(rs.getInt("plan_id"));
                pool.setHostId(rs.getInt("host_id"));
                pool.setPoolId(rs.getInt("pool_id"));
                pool.setPoolName(rs.getString("pool_name"));
                pool.setCurrentMembers(rs.getInt("current_members"));
                pool.setMembersAllowed(rs.getInt("members_allowed"));
                pool.setPoolType(rs.getString("pool_type"));
                pool.setPoolState(rs.getString("pool_state"));
                pool.setPrivate(rs.getBoolean("is_private"));
                // Add more fields as necessary based on your table structure
                
                pools.add(pool); // Add each pool to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pools;
    }
    
}
