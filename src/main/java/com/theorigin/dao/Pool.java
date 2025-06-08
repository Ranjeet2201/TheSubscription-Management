package main.java.com.theorigin.dao;



public class Pool {
    private int planId;
    private int hostId;
    private int poolId;
    private String poolName;
    private int currentMembers;
    private int membersAllowed;
    private String poolType;
    private String poolState;
    private boolean isPrivate;

    // Constructor
    public Pool(int planId, int hostId, int poolId, String poolName, int currentMembers, int membersAllowed, String poolType, String poolState, boolean isPrivate) {
        this.planId = planId;
        this.hostId = hostId;
        this.poolId = poolId;
        this.poolName = poolName;
        this.currentMembers = currentMembers;
        this.membersAllowed = membersAllowed;
        this.poolType = poolType;
        this.poolState = poolState;
        this.isPrivate = isPrivate;
    }

    // Getters
    public int getPlanId() { return planId; }
    public int getHostId() { return hostId; }
    public int getPoolId() { return poolId; }
    public String getPoolName() { return poolName; }
    public int getCurrentMembers() { return currentMembers; }
    public int getMembersAllowed() { return membersAllowed; }
    public String getPoolType() { return poolType; }
    public String getPoolState() { return poolState; }
    public boolean isPrivate() { return isPrivate; }

    // Setters
    public void setPlanId(int planId) { this.planId = planId; }
    public void setHostId(int hostId) { this.hostId = hostId; }
    public void setPoolId(int poolId) { this.poolId = poolId; }
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
                ", poolId=" + poolId +
                ", poolName='" + poolName + '\'' +
                ", currentMembers=" + currentMembers +
                ", membersAllowed=" + membersAllowed +
                ", poolType='" + poolType + '\'' +
                ", poolState='" + poolState + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
