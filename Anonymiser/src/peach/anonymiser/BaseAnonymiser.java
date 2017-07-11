package peach.anonymiser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.deidentifier.arx.ARXAnonymizer;
import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.criteria.KAnonymity;
import org.deidentifier.arx.io.CSVSyntax;

/**
 * This class is a superclass of all the CWTAnonymiser, COSDAnonymiser and SACTAnonymiser.
 * This class contains the methods that is common to all these Anonymiser's; namely 
 * accepting a CSV file and outputting a CSV file.
 * 
 * @author Krinal
 *
 */
public class BaseAnonymiser {
	/**
	 * The filepath of where to read the CSV file from
	 */
	private String filepath;
	/**
	 * Data object to hold the CSV file contents as well as any additional information
	 * about the data
	 */
	private Data data;
	/**
	 * The object that anonymises data
	 */
	private ARXAnonymizer anonymiser;
	/**
	 * The constructor sets the filepath field
	 * @param filepath - the filepath of where the CSV file is stored
	 * @throws IOException 
	 */
	public BaseAnonymiser(String filepath) throws IOException {
		setFilepath(filepath);
		createData(filepath);
		anonymiser = new ARXAnonymizer();
	}

	/**
	 * This method will create the data object from the csv file
	 * @param filepath
	 * @throws IOException 
	 */
	private void createData(String filepath) throws IOException {
		this.setData(Data.create(filepath, Charset.defaultCharset(), ','));

	}
	
	/**
	 * This method will output the anonymised CSV data at filepath
	 * @param filepath the filepath at where the anonymised data is to be outputted
	 * @param anonymise the anonymised object
	 * @throws IOException if file path does not exist
	 */
	public void outputCSV(String filepath, DataHandle anonymise) throws IOException {
		File outputFile = new File(filepath);
		CSVSyntax csvSyntax = new CSVSyntax();
		csvSyntax.setDelimiter(',');
		csvSyntax.setLinebreak(System.getProperty("line.separator"));
		  
		anonymise.save(outputFile, csvSyntax);
		
	}
	
	/**
	 * This method will anonymise the data using k-anonymity. The value of k
	 * is provided by the user.
	 * @param k used for k-anonymity
	 * @return the DataHandle after anonymising.
	 * @throws IOException
	 */
	public DataHandle anonymiseData(int k) throws IOException {
		ARXConfiguration config = ARXConfiguration.create();
		config.addPrivacyModel(new KAnonymity(k));
		ARXResult result = anonymiser.anonymize(getData(), config);
		return result.getOutput();
		
	}
	/**
	 * Returns the filepath of the input csv file
	 * @return the filepath of the input csv file
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * Sets the filepath of the input csv file
	 * @param filepath the input csv file
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}
