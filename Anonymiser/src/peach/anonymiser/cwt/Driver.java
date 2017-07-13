package peach.anonymiser.cwt;

import java.io.IOException;



public class Driver {

	public static void main(String[] args) throws IOException {
		String currentDir = System.getProperty("user.dir");
		currentDir = currentDir.replace('\\', '/');
		CWTAnonymiser cwt = new CWTAnonymiser(currentDir + "/cwt.csv",
				currentDir + "/output.csv");
		cwt.anonymise();

	}

}
