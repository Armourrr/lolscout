import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import services.CalcPerMin;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.rithms.riot.api.RiotApiException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    public static void main(String[] args) throws RiotApiException {
//        testThings();
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
    private static void testThings() throws RiotApiException {
        CalcPerMin stats = new CalcPerMin();

        ApiConfig config = new ApiConfig().setKey("RGAPI-f89967bf-f46a-4514-a91d-198eaddbda62");
        RiotApi api = new RiotApi(config);

        // First we need to request the summoner because we will need it's account ID
        Summoner summoner = api.getSummonerByName(Platform.EUW, "StratusLyte");
        api.getSummoner(Platform.EUW,summoner.getId());

        // Then we can use the account ID to request the summoner's match list

        MatchList matchList = api.getMatchListByAccountId(Platform.EUW, summoner.getAccountId());


        System.out.println("Total Games in requested match list: " + matchList.getTotalGames());
        Match match = api.getMatch(Platform.EUW, matchList.getMatches().get(5).getGameId());
        long duration = match.getGameDuration();
        ParticipantStats summStats;
        Summoner summ;
        ChampionList champList = api.getDataChampionList(Platform.EUW);
        List<ParticipantIdentity> list = match.getParticipantIdentities();

        int i = 0;
        for (Participant player:match.getParticipants()) {
            String champPlayed = "";
            for (Map.Entry<String, Champion> entry : champList.getData().entrySet())
            {
                if(entry.getValue().getId() == player.getChampionId()) {
                       champPlayed = entry.getKey();
                }
            }
            summ = api.getSummoner(Platform.EUW, list.get(i).getPlayer().getSummonerId());
            summStats = player.getStats();
            int gold = summStats.getGoldEarned();
            int wards = summStats.getWardsPlaced();
            int dmg = (int)summStats.getTotalDamageDealtToChampions();
            int wardsCleared = summStats.getWardsKilled();
            int creepScore = summStats.getTotalMinionsKilled() + summStats.getNeutralMinionsKilled();

            System.out.println("Duration:" + duration);

            System.out.println("---------------------------------------------");
            System.out.println(summ.getName());
            System.out.println("    Champion: " + champPlayed);
            System.out.println("    KDA: " + summStats.getKills() + "/" + summStats.getDeaths() + "/" + summStats.getAssists());
            System.out.println("    DPM: " + stats.calculateDamagePerMinute(dmg, duration));
            System.out.println("    WPM: " + stats.calculateWardsPerMinute(wards, duration));
            System.out.println("    WCPM: " + stats.calculateWardsClearedPerMinute(wardsCleared, duration));
            System.out.println("    CSPM: " + stats.calculateCreepsPerMinute(creepScore, duration));
            System.out.println("    GPM: " + stats.calculateGoldPerMinute(gold,duration));
            System.out.println("    Dmg per gold: " + stats.calculateDmgPerGold(dmg, gold));
            i++;

        }
    }
}
