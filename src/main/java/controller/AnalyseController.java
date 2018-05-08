package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.PlayerData;

public class AnalyseController {
    private PlayerData[] playerData;

    @FXML
    private Label lblSum1, lblSum2, lblSum3, lblSum4, lblSum5;
    @FXML
    private Label[] lbls;

    @FXML
    public void initialize() {

    }

    public void init(PlayerData[] playerData) {
        lbls = new Label[5];
        lbls[0] = lblSum1;
        lbls[1] = lblSum2;
        lbls[2] = lblSum3;
        lbls[3] = lblSum4;
        lbls[4] = lblSum5;

        this.playerData = playerData;

        for (int i = 0; i < playerData.length; i++) {
            initializeSummonerColumn(i);
        }
    }

    private void initializeSummonerColumn(int colIndex) {
        lbls[colIndex].setText(this.playerData[colIndex].getPlayer().getName());

    }
}
