package main.java.com.theorigin.dao;

import java.util.List;

public class UserPool {
    
    private int serviceId;
    private int planId;
    private int hostId;
    private int poolId;
    private String poolName;
    private int currentMembers;
    private int membersAllowed;
    private String poolType;
    private String poolState;
    private boolean isPrivate;

    // Add getters and setters here

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }

    public int getHostId() { return hostId; }
    public void setHostId(int hostId) { this.hostId = hostId; }

    public int getPoolId() { return poolId; }
    public void setPoolId(int poolId) { this.poolId = poolId; }

    public String getPoolName() { return poolName; }
    public void setPoolName(String poolName) { this.poolName = poolName; }

    public int getCurrentMembers() { return currentMembers; }
    public void setCurrentMembers(int currentMembers) { this.currentMembers = currentMembers; }

    public int getMembersAllowed() { return membersAllowed; }
    public void setMembersAllowed(int membersAllowed) { this.membersAllowed = membersAllowed; }

    public String getPoolType() { return poolType; }
    public void setPoolType(String poolType) { this.poolType = poolType; }

    public String getPoolState() { return poolState; }
    public void setPoolState(String poolState) { this.poolState = poolState; }

    public boolean isPrivate() { return isPrivate; }
    public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
}




