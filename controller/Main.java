/**
 * The main class
 * 
 * Created by Christopher Birmingham
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.MusicChecker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane root;
	private MessengerClass messenger = new MessengerClass(this);

	
	/**
	 * Method to load all the views
	 */
	@Override
	public void start(Stage primaryStage) {
		boolean loadedCorrectly;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My Completely Amazing Music Player");
		this.primaryStage.getIcons().add(new Image("images/icon.jpg"));
		loadedCorrectly = this.loadRootPane();
		if (loadedCorrectly) {
			loadedCorrectly = this.loadMenuBar();
			if (loadedCorrectly) {
				loadedCorrectly = this.loadPlayMusicScreen();
				if (loadedCorrectly) {
					loadedCorrectly = this.loadMainScreen();
					if (!loadedCorrectly) {
						System.out.println("Error loading the main screen pane");
						this.closeProgram();
					}
				} else {
					System.out.println("Error loading the play music pane");
					this.closeProgram();
				}
			} else {
				System.out.println("Error loading one of the menu bar pane");
				this.closeProgram();
			}
		} else {
			System.out.println("Error loading one of the root pane");
			this.closeProgram();
		}
		this.saveOSDetails(System.getProperty("os.name"));
		this.primaryStage.setResizable(false);
	}
	
	private void saveOSDetails(String OS){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/.metadata/settingsOS.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print(OS);
		writer.close();
		
	}

	private boolean loadRootPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootPane.fxml"));
			this.root = (BorderPane) loader.load();
			this.root.getStylesheets().add("css/application.css");
			Scene scene = new Scene(this.root);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to load the menu bar section
	 * @return
	 */
	private boolean loadMenuBar() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/MenuPane.fxml"));
			HBox menuBar = (HBox) loader.load();
			menuBar.getStylesheets().add("css/menuBar.css");
			this.root.setTop(menuBar);
			MenuController controller = loader.getController();
			controller.setMessengerClass(this.messenger);
			controller.setMainApp(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to load the main part of the screen
	 * @return
	 */
	private boolean loadMainScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/MainScreen.fxml"));
			AnchorPane mainScreen = (AnchorPane) loader.load();
			mainScreen.getStylesheets().add("css/mainScreen.css");
			this.root.setCenter(mainScreen);
			MainScreenController controller = loader.getController();
			controller.setMainApp(this);
			this.messenger.setMainScreenController(controller);
			controller.setMessengerClass(this.messenger);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to load the play music screen
	 * @return
	 */
	private boolean loadPlayMusicScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PlayMusicPane.fxml"));
			HBox playMusicScreen = (HBox) loader.load();
			playMusicScreen.getStylesheets().add("css/circle.css");
			this.root.setBottom(playMusicScreen);
			PlayMusicController controller = loader.getController();
			this.messenger.setPlayMusicController(controller);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to load the music upload box
	 */
	public void showUploadBox() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/UploadMusic.fxml"));
			AnchorPane uploadMusicBox = (AnchorPane) loader.load();
			Stage editStage = new Stage();
			editStage.setTitle("Upload Music");
			editStage.initModality(Modality.WINDOW_MODAL);
			UploadMusicController controller = loader.getController();
			controller.setMainApp(this);
			controller.setStage(editStage);
			Scene scene = new Scene(uploadMusicBox);
			editStage.setScene(scene);
			editStage.showAndWait();
		} catch (Exception e) {
			System.out.println("Error loading menu box");
		}
	}

	/**
	 * Method to change from album selection to music selection
	 * @param musicArea
	 * @param albumnName
	 */
	public void showMusicSection(ScrollPane musicArea, String albumnName) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/SongPane.fxml"));
			HBox mainScreen = (HBox) loader.load();
			mainScreen.setMaxWidth(583);
			mainScreen.setMaxHeight(458);
			mainScreen.getStylesheets().add("css/table.css");
			musicArea.setContent(mainScreen);
			MusicAreaController controller = loader.getController();
			controller.setMessengerClass(this.messenger);
			controller.setMainApp(albumnName);
		} catch (Exception e) {
			System.out.println("Error changing pane");
		}

	}

	/**
	 * Method to load file and check if it is a music file
	 */
	public void openFile() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		boolean uploaded;
		if (file != null) {
			MusicChecker checker = new MusicChecker();
			uploaded = checker.checkFile(file);
			if (!uploaded) {
				PopUpDisplayer displayer = new PopUpDisplayer();
				displayer.setErrorHeader(checker.getErrorHeader());
				displayer.setErrorMessage(checker.getErrorMessage());
				displayer.showPopUpBox("error");
			}
		}
	}

	public void closeProgram() {
		this.primaryStage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
