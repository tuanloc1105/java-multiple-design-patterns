package MyProject;

public class LogOut implements LogInOut{

	private Account acc;
	
	public LogOut(Account acc_) {
		this.acc = acc_;
	}
	
	@Override
	public boolean thuchien() {
		return acc.logout();
	}

}
