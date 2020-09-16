package com.cookandroid.volunteer;

public class SearchData {

    String Title = null;

    String addr1 = null;

    String addr2 = null;

    String category1 = null;

    String category2 = null;

    String sDate = null;

    String eDate = null;

    String target = null;

    boolean isEnd = false;

    boolean adultPossib = false;

    boolean youngPossib = false;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isAdultPossib() {
        return adultPossib;
    }

    public void setAdultPossib(boolean adultPossib) {
        this.adultPossib = adultPossib;
    }

    public boolean isYoungPossib() {
        return youngPossib;
    }

    public void setYoungPossib(boolean youngPossib) {
        this.youngPossib = youngPossib;
    }
}
