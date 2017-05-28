package application;

/**
 * Photo class
 * constructor for the Photo class
 * @author amandahinchman
 *
 */
public class Photo {
	String albumId;
	String id;
	String title;
	String url;
	String thumbNailUrl;

	/*
	 * retrieves albumId from object
	 * @return String
	 */
	public String getAlbumId() {
		return albumId;
	}
	
	/*
	 * retrieves id from object
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/*
	 * retrieves title from object
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * retrieves url from object
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/*
	 * retrieves thumbNailUrl from object
	 * @return String
	 */
	public String getThumbNailUrl() {
		return thumbNailUrl;
	}
	
	/*
	 * sets albumId for object
	 */
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	
	/*
	 * sets id for object
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/*
	 * sets title for object
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * sets url for object
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/*
	 * sets thumbNailUrl for object
	 */
	public void setThumbNailUrl(String TNurl) {
		this.thumbNailUrl = TNurl;
	}

}
