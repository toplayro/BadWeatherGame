package com.mygdx.game;

import static com.mygdx.game.GameScreen.SCR_HEIGHT;
import static com.mygdx.game.GameScreen.SCR_WIDTH;
import static com.mygdx.game.SettingsScreen.isLangEng;
import static com.mygdx.game.SettingsScreen.isLangRus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ShopScreen implements Screen {

    MainRain game;

    Texture bgShop;
    Texture trashSkin, mugSkin, soon, base;
    Texture trashSkinNB, mugSkinNB;

    byte MugPrice = 50;
    int TrashPrice = 150;
    int yTextL1 = 78;
    int yTextL2 = 410;
    int yImgL1 = 100;
    int yImgL2 = 432;

    TextButton btnExit;
    TextButton btnBuyMug, btnBuyTrash;
    TextButton btnChooseBase, btnChooseMug, btnChooseTrash;
    public static boolean isBuyedMug, isBuyedTrash;
    public static boolean isChoosedMug = false, isChoosedTrash;
    public static boolean isChoosedBase = true;


    public ShopScreen (MainRain shop) {
        game = shop;

        bgShop = new Texture("fonShop.png");
        trashSkin = new Texture("shopTrashBeta.png");
        mugSkin = new Texture("shopMugBeta.png");
        trashSkinNB = new Texture("shopTrashBeta.png");
        mugSkinNB = new Texture("shopMugBeta.png");
        soon = new Texture("shopSoonBeta.png");
        base = new Texture("shopBaseBeta.png");

        btnExit = new TextButton(game.font, "X", SCR_WIDTH-70, SCR_HEIGHT-22);

        btnBuyMug = new TextButton(game.font, "КУПИТЬ", 326, yTextL1);
        btnChooseMug = new TextButton(game.font, "ВЫБРАТЬ", 326, yTextL1);
        btnBuyTrash = new TextButton(game.font, "КУПИТЬ", 612, yTextL1);
        btnChooseTrash = new TextButton(game.font, "ВЫБРАТЬ", 612, yTextL1);
        btnChooseBase = new TextButton(game.font, "ВЫБРАТЬ", 40, yTextL1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.saveMenuMoney();

        ScreenUtils.clear(1, 0, 0, 1);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.saveBuyingMug();
        game.saveBuyingTrash();
        game.saveChoosedMug();
        game.saveChoosedTrash();
        game.saveChoosedBase();

        game.batch.begin();

        game.batch.draw(bgShop, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        if(isBuyedMug == true){
            game.batch.draw(mugSkin, 307, yImgL1, 256, 256);
        } else if (isBuyedMug == false) {
            game.batch.draw(mugSkinNB, 307, yImgL1, 256, 256);
        }
        if(isBuyedTrash == true){
            game.batch.draw(trashSkin, 593, yImgL1, 256, 256);
        } else if (isBuyedTrash == false) {
            game.batch.draw(trashSkinNB, 593, yImgL1, 256, 256);
        }
        game.batch.draw(soon, 879, yImgL1, 256, 256);
        game.batch.draw(soon, 879, yImgL2, 256, 256);
        game.batch.draw(soon, 593, yImgL2, 256, 256);
        game.batch.draw(soon, 307, yImgL2, 256, 256);
        game.batch.draw(soon, 22, yImgL2, 256, 256);
        game.batch.draw(base, 22, yImgL1, 256, 256);

        game.font.draw(game.batch, "X", SCR_WIDTH-70, SCR_HEIGHT-22);

        if(isLangRus == true) {
            if (isChoosedBase == false) {
                game.font.draw(game.batch, "ВЫБРАТЬ", 40, yTextL1);
            } else if (isChoosedBase == true) {
                game.font.draw(game.batch, "ВЫБРАНО", 40, yTextL1);
            }

            if (isBuyedMug == false) {
                game.font.draw(game.batch, "КУПИТЬ", 326, yTextL1);
            } else if (isBuyedMug == true && isChoosedMug == false) {
                game.font.draw(game.batch, "ВЫБРАТЬ", 326, yTextL1);
            } else if (isBuyedMug == true && isChoosedMug == true) {
                game.font.draw(game.batch, "ВЫБРАНО", 326, yTextL1);
            }

            if (isBuyedTrash == false) {
                game.font.draw(game.batch, "КУПИТЬ", 612, yTextL1);
            } else if (isBuyedTrash == true && isChoosedTrash == false) {
                game.font.draw(game.batch, "ВЫБРАТЬ", 612, yTextL1);
            } else if (isBuyedTrash == true && isChoosedTrash == true) {
                game.font.draw(game.batch, "ВЫБРАНО", 612, yTextL1);
            }

            game.font.draw(game.batch, "ВАШИ ДЕНЬГИ: " + game.menuMoney, 22, SCR_HEIGHT - 22);

            game.font.draw(game.batch, "СКОРО", 898, yTextL1);
            game.font.draw(game.batch, "СКОРО", 898, yTextL2);
            game.font.draw(game.batch, "СКОРО", 612, yTextL2);
            game.font.draw(game.batch, "СКОРО", 326, yTextL2);
            game.font.draw(game.batch, "СКОРО", 40, yTextL2);
        } else if (isLangEng == true) {
            if (isChoosedBase == false) {
                game.font.draw(game.batch, "CHOOSE", 40, yTextL1);
            } else if (isChoosedBase == true) {
                game.font.draw(game.batch, "CHOOSED", 40, yTextL1);
            }

            if (isBuyedMug == false) {
                game.font.draw(game.batch, "BUY", 326, yTextL1);
            } else if (isBuyedMug == true && isChoosedMug == false) {
                game.font.draw(game.batch, "CHOOSE", 326, yTextL1);
            } else if (isBuyedMug == true && isChoosedMug == true) {
                game.font.draw(game.batch, "CHOOSED", 326, yTextL1);
            }

            if (isBuyedTrash == false) {
                game.font.draw(game.batch, "BUY", 612, yTextL1);
            } else if (isBuyedTrash == true && isChoosedTrash == false) {
                game.font.draw(game.batch, "CHOOSE", 612, yTextL1);
            } else if (isBuyedTrash == true && isChoosedTrash == true) {
                game.font.draw(game.batch, "CHOOSED", 612, yTextL1);
            }

            game.font.draw(game.batch, "YOUR MONEY: " + game.menuMoney, 22, SCR_HEIGHT - 22);

            game.font.draw(game.batch, "SOON", 898, yTextL1);
            game.font.draw(game.batch, "SOON", 898, yTextL2);
            game.font.draw(game.batch, "SOON", 612, yTextL2);
            game.font.draw(game.batch, "SOON", 326, yTextL2);
            game.font.draw(game.batch, "SOON", 40, yTextL2);
        }
        game.batch.end();


        if (isChoosedTrash == true) {
            isChoosedBase = false;
            isChoosedMug = false;
        }

        if (Gdx.input.isTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            if(btnExit.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.menuScreen);
            }

            if(btnChooseMug.hit(game.touch.x, game.touch.y) && isBuyedMug == true) {
                isChoosedMug = true;
                isChoosedBase = false;
                isChoosedTrash = false;
            }
            if(btnChooseTrash.hit(game.touch.x, game.touch.y) && isBuyedTrash == true) {
                isChoosedTrash = true;
                isChoosedBase = false;
                isChoosedMug = false;
            }
            if(btnChooseBase.hit(game.touch.x, game.touch.y)) {
                isChoosedBase = true;
                isChoosedMug = false;
                isChoosedTrash = false;
            }
        }

        if (Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            if(isBuyedMug == false){
                if(btnBuyMug.hit(game.touch.x, game.touch.y) && game.menuMoney >= 50) {
                    isBuyedMug = true;
                    game.menuMoney = game.menuMoney-MugPrice;
                }
            }
            if(isBuyedTrash == false){
                if(btnBuyTrash.hit(game.touch.x, game.touch.y) && game.menuMoney >= 150) {
                    isBuyedTrash = true;
                    game.menuMoney = game.menuMoney-TrashPrice;
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
}

