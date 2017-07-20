package peach.anonymiser;

import peach.anonymiser.service.impl.CWTAnonymiser;

public class Driver {

	public static void main(String[] args) {
		
		String input ="/Users/ytachi/swprojects/PEACH-anonymiser/AnonymiserAPI/src/main/resources/cwt.csv";
		String output = "/Users/ytachi/swprojects/PEACH-anonymiser/AnonymiserAPI/src/main/resources/output.csv";
		
		CWTAnonymiser cwt = new CWTAnonymiser(input, output);
		cwt.anonymise();
	}
}
