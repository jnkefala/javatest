package app.util;

import org.apache.commons.validator.routines.EmailValidator;

public class AppUtil {
	
	public static boolean isPassValid(String pass){
		if(pass == null)
			return false;
		if(pass.length() < 8 || pass.length() > 16)
			return false;
		if(!pass.matches(".*[a-zA-Z].*"))
			return false;
		if(!pass.matches(".*[0-9].*"))
			return false;
		if(!pass.matches(".*[!@#$%^&*(),.?:{}|<>_].*"))
			return false;
		return true;
	}
	
	public static boolean isEmailValid(String email){
		if(email == null)
			return false;
		if(!EmailValidator.getInstance().isValid(email))
			return false;
		
		return true;	
	}
	
	public static boolean isPhoneValid(String val){
		if(val == null || val.length() != 10)
			return false;
		if(!val.matches("[0-9]+"))
			return false;
		
		return true;	
	}
	
	public static boolean isStringLengthValid(String val, int minLength, int maxLength){
		if(val == null)
			return false;
		if(val.length() < minLength || val.length() > maxLength)
			return false;
		return true;
	}
}
