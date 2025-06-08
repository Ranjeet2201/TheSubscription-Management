package main.java.com.theorigin.controller;
import main.java.com.theorigin.view.TheSubWallet;
import javafx.stage.Stage;
import main.java.com.theorigin.dao.WalletDao;
import main.java.com.theorigin.service.WalletService;
import java.util.Map;

public class WalletController {
    int user_id;
    private WalletService walletservice;

    public WalletController(String email){
        WalletDao walletDaoObj=new WalletDao(email);
        //Understand later this concept.
        this.walletservice=new WalletService(walletDaoObj);
    }
    public void walletCreator(Stage secondaryStage,int user_id,String panName,int mobileNum,String panNum,String birthDate){
        if(walletservice.createWallet(user_id,panName, mobileNum, panNum, birthDate)){
            secondaryStage.close();    
        }else{
            System.out.println("Error while creating wallet");
        }
    }
    public boolean creditMoneyToSubWallet(double amount,int user_id){
        if(walletservice.creditMoney(amount,user_id)){
            System.out.println("Money credited Successfully");
            return true;
        }else{
            System.err.println("Money not credited");
            return false;
        }
    }
    public boolean redeemMoneyFromSubWallet(double amount,int user_id){
        if(walletservice.redeemMoney(amount,user_id)){
            System.out.println("Money redeemed Successfully");
            return true;
        } else {
            System.err.println("Money not redeemed");
            return false;
        }
    }
    void showAllTranscation(int user_id){
        Map<Integer,Double> transcationMap=walletservice.showAllTransactions(user_id);
        System.out.println("Transaction "+transcationMap);
    }
    void showAllCredits(int user_id){
        Map<Integer,Double> creditHisShowMap=walletservice.showAllCredits(user_id);
        System.out.println("credits"+creditHisShowMap);
    }
    void showAllDebitS(int user_id){
        Map<Integer,Double> debitMap=walletservice.showAllDebits(user_id);
        System.out.println("Debits "+debitMap);
    }
    void showAllRefunds(int user_id){
        Map<Integer,Double> refundMap=walletservice.showAllRefunds(user_id);
        System.out.println("Refund "+refundMap);
    }
    // public static void main(String[] args) {
    //     WalletController obj=new WalletController();
    //     //obj.walletCreator(null, 0, null, 0, null, null);
    //     //obj.creditMoneyToSubWallet(1, 100,"UPI");
    //     obj.showAllTranscation(1);
    //     obj.showAllCredits(1);
    //     obj.showAllDebitS(1);
    //     obj.showAllRefunds(1);
        
    // }
}
