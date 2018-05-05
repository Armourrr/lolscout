package config;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

public class ApiManager {
    private final String KEY = "";
    private static RiotApi instance = null;
    protected ApiManager() {
    }
    public static RiotApi getInstance() {
        if(instance == null) {
            ApiConfig config = new ApiConfig();
            instance = new RiotApi(config);
        }
        return instance;
    }
}
