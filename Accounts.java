import java.util.ArrayList;

public class Accounts {
    private String account_name;   // String is taken as account_name is going to be text
    private String unique_id;     // we can also use unique_id as private make this usable only to this class
    private Users account_holder;  // User that holds this account

    private ArrayList<Transactions_for_users> transactions; // Array is chosen as there will be many series of transactions

    public Accounts(String account_name, Users account_holder, Bank theBank){

        this.account_name = account_name;   // setting account name and account holder
        this.account_holder = account_holder;

        this.unique_id = theBank.get_new_account_id(); // we give unique account for user

        this.transactions = new ArrayList<Transactions_for_users>(); // Here we are initialising the transactions



    }
    public String get_unique_id(){
        return this.unique_id;  //We used this method to return the id so that we can check the number while generating
    }
    public String display_details(){

        double balance = this.display_balance(); // Takes the info of user account balance

        if(balance >= 0){
            return String.format("%s : Rs%.02f : %s", this.unique_id, balance, this.account_name);
        }
        else{
            return String.format("%s : Rs(%.02f) : %s", this.unique_id, balance, this.account_name);

        }
    }
    public double display_balance(){ // Displays balance of the account by adding up transactions.
        double balance = 0;
        for(Transactions_for_users t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }
    public void transaction_history(){ // This constructor prints transaction history for the account.
        System.out.printf("\n Transaction history for the respective account %s\n",this.unique_id);
        for(int i= this.transactions.size()-1; i>=0; i--){
            System.out.println(this.transactions.get(i).get_info_line());

        }
        System.out.println();
    }
    public void add_transaction(double amount, String info){
        // Create transaction object and we add it to our list
        Transactions_for_users new_transaction = new Transactions_for_users(amount, info, this);
        this.transactions.add(new_transaction);
    }

}
