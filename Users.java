import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class Users {
    private String firstname;   // private is used here to make the variables visible only to this class
    private String lastname;
    private String unique_id;

    private byte pin_Hash_value[]; //Byte data type is used to save space in large arrays
    // we use MD5 hash function as it is a secure cryptographic has algorithm
    private ArrayList<Accounts> user_accounts; // List of accounts fo this user


    public Users(String firstname,String lastname,String pin, Bank theBank ){

        this.firstname = firstname; // setting up users name
        this.lastname = lastname;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // try and catch is used cuz of unhandled exception type
            this.pin_Hash_value = md.digest(pin.getBytes()); // Here we are getting bytes for our pin and digesting it to get a secured way

        } catch (NoSuchAlgorithmException e) {                   // with a fix of surround with try/catch
            System.err.println("Error = caught No_such_AlgorithmException ");  // System.err is just used to output error texts
            e.printStackTrace();
            System.exit(1); // exit the exception handling
        }

        this.unique_id = theBank.get_new_user_id(); // we give new unique ids for our user
        this.user_accounts = new ArrayList<Accounts>(); // This creates empty list of accounts

        System.out.printf("The new user %s.%s with ID %s is successfully created.\n",firstname,
                lastname,this.unique_id); // Message that outputs that account id has been created.

    }

    public void addAccount(Accounts x){  // This adds account for the user
        this.user_accounts.add(x);
    }

    public String get_unique_id(){
        return this.unique_id; // We used this method to return the id so that we can check the number while generating
    }

    public boolean valid_pin(String pin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pin_Hash_value);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error = caught No_such_AlgorithmException ");  // System.err is just used to output error texts
            e.printStackTrace();
            System.exit(1); // exit the exception handling

        }
        return false;
    }

    public String first_name(){
        return this.firstname;
    }

    public void my_account(){// prints account summaries of the user.
        System.out.printf("\n %s's Account details\n", this.firstname);
        for(int i=0; i < this.user_accounts.size();i++){
            System.out.printf(" %d. %s\n",i+1,this.user_accounts.get(i).display_details());

        }
        System.out.println();
    }
    public int number_of_accounts(){ // returns number of accounts of the user.
        return this.user_accounts.size();

    }

    public void display_account_transaction_history(int account_index){
        this.user_accounts.get(account_index).transaction_history();

    }
    public double get_account_balance(int account_index){ // Gives balance for a particular account.
        return this.user_accounts.get(account_index).display_balance();
    }

    public String get_account_uniqueid(int account_index){ // This gets unique id of the particular account
        return this.user_accounts.get(account_index).get_unique_id();
    }

    // This adds transaction to particular account.
    public void add_account_transaction(int account_index, double amount, String info){
        this.user_accounts.get(account_index).add_transaction(amount, info);
    }



}
