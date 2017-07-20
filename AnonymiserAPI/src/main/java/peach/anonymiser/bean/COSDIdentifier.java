package peach.anonymiser.bean;

public enum COSDIdentifier {
	NHS_NUMBER(Constant.SENSITIVE, "NHS Number", 0), 
	LOCAL_PATIENT_IDENTIFIER(Constant.SENSITIVE,"Local Patient Identifier",0), 
	PERSON_FAMILY_NAME(Constant.SENSITIVE, "Person Family Name", 0), 
	PERSON_GIVEN_NAME(Constant.SENSITIVE,"Person Given Name", 0), 
	PERSON_FAMILY_NAME_AT_BIRTH(Constant.SENSITIVE,"Person Family Name (At Birth)", 0), 
	PATIENT_USUAL_ADDRESS_AT_DIAGNOSIS(Constant.SENSITIVE,"Patient Usual Address (At Diagnosis)", 0), 
	ORGANISATION_CODE_CODE_OF_PROVIDER(Constant.SENSITIVE, "Organisation Code (Code of Provider)", 0),

	PATIENT_BIRTH_DATE(Constant.QUASI_SENSITIVE, "Patient Birth Date", 0), 
	POSTCODE_OF_USUAL_ADDRESS_AT_DIAGNOSIS(Constant.QUASI_SENSITIVE, "Postcode of Usual Address (At Diagnosis)", 0);

	private final String type;
	private final String description;
	private Integer index;

	private COSDIdentifier(String type, String description, Integer index) {
		this.type = type;
		this.description = description;
		this.index = index;

	}

	public String getDescription() {
		return description;
	}

	public static boolean isSensitive(Integer column) {
		for (COSDIdentifier sensitive : COSDIdentifier.values()) {
			if (Constant.SENSITIVE.equals(sensitive.getType())) {
				if (sensitive.getIndex() == column) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isIdentifierByID(COSDIdentifier identifier, Integer column) {
		if (identifier.getIndex() == column) {
			return true;
		}
		return false;
	}

	public static boolean hasToBeProcessed(String target) {
		for (COSDIdentifier sensitive : COSDIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				return true;
			}
		}
		return false;
	}

	public static void updateIndex(String target, int index) {
		for (COSDIdentifier sensitive : COSDIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				sensitive.setIndex(index);
				break;
			}
		}
	}

	public static void printIt() {
		for (COSDIdentifier identifier : COSDIdentifier.values()) {
			System.out.println(identifier.getDescription() + " - " + identifier.getIndex());
		}
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

}