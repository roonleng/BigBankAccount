
public  class CheckingAccount extends BankAccount
{
	/**
	 * fees do not need to change so they are made final. 
	 * free trans is the number of free transactions allowed before the transaction fee is applied
	 * 
	 */
	final double OVER_DRAFT_FEE;
	final double TRANSACTION_FEE;
	final double FREE_TRANS;
	int numTransactions;
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
	}
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
	public void withdraw(double amt)
	{
		if(amt>0 || super.getBalance()<0)
		{
			if (super.getBalance()<0)
			{
				throw new IllegalArgumentException();			
			}
			else if (super.getBalance()>=amt)
			{
				super.withdraw(amt);
				numTransactions++;
			}
			
			else
				{
					super.withdraw(OVER_DRAFT_FEE);
					super.withdraw(amt);
					numTransactions++;
				}
		}
		else
		{
			throw new IllegalArgumentException();			

		}
		if(numTransactions>FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
	}
	public void transfer(BankAccount other, double amt)
	{
		if (other.getName()==super.getName())
		{
			if (amt<=super.getBalance() && amt>0)
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
	public void endOfMonthUpdate()
	{
		numTransactions=0;
	}
}
