package model;

public class Movie {
	private String title;
	private String description;
	private String rating;
	
	public Movie() {
		super();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Movie-"
				+ "title: " + title
				+ "description: " + description
				+ "rating: " + rating;
	}
}
