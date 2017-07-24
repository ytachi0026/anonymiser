package peach.anonymiser.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.bouncycastle.util.encoders.Hex;

import peach.anonymiser.bean.CWTIdentifier;
import peach.anonymiser.bean.SACTIdentifier;
import peach.anonymiser.service.Anonymiser;
import peach.anonymiser.service.BaseAnonymiser;

public class SACTAnonymiser extends BaseAnonymiser implements Anonymiser {

	/**
	 * Initialises the source CSV filepath and output filepath
	 * 
	 * @param inFilepath
	 *            the source CSV filepath
	 * @param outFilepath
	 *            the output filepath of the anonymised CSV file
	 */
	public SACTAnonymiser(String inFilepath, String outFilepath) {
		super(inFilepath, outFilepath);
	}

	@Override
	public void anonymise() {
		initParser();
		try {
			CSVRecord headerList = getHeaderList();
			List<CSVRecord> records = getAllRecords();

			initCSVPrinter();
			CSVPrinter csvFilePrinter = getCSVPrinter();
			csvFilePrinter.printRecord(headerList); // output the header

			for (SACTIdentifier identifier : SACTIdentifier.values()) {
				int index = 0;
				for (String header : headerList) {
					if (identifier.getDescription().equals(header)) {
						identifier.setIndex(index);

						break;
					}
					index += 1;
				}

			}

			initHash();
			for (int row = 0; row < records.size(); row++) {
				CSVRecord currentRecord = records.get(row);
				ArrayList<String> newRecord = new ArrayList<String>();
				for (int column = 0; column < currentRecord.size(); column++) {
					String currentElement = currentRecord.get(column);
					if (SACTIdentifier.isSensitive(column)) {
						byte[] digest = getHash(currentElement.getBytes());
						newRecord.add(Hex.toHexString(digest));

					} else if (SACTIdentifier.isIdentifierByID(SACTIdentifier.PATIENT_POSTCODE, column)) {
						// currentElement = currentElement.replaceAll(" ", "");
						int endIndex = currentElement.indexOf(' ');
						String firstHalf = currentElement.substring(0, endIndex);
						newRecord.add(firstHalf);

					} else if (SACTIdentifier.isIdentifierByID(SACTIdentifier.DATE_OF_BIRTH, column)) {
						String dateComponents[] = currentElement.split("/");
						String year = "";
						for (String component : dateComponents) {
							if (component.length() == 4) {
								year = component;
								break;
							}
						}
						newRecord.add(year);

					}

					else {
						newRecord.add(currentElement); // add element to current
														// record
					}
				}
				csvFilePrinter.printRecord(newRecord); // output single record
			}
			csvFilePrinter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
