import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import config.DataConfig;
import config.FilesConfig;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import net.rithms.riot.api.RiotApi;
//import net.rithms.riot.api.RiotApiException;
//import net.rithms.riot.api.endpoints.static_data.dto.Champion;
//import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
//import net.rithms.riot.constant.Platform;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        Orianna.setRiotAPIKey("RGAPI-99e007ee-3d5a-4775-870d-31fd390523ec");
        Orianna.setDefaultRegion(Region.EUROPE_WEST);
        setupChampionList();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(FilesConfig.DIR_VIEW + "MainView.fxml"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Failed to load FXML file, exiting...");
            e.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("LoLScout");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
                System.exit(0);
            }
        });
        primaryStage.show();
    }

    private static void setupChampionList() {
        DataConfig.championMap = new HashMap<>();
        for(Champion champion : Champions.withRegion(Region.EUROPE_WEST).get()) {
            DataConfig.championMap.put(champion.getId(), champion.getName());
        }

    }
}
