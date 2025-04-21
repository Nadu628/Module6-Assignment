package org.example.module6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
        emailTF.textProperty().addListener((observable, oldValue, newValue) -> {validateDOB();});


        registerButton.setOnAction(e -> {
            if(validateAllInputs()){
                System.out.println("Registration Button pressed");
                showAlert(Alert.AlertType.INFORMATION, "Registration Complete", "All fields are complete! Thank you for registering");
            }else{
                showAlert(Alert.AlertType.ERROR, "Registration Error", "All inputs not filled in correctly! Please try again");
            }

        });
    }



   public void validateFirstName() {
        //first and last name: min 2 char and max 25
        String firstName = firstNameTF.getText();
        if (firstName == null || firstName.isEmpty() || !firstName.matches(FN_NAME_PATTERN)) {
            errorLabelFN.setText("must be 2-25 characters");
        } else {
            errorLabelFN.setText("correct");
        }
    }
    public void validateLastName() {
        String lastName = lastNameTF.getText();
        if (lastName == null || lastName.isEmpty() || !lastName.matches(LN_NAME_PATTERN)) {
            errorLabelLN.setText("must be 2-25 characters");
        } else {
            errorLabelLN.setText("correct");
        }
    }

        //dob: MM/DD/YYYY
    public void validateDOB(){
        String dob = dobTF.getText();
        if (dob == null || dob.isEmpty() || !dob.matches(DOB_PATTERN)) {
            errorLabelDOB.setText("must be MM/DD/YYYY");
        }else{
            errorLabelDOB.setText("correct");
        }
    }

        //email: only @farmingdale.edu
    public void validateEmail(){
        String email = emailTF.getText();
        if(email == null || email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
          errorLabelE.setText("must be a farmingdale.edu email");
        }else{
            errorLabelE.setText("correct");
        }
    }

        //zip-code: 5 digits
    public void validateZipCode(){
        String zipCode = zipCodeTF.getText().trim();

        if(zipCode.isEmpty() || !zipCode.matches(ZIPCODE_PATTERN)) {
          errorLabelZC.setText("must be 5 digits");
        }else{
            errorLabelZC.setText("correct");
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



}