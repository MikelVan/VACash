package com.vacash.android.models;

public class Game {

    private Integer gameLogo;
    private String gameTitle, gameDeveloper;

    public Game(Integer image, String gameTitle, String gameDeveloper) {
        this.gameLogo = image;
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
    }

    public Integer getGameLogo() {
        return gameLogo;
    }

    public void setGameLogo(Integer gameLogo) {
        this.gameLogo = gameLogo;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }
}
