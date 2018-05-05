package controller;

import config.FilesConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import net.rithms.riot.constant.Platform;

import java.io.File;
import java.util.Random;

public class MainController {
    @FXML
    private StackPane paneStack;
    @FXML
    private TextField txtSoloSumm;
    @FXML
    private ChoiceBox<Platform> cbRegion;
    @FXML
    private Button btnSearchSolo;
    @FXML
    private ImageView ivChampBg;



    @FXML
    public void initialize() {
        ivChampBg.setImage(getRandomSplashImg());
        cbRegion.getItems().setAll(Platform.values());
        cbRegion.getSelectionModel().select(0);
    }

    private Image getRandomSplashImg() {
        File dir = new File(FilesConfig.DIR_SPLASH);
        File[] files = dir.listFiles();

        if(files != null && files.length != 0) {
            Random rand = new Random();
            File file = files[rand.nextInt(files.length)];
            return new Image(file.toURI().toString());
        } else {
            // results in no image
            return null;
        }
    }

}
