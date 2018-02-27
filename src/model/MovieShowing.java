package model;

public class MovieShowing {
	Movie movie;
	Showroom showroom;
	int numOfPurchasedSeats;
	double cost;
	String startTime;
	String endTime;
	
	public MovieShowing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void updatePurchasedSeatCount(int seatValue) {
		numOfPurchasedSeats += seatValue;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Showroom getShowroom() {
		return showroom;
	}

	public void setShowroom(Showroom showRoom) {
		this.showroom = showRoom;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "MovieShowing-"
		   + "\nnumOfPurchasedSeats: " + numOfPurchasedSeats
		   + "\ncost: " + cost
		   + "\nstartTime: " + startTime
		   + "\nendTime: " + endTime
		   + "\nmovie: " + movie
		   + "\nshowroom: " + showroom;
	}

}
