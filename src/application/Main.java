package application;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    String[] imageSource;
    String[] title;
    private Pagination pagination;
    final int numOfPage = 5000;

    public VBox createPage(int pageIndex) {
        VBox pageBox = new VBox();
        Label pageLabel = new Label("Page: " + (pageIndex+1));
        Label pageLabel2 = new Label(title[pageIndex]);
        
        Image image = new Image(imageSource[pageIndex]);
		
		ImageView imgView = new ImageView(image);
		
		imgView.setX(100);
		imgView.setY(50);
		
		pageBox.getChildren().add(imgView);
        pageBox.getChildren().add(pageLabel);
        pageBox.getChildren().add(pageLabel2);
        
        return pageBox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
		String json = loadData();
		
		System.out.println("read data");
		
		Gson gson = new Gson();
		Photo[] photo = gson.fromJson(json, Photo[].class);
		
		String gsonJson = gson.toJson(photo);
		System.out.println(gsonJson);
	
		// load urls
		imageSource = new String[photo.length];
		for (int i = 0; i < photo.length; i++) {
			imageSource[i] = photo[i].getUrl();
		}
		
		// load titles
        title = new String[photo.length];
        for(int i = 0; i < photo.length; i++) {
        	title[i] = photo[i].getTitle();
        }
		
		System.out.println(imageSource[1]);
		System.out.println(imageSource.length);
		
        pagination = new Pagination(numOfPage);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });

        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().add(pagination);

        Scene scene = new Scene(anchor, 320, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
	/**
	 * loadData() reads the json file through Java API
	 * @return String
	 * @throws Exception
	 */
	public String loadData() throws Exception {
		String jsonUrl = readUrl("https://jsonplaceholder.typicode.com/photos");
		
		return jsonUrl;
	}
	
	/**
	 * readUrl() takes the string input from loadData() and translates 
	 * the string into readable String output
	 * @param urlString
	 * @return String
	 * @throws Exception
	 */
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

    public static void main(String[] args) {
        launch(args);
    }
}