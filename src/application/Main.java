package application;
	
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

class Album {
	String albumId;
	String id;
	String title;
	String url;
	String thumbNailUrl;
	List<Photo> photos;
}

public class Main extends Application {
	
	/*
	 * start() starts the javafx application, loads the data and displays
	 * the images
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Photo Album");
        Button btn = new Button();
        btn.setText("Say 'Display Images!'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Display Images!");
                try {
					String json = loadData();
				
					System.out.println("read data");
					
					Gson gson = new Gson();
					Photo[] photo = gson.fromJson(json, Photo[].class);
					
					String gsonJson = gson.toJson(photo);
					System.out.print(gsonJson);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //displayImage();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
	}
	
	/**
	 * loadData() reads the json file through Java API
	 * @return String
	 * @throws Exception
	 */
	public String loadData() throws Exception {
		String jsonUrl = readUrl("https://jsonplaceholder.typicode.com/photos");
		System.out.println(jsonUrl);
		
		
		
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
	

	/**
	 * main() launches application
	 */  
	public static void main(String[] args) {
		launch(args);
	}
}
