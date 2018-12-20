
public abstract class BankAccount 
{
	//fields
	/** add accnum to constructor and getnum method
	 * 
	 */
	private static int nextAccNum;
	private String accName;
	private int accNum;
	private double balance;
	//constructors
	/**
	 * 
	 * @param n; instantiates account name
	 * @param b; instantiates account balance
	 */
	public BankAccount(String n, double b)
	{
		accName= n;
		balance=b;
	}
	/**
	 * 
	 * @param n represents the name of the account
	 */
	public BankAccount(String n)
	{
		accName= n;
	}
	//methods
	/**
	 * deposits money into the bank account
	 * @param amt; the amount the user wants to deposit into the account
	 */
	public void deposit(double amt)
	{
		balance+=amt;
	}
	/**
	 * withdraws money from the bank account
	 * @param amt; the amount the user wants to withdraw from the account
	 */
	public void withdraw(double amt)
	{
		balance-=amt;
	}
	/**
	 * this method gives the name of the account holder
	 * @return returns the account name
	 */
	public String getName()
	{
		return accName;
	}
	/**
	 * this method lets the user check the balance in the account
	 * @return returns the balance of the account
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * 
	 * will be implemented later
	 */
	public abstract void endOfMonthUpdate();
	/**
	 * this method allows a user to transfer money from their own bank account to another
	 * @param other; another bankaccount that will have money transfered to it
	 * @param amt; the amount of money the user wishes to transfer
	 */
	public void transfer(BankAccount other, double amt)
	{
		balance-=amt;
		other.deposit(amt);
	}
	/**
	 * this method lets the user see all of the bankaccount information at once
	 */
	public String toString()
	{
		return "\t" + accNum + "\t" + accName + "\t$" + balance;
	}
}
