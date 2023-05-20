package com.mygdx.game;

import static com.mygdx.game.GameScreen.*;
import static com.mygdx.game.SettingsScreen.isLangEng;
import static com.mygdx.game.SettingsScreen.isLangRus;
import static com.mygdx.game.SettingsScreen.isMusicOn;
import static com.mygdx.game.SettingsScreen.isSoundOn;
import static com.mygdx.game.SettingsScreen.isPromoFUsed;
import static com.mygdx.game.ShopScreen.isChoosedBase;
import static com.mygdx.game.ShopScreen.isChoosedMug;
import static com.mygdx.game.ShopScreen.isChoosedTrash;
import static com.mygdx.game.ShopScreen.isBuyedMug;
import static com.mygdx.game.ShopScreen.isBuyedTrash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    MainRain game;

    OrthographicCamera camera;

    Texture bgMenu;

    static Music mscFon;

    static String version = "BETA v0.2.4";

    TextButton btnPlay, btnSettings, btnExit, btnRecords, btnShop;
    TextButton btnMission;

    static boolean isMissionOneComplite = false;
    static boolean isMissionTwoComplite = false;
    static boolean isMissionOneMoneyPut = false;
    static boolean isMissionTwoMoneyPut = false;


    public MainMenuScreen(MainRain context) {
        game = context;
        bgMenu = new Texture("fonmenubeta.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        btnPlay = new TextButton(game.font, "ИГРАТЬ", 100, 480);
        btnSettings = new TextButton(game.font, "ДРУГОЕ", 100, 240);
        btnShop = new TextButton(game.font, "МАГАЗИН", 100, 360);
        btnExit = new TextButton(game.font, "ВЫЙТИ", 100, 120);
        btnMission = new TextButton(game.font, "ЗАБРАТЬ ХХХХХХХХХ МОНЕТ", 640, SCR_HEIGHT-356);

        mscFon = Gdx.audio.newMusic(Gdx.files.internal("fonMusic1.mp3"));

        mscFon.setLooping(true);

        loadLastScore();
        loadScoreFTR();
        loadMenuMoney();
        loadBuyingMug();
        loadBuyingTrash();
        loadChoosedMug();
        loadChoosedTrash();
        loadChoosedBase();
        loadMusic();
        loadSound();
        loadLangRus();
        loadLangEng();
        loadPromoFUsed();
        loadMission1();
        loadMission2();
        loadMission1Money();
        loadMission2Money();
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bgMenu, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        if(isLangRus == true) {
            game.font.draw(game.batch, "ИГРАТЬ", 100, 480);
            game.font.draw(game.batch, "МАГАЗИН", 100, 360);
            game.font.draw(game.batch, "ДРУГОЕ", 100, 240);
            game.font.draw(game.batch, "BAD WEATHER GAME", 100, 640);
            game.fontVersion.draw(game.batch, version, 100, 580);
            game.font.draw(game.batch, "ВЫЙТИ", 100, 120);


            game.font.draw(game.batch, "Прошлый результат: " + game.lastScore, 640, 160);
            game.font.draw(game.batch, "Лучший результат: " + game.scoreFTR, 640, 90);
            game.font.draw(game.batch, "Ваши деньги: " + game.menuMoney, 640, SCR_HEIGHT-50);
            if(isMissionOneComplite == false || isMissionOneComplite == true && isMissionOneMoneyPut == false){
                game.font.draw(game.batch, "Миссия 1:", 640, SCR_HEIGHT-200);
                game.font.draw(game.batch, "Набрать 150 очков", 640, SCR_HEIGHT-278);
            } else if (isMissionOneMoneyPut == true && isMissionTwoMoneyPut == false && isMissionTwoComplite == false){
                game.font.draw(game.batch, "Миссия 2:", 640, SCR_HEIGHT-200);
                game.font.draw(game.batch, "Купить скин 'Свалка'", 640, SCR_HEIGHT-278);
            }
            if(isMissionOneComplite == true && isMissionOneMoneyPut == false){
                game.fontSettings.draw(game.batch, "Забрать 25 монет", 640, SCR_HEIGHT-356);
            } else if(isMissionTwoComplite == true && isMissionOneMoneyPut == true && isMissionTwoMoneyPut == false){
                game.fontSettings.draw(game.batch, "Забрать 45 монет", 640, SCR_HEIGHT-356);
            }
        } else if (isLangEng) {
            game.font.draw(game.batch, "PLAY", 100, 480);
            game.font.draw(game.batch, "SHOP", 100, 360);
            game.font.draw(game.batch, "OTHER", 100, 240);
            game.font.draw(game.batch, "BAD WEATHER GAME", 100, 640);
            game.fontVersion.draw(game.batch, version, 100, 580);
            game.font.draw(game.batch, "EXIT", 100, 120);


            game.font.draw(game.batch, "LAST SCORE: " + game.lastScore, 640, 160);
            game.font.draw(game.batch, "BEST SCORE: " + game.scoreFTR, 640, 90);
            game.font.draw(game.batch, "YOUR MONEY: " + game.menuMoney, 640, SCR_HEIGHT-50);
            if(isMissionOneComplite == false || isMissionOneComplite == true && isMissionOneMoneyPut == false){
                game.font.draw(game.batch, "Mission 1:", 640, SCR_HEIGHT-200);
                game.font.draw(game.batch, "Score 150 points", 640, SCR_HEIGHT-278);
            } else if (isMissionOneMoneyPut == true && isMissionTwoMoneyPut == false && isMissionTwoComplite == false){
                game.font.draw(game.batch, "Mission 2:", 640, SCR_HEIGHT-200);
                game.font.draw(game.batch, "Buy clothes 'junkyard'", 640, SCR_HEIGHT-278);
            }
            if(isMissionOneComplite == true && isMissionOneMoneyPut == false){
                game.fontSettings.draw(game.batch, "Collect 25 coins", 640, SCR_HEIGHT-356);
            } else if(isMissionTwoComplite == true && isMissionOneMoneyPut == true && isMissionTwoMoneyPut == false){
                game.fontSettings.draw(game.batch, "Collect 45 coins", 640, SCR_HEIGHT-356);
            }
        }

        game.batch.end();

        if(game.lastScore >= 150 || game.scoreFTR >= 150) {
            isMissionOneComplite = true;
            game.saveMission1();
        }
        if(isMissionOneComplite == true && isBuyedTrash == true) {
            isMissionTwoComplite = true;
            game.saveMission2();
        }

        game.menuMoney = game.menuMoney + game.money;

        if (Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            if(btnPlay.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.gameScreen);
                mscFon.stop();
            }
            if(btnSettings.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.settingsScreen);
            }
            if(btnShop.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.shopScreen);
            }
            if (btnExit.hit(game.touch.x, game.touch.y)) {
                Gdx.app.exit();
            }
            if (btnMission.hit(game.touch.x, game.touch.y)) {
                if(isMissionOneComplite == true && isMissionOneMoneyPut == false){
                    game.menuMoney = game.menuMoney+25;
                    isMissionOneMoneyPut = true;
                    game.saveMission1Money();
                    game.saveMenuMoney();
                } else if(isMissionTwoComplite == true && isMissionTwoMoneyPut == false){
                    game.menuMoney = game.menuMoney+45;
                    isMissionTwoMoneyPut = true;
                    game.saveMission2Money();
                    game.saveMenuMoney();
                }
            }
        }
    }



    void loadScoreFTR() {
        Preferences pref = Gdx.app.getPreferences("savedScoreFTR");
        if(pref.contains("savedScoreFTR")) game.scoreFTR = pref.getInteger("savedScoreFTR", 0);
    }

    void loadLastScore() {
        Preferences pref = Gdx.app.getPreferences("savedLastScore");
        if(pref.contains("savedLastScore")) game.lastScore = pref.getInteger("savedLastScore", 0);
    }

    void loadMenuMoney() {
        Preferences pref = Gdx.app.getPreferences("saveMoney");
        if(pref.contains("saveMoney")) game.menuMoney = pref.getInteger("saveMoney", 0);
    }

    void loadBuyingMug() {
        Preferences pref = Gdx.app.getPreferences("savedBuyedMug");
        if(pref.contains("savedBuyedMug")) isBuyedMug = pref.getBoolean("savedBuyedMug", false);
    }
    void loadChoosedMug() {
        Preferences pref = Gdx.app.getPreferences("savedChoosedMug");
        if(pref.contains("savedChoosedMug")) isChoosedMug = pref.getBoolean("savedChoosedMug", false);
    }

    void loadBuyingTrash() {
        Preferences pref = Gdx.app.getPreferences("savedBuyedTrash");
        if(pref.contains("savedBuyedTrash")) isBuyedTrash = pref.getBoolean("savedBuyedTrash", false);
    }

    void loadChoosedTrash() {
        Preferences pref = Gdx.app.getPreferences("savedChoosedTrash");
        if(pref.contains("savedChoosedTrash")) isChoosedTrash = pref.getBoolean("savedChoosedTrash", false);
    }

    void loadChoosedBase() {
        Preferences pref = Gdx.app.getPreferences("savedChoosedBase");
        if(pref.contains("savedChoosedBase")) isChoosedBase = pref.getBoolean("savedChoosedBase", true);
    }

    void loadMusic() {
        Preferences pref = Gdx.app.getPreferences("savedMusic");
        if(pref.contains("savedMusic")) isMusicOn = pref.getBoolean("savedMusic", true);
    }

    void loadSound() {
        Preferences pref = Gdx.app.getPreferences("savedSound");
        if(pref.contains("savedSound")) isSoundOn = pref.getBoolean("savedSound", true);
    }

    void loadLangRus() {
        Preferences pref = Gdx.app.getPreferences("savedLangRus");
        if(pref.contains("savedLangRus")) isLangRus = pref.getBoolean("savedLangRus", true);
    }

    void loadLangEng() {
        Preferences pref = Gdx.app.getPreferences("savedLangEng");
        if(pref.contains("savedLangEng")) isLangEng = pref.getBoolean("savedLangEng", false);
    }

    void loadPromoFUsed() {
        Preferences pref = Gdx.app.getPreferences("savePromoFUsed");
        if(pref.contains("savePromoFUsed")) isPromoFUsed = pref.getBoolean("savePromoFUsed", false);
    }

    void loadMission1() {
        Preferences pref = Gdx.app.getPreferences("saveMission1");
        if(pref.contains("saveMission1")) isMissionOneComplite = pref.getBoolean("saveMission1", false);
    }

    void loadMission2() {
        Preferences pref = Gdx.app.getPreferences("saveMission2");
        if(pref.contains("saveMission2")) isMissionTwoComplite = pref.getBoolean("saveMission2", false);
    }

    @Override
    public void resize(int width, int height) {

    }

    void loadMission1Money() {
        Preferences pref = Gdx.app.getPreferences("saveMission1Money");
        if(pref.contains("saveMission1Money")) isMissionOneMoneyPut = pref.getBoolean("saveMission1Money", false);
    }
    void loadMission2Money() {
        Preferences pref = Gdx.app.getPreferences("saveMission2Money");
        if(pref.contains("saveMission2Money")) isMissionTwoMoneyPut = pref.getBoolean("saveMission2Money", false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mscFon.dispose();
    }

    @Override
    public void show() {
        if(isMusicOn == true){
            mscFon.play();
        } else if (isMusicOn == false) {
            mscFon.stop();
        }
    }
}