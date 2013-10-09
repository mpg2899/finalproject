package final_project;

public class DBTest {

	public static void main(String[] args) {
		
		CalendarActivity myActivity = DBConnect.readDB();
		System.out.println(myActivity.name);
	}
	
}
