package model;

public class Movie {
	private String title;
	private String description;
	private String rating;
	private String thumbnail;
	
	public static void main(String[] args) {		
		
	}
	
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
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Movie-"
				+ "\ntitle: " + title
				+ "\ndescription: " + description
				+ "\nrating: " + rating;
	}
}
