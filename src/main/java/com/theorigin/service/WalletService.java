package main.java.com.theorigin.service;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.view.TheSubWallet;
import java.util.Map;
import java.util.HashMap;
import javafx.stage.Stage;

public class WalletService {
    int user_id;
    private WalletDao walletDaoObj;
    public WalletService(WalletDao walletDaoObj ){
        this.walletDaoObj=walletDaoObj;
    }
    public boolean createWallet(int user_id,String panName,int mobileNum,String panNum,String birthDate){
        if(walletDaoObj.insertWalletData(user_id,panName,mobileNum,panNum,birthDate)){
            System.out.println("Wallet created sucessfully");
            return true;
        }else{
            System.out.println("Error while creating Wallet ");
            return false;
        }
        
        
    }
    public boolean creditMoney(double amount,int user_id){
        if(walletDaoObj.creditMoney(amount,user_id)){
            System.out.println("Amount Credited Succesfully");
        }else{
            System.out.println("Transaction failed");
        }
        return true;
    }

    public boolean redeemMoney(double amount,int user_id){
        if(walletDaoObj.redeemMoney(amount,user_id)){
            System.out.println("Amount Redeemed Succesfully");
        }else{
            System.out.println("Transaction failed");
        }
        return true;
    }


    public Map<Integer,Double> showAllTransactions(int user_id){
        Map<Integer,Double>transactionHistory=walletDaoObj.getCreditHistory(user_id);
        return transactionHistory;
    }
    public Map<Integer,Double> showAllCredits(int user_id){
        Map<Integer,Double>creditHistory=walletDaoObj.getCreditHistory(user_id);
        return creditHistory;
    }
    public Map<Integer,Double> showAllDebits(int user_id){
        Map<Integer,Double>DebitHistory=walletDaoObj.getDebitsHistory(user_id);
        return DebitHistory;
    }
    public Map<Integer,Double> showAllRefunds(int user_id){
        Map<Integer,Double>refundHistory=walletDaoObj.getRefundHistory(user_id);
        return refundHistory;
    }
}
