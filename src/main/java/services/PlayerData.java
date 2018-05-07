package services;

import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.data.match.MatchList;

import java.util.List;
import java.util.Map;

public class PlayerData {
    private Summoner player;
    private Role role;
    private MatchList matchList;
    private MatchList recentMatchList;
    private List<ChampionMastery> championMasteryList;
    private Map<Integer, ChampionStats> championStatsList;

    public PlayerData(Summoner player) {
        this.player = player;
    }

    public PlayerData(Summoner player, Role role) {
        this.player = player;
        this.role = role;
    }

    public Summoner getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MatchList getMatchList() {
        return matchList;
    }

    public void setMatchList(MatchList matchList) {
        this.matchList = matchList;
    }

    public MatchList getRecentMatchList() {
        return recentMatchList;
    }

    public void setRecentMatchList(MatchList recentMatchList) {
        this.recentMatchList = recentMatchList;
    }

    public List<ChampionMastery> getChampionMasteryList() {
        return championMasteryList;
    }

    public void setChampionMasteryList(List<ChampionMastery> championMasteryList) {
        this.championMasteryList = championMasteryList;
    }

    public Map<Integer, ChampionStats> getChampionStatsList() {
        return championStatsList;
    }

    public void setChampionStatsList(Map<Integer, ChampionStats> championStatsList) {
        this.championStatsList = championStatsList;
    }
}
