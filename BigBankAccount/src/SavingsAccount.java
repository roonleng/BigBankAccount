
public class SavingsAccount extends BankAccount
{
	//fields/constants
	double intRate;
	final double MIN_BAL_FEE;
	final double MIN_BAL;
	/**
	 * constructs a savings account with a starting balance
	 * @param n, name: takes the name of the account
	 * @param b, the starting balance of the account
	 * @param intRate, the rate of interest applied to the savings account in endofthemonth and addInterest methods
	 * @param mbf, minimum balance fee, the fee incurred when the account drops below the minimum balance
	 * @param mb, minimum balance, the minimum ammount that must be in the account without the mbf being incurred
	 */

	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate= r;
		MIN_BAL_FEE= mbf;
		MIN_BAL= mb;
	}
	/**
	 * constructs a checking account with a starting balance
	 * @param n, name: takes the name of the account
	 * @param b, the starting balance of the account
	 * @param intRate, the rate of interest applied to the savings account in endofthemonth and addInterest methods
	 * @param mbf, minimum balance fee, the fee incurred when the account drops below the minimum balance
	 * @param mb, minimum balance, the minimum ammount that must be in the account without the mbf being incurred
	 */

	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		this(n, 0, r, mb, mbf);
	}
	/**
	 * deposit method takes an amount and attempts to deposit it into an account
	 * deposit checks to see if the amount is above zero, then deposits
	 */
	public void deposit(double amt)
	{
		if (amt<0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			super.deposit(amt);
		}
	}
	/**
	 * withdraw method takes an amount and attempts to with draw it from the account
	 * it checks to see if the amount is greater than 0; throws exception if else
	 * checks to see if the account balance is greater than amt, wont withdraw if else (wont let account go below zero)
	 * checks to see if the amount is greater than the balance, if not it withdraws and checls for transaction fee
	 * if the new balance is below the min bal, incurs minimum balance fee
	 */
	public void withdraw(double amt)
	{
		if (amt<=0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (super.getBalance()>=amt)
			{
				super.withdraw(amt);
				if (super.getBalance()<MIN_BAL)
				{
					super.withdraw(MIN_BAL_FEE);
				}
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
	}
	/**
	 * transfer method attempts to withdraw money the account calling the method and deposit it into another account with the same name
	 * first checks to see if accounts have the same name; throws exception if else
	 * then checks to see if amount is less than balance of the first account and greater than 0; throws exception if else
	 * then it withdraws from first and deposits into second; throws exception if else
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (other.getName()==super.getName())
		{
			this.withdraw(amt);
			other.deposit(amt);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	/**
	 * unused addInterest method
	 * adds interest to account balance
	 */
	public void addInterest()
	{
		super.deposit(super.getBalance()*intRate);
	}
	/**
	 * unused end of month update method
	 * adds interest
	 * useless
	 */
	public void endOfMonthUpdate()
	{
		this.addInterest();
	}
}
