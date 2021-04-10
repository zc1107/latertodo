package com.example.latertodo;

public class FavouriteList {
    private String nameOfFavourite;
    private String numOfFavourite;

    public FavouriteList(){
        nameOfFavourite = "收藏的声音";
        numOfFavourite = "15";
    }

    public FavouriteList(String name,String number){
        this.nameOfFavourite = name;
        this.numOfFavourite = number;
    }

    public String getNameOfFavourite(){
        return nameOfFavourite;
    }

    public String getNumberOfFavourite(){
        return numOfFavourite;
    }
}
