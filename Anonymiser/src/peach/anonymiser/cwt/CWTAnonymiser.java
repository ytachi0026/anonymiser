package peach.anonymiser.cwt;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import org.bouncycastle.util.encoders.Hex;

import peach.anonymiser.Anonymiser;
import peach.anonymiser.BaseAnonymiser;

/**
 * This class creates an anonymiser for CWT data. It will accept a CSV file
 * and returns an anonymised version of that CSV file. 
 * @author Krinal
 *
 */
public class CWTAnonymiser extends BaseAnonymiser implements Anonymiser {
	/**
	 * The source CSV filepath.
	 */
	private String inFilepath;
	/**
	 * The output filepath of the anonymised CSV file.
	 */
	private String outFilepath;
	
	/**
	 * Initialises the source CSV filepath and output filepath
	 * @param inFilepath the source CSV filepath
	 * @param outFilepath the output filepath of the anonymised CSV file
	 */
	public CWTAnonymiser(String inFilepath, String outFilepath) {
		//setInFilepath(inFilepath);
		//setOutFilepath(outFilepath);
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
							|| column == headerMap.get("Patient Pathway Identifier (PPI)")
							|| column == headerMap.get("Organisation Code (PPI Identifier)")
							|| column == headerMap.get("Site Code (of Provider Consultant upgrade)")
							|| column == headerMap.get("Site Code (of Provider First Seen)")
							|| column == headerMap.get("Site Code (of Provider Decision To Treat Cancer)")
							|| column == headerMap.get("Site Code (of Treatment Start Date Cancer)")) {
						
						
						
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

	/**
	 * Returns the input filepath
	 * @return input filepath
	 */
	public String getInFilepath() {
		return inFilepath;
	}

	/**
	 * Sets the input filepath
	 * @param inFilepath the input filepath
	 */
	public void setInFilepath(String inFilepath) {
		this.inFilepath = inFilepath;
	}

	/**
	 * Returns the output filepath
	 * @return the output filepath
	 */
	public String getOutFilepath() {
		return outFilepath;
	}

	/**
	 * Sets the output filepath
	 * @param outFilepath the output filepath.
	 */
	public void setOutFilepath(String outFilepath) {
		this.outFilepath = outFilepath;
	}
	


}
