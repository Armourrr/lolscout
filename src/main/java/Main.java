import Services.StatsAggregation;
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
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import javafx.*;

import java.io.IOException;
import java.util.Calendar;

public class Main extends Application {


    public static void main(String[] args) throws RiotApiException {
        StatsAggregation stats = new StatsAggregation();
        System.out.println("Dmg per gold: " + stats.calculateDmgPerGold(41642, 19411));

//        ApiConfig config = new ApiConfig().setKey("RGAPI-f89967bf-f46a-4514-a91d-198eaddbda62");
//        RiotApi api = new RiotApi(config);
//
//
//        // First we need to request the summoner because we will need it's account ID
//        Summoner summoner = api.getSummonerByName(Platform.EUW, "Armour");
//
//        // Then we can use the account ID to request the summoner's match list
//
//        MatchList matchList = api.getMatchListByAccountId(Platform.EUW, summoner.getAccountId());
//
//
//        System.out.println("Total Games in requested match list: " + matchList.getTotalGames());
//        Match match = api.getMatch(Platform.EUW, matchList.getMatches().get(0).getGameId());
//        long duration = match.getGameDuration();
//        int goldSummoner0 = match.getParticipants().get(0).getStats().getGoldEarned();
//        int wardsSummoner0 = match.getParticipants().get(0).getStats().getWardsPlaced();
//
//        StatsAggregation stats = new StatsAggregation();
//        System.out.println("Trynd gold" + goldSummoner0);
//
//        System.out.println("Trynd GPM: " + stats.calculateGoldPerMinute(goldSummoner0,duration));
//        System.out.println("Trynd WPM: " + stats.calculateWardsPerMinute(wardsSummoner0, duration));


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
