package peach.anonymiser.cosd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.bouncycastle.util.encoders.Hex;

import peach.anonymiser.Anonymiser;
import peach.anonymiser.BaseAnonymiser;

public class COSDAnonymiser extends BaseAnonymiser implements Anonymiser {

	public COSDAnonymiser(String inFilepath, String outFilepath) {
		super(inFilepath, outFilepath);
	}

	/**
	 * This method will hash sensitive fields, which is a pseudo identifier 
	 * to the original identifier. Same inputs will have the same hash, 
	 * therefore, analysis will be allowed. Insensitive data has not been modified.
	 * This method will first read the CSV data and parse it. A mapping will be created
	 * between the CSV headers and their indices to aid the process of anonymisation.
	 * Any data in the sensitive attributes are hashed, replaced and outputted in a CSV file.
	 */
	@Override
	public void anonymise() {
		
		initParser();
		try {

			HashMap<String, Integer> headerMap = getHeaderMap();
			CSVRecord headerList = getHeaderList();
			List<CSVRecord> records = getAllRecords();
			
			initCSVPrinter();
			CSVPrinter csvFilePrinter = getCSVPrinter();
			csvFilePrinter.printRecord(headerList); //output the header
			
			initHash();
			for (int row = 0; row < records.size(); row++) {
				CSVRecord currentRecord = records.get(row);
				ArrayList<String> newRecord = new ArrayList<String>();
				for (int column = 0; column < currentRecord.size(); column++) {
					
					String currentElement = currentRecord.get(column);
					if (column == headerMap.get("NHS Number") //sensitive attributes
							|| column == headerMap.get("Local Patient Identifier")
							|| column == headerMap.get("Person Family Name")
							|| column == headerMap.get("Person Given Name")
							|| column == headerMap.get("Person Family Name (At Birth)")
							|| column == headerMap.get("Patient Usual Address (At Diagnosis)")
							|| column == headerMap.get("Organisation Code (Code of Provider)")) {
						
						//hash sensitive attributes
						byte [] digest = getHash(currentElement.getBytes());
						newRecord.add(Hex.toHexString(digest));
						
					}  else if (column == headerMap.get("Patient Birth Date")) {
						//remove the date of birth and just keep the year.
						int endIndex = currentElement.indexOf("/");
						String year = currentElement.substring(0, endIndex);
						newRecord.add(year);
					} else if (column == headerMap.get("Postcode of Usual Address (At Diagnosis)")) {
						//only keep first half of the postcode
						currentElement = currentElement.replaceAll(" ", "");
						String firstHalf = currentElement.substring(0, 2); //add first two chars
						
						int remainingLength = currentElement.length() - firstHalf.length();

						if (remainingLength == 4) {
							//add one character.
							firstHalf = firstHalf + currentElement.substring(2, 3);
						} else if (remainingLength > 4){
							//add the next two characters
							firstHalf = firstHalf + currentElement.substring(2, 4);
						} else {
							//Keep the first half is it is, if anything else.
							firstHalf = firstHalf + "";
						}
						newRecord.add(firstHalf); //add first half of postcode to list.
						
					}
					
					else {
						//insensitive item, so just add it.
						newRecord.add(currentElement);
					}
				}
				csvFilePrinter.printRecord(newRecord); //output single record
			}
			csvFilePrinter.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
