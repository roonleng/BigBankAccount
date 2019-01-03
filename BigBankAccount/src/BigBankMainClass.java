import java.util.ArrayList;
import java.util.Scanner;
public class BigBankMainClass {
//TEST CLASS ISSUE GO BACK AND FIX LATER
//FIGURE OUT ISNUMERIC AND USING IT TO TEST NUMBER INPUTS
	public static boolean isNumeric(String num) 
	{
		try 
		{
			Double.parseDouble(num);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		//constants
		/**
		 * overdraft fee 
		 */
		final int OVER_DRAFT_FEE = 15;
		final  double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final int MIN_BAL = 300;
		final int MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS=10;
		//variables
		/**
		 * most of the booleans are just checking to see if the user entered things correctly
		 */
		boolean run=true;
		boolean checkingAcc = false;
		boolean choosingType;
		boolean runStartBalCheck;
		boolean runNextBlock;
		double startBal=0;
		String accountHolder;
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		String name;
		int accNumber;
		double amt;
		int number=0;
		System.out.println("Hello! Welcome to the Bank Account Class!");
		while (run==true)
		{ 
			System.out.println("Type 'N' to create a new account,"
					+ " 'T' to make a transaction, or 'E' to end the program: ");
			String choice = in.nextLine();
			if (choice.equals("N")||choice.equals("n"))
			{
				choosingType=true;
				while(choosingType==true)
				{
					System.out.println("What type of account would you like to create? Type 'C'"
							+ " for a checking account, or 'S' for a savings account: ");
					String checkSave = in.nextLine();
					if (checkSave.equals("C")||checkSave.equals("c"))
					{
						checkingAcc=true;
						choosingType=false;
					}
					else if (checkSave.equals("S")||checkSave.equals("s"))
					{
						checkingAcc=false;
						choosingType=false;
					}
					else
					{
						System.out.println("Hey! You put something in wrong. Please retry the input");
					}
				}
				System.out.println("Please enter the name of the account holder:   ");
				accountHolder=in.nextLine();
				runNextBlock=true;
				while (runNextBlock==true)
				{
					System.out.println("Would you like to create a starting balance? "
							+ "Please enter 'Y' for yes, or 'N' for no.");
					String startingBal = in.nextLine();
					if (startingBal.equals("Y")||startingBal.equals("y"))
					{
						runStartBalCheck=true;
						//messes up when u enter something that isnt an int
						while (runStartBalCheck==true)
						{
							System.out.println("Please enter a starting balance: ");
							if (isNumeric(in.hasNextLine))
							{
								
							}
							else
							{
								System.out.println("Yo! Your input wasn't an integer. Please try again. ");
							}
						}
					}
					else if (startingBal.equals("N")||startingBal.equals("n"))
					{
							runNextBlock=false;
					}
					else
					{
						System.out.println("Yo! Your input wasn't Y or N. Please try again. ");
					}
				}
				if (checkingAcc==true)
				{
					accounts.add(new CheckingAccount(accountHolder, startBal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				}
				else
				{
					accounts.add(new SavingsAccount(accountHolder, startBal, RATE, MIN_BAL, MIN_BAL_FEE));
				}
				
			}
			if (choice.equals("T")||choice.equals("t"))
			{
				runNextBlock=true;
				while (runNextBlock==true)
				{
					System.out.println("What is the number of the account would you like to make a transaction in? ");
					number= in.nextInt();
					for(BankAccount account : accounts)
					{
						if(number==account.getAccNum());
							runNextBlock=false;
					}
					if (runNextBlock=true)
					{
						System.out.print("What you entered was not the name of the account. Please try again. ");
					}
				}
				runNextBlock=true;
				while (runNextBlock=true)
				{
					System.out.println("What type of transactiton would you like to make? Type 'W' for  withdraw, 'D' for deposit, or 'T' for a transfer");
					choice=in.nextLine();
					if (choice.equals("W")||choice.equals("w"))
					{
						runNextBlock=false;
						System.out.println("How much money would you like to withdraw?: ");
						if(in.hasNextDouble()||in.hasNextInt())
						{
							amt=in.nextDouble();
							accounts.get(number).withdraw(amt);
						}
					}
					else if (choice.equals("D")||choice.equals("d"))
					{
						runNextBlock=false;
						System.out.println("How much money would you like to deposit?: ");
						if(in.hasNextDouble()||in.hasNextInt())
						{
							amt=in.nextDouble();
							accounts.get(number).withdraw(amt);
						}

					}
					else if (choice.equals("T")||choice.equals("t"))
					{
						runNextBlock=false;
						System.out.println("How much money would you like to transfer?: ");
						if(in.hasNextDouble()||in.hasNextInt())
						{
							amt=in.nextDouble();
							accounts.get(number).withdraw(amt);
						}

					}
					else
					{
						System.out.print("Yo! You entered something that wasn't an option. Try again.");
					}
				}
			}
			else if (choice.equals("E")||choice.equals("e"))
			{
				System.out.println("Goodbye!");
				run=false;
			}
			else
			{
				System.out.println("Yo! You typed in something that wasn't an option. Please try again.");
			}
		}
	}
}
