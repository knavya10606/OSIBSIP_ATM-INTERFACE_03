import java.util.*;

public class atminterface {

    // ===== User Class =====
    static class User {
        String userId;
        String pin;

        User(String userId, String pin) {
            this.userId = userId;
            this.pin = pin;
        }

        boolean login(String id, String p) {
            return userId.equals(id) && pin.equals(p);
        }
    }

    // ===== BankAccount Class =====
    static class BankAccount {
        double balance;
        List<String> history = new ArrayList<>();

        BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        void printHistory() {
            if (history.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                System.out.println("\n---- Transaction History ----");
                history.forEach(System.out::println);
            }
        }

        void withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                history.add("Withdrawn: " + amount);
                System.out.println("Withdraw successful.");
            } else {
                System.out.println("Insufficient balance!");
            }
        }

        void deposit(double amount) {
            balance += amount;
            history.add("Deposited: " + amount);
            System.out.println("Deposit successful.");
        }

        void transfer(double amount, String receiver) {
            if (amount <= balance) {
                balance -= amount;
                history.add("Transferred: " + amount + " to " + receiver);
                System.out.println("Transfer successful to " + receiver);
            } else {
                System.out.println("Insufficient balance!");
            }
        }
    }

    // ===== MAIN ATM INTERFACE =====
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = new User("navya123", "0000");
        BankAccount account = new BankAccount(5000);

        System.out.println("===== WELCOME TO ATM INTERFACE =====");

        // Login
        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (!user.login(id, pin)) {
            System.out.println("Invalid login. Exiting...");
            return;
        }

        System.out.println("Login Successful!");

        // Menu Loop
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.printHistory();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    account.withdraw(sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    account.deposit(sc.nextDouble());
                    break;

                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter receiver name: ");
                    String receiver = sc.nextLine();
                    account.transfer(amt, receiver);
                    break;

                case 5:
                    System.out.println("Thank you for using ATM!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
