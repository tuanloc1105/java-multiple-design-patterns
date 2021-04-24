package MyProject;

public class doValidate {
	private Validation val;
	
	public doValidate(Validation val_) {
		this.val = val_;
	}
	
	public boolean execute(String data) {
		return val.validate(data);
	}
}
