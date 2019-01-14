import java.util.ArrayList;
import java.util.Scanner;
public class BigBankMainClass 
{
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
		int accNumber=0;
		double amt;
		int number=0;
		String isNum;
		boolean numMatch=false;
		double inputNum;
		boolean runNextBlock2=true;
		String checkChoice;
		String accNameCheck;
		boolean validName;
		String invalidNameChoice;
		boolean validNameQ;
		boolean askCheck;
		boolean runNextBlock3=true;
		String getAccNumbers;
		boolean askGetNums;
		String response;
		int accNumInfo;
		String accNumTrans;
		int accIntTrans=0;
		String transferToName=null;
		String transferFromName=null;
		BankAccount transferTo=null;
		System.out.println("Hello! Welcome to the Bank Account Class!");
		while (run)
		{ 
			System.out.println("Type 'N' to create a new account,"
					+ " 'T' to make a transaction, 'I' to check account info, or 'E' to end the program: ");
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
						while (runStartBalCheck)
						{
							System.out.println("Please enter a starting balance: ");
							isNum=in.nextLine();
							if (isNumeric(isNum))
							{
								startBal=Double.parseDouble(isNum);
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
					askGetNums=true;
					while (askGetNums)
					{
						System.out.println("Would you like to get your account numbers first? Enter 'Y' for yes, or 'N' to proceed to transactions.");
						getAccNumbers=in.nextLine();
						validName=false;
						if (getAccNumbers.equals("y")||getAccNumbers.equals("Y"))
						{
							System.out.println("Enter in the name of the account");
							accNameCheck=in.nextLine();
							for (BankAccount account: accounts)
							{
								if (accNameCheck.equals(account.getName()))
								{
									System.out.println("The number of that account is " + account.getAccNum());
									validName=true;
									askGetNums=false;
								}
							}
						}
						else if (getAccNumbers.equals("n")||getAccNumbers.equals("N"))
						{
							askGetNums=false;
						}
						else
						{
							System.out.println("That wasn't a yes or no answer- try again.");
						}
						if (!validName&&getAccNumbers.equals("y")||getAccNumbers.equals("Y"))
						{
							System.out.println("You entered something wrong. Try again.");
						}
					}
					System.out.println("What is the number of the account you would like to make a transaction in? ");
					//waits for response before running any of the following blocks; even though the if/else unarchived is on the same indent, it doesn't run until it has an input and has tested it
					String test=in.nextLine();
					if (isNumeric(test))
					{
						accNumber=Integer.parseInt(test);
						for(BankAccount account : accounts)
						{
							if(accNumber==account.getAccNum())
							{
								runNextBlock=false;
								numMatch=true;
							}
						}
					}
					if (!numMatch||!(isNumeric(test)))
					{
						askCheck=true;
						validName=false;
						while(askCheck)
						{
							runNextBlock2=true;
							System.out.println("That's not a valid input; would you like to see the number of your account? Enter 'Y' to check the number, or 'N' to re-enter");
							checkChoice=in.nextLine();
							if (checkChoice.equals("y")||checkChoice.equals("Y"))
							{
								askCheck=false;
								runNextBlock3=true;
							}
							else if (checkChoice.equals("n")||checkChoice.equals("N"))
							{
								askCheck=false;
								validName=true;
								runNextBlock3=false;
							}
							else
							{
								System.out.println("Hey! Whatever you entered wasnt y or n; try again.");
							}
						}
						while (runNextBlock3)
						{
							while (runNextBlock2)
							{
								System.out.println("Enter in the name of the account");
								accNameCheck=in.nextLine();
								for (BankAccount account: accounts)
								{
									if (accNameCheck.equals(account.getName()))
									{
										System.out.println("The number of that account is " + account.getAccNum());
										validName=true;
										runNextBlock3=false;
									}
								}
								runNextBlock2=false;
							}
							if(!validName)
							{
								validNameQ=true;
								while (validNameQ)
								{
									System.out.println("That account name isn't in the register. Would you like to try again or go back to main menu? Enter 'Y' to try again or 'N' to go back to the menu.");
									invalidNameChoice=in.nextLine();
									if (invalidNameChoice.equals("n")||invalidNameChoice.equals("N"))
									{
										runNextBlock3=false;
										runNextBlock2=false;
										runNextBlock=false;
										validNameQ=false;
									}
									else if (invalidNameChoice.equals("y")||invalidNameChoice.equals("Y"))
									{
										validNameQ=false;
										runNextBlock2=true;
										runNextBlock3=true;
									}
									else
									{
										System.out.println("Hey! Thats not a yes or no answer. Try again.");
									}
								}
							}
						}
					}
				}
				runNextBlock=true;
				while (runNextBlock)
				{
					System.out.println("What type of transactiton would you like to make? Type 'W' for  withdraw, 'D' for deposit, or 'T' for a transfer. Press 'M' to return to main menu");
					choice=in.nextLine();
					if (choice.equals("W")||choice.equals("w"))
					{
						runNextBlock2=true;
						while (runNextBlock2)
						{
							System.out.println("How much money would you like to withdraw?: ");
							isNum=in.nextLine();
							for (BankAccount account : accounts)
							{
								if (accNumber==account.getAccNum())
								{
									runNextBlock2=false;
									try
									{
										account.withdraw(Double.parseDouble(isNum));
									}
									catch(IllegalArgumentException e)
									{
										System.out.println("Transaction not authorized.");
										runNextBlock2=false;
									}
								}
							}
						}
					}
					else if (choice.equals("D")||choice.equals("d"))
					{
						runNextBlock2=true;
						while(runNextBlock2)
						{
							System.out.println("How much money would you like to deposit?: ");
							isNum=in.nextLine();
							for (BankAccount account : accounts)
							{
								if (accNumber==account.getAccNum())
								{
									runNextBlock2=false;
									try
									{
										account.deposit(Double.parseDouble(isNum));
									}
									catch(IllegalArgumentException e)
									{
										System.out.println("Transaction not authorized.");
										runNextBlock2=false;
									}
								}
							}
						}
					}
					else if (choice.equals("T")||choice.equals("t"))
					{
						runNextBlock2=true;
						while(runNextBlock2)
						{
							runNextBlock3=true;
							while(runNextBlock3)
							{
								System.out.println("What is the number of the account you're transferring to?");
								accNumTrans=in.nextLine();
								try
								{
									accIntTrans=Integer.parseInt(accNumTrans);
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized.");
								}
								for (BankAccount account : accounts)
								{
									if (accNumber==account.getAccNum())
									{
										transferFromName=account.getName();
									}
								}
								accIntTrans=Integer.parseInt(accNumTrans);
								for (BankAccount account : accounts)
								{
									if (accIntTrans==account.getAccNum())
									{
										transferToName=account.getName();
									}
								}
								if (transferToName.equals(transferFromName))
								{
									runNextBlock3=false;
								}
								else
								{
									System.out.println("The accounts aren't under the same name. Try again");
								}
							}
							runNextBlock3=true;
							while(runNextBlock3)
							{
								System.out.println("How much money would you like to transfer?: ");
								isNum=in.nextLine();
								for (BankAccount transferToLoop: accounts)
								for (BankAccount account : accounts)
								{
									System.out.println("test1");
									/**for (BankAccount transferToLoop: accounts)
									{
										System.out.println("test2");

										if (accIntTrans==transferTo.getAccNum())
										{
											System.out.println("test3");

											transferTo = transferToLoop;
										}
									*/}
									if (accNumber==account.getAccNum())
									{
										try
										{
											System.out.println("test4");

											account.transfer(transferTo, Double.parseDouble(isNum));
											System.out.println("We out here");
										}
										catch(IllegalArgumentException e)
										{
											System.out.println("Transaction not authorized");
											runNextBlock2=true;
											runNextBlock3=false;
										}
									}
								}
							}
						}

					}
					else if (choice.equals("M")||choice.equals("m"))
					{
						runNextBlock=false;
					}
					else
					{
						System.out.println("Yo! You entered something wrong. Try again.");
					}
				}
			}

			else if (choice.equals("I")||choice.equals("i"))
			{
				System.out.println("Pick an account number you want the info for");
				response=in.nextLine();
				try
				{
					accNumInfo=Integer.parseInt(response);
					for (BankAccount account : accounts)
					{
						if (accNumInfo==account.getAccNum())
						{
							System.out.println(account.toString());
						}
					}
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Transaction not Authorized");
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