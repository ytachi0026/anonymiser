package peach.anonymiser.cwt;

import java.io.IOException;

import org.deidentifier.arx.DataHandle;

public class Driver {

	public static void main(String[] args) throws IOException {
		CWTAnonymiser cwt = new CWTAnonymiser("C:/Users/Krinal/Desktop/cwt.csv");
		try {
			
			DataHandle anonymise = cwt.anonymiseData(2);
			cwt.outputCSV("C:/Users/Krinal/Desktop/output.csv", anonymise);
		} catch (Exception e) {
e.printStackTrace();
		}

	}

}
