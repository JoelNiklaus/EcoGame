package ch.joelniklaus.ecogame.model;


public class ChartInformation {
	
	private String title;
	
	private String json;
	
	public ChartInformation(String title, String json) {
		super();
		this.title = title;
		this.json = json;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}
