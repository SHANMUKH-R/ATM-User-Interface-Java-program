import java.util.Scanner;

public class ATM {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Bank theBank = new Bank("Bank of SRM");

        Users account_holder = theBank.addUser("Shanmukh","R","1111");
        Accounts newAccount = new Accounts("Ckecking",account_holder, theBank);
        theBank.addAccount(newAccount);
        account_holder.addAccount(newAccount);

        Users login;
        while(true){ // Stay in this loop until the login is successful
            login = ATM.login_menu(theBank, sc);
            ATM.output_menu(login, sc); //We have to stay in this menu until user exits


        }

    }
    public static Users login_menu(Bank theBank, Scanner sc){
        String User_Id;
        String pin;
        Users authenticated_user;

        do{ // This provides user the user_id an the pin
            System.out.printf("\nWelcome to %s\n",theBank.get_bank_name());
            System.out.println("Enter the user Id :");
            User_Id = sc.nextLine();
            System.out.println("Enter the pin :");
            pin = sc.nextLine();

            authenticated_user = theBank.account_holder_login(User_Id, pin);
            if(authenticated_user == null){
                System.out.println("Incorrect user id or pin"+"Please try again");

            }

        }while(authenticated_user == null);{ // This loop continues until login is successful
            return authenticated_user;

        }

    }
    public static void output_menu(Users login, Scanner sc){
        login.my_account();

        int ch;
        do{
            System.out.printf("Greetings %s, what would you like to do?", login.first_name());
            System.out.println("\n 1. Transaction History");
            System.out.println("\n 2. Withdraw");
            System.out.println("\n 3. Deposit");
            System.out.println("\n 4. Transfer");
            System.out.println("\n 5. Exit");

            System.out.println("\n Enter the choice :");
            ch = sc.nextInt();
            if(ch < 1 || ch > 5){
                System.out.println("Invalid choice, Please try again");
            }
        }while(ch < 1 || ch > 5);{
            switch(ch){
                case 1:
                    ATM.display_Transaction_History(login, sc);
                    break;
                case 2:
                    ATM.display_withdraw_money(login, sc);
                    break;
                case 3:
                    ATM.display_deposit_money(login, sc);
                    break;
                case 4:
                    ATM.display_transfer_money(login, sc);
                    break;
                case 5:
                    sc.nextLine();
                    break;

            }
            if(ch != 5){
                ATM.output_menu(login, sc);
            }

        }

        }

    public static void display_Transaction_History(Users login,Scanner sc) {
        int particular_account;
        // This is to get account whose transaction history needed to be as an output

        do {
            System.out.printf("\nEnter the number(1-%d) of the account whose transaction to be showed", login.number_of_accounts());
            particular_account = sc.nextInt()-1;
            if(particular_account < 0 || particular_account >= login.number_of_accounts()){
                System.out.println("Invalid account! Please try again.");
            }

        } while (particular_account < 0 || particular_account >= login.number_of_accounts());
        // Printing transaction history
        login.display_account_transaction_history(particular_account);
    }
    public static void display_transfer_money(Users login, Scanner sc){
        int from_account;
        int to_account;
        double amount;
        double account_balance;

        // First get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer money form:",login.number_of_accounts());
            from_account = sc.nextInt()-1;
            if(from_account < 0 || from_account >= login.number_of_accounts()){
                System.out.println("Invalid account! Please try again");
            }

        }while(from_account < 0 || from_account >= login.number_of_accounts());
        account_balance = login.get_account_balance(from_account);

        // money transfers to the account to
        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer money to:",login.number_of_accounts());
            to_account = sc.nextInt()-1;
            if(to_account < 0 || to_account >= login.number_of_accounts()){
                System.out.println("Invalid account! Please try again");
            }

        }while(to_account < 0 || to_account >= login.number_of_accounts());

        // Amount to be transferred
        do{
            System.out.printf("Enter the amount to be transferred "
        +"(Note : You can transfer maximum Rs%.02f): Rs", account_balance);
            amount = sc.nextDouble();
            if(amount < 0 ){
                System.out.println("Amount must be great than zero");
            }else if(amount > account_balance){
                System.out.printf("Amount entered Rs%.02f is greater than the balance Rs%.02f\n",amount,account_balance);

            }
        }while(amount < 0 || amount > account_balance);

        // Perform the transfer
        login.add_account_transaction(from_account, -1*amount, String.format("Transfer to account %s", login.get_account_uniqueid(to_account)));
        login.add_account_transaction(to_account, amount, String.format("Transfer to account %s", login.get_account_uniqueid(to_account)));

    }
    public static void display_withdraw_money(Users login, Scanner sc){
        int from_account;

        double amount;
        double account_balance;
        String Info;

        // First get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account to withdraw money form:",login.number_of_accounts());
            from_account = sc.nextInt()-1;
            if(from_account < 0 || from_account >= login.number_of_accounts()){
                System.out.println("Invalid account! Please try again");
            }

        }while(from_account < 0 || from_account >= login.number_of_accounts());
        account_balance = login.get_account_balance(from_account);
        // Amount to be transferred
        do{
            System.out.printf("Enter th e amount to be withdraw "
                    +"(Note : You can transfer maximum Rs%.02f): Rs", account_balance);
            amount = sc.nextDouble();
            if(amount < 0 ){
                System.out.println("Amount must be great than zero");
            }else if(amount > account_balance){
                System.out.printf("Amount entered Rs%.02f is greater than the balance Rs%.02f\n",amount,account_balance);

            }
        }while(amount < 0 || amount > account_balance);

        sc.nextLine();
        // get info
        System.out.println("Enter the info :");
        Info = sc.nextLine();

        // The withdraw procedure
        login.add_account_transaction(from_account, -1*amount, Info);


    }

    // Procedure to deposit money
    public static void display_deposit_money(Users login, Scanner sc){

        int to_account;
        double amount;
        double account_balance;
        String Info;

        // First get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account to deposit money :",
                    login.number_of_accounts());
            to_account = sc.nextInt()-1;
            if(to_account < 0 || to_account >= login.number_of_accounts()){
                System.out.println("Invalid account! Please try again");
            }

        }while(to_account < 0 || to_account >= login.number_of_accounts());
        account_balance = login.get_account_balance(to_account);
        // Amount to be transferred
        do{
            System.out.printf("Enter the amount to be transferred "
                    +"(Note : You can transfer maximum Rs%.02f): Rs", account_balance);
            amount = sc.nextDouble();
            if(amount < 0 ){
                System.out.println("Amount must be great than zero");
            }
        }while(amount < 0 );

        sc.nextLine();
        // get info
        System.out.println("Enter the info :");
        Info = sc.nextLine();

        // The withdraw procedure
        login.add_account_transaction(to_account, amount, Info);


    }
}
