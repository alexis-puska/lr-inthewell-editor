package lr_in_the_well.alexis_puska.constant;

public enum LocaleEnum {

	FRENCH("fr"), 
	ENGLISH("en"), 
	SPANISH("es");

	private String code;

	private LocaleEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
