package peach.anonymiser.cwt;

import java.io.IOException;

import peach.anonymiser.service.impl.COSDAnonymiser;



public class Driver {

	public static void main(String[] args) throws IOException {
		String currentDir = System.getProperty("user.dir");
		currentDir = currentDir.replace('\\', '/');
		String input = currentDir + "/cwt.csv";
		String output = currentDir + "/output.csv";
		
/*		CWTAnonymiser cwt = new CWTAnonymiser(currentDir + "/cwt.csv",
				currentDir + "/output.csv");
		cwt.anonymise(); */
		input = currentDir + "/cosd.csv";
		COSDAnonymiser cosd = new COSDAnonymiser(input, output);
		cosd.anonymise();

	}

}
