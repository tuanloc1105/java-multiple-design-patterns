package MyProject;

public class Invoker {
	
	private LogInOut io;
	
	public boolean active(LogInOut io_) {
		this.io = io_;
		return io.thuchien();
	}
}
