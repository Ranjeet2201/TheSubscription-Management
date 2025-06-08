package main.java.com.theorigin.dao;



public class ActivePool {
    
    private int planId1;
    private int poolId1;
    private int serviceId1;
    private String poolName1;
    private int currentMembers1;
    private int membersAllowed1;
    private String poolType1;
    private String poolState1;
    private boolean isPrivate1;

    // Constructor
    public ActivePool(int serviceId,int planId, int poolId, String poolName, int currentMembers, int membersAllowed, String poolType, String poolState, boolean isPrivate) {
        this.planId1 = planId;
        this.poolId1 = poolId;
        this.serviceId1 = serviceId;
        this.poolName1 = poolName;
        this.currentMembers1 = currentMembers;
        this.membersAllowed1 = membersAllowed;
        this.poolType1 = poolType;
        this.poolState1 = poolState;
        this.isPrivate1 = isPrivate;
    }

    // Getters
    public  int getPlanId1() { return planId1; }
    public  int getPoolId1() { 
        return poolId1; 
    }
    public  int getServiceId1() { return serviceId1; }
    public  String getPoolName1() { return poolName1; }
    public  int getCurrentMembers1() { return currentMembers1; }
    public  int getMembersAllowed1() { return membersAllowed1; }
    public  String getPoolType1() { return poolType1; }
    public  String getPoolState1() { return poolState1; }
    public  boolean isPrivate1() { return isPrivate1; }

    // Setters
    public void setPlanId(int planId) { this.planId1 = planId; }
    public void setHostId(int hostId) { this.poolId1 = hostId; }
    public void setServiceId(int poolId) { this.serviceId1 = poolId; }
    public void setPoolName(String poolName) { this.poolName1 = poolName; }
    public void setCurrentMembers(int currentMembers) { this.currentMembers1 = currentMembers; }
    public void setMembersAllowed(int membersAllowed) { this.membersAllowed1 = membersAllowed; }
    public void setPoolType(String poolType) { this.poolType1 = poolType; }
    public void setPoolState(String poolState) { this.poolState1 = poolState; }
    public void setPrivate(boolean isPrivate) { this.isPrivate1 = isPrivate; }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Pool{" +
                "planId=" + planId1 +
                ", hostId=" + poolId1 +
                ", serviceId=" + serviceId1 +
                ", poolName='" + poolName1 + '\'' +
                ", currentMembers=" + currentMembers1 +
                ", membersAllowed=" + membersAllowed1 +
                ", poolType='" + poolType1 + '\'' +
                ", poolState='" + poolState1 + '\'' +
                ", isPrivate=" + isPrivate1 +
                '}';
    }

    
}
