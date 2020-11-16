import java.util.Date;

public class Transactions_for_users {
    private double amount; //The amount after or before the transaction, also includes balance
    private Date timestamp; // java.util.Date is imported to check on which date the transaction is done

    private String info; // info gives for what reason the money is transacted
    private Accounts user_to_account; // this refers to account in which transaction was involved


    public Transactions_for_users(double amount, Accounts user_to_account){

        this.amount = amount;
        this.user_to_account = user_to_account;
        this.timestamp = new Date();
        this.info ="";

    }
    public Transactions_for_users(double amount, String info,Accounts user_to_account){
        this(amount, user_to_account); // This is two argument constructor

        this.info = info;

    }
    public double getAmount(){
        return this.amount;
    }
    public String get_info_line(){ // Getting a info line for the transaction.
        if(this.amount >=0){
            return String.format("%s : Rs%.02f : %s",this.timestamp.toString(),this.amount,this.info);
        } else{
            return String.format("%s : Rs(%.02f) : %s",this.timestamp.toString(),-this.amount,this.info);

        }
    }
}
