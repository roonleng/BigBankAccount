public  class CheckingAccount extends BankAccount
{
	//constants
	/**
	 * fees do not need to change so they are made final. 
	 * overdraft fee is the amount of money withdrawn as penalty for when the account is overdrawn
	 * transaction fee is incurred when the supply of free transactions has been used up
	 * free trans is the number of free transactions allowed before the transaction fee is applied
	 * 
	 */
	final double OVER_DRAFT_FEE;
	final double TRANSACTION_FEE;
	final double FREE_TRANS;
	int numTransactions;
	/**
	 * constructs a checking account with a starting balance
	 * @param n, name: takes the name of the account
	 * @param b, the starting balance of the account
	 * @param odf, over draft fee, incurred when the account is overdrawn
	 * @param tf transaction fee, incurred when the number of free transactions is exceeded
	 * @param freeTrans, free transactions, the number of free transactions that can occur
	 */

	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	/**
	 * constructs a checking account without a starting balance
	 * @param n, name: takes the name of the account
	 * @param odf, over draft fee, incurred when the account is overdrawn
	 * @param tf transaction fee, incurred when the number of free transactions is exceeded
	 * @param freeTrans, free transactions, the number of free transactions that can occur
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
	}
	/**
	 * deposit method takes an amount and attempts to deposit it into an account
	 * deposit checks to see if the amount is above zero, then deposits
	 * it also checks the free trans to see if it should incur the transaction fee
	 */
	public void deposit(double amt)
	{
		if (amt<=0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			super.deposit(amt);
			numTransactions++;
			if(numTransactions>FREE_TRANS)
			{
				super.withdraw(TRANSACTION_FEE);
			}
		}
	}
	/**
	 * withdraw method takes an amount and attempts to with draw it from the account
	 * it checks to see if the amount is greater than 0
	 * checks to see if the account balance is greater than 0, wont withdraw if else
	 * checks to see if the amount is greater than the balance, if not it withdraws and checls for transaction fee
	 * if the amount is greater than the balance, it incurs the overdraft fee and checks trans fee
	 */
	public void withdraw(double amt)
	{
		if(amt>0)
		{
			if (super.getBalance()<=0)
			{
				throw new IllegalArgumentException();			
			}
			else if (super.getBalance()>=amt)
			{
				super.withdraw(amt);
				numTransactions++;
				if(numTransactions>FREE_TRANS)
				{
					super.withdraw(TRANSACTION_FEE);
				}
			}
			
			else
				{
					super.withdraw(OVER_DRAFT_FEE);
					super.withdraw(amt);
					numTransactions++;
					if(numTransactions>FREE_TRANS)
					{
						super.withdraw(TRANSACTION_FEE);
					}
				}
		}
		else
		{
			throw new IllegalArgumentException();			

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
		if (other.getName().equals(this.getName()))
		{
			if (amt<=this.getBalance() && amt>0)
			{
				this.withdraw(amt);
				other.deposit(amt);
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	/**
	 * unused endOfMothUpdate() method
	 * adds interst rate to account
	 */
	public void endOfMonthUpdate()
	{
		numTransactions=0;
	}
}