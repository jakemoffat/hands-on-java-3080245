package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  // Take input class essentially

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    // Everything in between opening and closing the scanner
    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }



    menu.scanner.close();
  }

  private Customer authenticateUser() {
    System.out.print("Please enter your Username: ");
    String username = scanner.next();

    System.out.print("Please enter your Password: ");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);

    } catch (LoginException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while(selection != 4 && customer.isAuthenticated()) {
      System.out.println("===================================================");
      System.out.println("Please selection one of the following options: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("===================================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch(selection) {
        case 1:
        System.out.println("How much would you like to deposit?");
        System.out.print("$");
        amount = scanner.nextDouble();
        try {
          account.deposit(amount);
        } catch (AmountException e) {
          System.out.println(e.getMessage());
          System.out.println("Please try again");
        }
        break;

        case 2:
        System.out.println("How much would you like to withdraw?");
        System.out.print("$");
        amount = scanner.nextDouble();
        try{
          account.withdraw(amount);
        } catch (AmountException e) {
          System.out.println(e.getMessage());
          System.out.println("Please try again");
        }
        break;

        case 3:
        System.out.println("Your Current Balance: $" + account.getBalance());
        break;

        case 4:
        Authenticator.logout(customer);
        System.out.println("Thanks for banking at Globe Bank International");
        break;

        default:
        System.out.println("Invalid Input, Please try again");
        break;
      }
    }
  }
}
