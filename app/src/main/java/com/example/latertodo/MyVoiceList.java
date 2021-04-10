package com.example.latertodo;

public class MyVoiceList {
    private String nameOfMyVoice;
    private String numOfMyVoice;

    public MyVoiceList(){
        nameOfMyVoice = "我的海螺";
        numOfMyVoice = "520";
    }

    public MyVoiceList(String name,String number){
        this.nameOfMyVoice = name;
        this.numOfMyVoice = number;
    }

    public String getNameOfFavourite(){
        return nameOfMyVoice;
    }

    public String getNumberOfFavourite(){
        return numOfMyVoice;
    }
}
