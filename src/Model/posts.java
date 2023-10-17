package Model;

import java.time.LocalDateTime;

public class posts { //Post class to create post objects
	// Attributes of each post
	private int postID;
	private int authorID;
	private String author;
	private String content;
	private int likes;
	private int shares;
	private LocalDateTime dateTime;

	// Constructor for the Post class ; Whenever a post object is created, the
	// attributes should be given in its parameters.
	public posts( String author , String content, int likes, int shares, LocalDateTime dateTime) {
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.dateTime = dateTime;

	}
	
	 public posts(int postID, int authorID, String content, String author, int likes, int shares, LocalDateTime dateTime) {
	        this.postID = postID;
	        this.authorID = authorID;
	        this.content = content;
	        this.author = author;
	        this.likes = likes;
	        this.shares = shares;
	        this.dateTime = dateTime;
	    }

	// Methods to retrieve information about post(getter methods)

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public int getLikes() {
		return likes;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public int getShares() {
		return shares;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	

}

	