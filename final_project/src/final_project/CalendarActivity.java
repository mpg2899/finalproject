package final_project;

public class CalendarActivity {

	public String name, starttime, stoptime, summary;
	public int userid, dateid;
	
	public void setName(String myname) {
		this.name = myname;
	}
	public void setTime(String mystarttime, String mystoptime) {
		this.starttime = mystarttime;
		this.stoptime = mystoptime;
	}
	public void setSummary(String mysummary) {
		this.summary = mysummary;
	}
	public void setDateID(int mydateid) {
		this.dateid = mydateid;
	}
}
