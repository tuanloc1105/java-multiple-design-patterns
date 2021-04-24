package MyProject;

public class UsernameValidation implements Validation{
	
	@Override
	public boolean validate(String data) {
		/*
		This regular expression refers to a pattern which accepts 5 to 15
		characters with any lower case character, digit or special symbol “_-” only.
		*/
		String regex = "^[a-z0-9_-]{5,15}$";
	    return data.matches(regex);
	}
	
}
