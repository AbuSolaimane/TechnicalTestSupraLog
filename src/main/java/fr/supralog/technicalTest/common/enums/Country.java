package fr.supralog.technicalTest.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT) 
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
