package peach.anonymiser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import peach.anonymiser.bean.COSDIdentifier;

/**
 * This class is a superclass of all the CWTAnonymiser, COSDAnonymiser and SACTAnonymiser.
 * This class contains the methods that is common to all these Anonymiser's; namely 
 * accepting a CSV file, outputting a CSV file and hashing inputs.
 * 
 * @author Krinal
 *
 */
public class BaseAnonymiser {
	/**
	 * The filepath of the input CSV file.
	 */
	private String inFilepath;
	
	/**
	 * The filepath of the output CSV file.
	 */
	private String outFilepath;
	
	/**
	 * The object that will parse the CSV file.
	 */
	private CSVParser parser;
	
	/**
	 * The object that will store the headers of the input CSV file.
	 */
	private CSVRecord headerList;
	
	/**
	 * The object that will output the newly constructed CSV file.
	 */
	private CSVPrinter csvFilePrinter;
	
	/**
	 * A map of the header to its column index
	 */
	private HashMap<String, Integer> headerMap;
	
	/**
	 * The object that will hash the input using SHA3.
	 */
	private SHA3.DigestSHA3 digestSHA3;
	
	/**
	 * The constructor will set the inFilePath field with the input file path, and also
	 * the outFilepath field with the output filepath.
	 * @param inFilepath the filepath of the input CSV file.
	 * @param outFilepath the filepath of the output CSV file.
	 */
	public BaseAnonymiser(String inFilepath, String outFilepath) {
		this.inFilepath = inFilepath;
		this.outFilepath = outFilepath;
	}
	
	/**
	 * Initialises the parser object and initiates objects to aid parsing. This includes
	 * creating a list of headers and a mapping of headers to column indices.
	 */
	protected void initParser() throws IllegalStateException{
		try {
			File csvData = new File(inFilepath);
			parser = CSVParser.parse(csvData,  Charset.defaultCharset(), CSVFormat.DEFAULT);
			createHeaderList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	/**
	 * Creates a headerList by calling the iterator once to get the first row.
	 * This is the header of a CSV file. All iterations after this will start from
	 * the second row (first record).
	 */
	private void createHeaderList() {
		headerList = parser.iterator().next(); //get first row, which is the header
		int index = 0;
		for (String header: headerList) {
			if(COSDIdentifier.hasToBeProcessed(header)) {
				COSDIdentifier.updateIndex(header, index);
			}
			index++;
		}

	}
	
	/**
	 * Returns all the headers in the input CSV file in a list.
	 * @return the all the headers in the input CSV file in a list.
	 */
	public CSVRecord getHeaderList() {
		return headerList;
	}
	
	/**
	 * Returns a map of the headers and column indices.
	 * @return a map of the headers and column indices.
	 */
	public HashMap<String, Integer> getHeaderMap() {
		return headerMap;
	}
	
	/**
	 * Returns all the records in the input CSV file, except the first row 
	 * (which is the header).
	 * @return all the records in the input CSV file, except the first row.
	 * @throws IOException
	 */
	public List<CSVRecord> getAllRecords() throws IOException {
		return parser.getRecords();
	}
	
	/**
	 * Initialises the object that will perform the hashing on input.
	 */
	public void initHash() {
		digestSHA3 = new SHA3.DigestSHA3(256);
	}
	
	/**
	 * Returns the bytes of the input after hashing.
	 * @param input the input in bytes that is to be hashed.
	 * @return
	 */
	public byte [] getHash(byte [] input) {
		return digestSHA3.digest(input);
	}

	/**
	 * Initialises the CSVPrinter object that will be used to output the newly
	 * constructed file. The destination will of the newly constructed CSV file
	 * will be outFilepath.
	 */
	public void initCSVPrinter() throws IllegalStateException{
		try {
			FileWriter fileWriter = new FileWriter(outFilepath);
			csvFilePrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		} 
		
	}
	
	/**
	 * Returns the CSVPrinter object used for outputting a new CSV file.
	 * @return the CSVPrinter object used for outputting a new CSV file.
	 */
	public CSVPrinter getCSVPrinter() {
		return csvFilePrinter;
	}
	
	/**
	 * Sets the filepath for the input CSV file with another CSV file
	 * @param inFilepath a replacement of the input CSV file.
	 */
	public void setInFilepath(String inFilepath) {
		this.inFilepath = inFilepath;
	}
	
	/**
	 * Returns the String of the input CSV filepath.
	 * @return the Strign of the input CSV filepath.
	 */
	public String getInFilepath() {
		return this.inFilepath;
	}
	
	/**
	 * Sets the output filepath with the new output filepath.
	 * @param outFilepath the new output filepath 
	 */
	public void setOutFilepath(String outFilepath) {
		this.outFilepath = outFilepath;
	}
	
	/**
	 * Retruns the filepath of the output CSV file.
	 * @return the filepath of the output CSV file.
	 */
	public String getOutFilepath() {
		return this.outFilepath;
	}
	
}
