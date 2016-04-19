package edu.milton.justin.enums;

public enum ErrorType {
	
	ELSECHECK("Something that wasn't "
			+ "suppose to happen, "
			+ "just happened."),
	
	GENERAL("General Error"),
	
	//Remove this before deployment
	WTF("...wtf?")
	;
	
	private String error;
	
	private ErrorType(String msg){
		
		error = msg;
		
	}
	
	public String getMessage(){
		
		return error;
	}
	
	
}
