package controller;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.dto.match.Player;
import config.FilesConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ChampionStats;
import services.PlayerData;
import services.stats.PlayerDataExtractor;

import java.io.File;
import java.io.IOException;
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
        String[] summoners = txtSoloSumm.getText().split(",");
        if(summoners.length > 5) {
            System.out.println("TOO MANY SUMMONERS. MAXIMUM IS 5.");
            btnSearchSolo.setDisable(false);
            return;
        }
        PlayerData[] playerData = new PlayerData[summoners.length];
        PlayerDataExtractor extractor = new PlayerDataExtractor();

        int i = 0;
        for (String summoner:summoners) {
            Summoner player = Summoner.named(summoner).withPlatform(cbRegion.getValue()).get();
            try {
                player.getAccountId(); // throws NullPointerException if invalid summoner
            } catch (NullPointerException e) {
                System.out.println("Invalid username for region.");
                btnSearchSolo.setDisable(false);
                return;
            }
            playerData[i] = extractor.extract(player);
            i++;
        }
        try {
            openAnalyseView(playerData);
        } catch (IOException e) {
            System.out.println("Unable to load FXML file.");
        }
        btnSearchSolo.setDisable(false);
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

    private void openAnalyseView(PlayerData[] playerData) throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FilesConfig.DIR_VIEW + "AnalyseView.fxml"));
        Parent root = loader.load();

        AnalyseController controller = loader.<AnalyseController>getController();
        controller.init(playerData);

        Scene window = new Scene(root);

        Stage analyseStage = new Stage();
        analyseStage.setTitle("LoLScout");
        analyseStage.setMaximized(true);
        analyseStage.setScene(window);
        analyseStage.showAndWait();
    }
}
