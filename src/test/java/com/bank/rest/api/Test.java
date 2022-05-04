package com.bank.rest.api;

public class Test {

	public static void main(String[] args) {

		printAmortizationSchedule(100,1,1);
		double p =500.0;
		double r =1;
		int t = 2;
		double amt = p;
		 for (int i = 1; i <= t; i++) {
	            double interest = (amt * r * 1) / 100.0;
	            amt += interest;
	            System.out.println("Amount after " + i 
	                                + " year = " + amt);
	        }
	}

	public static void printAmortizationSchedule(double principal, double annualInterestRate, int numYears) {
		double interestPaid, principalPaid, newBalance;
		double monthlyInterestRate, monthlyPayment;
		int month;
		int numMonths = numYears * 12;

// Output monthly payment and total payment
		monthlyInterestRate = annualInterestRate / 12;
		monthlyPayment = monthlyPayment(principal, monthlyInterestRate, numYears);
		System.out.format("Monthly Payment: %8.2f%n", monthlyPayment);
		System.out.format("Total Payment:   %8.2f%n", monthlyPayment * numYears * 12);

// Print the table header
		printTableHeader();

		for (month = 1; month <= numMonths; month++) {
// Compute amount paid and new balance for each payment period
			interestPaid = principal * (monthlyInterestRate / 100);
			principalPaid = monthlyPayment - interestPaid;
			newBalance = principal - principalPaid;

// Output the data item
			printScheduleItem(month, interestPaid, principalPaid, newBalance);

// Update the balance
			principal = newBalance;
		}
	}

	/**
	 * @param loanAmount
	 * @param monthlyInterestRate in percent
	 * @param numberOfYears
	 * @return the amount of the monthly payment of the loan
	 */
	static double monthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfYears) {
		monthlyInterestRate /= 100; // e.g. 5% => 0.05
		return loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
	}

	/**
	 * Prints a table data of the amortization schedule as a table row.
	 */
	private static void printScheduleItem(int month, double interestPaid, double principalPaid, double newBalance) {
		System.out.format("%8d%10.2f%10.2f%12.2f\n", month, interestPaid, principalPaid, newBalance);
	}

	/**
	 * Prints the table header for the amortization schedule.
	 */
	private static void printTableHeader() {
		System.out.println("\nAmortization schedule");
		for (int i = 0; i < 40; i++) { // Draw a line
			System.out.print("-");
		}
		System.out.format("\n%8s%10s%10s%12s\n", "Payment#", "Interest", "Principal", "Balance");
		System.out.format("%8s%10s%10s%12s\n\n", "", "paid", "paid", "");
	}
}
