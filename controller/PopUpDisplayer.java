package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MusicFile;

public class PopUpDisplayer {
	private String errorHeader;
	private String errorMessage;

	public void setErrorHeader(String header){
		this.errorHeader = header;
	}

	public void setErrorMessage(String message){
		this.errorMessage = message;
	}
	
	/**
	 * Method to display a set of pop up boxes
	 * @param boxType
	 */
	public void showPopUpBox(String boxType) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PopUpBoxView.fxml"));
			AnchorPane menuBox = (AnchorPane) loader.load();
			Stage editStage = new Stage();
			switch(boxType){
				case "edit error":
					editStage.setTitle("There was an error editing your file");
					break;
				case "about":
					editStage.setTitle("About");
					break;
				case "license":
					editStage.setTitle("License");
					break;
				case "help":
					editStage.setTitle("Help");
					break;
				case "error":
					editStage.setTitle(this.errorHeader);
					break;
				default:
					editStage.setTitle("Unexpected Error");
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			PopUpController controller = loader.getController();
			controller.setStage(editStage);
			switch(boxType){
				case "edit error":
					controller.setText("There was an error editing your file");
					break;
				case "about":
					controller.setText("This is a music player application built by Christopher Birmingham for G52GUI");
					break;
				case "license":
					controller.setText("This product is under the Do What Ever the Heck You Like License");
					break;
				case "help":
					controller.setText("If you require help please send a bug report to chris.birmingham@hotmail.co.uk");
					break;
				case "error":
					controller.setText(this.errorMessage);
					break;
				default:
					controller.setText("There was an unexpected error");
			}
			Scene scene = new Scene(menuBox);
			editStage.setScene(scene);
			editStage.showAndWait();
		} catch (Exception e) {
			System.out.println("Pop up failed to display");
		}
	}
	
	public void showEditSongBox(MusicFile song){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/EditSongView.fxml"));
			AnchorPane menuBox = (AnchorPane) loader.load();
			Stage editStage = new Stage();
			editStage.setTitle("Edit Song");
			editStage.initModality(Modality.WINDOW_MODAL);
			EditSongController controller = loader.getController();
			controller.setStage(editStage);
			controller.setMusicFile(song);
			Scene scene = new Scene(menuBox);
			editStage.setScene(scene);
			editStage.showAndWait();
		} catch (Exception e) {
			System.out.println("Pop up filed to display");
		}
	}
	
	/**
	 * Method to load the select directory pop up
	 * @param mainScreenController
	 */
	public void loadSelectDirectoryPopUp(MainScreenController mainScreenController) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/SelectDirectoryPopUp.fxml"));
			AnchorPane menuBox = (AnchorPane) loader.load();
			Stage editStage = new Stage();
			editStage.setTitle("Find Music");
			editStage.initModality(Modality.WINDOW_MODAL);
		    SelectDirectoryController controller = loader.getController();
			controller.setStage(editStage);
			controller.setMainScreenController(mainScreenController);
			Scene scene = new Scene(menuBox);
			editStage.setScene(scene);
			editStage.showAndWait();
		} catch (Exception e) {
			System.out.println("Pop up filed to display");
		}
	}
	
}
