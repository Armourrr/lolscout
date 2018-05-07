package controller;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import config.FilesConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import services.ChampionStats;
import services.PlayerData;
import services.stats.PlayerDataExtractor;
import utils.MapPrinter;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void initialize() throws URISyntaxException {
        ivChampBg.setImage(getRandomSplashImg());
        cbRegion.getItems().setAll(Platform.values());
        cbRegion.getSelectionModel().select(0);
    }

    @FXML
    public void handleSearchSoloBtn() {
        btnSearchSolo.setDisable(true);
        Summoner summoner = Summoner.named(txtSoloSumm.getText()).withPlatform(cbRegion.getValue()).get();
        try {
            summoner.getAccountId(); // throws NullPointerException if invalid summoner
            PlayerDataExtractor extractor = new PlayerDataExtractor();
            PlayerData summonerData = extractor.getChampionHistoryData(summoner, 20);
        } catch (NullPointerException e ) {
            System.out.println("Invalid username for region.");
            btnSearchSolo.setDisable(false);
        }

    }

    private Image getRandomSplashImg() throws URISyntaxException {
        Path path = Paths.get(getClass().getResource(FilesConfig.DIR_SPLASH).toURI());
        File dir = new File(path.toAbsolutePath().toString());
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
