package enums;

public enum ErrorMessageType {

	ELSECHECK("Something that wasn't " + "suppose to happen, " + "just happened."),

	GENERAL("General Error"),

	// Remove this before deployment
	WTH("...uhhhhh");

	private String error;

	private ErrorMessageType(String msg) {

		error = msg;

	}

	public String getMessage() {

		return error;
	}

}
