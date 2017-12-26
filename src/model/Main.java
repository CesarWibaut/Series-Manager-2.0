package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;


public class Main extends Application{


	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model m = new Model();
		new View(m);
	}	
}