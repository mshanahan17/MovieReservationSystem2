package model;

public class MovieShowingBean {
	MovieBean movie;
	ShowroomBean showRoom;
	int numOfPurchasedSeats;
	double cost;
	
	public MovieShowingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieBean getMovie() {
		return movie;
	}

	public void setMovie(MovieBean movie) {
		this.movie = movie;
	}

	public ShowroomBean getShowRoom() {
		return showRoom;
	}

	public void setShowRoom(ShowroomBean showRoom) {
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
