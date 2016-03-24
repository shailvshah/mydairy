package mydiary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.bson.Document;

public class SignUpStudentScreenController implements Initializable {

    
    private Stage loginStage;
    private final JFXDialog dialog = new JFXDialog();
    @FXML
    private StackPane dialogContainer;
    
    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField mobileNumberField;
    @FXML
    private ComboBox<?> departmentBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private JFXTextField emailIdField;
    @FXML
    private JFXTextField registerNumberField;
    
    public void setLoginStage(Stage stage) {
        this.loginStage = stage;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void onSignUpClicked(ActionEvent event) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        
        MongoDatabase mongoDatabase = mongoClient.getDatabase("students");
        
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("users");
        
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String registerNumber = registerNumberField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String emailId = emailIdField.getText().trim();
        String mobileNumber = mobileNumberField.getText().trim();
        String dateOfBirth = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).trim();
        String department = departmentBox.getValue().toString().trim();
        
        Document user = new Document("registerno", registerNumber)
                            .append("firstname", firstName)
                            .append("lastname", lastName)
                            .append("username", username)
                            .append("password", password)
                            .append("emailid", emailId)
                            .append("mobilenumber", mobileNumber)
                            .append("dateofbirth", dateOfBirth)
                            .append("department", department);
        mongoCollection.insertOne(user);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Label headingLabel = new Label("Signed Up");
        Label label = new Label("You have signed up successfully please use your username and password to login");
        JFXButton button = new JFXButton("OKAY");
        button.setStyle("-fx-text-fill:  #03A9F4");
        button.getStyleClass().add("dialog-accept");
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(headingLabel);
        layout.setBody(label);
        layout.setActions(button);
        dialogContainer.setVisible(true);
        dialog.setContent(layout);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show(dialogContainer);
        button.setOnMouseClicked((e)->{
            dialog.close();
            stage.close();
            this.loginStage.show();
        });
    }
}
