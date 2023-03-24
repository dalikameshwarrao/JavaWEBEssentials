import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
public class PasswordValidator {
	
	    public static void main(String[] args) {
	        testIsValidPassword();
	    }

	    public static boolean isValidPassword(String password) {
	        // Password must be at least 8 characters long, contain at least one uppercase letter,
	        // at least 2 letters, at least 1 number, no spaces, at least one special character !@#$%^&,
	        // and no invalid special characters
	        String regex = "^(?!.*[\\s])(?=.*[A-Z])(?=(.*[a-zA-Z]){2,})(?=.*[0-9])(?=.*[!@#$%^&])[A-Za-z0-9!@#$%^&]{8,}$";
	        Pattern pattern = Pattern.compile(regex);
	        // Password must not contain any character that repeats 3 or more times consecutively
	        String repeatRegex = ".*(.)\\1\\1.*";
	        Pattern repeatPattern = Pattern.compile(repeatRegex);

	        return pattern.matcher(password).matches() && !repeatPattern.matcher(password).matches();
	    }
	    
	    

		
		  public static void testIsValidPassword() { 
			  
		String[] passwords = {
				"passw0r","password123","p@ssword123","Password","Pass word@123","Password123","Passw0rd?","Paaaassw0rd@"		
		};
		System.out.println(isValidPassword2("Password@123"));
		System.out.println(isValidPassword2("Passw0r"));  //digit
		System.out.println(isValidPassword2("Password123")); // one special char
		System.out.println(isValidPassword2("p@ssword123")); //one upper case letter
		System.out.println(isValidPassword2("Password"));// at least one digit
		System.out.println(isValidPassword2("Pass word@123")); // no space
		System.out.println(isValidPassword2("Passw0rd?")); // invalid special character
		System.out.println(isValidPassword2("Paasssw0rd@")); // consecutive more than 2
		System.out.println(isValidPassword2("1P@23459988")); // two letters
		
		System.out.println(isValidPassword("Password@123"));
		System.out.println(isValidPassword("Passw0r"));  //digit
		System.out.println(isValidPassword("Password13"));  //one special char
		System.out.println(isValidPassword("p@ssword13")); //one upper case letter
		System.out.println(isValidPassword("Password")); //at least one digit
		System.out.println(isValidPassword("Pass word@13"));  //no space
		System.out.println(isValidPassword("Passw0rd?"));  //invalid special character
		System.out.println(isValidPassword("Paasssw0rd@"));  //consecutive more than 
		System.out.println(isValidPassword("1P@3459988"));  //two letters
		  
		  
		  }
		  
		 	    
	    
	    
	    private static String isValidPassword2(String password) {
		    String regex = "^(?=.*[A-Z])(?=.*[a-z].*[a-z])(?=.*\\d)(?=.*[!@#$%^&])(?!.*(.)\\1\\1)[A-Za-z\\d!@#$%^&]{8,}$";
		    Map<Pattern, String> errorMessages = new LinkedHashMap<>();
		    errorMessages.put(Pattern.compile("^.{0,7}$"), "Password must be at least 8 characters long.");
		    errorMessages.put(Pattern.compile("^[^A-Z]*$"), "Password must contain at least one uppercase letter.");
		    errorMessages.put(Pattern.compile(".*\\s.*"), "Password must not contain spaces.");
		    errorMessages.put(Pattern.compile("^.*[^A-Za-z\\d!@#$%^&].*$"), "Password contains invalid special characters.");
		    errorMessages.put(Pattern.compile("^[^\\d]*$"), "Password must contain at least one digit.");
		    errorMessages.put(Pattern.compile("^[^!@#$%^&]*$"), "Password must contain at least one special character !@#$%^&.");
		    errorMessages.put(Pattern.compile(".*(.)\\1\\1.*"), "Password must not contain any character that repeats 3 or more times consecutively.");
		    errorMessages.put(Pattern.compile("^[^a-zA-Z]*([a-zA-Z])[^a-zA-Z]*$"), "Password must contain at least two letters.");

		    for (Map.Entry<Pattern, String> entry : errorMessages.entrySet()) {
		        if (entry.getKey().matcher(password).matches()) {
		        	//System.out.println(entry.getValue());
		            return entry.getValue();
		        }
		    }
		    return "valid";
		}

	}

	


