package com.cookandroid.volunteer;

public class searchItem {

    String progrmRegistNo; //프로그램등록번호
    String progrmSj; //봉사제목
    String progrmBgnde; //봉사시작일
    String progrmEndde; //봉사종료일
    String actBeginTm; //봉사시작시간
    String actEndTm; //봉사종료시간
    String actPlace; //봉사장소
    String progrmLink;
    String actWkdy;


    public String getProgrmRegistNo() { return progrmRegistNo; }

    public String getProgrmSj() {
        return progrmSj;
    }

    public String getProgrmBgnde() {
        return progrmBgnde;
    }

    public String getProgrmEndde() {
        return progrmEndde;
    }

    public String getActBeginTm() {
        return actBeginTm;
    }

    public String getActEndTm() {
        return actEndTm;
    }

    public String getActPlace() {
        return actPlace;
    }

    public String getActWkdy() {
        return actWkdy;
    }

    public void setProgrmRegistNo(String progrmRegistNo) {
        this.progrmRegistNo = progrmRegistNo;
    }

    public void setProgrmSj(String progrmSj) {
        this.progrmSj = progrmSj;
    }

    public void setProgrmBgnde(String progrmBgnde) {
        this.progrmBgnde = progrmBgnde;
    }

    public void setProgrmEndde(String progrmEndde) {
        this.progrmEndde = progrmEndde;
    }

    public void setActBeginTm(String actBeginTm) {
        this.actBeginTm = actBeginTm;
    }

    public void setActEndTm(String actEndTm) {
        this.actEndTm = actEndTm;
    }

    public void setActPlace(String actPlace) {
        this.actPlace = actPlace;
    }

    public void setActWkdy(String actWkdy) {
        this.actWkdy = actWkdy;
    }

    public String getProgrmLink() {
        return progrmLink;
    }

    public void setProgrmLink(String url) {
        this.progrmLink = url;
    }
}
