package MyProject;

public class LogIn implements LogInOut{
	
	private Account acc;
	private String username;
	private String password;
	
	public LogIn(Account acc_) {
		this.acc = acc_;
		this.username = acc_.getUsername();
		this.password = acc_.getPassword();
	}
	
	public boolean thuchien() {
		return acc.login(username, password);
	}

}
