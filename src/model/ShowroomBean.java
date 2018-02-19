package model;

public class ShowroomBean {
	int capacity;
	TheatreBean theatre; //TODO: Is this the correct class it should be referencing?
	
	public ShowroomBean() {	
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public TheatreBean getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreBean theatre) {
		this.theatre = theatre;
	}

}
