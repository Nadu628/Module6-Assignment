package org.example.module6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.regex.*;

public class RegistrationController {
    private static final String FN_NAME_PATTERN = "[a-zA-Z]{2,25}$";
    private static final String LN_NAME_PATTERN = "[a-zA-Z]{2,25}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+@farmingdale.edu";
    private static final String DOB_PATTERN = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/([0-9]{4})$";
    private static final String ZIPCODE_PATTERN = "^[0-9]{5}$";
    @FXML
    private TextField firstNameTF, lastNameTF, emailTF, dobTF, zipCodeTF;
    @FXML
    private Button registerButton;
    @FXML
    ImageView thumbsUpIV;
    @FXML
    private Label errorLabelFN, errorLabelLN, errorLabelE, errorLabelDOB, errorLabelZC;


    @FXML
    private void initialize() {
        firstNameTF.textProperty().addListener((observable, oldValue, newValue) -> {validateFirstName();});
        lastNameTF.textProperty().addListener((observable, oldValue, newValue) -> {validateLastName();});
        emailTF.textProperty().addListener((observable, oldValue, newValue) -> {validateEmail();});
        dobTF.textProperty().addListener((observable, oldValue, newValue) -> {validateDOB();});
        zipCodeTF.textProperty().addListener((observable, oldValue, newValue) -> {validateZipCode();});


        registerButton.setOnAction(e -> {
            if(validateAllInputs()){
                System.out.println("Registration Button pressed");
                showAlert(Alert.AlertType.INFORMATION, "Registration Complete", "All fields are complete! Thank you for registering");
                transitionToParty(firstNameTF.getText());
            }else{
                showAlert(Alert.AlertType.ERROR, "Registration Error","All inputs not filled in correctly! Please try again");
            }

        });
    }

   public void validateFirstName() {
        //first name: min 2 char and max 25
        String firstName = firstNameTF.getText();
        if (firstName == null || firstName.isEmpty() || !firstName.matches(FN_NAME_PATTERN)) {
            errorLabelFN.setText("must be 2-25 characters");
            errorLabelFN.getStyleClass().removeAll("label-success");
            errorLabelFN.getStyleClass().add("label-error");

            firstNameTF.getStyleClass().removeAll("text-field-valid");
            firstNameTF.getStyleClass().add("text-field-error");
        } else {
            errorLabelFN.setText("correct");
            errorLabelFN.getStyleClass().removeAll("label-error");
            errorLabelFN.getStyleClass().add("label-success");

            firstNameTF.getStyleClass().removeAll("text-field-error");
            firstNameTF.getStyleClass().add("text-field-valid");
        }
    }
    public void validateLastName() {
        //last name: min 2 char and max 25
        String lastName = lastNameTF.getText();
        if (lastName == null || lastName.isEmpty() || !lastName.matches(LN_NAME_PATTERN)) {
            errorLabelLN.setText("must be 2-25 characters");
            errorLabelLN.getStyleClass().removeAll("label-success");
            errorLabelLN.getStyleClass().add("label-error");

            lastNameTF.getStyleClass().removeAll("text-field-valid");
            lastNameTF.getStyleClass().add("text-field-error");
        } else {
            errorLabelLN.setText("correct");
            errorLabelLN.getStyleClass().removeAll("label-error");
            errorLabelLN.getStyleClass().add("label-success");

            lastNameTF.getStyleClass().removeAll("text-field-error");
            lastNameTF.getStyleClass().add("text-field-valid");
        }
    }

        //dob: MM/DD/YYYY
    public void validateDOB(){
        String dob = dobTF.getText();
        if (dob == null || dob.isEmpty() || !dob.matches(DOB_PATTERN)) {
            errorLabelDOB.setText("must be MM/DD/YYYY");
            errorLabelDOB.getStyleClass().removeAll("label-success");
            errorLabelDOB.getStyleClass().add("label-error");

            dobTF.getStyleClass().removeAll("text-field-valid");
            dobTF.getStyleClass().add("text-field-error");
        }else{
            errorLabelDOB.setText("correct");
            errorLabelDOB.getStyleClass().removeAll("label-error");
            errorLabelDOB.getStyleClass().add("label-success");

            dobTF.getStyleClass().removeAll("text-field-error");
            dobTF.getStyleClass().add("text-field-valid");
        }
    }

        //email: only @farmingdale.edu
    public void validateEmail(){
        String email = emailTF.getText();
        if(email == null || email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
          errorLabelE.setText("must be a farmingdale.edu email");
            errorLabelE.getStyleClass().removeAll("label-success");
            errorLabelE.getStyleClass().add("label-error");

            emailTF.getStyleClass().removeAll("text-field-valid");
            emailTF.getStyleClass().add("text-field-error");
        }else{
            errorLabelE.setText("correct");
            errorLabelE.getStyleClass().removeAll("label-error");
            errorLabelE.getStyleClass().add("label-success");

            emailTF.getStyleClass().removeAll("text-field-error");
            emailTF.getStyleClass().add("text-field-valid");
        }
    }

        //zip-code: 5 digits
    public void validateZipCode(){
        String zipCode = zipCodeTF.getText().trim();

        if(zipCode.isEmpty() || !zipCode.matches(ZIPCODE_PATTERN)) {
          errorLabelZC.setText("must be 5 digits");
            errorLabelZC.getStyleClass().removeAll("label-success");
            errorLabelZC.getStyleClass().add("label-error");

            zipCodeTF.getStyleClass().removeAll("text-field-valid");
            zipCodeTF.getStyleClass().add("text-field-error");
        }else{
            errorLabelZC.setText("correct");
            errorLabelZC.getStyleClass().removeAll("label-error");
            errorLabelZC.getStyleClass().add("label-success");

            zipCodeTF.getStyleClass().removeAll("text-field-error");
            zipCodeTF.getStyleClass().add("text-field-valid");
        }
    }

    private boolean validateAllInputs(){
        validateFirstName();
        validateLastName();
        validateDOB();
        validateEmail();
        validateZipCode();

        boolean isValid = !errorLabelFN.getText().equals("must be 2-25 characters") &&
                !errorLabelLN.getText().equals("must be 2-25 characters") &&
                !errorLabelE.getText().equals("must be a farmingdale.edu email") &&
                !errorLabelZC.getText().equals("must be 5 digits") &&
                !errorLabelDOB.getText().equals("must be MM/DD/YYYY");
        return isValid;
    }
    public void showAlert(Alert.AlertType alertType, String title, String message ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }

    public void transitionToParty(String name){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcometotheparty.fxml"));
            AnchorPane root = loader.load();

            WelcomeToThePartyController controller = loader.getController();
            controller.welcomeMessage(name);

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}