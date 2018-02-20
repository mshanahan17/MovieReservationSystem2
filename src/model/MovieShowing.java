package model;

public class MovieShowing {
	Movie movie;
	Showroom showRoom;
	int numOfPurchasedSeats;
	double cost;
	
	public MovieShowing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Showroom getShowRoom() {
		return showRoom;
	}

	public void setShowRoom(Showroom showRoom) {
		this.showRoom = showRoom;
	}

	public int getNumOfPurchasedSeats() {
		return numOfPurchasedSeats;
	}

	public void setNumOfPurchasedSeats(int numOfPurchasedSeats) {
		this.numOfPurchasedSeats = numOfPurchasedSeats;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
