/**
 * Class that controls everything on the main screen
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import model.MusicFinder;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainScreenController implements Initializable {

	private Main main;
	private File[] musicList;
	@FXML
	private ScrollPane musicArea;
	@FXML
	private Label addMusic;
	@FXML
	private Label myLibrary;
	@FXML
	private AnchorPane changeArea;
	@FXML ImageView logoImage;
	@FXML Label editMusic;
	@FXML SplitPane mainSplitScreen;
	private MessengerClass messenger;
	private String albumnName;
	
	public MainScreenController() {
	}

	/**
	 * Start up method, if there is no settings file it displays an 
	 * image else displays the albums
	 * @param main
	 */
	public void setMainApp(Main main) {
		this.main = main;
		File file = new File("src/.metadata/settings.txt");
		if(file.exists()){
			this.findMusicAlbumns();
		} else {
			ImageView image = new ImageView(new Image("images/noMusic.jpg"));
			image.setFitWidth(650);
			this.changeArea.getChildren().add(image);
			this.displayDirectorySelector();
		}
	}
	
	public void displayDirectorySelector(){
		PopUpDisplayer displayer = new PopUpDisplayer();
		displayer.loadSelectDirectoryPopUp(this);
	}
	
	public Main getMainApp(){
		return this.main;
	}
	
	public ScrollPane getMusicArea(){
		return this.musicArea;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image = new Image("images/logo.jpg");
		this.logoImage.setImage(image);
	}

	public void findMusicAlbumns() {
		MusicFinder finder = new MusicFinder();
		this.musicList = finder.findMusicAlbumns();
		this.displayAlbumns();
	}

	/**
	 * Method to display the albums on the main screen
	 */
	private void displayAlbumns() {
		GridPane gridPane = new GridPane();
		int counter = 0;
		int rowCount = 1;
		ImageView image = null;
		//Image byteImage;
		for (int index = 0; index < this.musicList.length; index++) {
			if (this.musicList[index].isDirectory()) {
				Label textArea = new Label(this.musicList[index].getName());
				textArea.setMaxWidth(100);
				textArea.setAlignment(Pos.CENTER);
				textArea.setStyle("-fx-font: 8pt 'Arial';");
				VBox tempbox = new VBox();
				image = new ImageView(new Image("images/music.png"));
				image.setFitHeight(100);
				image.setFitWidth(80);
				tempbox.getChildren().add(image);
				tempbox.getChildren().add(textArea);
				tempbox.setAlignment(Pos.CENTER);
				tempbox.setPadding(new Insets(15.0,15.0,15.0,15.0));
				tempbox.setUserData(this);
				tempbox.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							public void handle(MouseEvent event) {
								ObservableList<Node> list = tempbox.getChildren();
								Label area = (Label) list.get(1);
								MessengerClass main = (MessengerClass) ((MainScreenController) tempbox.getUserData()).messenger;
								((MainScreenController) tempbox.getUserData()).albumnName = area.getText();
								main.tellMainToShowMusic(((MainScreenController) tempbox.getUserData()).getMusicArea(), area.getText());
								event.consume();
							}
						});
				if (counter < 5) {
					gridPane.addColumn(counter, tempbox);
				} else {
					counter = 0;
					gridPane.addRow(rowCount, tempbox);
					rowCount++;
				}
				counter++;
			}
		}
		gridPane.setMaxWidth(this.musicArea.getMaxWidth() - 10);
		this.musicArea.setContent(gridPane);
		this.changeArea.getChildren().setAll(this.musicArea);
	}

	public void addMusic() {
		MusicFinder finder = new MusicFinder();
		this.main.showUploadBox();
		this.musicList = finder.findMusicAlbumns();
		this.displayAlbumns();
	}

	public void setMessengerClass(MessengerClass messenger) {
		this.messenger = messenger;
	}
	
	public void editSong(){
		this.messenger.editSong();	
	}
	
	public void showMainFolderOrMusicSelection(boolean showOneOrTheOther){
		if(showOneOrTheOther){
			this.findMusicAlbumns();
		} else {
			this.messenger.tellMainToShowMusic(this.musicArea, this.albumnName);
		}
	}

}
