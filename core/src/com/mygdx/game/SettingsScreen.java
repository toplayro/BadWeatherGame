package com.mygdx.game;

import static com.mygdx.game.GameScreen.SCR_HEIGHT;
import static com.mygdx.game.GameScreen.SCR_WIDTH;
import static com.mygdx.game.MainMenuScreen.mscFon;
import static com.mygdx.game.MainMenuScreen.version;
import static com.mygdx.game.ShopScreen.isBuyedMug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen implements Screen {

    MainRain game;

    Texture bgSettings;

    TextButton btnExit;
    TextButton btnSound, btnMusic;
    TextButton btnLangEng, btnLangRus;
    TextButton btnEnterPromo;

    String promo="";
    String promoFriends = "yy6Ho";

    public static boolean isSoundOn = true;
    public static boolean isMusicOn = true;
    public static boolean isLangRus = true;
    public static boolean isLangEng = false;
    public static boolean isPromoFUsed = false;

    public SettingsScreen (MainRain sett) {
        game = sett;

        bgSettings = new Texture("fonSettings.png");

        btnExit = new TextButton(game.font, "X", SCR_WIDTH-70, SCR_HEIGHT-22);
        btnSound = new TextButton(game.font, "Убрать звуки  ", 22, SCR_HEIGHT-70);
        btnMusic = new TextButton(game.font, "Убрать музыку", 22, SCR_HEIGHT-130);
        btnLangEng = new TextButton(game.font, "English", 22, SCR_HEIGHT-290);
        btnLangRus = new TextButton(game.font, "Русский", 22, SCR_HEIGHT-350);
        btnEnterPromo = new TextButton(game.font, "Введите промокод", 22, SCR_HEIGHT-440);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.saveMusic();
        game.saveSound();
        game.saveLangRus();
        game.saveLangEng();
        game.saveMenuMoney();
        game.savePromoFUsed();
        game.saveBuyingMug();

        game.batch.begin();

        game.batch.draw(bgSettings, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        game.font.draw(game.batch, "X", SCR_WIDTH-70, SCR_HEIGHT-22);

        if(isLangRus == true) {
            if (isSoundOn == true){
                game.font.draw(game.batch, "Убрать звуки", 22, SCR_HEIGHT-70);
            } else if (isSoundOn == false){
                game.font.draw(game.batch, "Включить звуки", 22, SCR_HEIGHT-70);
            }
            if (isMusicOn == true){
                game.font.draw(game.batch, "Убрать музыку", 22, SCR_HEIGHT-130);
            } else if (isMusicOn == false){
                game.font.draw(game.batch, "Включить музыку", 22, SCR_HEIGHT-130);
            }
            game.font.draw(game.batch, "Ввести промокод", 22, SCR_HEIGHT-440);

            game.font.draw(game.batch, "Выберите язык:", 22, SCR_HEIGHT-210);
            game.font.draw(game.batch, "English", 22, SCR_HEIGHT-290);
            game.font.draw(game.batch, "Русский", 22, SCR_HEIGHT-350);
            game.fontSettings.draw(game.batch, "Информация:", SCR_WIDTH/2f-60, SCR_HEIGHT-70);
            game.fontSettings.draw(game.batch, "Игру сделал Бекенёв Данил", SCR_WIDTH/2f-60, SCR_HEIGHT-130);
            game.fontSettings.draw(game.batch, "E-mail: tockarostak@gmail.com", SCR_WIDTH/2f-60, SCR_HEIGHT-190);
            game.fontSettings.draw(game.batch, "Соцсети:", SCR_WIDTH/2f-60, SCR_HEIGHT-250);
            game.fontSettings.draw(game.batch, "VK:", SCR_WIDTH/2f-60, SCR_HEIGHT-310);
            game.fontSettings.draw(game.batch, "telegramm: toplayro", SCR_WIDTH/2f-60, SCR_HEIGHT-370);
            game.fontSettings.draw(game.batch, "YouTube: toplayro", SCR_WIDTH/2f-60, SCR_HEIGHT-430);
            game.fontSettings.draw(game.batch, "Омск, 2023", SCR_WIDTH/2f-60, SCR_HEIGHT-490);
            game.fontVersion.draw(game.batch, version, 22, 40);
        } else if (isLangEng == true) {
            if (isSoundOn == true){
                game.font.draw(game.batch, "Remove sounds", 22, SCR_HEIGHT-70);
            } else if (isSoundOn == false){
                game.font.draw(game.batch, "Enable sounds", 22, SCR_HEIGHT-70);
            }
            if (isMusicOn == true){
                game.font.draw(game.batch, "Remove music", 22, SCR_HEIGHT-130);
            } else if (isMusicOn == false){
                game.font.draw(game.batch, "Enable music", 22, SCR_HEIGHT-130);
            }
            game.font.draw(game.batch, "Enter promo", 22, SCR_HEIGHT-440);

            game.font.draw(game.batch, "Choose language:", 22, SCR_HEIGHT-210);
            game.font.draw(game.batch, "English", 22, SCR_HEIGHT-290);
            game.font.draw(game.batch, "Русский", 22, SCR_HEIGHT-350);
            game.fontSettings.draw(game.batch, "Information:", SCR_WIDTH/2f-60, SCR_HEIGHT-70);
            game.fontSettings.draw(game.batch, "Game made by Becenev Danil", SCR_WIDTH/2f-60, SCR_HEIGHT-130);
            game.fontSettings.draw(game.batch, "E-mail: tockarostak@gmail.com", SCR_WIDTH/2f-60, SCR_HEIGHT-190);
            game.fontSettings.draw(game.batch, "Social network:", SCR_WIDTH/2f-60, SCR_HEIGHT-250);
            game.fontSettings.draw(game.batch, "VK: toplayro", SCR_WIDTH/2f-60, SCR_HEIGHT-310);
            game.fontSettings.draw(game.batch, "telegramm: toplayro", SCR_WIDTH/2f-60, SCR_HEIGHT-370);
            game.fontSettings.draw(game.batch, "YouTube: toplayro", SCR_WIDTH/2f-60, SCR_HEIGHT-430);
            game.fontSettings.draw(game.batch, "Omsk, 2023", SCR_WIDTH/2f-60, SCR_HEIGHT-490);
            game.fontVersion.draw(game.batch, version, 22, 40);
        }


        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            if(btnExit.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.menuScreen);
            }
        }

        if (Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            if(btnSound.hit(game.touch.x, game.touch.y)) {
                isSoundOn=!isSoundOn;
            }
            if(btnMusic.hit(game.touch.x, game.touch.y)) {
                isMusicOn=!isMusicOn;
                if(isMusicOn == true){
                    mscFon.play();
                } else if (isMusicOn == false) {
                    mscFon.stop();
                }
            }
            if(btnLangRus.hit(game.touch.x, game.touch.y)) {
                isLangRus = true;
                isLangEng = false;
            }
            if(btnLangEng.hit(game.touch.x, game.touch.y)) {
                isLangRus = false;
                isLangEng = true;
            }
            if (btnEnterPromo.hit(game.touch.x, game.touch.y)){
                Gdx.input.getTextInput(new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        promo = text;
                    }


                    @Override
                    public void canceled() {
                    }

                }, "Введите промокод:", promo, "PROMO" );
            }
            if(isPromoFUsed == false){
                if(promo.equals(promoFriends)) {
//                    game.menuMoney+=200;
//                    isPromoFUsed = true;
//                    if(game.menuMoney >= 200) {
//                        promo = "";
//                    }

                    isPromoFUsed = true;
                    if(isBuyedMug == false) {
                        promo = "";
                        isBuyedMug = true;
                    } else if (isBuyedMug == true){
                        promo = "";
                    }
                }
            }
        }
    }



    @Override
    public void resize(int width, int height) {

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
    }

    @Override
    public void show() {
    }
}

