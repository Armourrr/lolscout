

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.match.Team;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import config.DataConfig;
import services.ChampionStats;
import services.PlayerData;

import java.util.HashMap;
import java.util.Map;

public class TestingClass {

    private String summonerName = "Armour";

    public TestingClass(){

    }

    public void getChampionHistory() {
        Summoner summoner = Summoner.named(summonerName).get();
        MatchHistory matchHistory = MatchHistory.forSummoner(summoner).withSeasons(Season.SEASON_8).get();
        PlayerData playerData = new PlayerData(summoner);
        Map<Integer, ChampionStats> championStatsMap = new HashMap<>();

        int matchesLeft = 20;
        for(Match match: matchHistory) {
            ParticipantStats playerMatchStats = match.getParticipants().find(summoner).getStats();
            Integer champId = match.getParticipants().find(summoner).getChampion().getId();

            if(!championStatsMap.containsKey(champId)) {
                championStatsMap.put(champId, new ChampionStats(champId));
            }

            ChampionStats stats = championStatsMap.get(champId);
            stats.addGamesPlayed(1);
            stats.addKills(playerMatchStats.getKills());
            stats.addDeaths(playerMatchStats.getDeaths());
            stats.addAssists(playerMatchStats.getAssists());
            stats.addDamageDealt(playerMatchStats.getDamageDealtToChampions());
            stats.addGoldEarned(playerMatchStats.getGoldEarned());
            stats.addWardsPlaced(playerMatchStats.getWardsPlaced());
            stats.addWardsCleared(playerMatchStats.getWardsKilled());
            stats.addMinionsKilled(playerMatchStats.getCreepScore());
            if(playerMatchStats.isWinner()) {
                stats.addWins(1);
            } else {
                stats.addLosses(1);
            }
            System.out.println("Finished match " + match.getId());
            matchesLeft--;
            if(matchesLeft == 0) {
                break;
            }
        }
        playerData.setChampionStatsList(championStatsMap);
        for (Map.Entry<Integer, ChampionStats> stats:playerData.getChampionStatsList().entrySet()) {
            int kills = stats.getValue().getKills();
            int deaths = stats.getValue().getDeaths();
            int assists = stats.getValue().getAssists();
            double kda = ((double)kills+(double)assists)/(double)deaths;
            System.out.println(DataConfig.championMap.get(stats.getValue().getChampionId())
                    + " | " + stats.getValue().getGamesPlayed()
                    + " | Kills: " + kills
                    + " Deaths: " + deaths
                    + " Assists: " + assists
                    + " KDA: " + kda
            );
        }
    }

}
