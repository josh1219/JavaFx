package com.itgroup.bean;

// 상품1개를 의미하는 자바 Bean 클래스
public class League {
    private int IDX;
    private String NAME;
    private String POSITION;
    private String PIMAGE;
    private String PNAME;
    private String QIMAGE;
    private String QNAME;
    private String WIMAGE;
    private String WNAME;
    private String EIMAGE;
    private String ENAME;
    private String RIMAGE;
    private String RNAME;
    private String SKIN;
    private String CHAMPEXPLANATION;
    private String ILLUSTRATION;

    //6) getter,setter,toString만들기

    public League(){

    }

    public int getIDX() {
        return IDX;
    }

    public void setIDX(int IDX) {
        this.IDX = IDX;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPOSITION() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }

    public String getPIMAGE() {
        return PIMAGE;
    }

    public void setPIMAGE(String PIMAGE) {
        this.PIMAGE = PIMAGE;
    }

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getQIMAGE() {
        return QIMAGE;
    }

    public void setQIMAGE(String QIMAGE) {
        this.QIMAGE = QIMAGE;
    }

    public String getQNAME() {
        return QNAME;
    }

    public void setQNAME(String QNAME) {
        this.QNAME = QNAME;
    }

    public String getWIMAGE() {
        return WIMAGE;
    }

    public void setWIMAGE(String WIMAGE) {
        this.WIMAGE = WIMAGE;
    }

    public String getWNAME() {
        return WNAME;
    }

    public void setWNAME(String WNAME) {
        this.WNAME = WNAME;
    }

    public String getEIMAGE() {
        return EIMAGE;
    }

    public void setEIMAGE(String EIMAGE) {
        this.EIMAGE = EIMAGE;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getRIMAGE() {
        return RIMAGE;
    }

    public void setRIMAGE(String RIMAGE) {
        this.RIMAGE = RIMAGE;
    }

    public String getRNAME() {
        return RNAME;
    }

    public void setRNAME(String RNAME) {
        this.RNAME = RNAME;
    }

    public String getSKIN() {
        return SKIN;
    }

    public void setSKIN(String SKIN) {
        this.SKIN = SKIN;
    }

    public String getCHAMPEXPLANATION() {
        return CHAMPEXPLANATION;
    }

    public void setCHAMPEXPLANATION(String CHAMPEXPLANATION) {
        this.CHAMPEXPLANATION = CHAMPEXPLANATION;
    }

    public String getILLUSTRATION() {
        return ILLUSTRATION;
    }

    public void setILLUSTRATION(String ILLUSTRATION) {
        this.ILLUSTRATION = ILLUSTRATION;
    }

    @Override
    public String toString() {
        return "Leage{" +
                "IDX=" + IDX +
                ", NAME='" + NAME + '\'' +
                ", POSITION='" + POSITION + '\'' +
                ", PIMAGE='" + PIMAGE + '\'' +
                ", PNAME='" + PNAME + '\'' +
                ", QIMAGE='" + QIMAGE + '\'' +
                ", QNAME='" + QNAME + '\'' +
                ", WIMAGE='" + WIMAGE + '\'' +
                ", WNAME='" + WNAME + '\'' +
                ", EIMAGE='" + EIMAGE + '\'' +
                ", ENAME='" + ENAME + '\'' +
                ", RIMAGE='" + RIMAGE + '\'' +
                ", RNAME='" + RNAME + '\'' +
                ", SKIN='" + SKIN + '\'' +
                ", CHAMPEXPLANATION='" + CHAMPEXPLANATION + '\'' +
                ", ILLUSTRATION='" + ILLUSTRATION + '\'' +
                '}';
    }
}
