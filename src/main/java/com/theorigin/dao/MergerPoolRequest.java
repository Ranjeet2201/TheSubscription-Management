package main.java.com.theorigin.dao;

public class MergerPoolRequest {
    
    private int planId;
    private int senderPoolId;
    private int receiverPoolId;
    private int senderHostId;
    private int receiverHostId;

    // Constructor
    public MergerPoolRequest(int planId, int senderPoolId, int receiverPoolId, int senderHostId, int receiverHostId) {
        this.planId = planId;
        this.senderPoolId = senderPoolId;
        this.receiverPoolId = receiverPoolId;
        this.senderHostId = senderHostId;
        this.receiverHostId = receiverHostId;
    }

    // Getters and Setters
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getSenderPoolId() {
        return senderPoolId;
    }

    public void setSenderPoolId(int senderPoolId) {
        this.senderPoolId = senderPoolId;
    }

    public int getReceiverPoolId() {
        return receiverPoolId;
    }

    public void setReceiverPoolId(int receiverPoolId) {
        this.receiverPoolId = receiverPoolId;
    }

    public int getSenderHostId() {
        return senderHostId;
    }

    public void setSenderHostId(int senderHostId) {
        this.senderHostId = senderHostId;
    }

    public int getReceiverHostId() {
        return receiverHostId;
    }

    public void setReceiverHostId(int receiverHostId) {
        this.receiverHostId = receiverHostId;
    }

    @Override
    public String toString() {
        return "MergerPoolRequest{" +
                "planId=" + planId +
                ", senderPoolId=" + senderPoolId +
                ", receiverPoolId=" + receiverPoolId +
                ", senderHostId=" + senderHostId +
                ", receiverHostId=" + receiverHostId +
                '}';
    }
}
