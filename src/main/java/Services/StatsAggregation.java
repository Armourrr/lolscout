package Services;

import java.text.DecimalFormat;

public class StatsAggregation {
    public int calculateGoldPerMinute(int gold, long seconds) {
        return (int) ((double)gold/((double)seconds/60));
    }

    public double calculateWardsPerMinute(int wards, long seconds) {
        double wpm = ((double)wards/((double)seconds/60));
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(wpm));
    }

    public double calculateWardsClearedPerMinute(int wardsCleared, long seconds) {
        double wcpm = ((double)wardsCleared/(double)((double)seconds/60));
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(wcpm));
    }

    public double calculateDamagePerMinute(int damage, long seconds) {
        double dpm = ((double)damage/((double)seconds/60));
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(dpm));
    }

    public double calculateCreepsPerMinute(int cs, long seconds) {
        double cspm = ((double)cs/((double)seconds/60));
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(cspm));
    }

    public double calculateDmgPerGold(int damage, int gold) {
        double dpg = (double) damage/gold;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(dpg));
    }
}
