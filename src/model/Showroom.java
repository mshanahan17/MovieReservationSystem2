package model;

public class Showroom {
	int capacity;
	Theater theatre; //TODO: Is this the correct class it should be referencing?
	
	public Showroom() {	
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Theater getTheatre() {
		return theatre;
	}

	public void setTheatre(Theater theatre) {
		this.theatre = theatre;
	}

}
