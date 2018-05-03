package controller;

import config.FilesConfig;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.util.Random;

public class MainController {
    @FXML
    private StackPane paneStack;
    @FXML
    private ImageView ivChampBg;

    @FXML
    public void initialize() {
        ivChampBg.setImage(getRandomSplashImg());
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
