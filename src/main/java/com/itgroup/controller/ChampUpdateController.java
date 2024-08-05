
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

public class ChampUpdateController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("수정 할꺼야");
    }

    @FXML private TextField fxmlIDX;
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

    private League oldBean = null; // 수정될 행의 정보
    private League newBean = null; // 수정될 행의 정보

    public void setBean(League bean) {
        this.oldBean = bean;
        System.out.println(bean);
        fillPreviousData();

        // 데이터 베이스의 primary key에 해당하는 상품 번호를 숨겨줍니다.
        fxmlIDX.setVisible(false);
    }

    private void fillPreviousData() {
        // 과거에 내가 등록했던 정보들을 해당 컨트롤에 다시 입력해 줍니다
        fxmlIDX.setText(String.valueOf(this.oldBean.getIDX()));
        fxmlNAME.setText(this.oldBean.getNAME());
        fxmlPIMAGE.setText(this.oldBean.getPIMAGE());
        fxmlPNAME.setText(this.oldBean.getPNAME());
        fxmlQIMAGE.setText(this.oldBean.getQIMAGE());
        fxmlQNAME.setText(this.oldBean.getQNAME());
        fxmlWIMAGE.setText(this.oldBean.getWIMAGE());
        fxmlWNAME.setText(this.oldBean.getWNAME());
        fxmlEIMAGE.setText(this.oldBean.getEIMAGE());
        fxmlENAME.setText(this.oldBean.getENAME());
        fxmlRIMAGE.setText(this.oldBean.getRIMAGE());
        fxmlRNAME.setText(this.oldBean.getRNAME());
        fxmlSKIN.setText(this.oldBean.getSKIN());
        fxmlCHAMPEXPLANATION.setText(this.oldBean.getCHAMPEXPLANATION());
        fxmlILLUSTRATION.setText(this.oldBean.getILLUSTRATION());

        // DB에서 읽어온 영문 카테고리 이름을 한글로 변경해 줍니다.
        String position = this.oldBean.getPOSITION(); // 영문으로 되어 있습니다.
        fxmlPOSITION.setValue(Utility.getCategoryName(position, "key"));
    }

    public void onChampUpdate(ActionEvent event) {
        // 먼저, 유효성 검사를 진행합니다.

        boolean bool = validationCheck();

        // 사용자가 변경한 내역을 데이터베이스에 업데이트 시킵니다.
        if (bool == true) {
            LeageDao dao = new LeageDao();
            int cnt = -1; // -1이면 실패
            cnt = dao.updateData(this.newBean);

            if (cnt == -1) {
                System.out.println("수정 실패");

            } else { // 수정이 되었으므로 창을 닫습니다.
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } else {
            System.out.println("유효성 검사를 통과하지 못했습니다.");
        }

    }

    private boolean validationCheck() {
        // 유효성 검사를 통과하면 true가 됩니다.

        // 수정을 위한 핵심 키(primary key)
        System.out.println("===========================");
        System.out.println(fxmlIDX.getText().trim());
        System.out.println("===========================");
        int idx = Integer.valueOf(fxmlIDX.getText().trim());


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
        this.newBean = new League();

        newBean.setIDX(idx);
        newBean.setNAME(name);
        newBean.setPOSITION(Utility.getCategoryName(position, "value"));
        // 사용자가 입력한 key인 한글 카테고리 이름을 영문으로 변환시켜 셋팅합니다.
        newBean.setCHAMPEXPLANATION(champExplanation);
        newBean.setPIMAGE(pImage);
        newBean.setPNAME(pName);
        newBean.setQIMAGE(qImage);
        newBean.setQNAME(qName);
        newBean.setWIMAGE(wImage);
        newBean.setWNAME(wName);
        newBean.setEIMAGE(eImage);
        newBean.setENAME(eName);
        newBean.setRIMAGE(rImage);
        newBean.setRNAME(rName);
        newBean.setSKIN(skinImage);
        newBean.setILLUSTRATION(illustImage);

        return true;
    }
}
