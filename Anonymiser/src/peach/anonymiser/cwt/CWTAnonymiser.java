package peach.anonymiser.cwt;

import java.io.IOException;

import org.deidentifier.arx.AttributeType;

import peach.anonymiser.Anonymiser;
import peach.anonymiser.BaseAnonymiser;

public class CWTAnonymiser extends BaseAnonymiser implements Anonymiser {

	public CWTAnonymiser(String filepath) throws IOException {
		super(filepath);
		setAttributes();
	}
	
	@Override
	public void setAttributes() {
		//identifying attributes
		getData().getDefinition().setAttributeType("NHS Number"
				, AttributeType.IDENTIFYING_ATTRIBUTE);
		getData().getDefinition().setAttributeType("Patient Pathway Identifier"
				, AttributeType.IDENTIFYING_ATTRIBUTE);
		getData().getDefinition().setAttributeType("Patient Pathway Identifer Issuer"
				, AttributeType.IDENTIFYING_ATTRIBUTE);
		
		//quasi identifying attributes
		getData().getDefinition().setAttributeType("Provider Consultant Upgrade"
				, AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
		getData().getDefinition().setAttributeType("Provider First See"
				, AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
		getData().getDefinition().setAttributeType("Provider Decision to treat cancer"
				, AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
		getData().getDefinition().setAttributeType("Provider treatment start date"
				, AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
	}

}
