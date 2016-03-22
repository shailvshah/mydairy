package mydiary;

import com.jfoenix.controls.JFXSnackbar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.mongodb.*;
import com.mongodb.client.*;
import javafx.scene.control.PasswordField;
import org.bson.*;

public class LoginScreenController implements Initializable {
    
    Stage prevStage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private JFXSnackbar snackbar;
    @FXML
    private Pane root;
    
    private MongoCollection<Document> mongoCollection;
    
    public void setPreviousStage(Stage stage) {
        this.prevStage = stage;
    }
    
    public Pane getPane() {
        return this.root;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("accounts");
        mongoCollection = mongoDatabase.getCollection("users");
    }    

    @FXML
    private void signUpAction(ActionEvent event) throws Exception {
        Stage signUpStage =  new Stage();
        signUpStage.setTitle("Sign Up");
        
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("SignUpScreen.fxml"));
        Pane pane = (Pane) signUpLoader.load();
        Scene signUpScene = new Scene(pane);
        
        SignUpScreenController signUpController = (SignUpScreenController) signUpLoader.getController();
        signUpController.setLoginStage(prevStage);
        
        signUpStage.setScene(signUpScene);
        prevStage.close();
        signUpStage.setResizable(false);
        signUpStage.show();
    }

    @FXML
    private void loginAction(ActionEvent event) throws Exception {
        String user = username.getText().trim();
        String pass = password.getText().trim();
        boolean flag = false;
        
        MongoCursor<Document> cursor = mongoCollection.find().iterator();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            String u = doc.getString("username");
            String p = doc.getString("password");
            if((u.equals(user)) && (p.equals(pass))) {
                flag = true;
                break;
            }
        }
        if(flag) {
            Stage mainStage = new Stage();
            mainStage.setTitle("Cloud Manager");
            FXMLLoader mainLoader =  new FXMLLoader(getClass().getResource("MainActivity.fxml"));
            Pane pane = (Pane) mainLoader.load();
            Scene mainScene = new Scene(pane);
            mainStage.setScene(mainScene);
            prevStage.close();
            mainStage.show();
            mainStage.centerOnScreen();
            mainStage.setMinHeight(550);
            mainStage.setMinWidth(838);
        } else {

            if(snackbar.getPopupContainer() == null) {
                snackbar.registerSnackbarContainer(root);
            }
            snackbar.show("Either username or password is wrong", 3000);
        }
    }
    
}
