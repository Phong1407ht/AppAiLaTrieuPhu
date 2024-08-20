package com.example.appailatrieuphu;

import android.media.MediaPlayer;

public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer playerBG;
    private MediaPlayer playerGame;
    private Boolean isPauseGame;
    private Boolean isPauseBG;
    private MediaManager() {
    }

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }
    public void playBG(int song){
        if(playerBG != null){
            playerBG.reset();
        }
        playerBG = MediaPlayer.create(App.getInstance(),song);
        playerBG.setLooping(true);
        playerBG.start();
    }

    public void playGame(int song,MediaPlayer.OnCompletionListener event){
        if(playerGame != null){
            playerGame.reset();
        }
        playerGame = MediaPlayer.create(App.getInstance(),song);
        playerGame.setOnCompletionListener(event);
        playerGame.start();
    }
    public void playSong(){
        if(playerBG != null && isPauseBG){
            isPauseBG = false;
            playerBG.start();
        }
        if(playerGame != null && isPauseGame){
            isPauseGame = false;
            playerGame.start();
        }
    }
    public void pauseSong(){
        if(playerBG != null && playerBG.isPlaying()){
            playerBG.pause();
            isPauseBG = true;
        }
        if(playerGame != null && playerGame.isPlaying()){
            playerGame.pause();
            isPauseGame = true;
        }
    }

    public void stopBG(){
        if(playerBG != null){
            playerBG.reset();
        }
    }
    public void stopPlayGame(){
        if(playerGame != null){
            playerGame.reset();
        }
    }
}
