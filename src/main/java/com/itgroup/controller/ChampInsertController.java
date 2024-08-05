package com.itgroup.controller;

import com.itgroup.bean.League;
import com.itgroup.dao.LeageDao;
import com.itgroup.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChampInsertController implements Initializable {
    // fxml 파일 내에서 "fxml + 변수이름"으로 명명했습니다.
    @FXML private TextField fxmlNAME;
    @FXML private ComboBox<String> fxmlPOSITION;
    @FXML private TextField fxmlPIMAGE;
    @FXML private TextField fxmlPNAME;
    @FXML private TextField fxmlQIMAGE;
    @FXML private TextField fxmlQNAME;
    @FXML private TextField fxmlWIMAGE;
    @FXML private TextField fxmlWNAME;
    @FXML private TextField fxmlEIMAGE;
    @FXML private TextField fxmlENAME;
    @FXML private TextField fxmlRIMAGE;
    @FXML private TextField fxmlRNAME;
    @FXML private TextField fxmlSKIN;
    @FXML private TextField fxmlCHAMPEXPLANATION;
    @FXML private TextField fxmlILLUSTRATION;


    LeageDao dao = null ;
    League bean = null ; // 상품 1개를 의미하는 빈 클래스

    public void onProductInsert(ActionEvent event) {
        // 기입한 상품 목록을 데이터 베이스에 추가합니다.
        // event 객체는 해당 이벤트를 발생시킨 객체입니다.
        System.out.println(event);

        boolean bool = validationCheck();
        if(bool == true){
            int cnt = -1 ;
            cnt = insertDatabase();
            if(cnt==1){ // 인서트 성공시
                Node source = (Node)event.getSource(); // 강등
                Stage stage = (Stage)source.getScene().getWindow() ; // 강등
                stage.close(); // 현재 창을 닫습니다.
            }
        }else{
            System.out.println("등록 실패");
        }
    }

    private int insertDatabase() {
        // 1건의 데이터인 bean을 dao를 사용하여 데이터 베이스에 추가합니다.
        int cnt = -1 ; // 작업 실패
        cnt = dao.insertData(this.bean);
        if(cnt == -1){
            String[] message = new String[]{"챔피언 등록", "챔피언 등록 실패", "챔피언 등록을 실패하였습니다."} ;
            Utility.showAlert(Alert.AlertType.ERROR, message);
        }
        return cnt ;
    }

    private boolean validationCheck() {
        // 유효성 검사를 통과하면 true가 됩니다.
        String[] message = null;

        String name = fxmlNAME.getText().trim();
        if (name.length() <= 2 || name.length() >= 11) {
            message = new String[]{"유효성 검사 : 이름", "길이 제한 위배", "이름은 3글자 이상 10글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String pImage = fxmlPIMAGE.getText().trim();
        if (pImage.length() <= 2 || pImage.length() >= 16) {
            message = new String[]{"유효성 검사 : 패시브 이미지", "길이 제한 위배", "패시브 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (pImage == null || pImage.length() < 5) {
            message = new String[]{"유효성 검사 : 패시브 이미지", "필수 입력 체크", "패시브 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        boolean bool = false;
        // startsWith()와 endsWith()
        bool = pImage.endsWith(".jpg") || pImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : 패시브 이미지", "확장자 점검", "패시브 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String pName = fxmlPNAME.getText().trim();
        if (pName.length() <= 1 || pName.length() >= 31) {
            message = new String[]{"유효성 검사 : 패시브 스킬 이름", "길이 제한 위배", "패시브 스킬 이름은 1글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String qImage = fxmlQIMAGE.getText().trim();
        if (qImage.length() <= 2 || qImage.length() >= 16) {
            message = new String[]{"유효성 검사 : q스킬 이미지", "길이 제한 위배", "q스킬 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (qImage == null || qImage.length() < 5) {
            message = new String[]{"유효성 검사 : q스킬 이미지", "필수 입력 체크", "q스킬 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = qImage.endsWith(".jpg") || qImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : q스킬 이미지", "확장자 점검", "q스킬 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String qName = fxmlQNAME.getText().trim();
        if (qName.length() <= 1 || qName.length() >= 31) {
            message = new String[]{"유효성 검사 : q스킬 이름", "길이 제한 위배", "q스킬 이름은 1글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String wImage = fxmlWIMAGE.getText().trim();
        if (wImage.length() <= 2 || wImage.length() >= 16) {
            message = new String[]{"유효성 검사 : w스킬 이미지", "길이 제한 위배", "w스킬 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (wImage == null || wImage.length() < 5) {
            message = new String[]{"유효성 검사 : w스킬 이미지", "필수 입력 체크", "w스킬 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = wImage.endsWith(".jpg") || wImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : w스킬 이미지", "확장자 점검", "w스킬 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String wName = fxmlWNAME.getText().trim();
        if (wName.length() <= 1 || wName.length() >= 31) {
            message = new String[]{"유효성 검사 : w스킬 이름", "길이 제한 위배", "w스킬 이름은 1글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String eImage = fxmlEIMAGE.getText().trim();
        if (eImage.length() <= 2 || eImage.length() >= 16) {
            message = new String[]{"유효성 검사 : e스킬 이미지", "길이 제한 위배", "e스킬 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (eImage == null || eImage.length() < 5) {
            message = new String[]{"유효성 검사 : e스킬 이미지", "필수 입력 체크", "e스킬 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = eImage.endsWith(".jpg") || eImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : e스킬 이미지", "확장자 점검", "e스킬 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String eName = fxmlENAME.getText().trim();
        if (eName.length() <= 1 || eName.length() >= 31) {
            message = new String[]{"유효성 검사 : e스킬 이름", "길이 제한 위배", "e스킬 이름은 1글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String rImage = fxmlRIMAGE.getText().trim();
        if (rImage.length() <= 2 || rImage.length() >= 16) {
            message = new String[]{"유효성 검사 : r스킬 이미지", "길이 제한 위배", "r스킬 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (rImage == null || rImage.length() < 5) {
            message = new String[]{"유효성 검사 : r스킬 이미지", "필수 입력 체크", "r스킬 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = rImage.endsWith(".jpg") || rImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : r스킬 이미지", "확장자 점검", "r스킬 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String rName = fxmlRNAME.getText().trim();
        if (rName.length() <= 1 || rName.length() >= 31) {
            message = new String[]{"유효성 검사 : r스킬 이름", "길이 제한 위배", "r스킬 이름은 1글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String skinImage = fxmlSKIN.getText().trim();
        if (skinImage.length() <= 2 || skinImage.length() >= 16) {
            message = new String[]{"유효성 검사 : 대표스킨 이미지", "길이 제한 위배", "대표스킨 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (skinImage == null || skinImage.length() < 5) {
            message = new String[]{"유효성 검사 : 대표스킨 이미지", "필수 입력 체크", "대표스킨 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = skinImage.endsWith(".jpg") || skinImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : 대표스킨 이미지", "확장자 점검", "대표스킨 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String champExplanation = fxmlCHAMPEXPLANATION.getText().trim();
        if (champExplanation.length() <= 5 || champExplanation.length() >= 101) {
            message = new String[]{"유효성 검사 : 챔피언 설명", "길이 제한 위배", "챔피언 설명은 5글자 이상 100글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String illustImage = fxmlILLUSTRATION.getText().trim();
        if (illustImage.length() <= 2 || illustImage.length() >= 16) {
            message = new String[]{"유효성 검사 : 일러스트 이미지", "길이 제한 위배", "일러스트 이미지는 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        if (illustImage == null || illustImage.length() < 5) {
            message = new String[]{"유효성 검사 : 일러스트 이미지", "필수 입력 체크", "일러스트 이미지는 필수 입력 사항니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        // startsWith()와 endsWith()
        bool = illustImage.endsWith(".jpg") || illustImage.endsWith(".png");
        if (!bool) {
            message = new String[]{"유효성 검사 : 일러스트 이미지", "확장자 점검", "일러스트 이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }


        int selectedIndex = fxmlPOSITION.getSelectionModel().getSelectedIndex();
        String position = null;

        if (selectedIndex == 0) {
            message = new String[]{"유효성 검사 : 카테고리", "카테고리 미선택", "원하시는 카테고리를 반드시 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        } else {
            position = fxmlPOSITION.getSelectionModel().getSelectedItem();
            System.out.println("선택된 항목");
            System.out.println(position);
        }

        // 유효성 검사가 통과되면 비로소 객체 생성합니다.

        if (selectedIndex == 0) {
            message = new String[]{"유효성 검사 : 카테고리", "카테고리 미선택", "원하시는 카테고리를 반드시 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        } else {
            position = fxmlPOSITION.getSelectionModel().getSelectedItem();
            System.out.println("선택된 항목");
            System.out.println(position);
        }


        this.bean = new League() ;

        bean.setNAME(name);
        bean.setPOSITION( Utility.getCategoryName(position, "value"));
        // 사용자가 입력한 key인 한글 카테고리 이름을 value인 영문으로 변환시켜 셋팅합니다.
        bean.setCHAMPEXPLANATION(champExplanation);
        bean.setPIMAGE(pImage);
        bean.setPNAME(pName);
        bean.setQIMAGE(qImage);
        bean.setQNAME(qName);
        bean.setWIMAGE(wImage);
        bean.setWNAME(wName);
        bean.setEIMAGE(eImage);
        bean.setENAME(eName);
        bean.setRIMAGE(rImage);
        bean.setRNAME(rName);
        bean.setSKIN(skinImage);
        bean.setILLUSTRATION(illustImage);
        return true ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dao = new LeageDao();

        // 최초 시작시 콤보 박스의 0번째 항목 선택하기
        fxmlPOSITION.getSelectionModel().select(0);
    }
}
