package peach.anonymiser;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import peach.anonymiser.service.impl.COSDAnonymiser;
import peach.anonymiser.service.impl.CWTAnonymiser;



public class Driver2 {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		boolean completed = false;
		try {
			
			while (!completed) {
				System.out.println("Please type one of the following:");
				System.out.println("\t 1 for CWT Anonymiser");
				System.out.println("\t 2 for COSD Anonymiser");
				
				int anonymiserType = scanner.nextInt();
				
				if (anonymiserType == 1 || anonymiserType == 2) {
					System.out.println("Enter the file path of the input data. Use '/' to denote subdirectories. End with "
							+ "<filename>.csv. Alternatively, if it is stored in your current working directory, just enter "
							+ "<filename>.csv ");
					String input = scanner.next();
					
					
					System.out.println("Enter the file path of where the anonymised data is to be stored. "
							+ "Use '/' to denote subdirectories. End with "
							+ "<filename>.csv. Alternatively, if it is stored in your current working directory, just enter "
							+ "<filename>.csv ");
					String output = scanner.next();
					
					String currentDir = System.getProperty("user.dir");
					currentDir = currentDir.replace('\\', '/');
					
					if (!input.contains("/")) {
						String temp = input;
						input = currentDir + "/" + temp;
						
					} 
					if (!output.contains("/")) {
						String temp = output;
						output = currentDir + "/" + temp;
					}
					
					if (anonymiserType == 1) {
						CWTAnonymiser cwt = new CWTAnonymiser(input, output);
						cwt.anonymise();
					} else {
						COSDAnonymiser cosd = new COSDAnonymiser(input, output);
						cosd.anonymise();
					}
					System.out.println("Anonymization completed.");
					completed = true;
				} else {
					System.out.println("Enter either 1 or 2");
				}

			}

		} catch (InputMismatchException e) {
			System.out.println("Please enter a number");
		} finally {
			scanner.close();
		}
	}

}
