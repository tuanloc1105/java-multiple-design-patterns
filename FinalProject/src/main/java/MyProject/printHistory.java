package MyProject;

public class printHistory {
	String his;
	public printHistory() {
		
	}
	public void setHis(String his) {
		this.his += his + "\n";
	}
	
	public String getHis() {
		return this.his;
	}
}
