package tableManipulation;

import java.io.Serializable;
import java.util.List;


public class Record implements Serializable {
	 String column;
	 String value;
	
	public Record(String c, String v) {
		column = c;
		value = v;
	}
	
	public String toString() {
		return "Column name "+column+" and its Value is "+ value;
	}
	public void printList(List x) {
		for(int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i));
		}
	}

}
