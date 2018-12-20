
public class SavingsAccount extends BankAccount
{
	//fields
	double intRate;
	final double MIN_BAL_FEE;
	final double MIN_BAL;
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate= r;
		MIN_BAL_FEE= mbf;
		MIN_BAL= mb;
	}
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		this(n, 0, r, mb, mbf);
	}
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
	public void addInterest()
	{
		super.deposit(super.getBalance()*intRate);
	}
	public void endOfMonthUpdate()
	{
		this.addInterest();
	}
}
