import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import javafx.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main extends Application {


    public static void main(String[] args) throws RiotApiException {
//        ApiConfig config = new ApiConfig().setKey("RGAPI-96f449c7-2138-466b-9891-5a52715f5963");
//        RiotApi api = new RiotApi(config);
//
//        // First we need to request the summoner because we will need it's account ID
//        Summoner summoner = api.getSummonerByName(Platform.EUW, "Armour");
//
//        // Then we can use the account ID to request the summoner's match list
//        MatchList matchList = api.getMatchListByAccountId(Platform.EUW, summoner.getAccountId());
//
//        System.out.println("Total Games in requested match list: " + matchList.getTotalGames());
//
//
//
//        // We can now iterate over the match list to access the data
//        if (matchList.getMatches() != null) {
//            for (MatchReference match : matchList.getMatches()) {
//                System.out.println(match.getChampion());
//            }
//        }
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
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
//        primaryStage.setTitle("LoLScout");
//        StackPane root = new StackPane();
//        Scene scene = new Scene(root, 1280, 720);
//
//        Rectangle rec = new Rectangle(1280,720);
//        rec.setFill(Color.BLACK);
//        root.getChildren().add(rec);
//
//        File dir = new File(Config.DIR_SPLASH);
//        File[] files = dir.listFiles();
//
//        Random rand = new Random();
//
//        File file = files[rand.nextInt(files.length)];
//
//        Image image = new Image(file.toURI().toString());
//        ImageView iv = new ImageView(image);
//        iv.setFitWidth(1280);
//        iv.setFitHeight(720);
//        iv.setOpacity(0.3);
//
//        root.getChildren().add(iv);
//        primaryStage.setScene(scene);
//        primaryStage.show();
}
