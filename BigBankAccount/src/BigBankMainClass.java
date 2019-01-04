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
		String isNum;
		boolean numMatch=false;
		boolean error=false;
		double inputNum;
		boolean runNextBlock2;
		System.out.println("Hello! Welcome to the Bank Account Class!");
		while (run)
		{ 
			System.out.println("Type 'N' to create a new account,"
					+ " 'T' to make a transaction, or 'E' to end the program: ");
			String choice = in.nextLine();
			if (choice.equals("N")||choice.equals("n"))
			{
				choosingType=true;
				while(choosingType)
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
				while (runNextBlock)
				{
					System.out.println("Would you like to create a starting balance? "
							+ "Please enter 'Y' for yes, or 'N' for no.");
					String startingBal = in.nextLine();
					if (startingBal.equals("Y")||startingBal.equals("y"))
					{
						runStartBalCheck=true;
						//messes up when u enter something that isnt an int
						while (runStartBalCheck)
						{
							System.out.println("Please enter a starting balance: ");
							isNum=in.nextLine();
							if (isNumeric(isNum))
							{
								startBal=Double.parseDouble(isNum);
								System.out.println(startBal);
								runStartBalCheck=false;
								runNextBlock=false;
							}
							else if (!isNumeric(isNum))
							{
								System.out.println("Yo! Your input wasn't a number. Please try again. ");
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
				if (checkingAcc)
				{
					accounts.add(new CheckingAccount(accountHolder, startBal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				}
				else
				{
					accounts.add(new SavingsAccount(accountHolder, startBal, RATE, MIN_BAL, MIN_BAL_FEE));
				}
				
			}
			else if (choice.equals("T")||choice.equals("t"))
			{
				runNextBlock=true;
				while (runNextBlock)
				{
					System.out.println("What is the number of the account would you like to make a transaction in? ");
					if (error)
					{
						in.next();
					}
					if(in.hasNextInt())
					{
						number= in.nextInt();
						for(BankAccount account : accounts)
						{
							if(number==account.getAccNum())
							{
								System.out.println("It works!");
								runNextBlock=false;
								numMatch=true;
							}
						}
						if (!numMatch)
						{
							System.out.println("Hey! You didn't enter an account number! Try again");
						}
					}
					else
					{
						error=true;
						System.out.println("That's not an integer! Try again.");
					}
				}
				runNextBlock=true;
				while (runNextBlock)
				{
					System.out.println("What type of transactiton would you like to make? Type 'W' for  withdraw, 'D' for deposit, or 'T' for a transfer");
					System.out.println("before in.next");
					in.next();
					System.out.println("after in.next");
					choice=in.nextLine();
					System.out.println("after in.nextLine");
					if (choice.equals("W")||choice.equals("w"))
					{
						System.out.println("does this fix it?");
						System.out.println("How much money would you like to withdraw?: ");
						isNum=in.nextLine();
						if (isNumeric(isNum))
						{
							inputNum=Double.parseDouble(isNum);
							System.out.println(inputNum);
							runStartBalCheck=false;
							runNextBlock=false;
						}
						else if (!isNumeric(isNum))
						{
							System.out.println("Yo! Your input wasn't a number. Please try again. ");
						}
					}
					else if (choice.equals("D")||choice.equals("d"))
					{
						runNextBlock2=true;
						while(runNextBlock2)
						{
							System.out.println("How much money would you like to deposit?: ");
							isNum=in.nextLine();
							if (isNumeric(isNum))
							{
								inputNum=Double.parseDouble(isNum);
								System.out.println(inputNum);
								runNextBlock=false;
							}
							else if (!isNumeric(isNum))
							{
								System.out.println("Yo! Your input wasn't a number. Please try again. ");
							}
						}
					}
					else if (choice.equals("T")||choice.equals("t"))
					{
						runNextBlock=false;
						System.out.println("How much money would you like to transfer?: ");
						isNum=in.nextLine();
						if (isNumeric(isNum))
						{
							inputNum=Double.parseDouble(isNum);
							System.out.println(inputNum);
							runStartBalCheck=false;
							runNextBlock=false;
						}
						else if (!isNumeric(isNum))
						{
							System.out.println("Yo! Your input wasn't a number. Please try again. ");
						}
					}
					else
					{
						System.out.println("Yo! You entered something wrong. Try again.");
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
