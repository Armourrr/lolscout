package services.stats;

import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import services.ChampionStats;
import services.PlayerData;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataExtractor {
    public PlayerData getChampionHistoryData(Summoner summoner, int numberOfMatches) {
        PlayerData playerData = new PlayerData(summoner);

        MatchHistory matchHistory = MatchHistory.forSummoner(summoner).withSeasons(Season.SEASON_8).get();
        Map<Integer, ChampionStats> championStatsMap = new HashMap<>();

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
            numberOfMatches--;
            if(numberOfMatches == 0) {
                break;
            }
        }
        playerData.setChampionStatsList(championStatsMap);
        return playerData;
    }
}
