package bank;

import bank.exceptions.AmountException;

public class Account {

  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException{
    if (amount <= 0) {
      throw new AmountException("Deposit Value cannot be $0 or Negative");
    }
    else {
      double newBalance = balance + amount;
      setBalance(newBalance);
    }
  }

  public void withdraw(double amount) {

  }

}