package peach.anonymiser.bean;

import org.springframework.expression.spel.ast.Identifier;

public enum SACTIdentifier {
	NHS_NUMBER(Constant.SENSITIVE, "NHS_number", 0),  
	ORGANISATION_CODE_OF_PROVIDER(Constant.SENSITIVE, "Organisation_code_of_provider", 0), 
	
	DATE_OF_BIRTH(Constant.QUASI_SENSITIVE, "Date_of_birth", 0), 
	PATIENT_POSTCODE(Constant.QUASI_SENSITIVE, "Patient_postcode", 0);

	private final String type;
	private final String description;
	private Integer index;

	private SACTIdentifier(String type, String description, Integer index) {
		this.type = type;
		this.description = description;
		this.index = index;

	}

	public String getDescription() {
		return description;
	}

	public static boolean isSensitive(Integer column) {
		for (SACTIdentifier sensitive : SACTIdentifier.values()) {
			if (Constant.SENSITIVE.equals(sensitive.getType())) {
				if (sensitive.getIndex() == column) {
					return true;
				}
			}
		}
		return false;
	}
		

	public static boolean isIdentifierByID(SACTIdentifier identifier, Integer column) {
		if (identifier.getIndex() == column) {
			return true;
		}
		return false;
	}

	public static boolean hasToBeProcessed(String target) {
		for (SACTIdentifier sensitive : SACTIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				return true;
			}
		}
		return false;
	}

	public static void updateIndex(String target, int index) {
		for (SACTIdentifier sensitive : SACTIdentifier.values()) {
			if (sensitive.getDescription().equals(target)) {
				sensitive.setIndex(index);
				break;
			}
		}
	}

	public static void printIt() {
		for (SACTIdentifier identifier : SACTIdentifier.values()) {
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