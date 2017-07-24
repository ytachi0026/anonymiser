package peach.anonymiser.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import peach.anonymiser.bean.CWTIdentifier;
import peach.anonymiser.service.Anonymiser;
import peach.anonymiser.service.BaseAnonymiser;

/**
 * This class creates an anonymiser for CWT data. It will accept a CSV file
 * and returns an anonymised version of that CSV file. 
 * @author Krinal
 *
 */
public class CWTAnonymiser extends BaseAnonymiser implements Anonymiser {
	
	/**
	 * Initialises the source CSV filepath and output filepath
	 * @param inFilepath the source CSV filepath
	 * @param outFilepath the output filepath of the anonymised CSV file
	 */
	public CWTAnonymiser(String inFilepath, String outFilepath) {
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
			CSVRecord headerList = getHeaderList();
			List<CSVRecord> records = getAllRecords();
			
			initCSVPrinter();
			CSVPrinter csvFilePrinter = getCSVPrinter();
			csvFilePrinter.printRecord(headerList); //output the header
			
			int index = 0;
			for (CWTIdentifier identifier: CWTIdentifier.values()) {
				for (String header: headerList) {
					if (identifier.getDescription().equals(header)) {
						identifier.setIndex(index);
						break;
					}
				}
				index += 1;
			}
			
			initHash();
			for (int row = 0; row < records.size(); row++) {
				CSVRecord currentRecord = records.get(row);
				ArrayList<String> newRecord = new ArrayList<String>();
				for (int column = 0; column < currentRecord.size(); column++) {
					String currentElement = currentRecord.get(column);
					if(CWTIdentifier.isSensitive(column)) {
						byte [] digest = getHash(currentElement.getBytes());
						newRecord.add(Hex.toHexString(digest));
						
					} else {
						newRecord.add(currentElement); //add element to current record
					}
				}
				csvFilePrinter.printRecord(newRecord); //output single record
			}
			csvFilePrinter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
