package com.cookandroid.volunteer;

public class Dictionary {
    private int ID;
    private String id;
    private String English;
    private String Korean;

    public int getID() {
        return ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getKorean() {
        return Korean;
    }

    public void setKorean(String korean) {
        Korean = korean;
    }

    public Dictionary(int ID, String id, String english, String korean) {
        this.ID = ID;
        this.id = id;
        English = english;
        Korean = korean;
    }
}
