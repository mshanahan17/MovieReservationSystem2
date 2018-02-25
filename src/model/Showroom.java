package model;

public class Showroom {
	private int capacity;
	private Theater theater; //TODO: Is this the correct class it should be referencing?
	
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

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}
	
	@Override
	public String toString() {
		return "Showroom-"
		   + "\ncapacity: " + capacity
		   + "\ntheater: " + theater;
	}
	

}
