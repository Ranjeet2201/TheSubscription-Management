package main.java.com.theorigin.service;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import main.java.com.theorigin.dao.HostSideDao;

public class HostSideService {
    int host_id = 1;
    int service_id;
    
    public void hostExplorePage() {
        System.out.println("This is Host Explore Page");
    }

    /**
     * @param user_id
     * @param poolName
     * @param serviceName
     * @param planName
     * @param membersAllowed
     * @param poolType
     * @param poolState
     * @param isPrivate
     */
    public void hostFormService(int user_id,String poolName,String serviceName,String planName,int membersAllowed,String poolType,String poolState,Boolean isPrivate ){
        System.out.println("Host Service"+user_id);
        int fetchedPlanId = new HostSideDao().fetchPlanId(planName);
        System.out.println("Fetched Plan "+fetchedPlanId);
        int fetchedServiceId = new HostSideDao().fetchServiceId(serviceName);
        System.out.println("Fetched Service"+fetchedServiceId);
        final int host_id = new HostSideDao().insertHostInfoData(user_id);
        System.out.println("Fetched Host_id"+host_id); 
        final int pool_id = new HostSideDao().insertPoolsData(fetchedServiceId, fetchedPlanId, host_id, poolName, 1, membersAllowed, poolType, poolState, isPrivate);
        System.out.println("Fetched Pool_id"+pool_id); 
        new HostSideDao().insertPoolMembersData(fetchedServiceId, pool_id, user_id);
        new HostSideDao().insertActivePoolsData(pool_id);
    }


    public void hostHistory(){
        System.out.println("This is HOstHistory PAge.");
    }
    public void fetchHostLivePools(int host_id){
        System.out.println("This is Live Pools.");
        
    }
    public void fetchHostActivePools(int host_id){
        System.out.println("This is Active Pools.");
        
    }
    public void fetchHostMergerPools(int host_id){
        System.out.println("This is Merger Pools.");
        
    }
    public void fetchHostVerificationPools(int host_id){
        System.out.println("This is Merger Pools.");
        
    }
    public void fetchHostDeadPools(int host_id){
        System.out.println("This is DeaDpool.");
        
    }

    public void fetchThisService(int service_id){
        System.out.println("This is service.");
    }

    public void addThisPool(){
        
    }
    
}
