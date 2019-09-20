package views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Volunteer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.jar.Attributes.Name;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddVolunteerController implements Initializable {

	// declare widgets
	@FXML
	private TextField firstNameInput;
	@FXML
	private DatePicker birthdayTextfield;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private Button addVolunteerBtn;
	@FXML
	private Label errorMessagelbl;
	@FXML
	private ImageView volImage;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			 File file =new File("src/images/defaultPerson.png");
			 Image image = new Image(file.toURI().toString());
			 volImage.setImage(image);
		} catch (Exception e) {
			System.err.println("There was a problem loading that image");
		}

	}
// we use the gui instead of hard code to populate db stiil calls method
	public void onAddButtonClicked(ActionEvent event) {
		try {
			errorMessagelbl.setText("Succesful!");
			errorMessagelbl.setVisible(true);
			Volunteer volunteer =new Volunteer(firstNameInput.getText(), lastNameTextField.getText(), phoneNumberTextField.getText(), birthdayTextfield.getValue());
			volunteer.insertIntoDB();
		} catch (Exception e) {
			errorMessagelbl.setText(e.getMessage());
			errorMessagelbl.setVisible(true);
		}




		}




}

