package MyProject;

public class modifyHistory {
	public History getType(String type) {
		if(type == null){
	         return null;
	    }
		if (type.equalsIgnoreCase("them lich su")) {
			return new addHistory(); 
		}
		else if (type.equalsIgnoreCase("xoa lich su")) {
			return new removeHistory(); 
		}
		else if (type.equalsIgnoreCase("xem lich su")) {
			return new showHistory();
		}
		return null;
	}
}
