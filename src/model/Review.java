package model;

public class Review {
	String content; //TODO: Validate content - make sure it doesn't exceed max review size
	String rating;
	User user;
	Movie movie;
	String date;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Review-"
				+ "\ncontent: " + content
				+ "\nrating: " + rating
				+ "\nuser:" + user
				+ "\ndate: " + date
				+ "\nmovie: " + movie;
	}

}
