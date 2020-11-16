import java.util.ArrayList;
import java.util.Random;


public class Bank {
    private String name; // Account holder name
    private ArrayList<Users> account_holders; // List of all accounts
    private ArrayList<Accounts> track_account; // Track of all the accounts


     public Bank(String name){
         this.name = name;
         this.account_holders = new ArrayList<Users>();
         this.track_account = new ArrayList<Accounts>();
     }

    public String get_new_user_id(){
         String unique_id;
         Random num_generator = new Random();
         int length = 8;
         boolean nonUnique;
         do{
             unique_id="";
             for(int i=0;i<length;i++){ // This loop generated unique numbers for the account
                 unique_id += ((Integer)num_generator.nextInt(10)).toString();
             }
             nonUnique = false;
             for(Users u: this.account_holders){ //This loops checks weather the acoount number already exists
                 if(unique_id.compareTo(u.get_unique_id()) == 0){
                     nonUnique = true;
                     break;
                 }
             }
         }while(nonUnique);{
            return unique_id;
         }


    }
    public String get_new_account_id(){
        String unique_id;
        Random num_generator = new Random();
        int length = 8;
        boolean nonUnique;
        do{
            unique_id="";
            for(int i=0;i<length;i++){ // This loop generated unique numbers for the account
                unique_id += ((Integer)num_generator.nextInt(10)).toString();
            }
            nonUnique = false;
            for(Accounts a: this.track_account){ //This loops checks weather the acoount number already exists
                if(unique_id.compareTo(a.get_unique_id()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);{
            return unique_id;
        }


    }
    public void addAccount(Accounts x){
        this.track_account.add(x);
    }


    public Users addUser(String firstname, String lastname, String pin){
        Users newUser = new Users(firstname, lastname, pin, this); // this at end is used cuz it is used in
        this.account_holders.add(newUser);                                                                // theBank place while creating first name and
                                                                          // last name in a method in Users class
        this.account_holders.add(newUser);
        // Creating a savings account and updating it to User and Bank
        Accounts newAccount = new Accounts("Savings",newUser,this);
        newUser.addAccount(newAccount); // adds the created object info to holder and bank lists
        this.track_account.add(newAccount);

        return newUser;
    }
    public Users account_holder_login(String user_id,String pin){

        for(Users u: this.account_holders){ // loop to check in all account holder array list
            if(u.get_unique_id().compareTo(user_id) == 0 && u.valid_pin(pin)){ // Condition to compare user id and pin if
                                                                             // same then proceed
                return u;
            }
        }
        return null; // if we didnt find any account holder detail then give null as default.
    }
    public String get_bank_name(){
         return this.name; // The name given to the bank will be returned.
    }
}
