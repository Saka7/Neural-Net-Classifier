package gui;

import beans.RealLayerFeatures;
import beans.TrainingLayerFeatures;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage = new Stage();
	private VBox rootPane;
	
	private ObservableList<TrainingLayerFeatures> trainingLayersFeatures = FXCollections.observableArrayList();
	private ObservableList<RealLayerFeatures> realLayersFeatures = FXCollections.observableArrayList();
	
	public Main() {
		for (int i = 0; i < 20; i++)
			trainingLayersFeatures.add(new TrainingLayerFeatures(i, i/10, i/5, i/20, i*15, 0));
	}
	
	public ObservableList<TrainingLayerFeatures> getLayerFeatures() {
		return trainingLayersFeatures;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("neural-nets-main.fxml"));
			rootPane = (VBox) loader.load();
			
			MainController controller = loader.getController();
			controller.setMain(this);
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootPane);
			scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Neural Nets Predicator");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}