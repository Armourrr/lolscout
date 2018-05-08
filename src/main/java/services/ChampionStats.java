package services;

import com.merakianalytics.orianna.types.core.staticdata.Champion;

public class ChampionStats {
    private int championId;
    private int gamesPlayed;
    private int kills;
    private int deaths;
    private int assists;
    private int wins;
    private int losses;
    private int damageDealt;
    private int goldEarned;
    private int wardsPlaced;
    private int wardsCleared;
    private int minionsKilled;

    public ChampionStats(int championId) {
        this.championId = championId;
        this.gamesPlayed = 0;
        this.kills = 0;
        this.deaths = 0;
        this.assists = 0;
        this.wins = 0;
        this.losses = 0;
        this.damageDealt = 0;
        this.goldEarned = 0;
        this.wardsPlaced = 0;
        this.wardsCleared = 0;
        this.minionsKilled = 0;
    }

    public void addGamesPlayed(int games) {
        this.gamesPlayed += games;
    }
    
    public void addKills(int kills) {
        this.kills += kills;
    }

    public void addDeaths(int deaths) {
        this.deaths += deaths;
    }

    public void addAssists(int assists) {
        this.assists += assists;
    }

    public void addWins(int wins) {
        this.wins += wins;
    }

    public void addLosses(int losses) {
        this.losses += losses;
    }

    public void addGoldEarned(int gold) {
        this.goldEarned += gold;
    }

    public void addDamageDealt(int dmg) {
        this.damageDealt += dmg;
    }

    public void addWardsPlaced(int wards) {
        this.wardsPlaced += wards;
    }

    public void addWardsCleared(int wards) {
        this.wardsCleared += wards;
    }

    public void addMinionsKilled(int minions) {
        this.minionsKilled += minions;
    }

    public int getChampionId() {
        return championId;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public int getWardsCleared() {
        return wardsCleared;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    @Override
    public String toString() {
        String champ = Champion.withId(getChampionId()).get().getName();
        return champ
                + " Kills: " + getKills()
                + " Deaths: " + getDeaths()
                + " Assists: " + getAssists()
                + " Wins: " + getWins()
                + " Losses: " + getLosses()
                ;
    }
}
