package peach.anonymiser.bean;

public enum CWTIdentifier {
	NHS_NUMBER(Constant.SENSITIVE, "NHS Number", 0), 
	PATIENT_PATHWAY_IDENTIFIER_PPI (Constant.SENSITIVE, "Patient Pathway Identifier (PPI)", 0), 
	ORGANISATION_CODE_PPI_IDENTIFIER(Constant.SENSITIVE, "Organisation Code (PPI Identifier)", 0), 
	SITE_CODE_OF_PROVIDER_CONSULTANT_UPDGRADE(Constant.SENSITIVE, "Site Code (of Provider Consultant upgrade)", 0), 
	SITE_CODE_OF_PROVIDER_FIRST_SEEN(Constant.SENSITIVE, "Site Code (of Provider First Seen)", 0), 
	SITE_CODE_DECISION_TO_TREAT_CANCER(Constant.SENSITIVE, "Site Code (of Provider Decision To Treat Cancer)", 0), 
	SITE_CODE_OF_TREATMENT_SATART_DATE_CANCER(Constant.SENSITIVE, "Site Code (of Treatment Start Date Cancer)", 0); 

	private final String type;
	private final String description;
	private Integer index;

	private CWTIdentifier(String type, String description, Integer index) {
		this.type = type;
		this.description = description;
		this.index = index;

	}

	public String getDescription() {
		return description;
	}

	public static boolean isSensitive(Integer column) {
		for (CWTIdentifier sensitive : CWTIdentifier.values()) {
			if (Constant.SENSITIVE.equals(sensitive.getType())) {
				if (sensitive.getIndex() == column) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isIdentifierByID(CWTIdentifier identifier, Integer column) {
		if (identifier.getIndex() == column) {
			return true;
		}
		return false;
	}

	public static boolean hasToBeProcessed(String target) {
		for (CWTIdentifier sensitive : CWTIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				return true;
			}
		}
		return false;
	}

	public static void updateIndex(String target, int index) {
		for (CWTIdentifier sensitive : CWTIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				sensitive.setIndex(index);
				break;
			}
		}
	}

	public static void printIt() {
		for (CWTIdentifier identifier : CWTIdentifier.values()) {
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