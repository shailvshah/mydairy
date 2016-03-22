package mydiary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainActivityController implements Initializable {

    @FXML
    private TreeView<?> directoryTree;
    @FXML
    private TableView<?> fileTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void onUploadClicked(ActionEvent event) {
        Stage sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser uploadFileChooser = new FileChooser();
        uploadFileChooser.setTitle("Choose Upload File");
        uploadFileChooser.showOpenDialog(sourceStage);
    }

    @FXML
    private void onDownloadClicked(ActionEvent event) {
    }
    
}
