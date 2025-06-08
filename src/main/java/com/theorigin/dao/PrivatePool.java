package main.java.com.theorigin.dao;


public class PrivatePool {
    
    private static int planId;
    private static int hostId;
    private static int serviceId;
    private static String poolName;
    private static int currentMembers;
    private static int membersAllowed;
    private static String poolType;
    private static String poolState;
    private static boolean isPrivate;

    // Constructor
    public PrivatePool(int serviceId,int planId, int hostId, String poolName, int currentMembers, int membersAllowed, String poolType, String poolState, boolean isPrivate) {
        this.planId = planId;
        this.hostId = hostId;
        this.serviceId = serviceId;
        this.poolName = poolName;
        this.currentMembers = currentMembers;
        this.membersAllowed = membersAllowed;
        this.poolType = poolType;
        this.poolState = poolState;
        this.isPrivate = isPrivate;
    }

    // public PrivatePool(int serviceId, int planId2, int poolId2, String poolName2, int currentMembers2,
    //         int membersAllowed2, String poolType2, String poolState2, boolean isPrivate2) {
    //     //TODO Auto-generated constructor stub
    // }

    // Getters
    public static int getPlanId() { return planId; }
    public static int getHostId() { 
        return hostId; 
    }
    public  int getServiceId() { return serviceId; }
    public  String getPoolName() { return poolName; }
    public  int getCurrentMembers() { return currentMembers; }
    public  int getMembersAllowed() { return membersAllowed; }
    public  String getPoolType() { return poolType; }
    public  String getPoolState() { return poolState; }
    public  boolean isPrivate() { return isPrivate; }

    // Setters
    public void setPlanId(int planId) { this.planId = planId; }
    public void setHostId(int hostId) { this.hostId = hostId; }
    public void setServiceId(int poolId) { this.serviceId = poolId; }
    public void setPoolName(String poolName) { this.poolName = poolName; }
    public void setCurrentMembers(int currentMembers) { this.currentMembers = currentMembers; }
    public void setMembersAllowed(int membersAllowed) { this.membersAllowed = membersAllowed; }
    public void setPoolType(String poolType) { this.poolType = poolType; }
    public void setPoolState(String poolState) { this.poolState = poolState; }
    public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Pool{" +
                "planId=" + planId +
                ", hostId=" + hostId +
                ", serviceId=" + serviceId +
                ", poolName='" + poolName + '\'' +
                ", currentMembers=" + currentMembers +
                ", membersAllowed=" + membersAllowed +
                ", poolType='" + poolType + '\'' +
                ", poolState='" + poolState + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }

    
}

