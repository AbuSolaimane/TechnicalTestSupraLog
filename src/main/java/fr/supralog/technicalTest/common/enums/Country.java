package fr.supralog.technicalTest.common.enums;

public enum Country {
    
	MOROCCO("Morocco"),
	FRANCE("France"),
	USA("United States of America"),
    CANADA("Canada"),
    GERMANY("Germany");

    private final String displayName;

    private Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
