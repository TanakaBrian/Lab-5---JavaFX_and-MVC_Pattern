/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author wolves
 */
public class PrimaryFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Add input restrictions
        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    } 
    
    @FXML
    TextField nameTextField;
    
   @FXML
    TextField ageTextField;
   
    @FXML
    TextField emailTextField;
    
     private boolean isValidName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z\\s]+");
        
     }
     
    
   private boolean isValidAge(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue > 0 && ageValue <= 120;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public void submit() {
        String name = nameTextField.getText().trim();
        String age = ageTextField.getText().trim();
        String email = emailTextField.getText().trim();

        StringBuilder errors = new StringBuilder();

        // Name validation
        if (name.isEmpty()) {
            errors.append("• Name cannot be empty\n");
        } else if (!isValidName(name)) {
            errors.append("• Name can only contain letters and spaces\n");
        }

        // Age validation
        if (age.isEmpty()) {
            errors.append("• Age cannot be empty\n");
        } else if (!isValidAge(age)) {
            errors.append("• Age must be a number between 1 and 120\n");
        }

        // Email validation
        if (email.isEmpty()) {
            errors.append("• Email cannot be empty\n");
        } else if (!isValidEmail(email)) {
            errors.append("• Invalid email format\n");
        }

        if (errors.length() > 0) {
            showAlert(AlertType.ERROR, "Validation Error", "Please fix the following errors:", errors.toString());
        } else {
            showAlert(AlertType.INFORMATION, "Success", "Thank you for submitting", 
                     "Name: " + name + "\nAge: " + age + "\nEmail: " + email);
        }
        }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

        
    
    
    


}

