package com.itgroup.controller;

import com.itgroup.bean.League;
import com.itgroup.utility.Utility;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChampController implements Initializable {
    @FXML Label champExplanation;
    @FXML ImageView champillust;
    @FXML ImageView pImage;
    @FXML ImageView qImage;
    @FXML ImageView wImage;
    @FXML ImageView eImage;
    @FXML ImageView rImage;
    @FXML Label pName;
    @FXML Label qName;
    @FXML Label wName;
    @FXML Label eName;
    @FXML Label rName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void champMenu(ObservableList<League> items, int idx) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getIDX() == idx){
                Image image = new Image(String.valueOf(getClass().getResource(Utility.CHAMP_IMAGE_PATH + items.get(i).getILLUSTRATION())));

                champillust.setImage(image);
                champExplanation.setText(items.get(i).getCHAMPEXPLANATION());
            }
        }
    }

    public void skillMenu(ObservableList<League> items, int idx) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getIDX() == idx){
                Image pImages = new Image(String.valueOf(getClass().getResource(Utility.SKILL_IMAGE_PATH + items.get(i).getPIMAGE())));

                Image qImages = new Image(String.valueOf(getClass().getResource(Utility.SKILL_IMAGE_PATH + items.get(i).getQIMAGE())));

                Image wImages = new Image(String.valueOf(getClass().getResource(Utility.SKILL_IMAGE_PATH + items.get(i).getWIMAGE())));

                Image eImages = new Image(String.valueOf(getClass().getResource(Utility.SKILL_IMAGE_PATH + items.get(i).getEIMAGE())));

                Image rImages = new Image(String.valueOf(getClass().getResource(Utility.SKILL_IMAGE_PATH + items.get(i).getRIMAGE())));

                pImage.setImage(pImages);
                qImage.setImage(qImages);;
                wImage.setImage(wImages);;
                eImage.setImage(eImages);;
                rImage.setImage(rImages);;
                pName.setText(items.get(i).getPNAME());
                qName.setText(items.get(i).getQNAME());
                wName.setText(items.get(i).getWNAME());
                eName.setText(items.get(i).getENAME());
                rName.setText(items.get(i).getRNAME());
            }
        }
    }
}
