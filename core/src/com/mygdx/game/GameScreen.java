package com.mygdx.game;

import static com.mygdx.game.SettingsScreen.isLangEng;
import static com.mygdx.game.SettingsScreen.isLangRus;
import static com.mygdx.game.SettingsScreen.isMusicOn;
import static com.mygdx.game.SettingsScreen.isSoundOn;
import static com.mygdx.game.ShopScreen.isChoosedBase;
import static com.mygdx.game.ShopScreen.isChoosedMug;
import static com.mygdx.game.ShopScreen.isChoosedTrash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.math.Rectangle;
import java.util.Iterator;

public class GameScreen implements Screen {
    public static final int SCR_WIDTH = 1200, SCR_HEIGHT = 720;

    MainRain game;

    SpriteBatch batch;
    Rectangle bucket;

    Texture imgVedr;
    Texture imgMug;
    Texture imgTrash;
    Texture imgKapl;
    Texture imgMusor;
    Texture imgDeathKapl;
    Texture imgMoney;
    Texture imgHealthKapl;
    Texture imgHeart;
    Texture imgNoHeart;
    Texture imgBgBase;
    Texture imgBgMug;
    Texture imgBgTrash;

    Texture[] imgRainGif = new Texture[3];

    TextButton btnExit;

    Sound sndDropkapl;
    Sound sndDropTrash;
    Sound sndDropMug;
    Sound sndCmert;
    Sound sndYad;
    Sound sndHPPlus;
    Sound sndMoney;
    Music mscDozjd;
    Music mscGrom;
    Music mscSvalka;
    Music mscHandShoot;

    Array<Rectangle> raindrops;
    Array<Rectangle> deathRaindrops;
    Array<Rectangle> moneyRaindrops;
    Array<Rectangle> healthRaindrops;

    boolean gamestop = false;

    long lastDropTime;
    long lastDropDKTime;
    long lastDropMKTime;
    long lastDropHKTime;

    int score;
    int lifeN=3;

    int time = 0;

    int tt = 0;

    int vremyaDPoyavleniyaS1 = MathUtils.random(30000, 60000);

    int vremyaMPoyavleniyaS1 = MathUtils.random(2000, 15000);
    int vremyaMPoyavleniyaS2 = MathUtils.random(1000, 10000);
    int vremyaHPoyavleniyaS1 = MathUtils.random(30000, 50000);


    int vremyaPoyavleniyaS1 = 1300000000;
    int vremyaPoyavleniyaS2 = 1200000000;
    int vremyaPoyavleniyaS3 = 600000000;
    int vremyaPoyavleniyaS4 = 350000000;
    int vremyaPoyavleniyaS5 = 300000000;
    int vremyaPoyavleniyaS6 = 250000000;
    int vremyaPoyavleniyaS7 = 200000000;

    int radrSpeedDS1=400;
    int radrSpeedMS1=400;
    int radrSpeedHS1=400;

    int radrSpeedS1=300;
    int radrSpeedS2=300;
    int radrSpeedS3=400;
    int radrSpeedS4=800;
    int radrSpeedS5=900;
    int radrSpeedS6=1200;

    public GameScreen (final MainRain gam) {
        this.game = gam;

        batch = new SpriteBatch();

        imgKapl = new Texture("kaplya.png");
        imgMusor = new Texture("musor.png");
        imgDeathKapl = new Texture("bomba.png");
        imgMoney = new Texture("money.png");
        imgHealthKapl = new Texture("healthKaplya.png");
        for (int i = 0; i < imgRainGif.length; i++) {
            imgRainGif[i] = new Texture("gifRain-"+i+".png");
        }

        imgVedr = new Texture("vedro.png");

        imgMug = new Texture("mug.png");

        imgTrash = new Texture("trash.png");


        imgBgBase = new Texture("fon1.jpg");
        imgBgMug = new Texture("fon3.png");
        imgBgTrash = new Texture("fonTrash.png");

        imgHeart = new Texture("heart.png");
        imgNoHeart = new Texture("noHeart.png");

        sndDropkapl = Gdx.audio.newSound(Gdx.files.internal("kaplya.mp3"));
        sndDropTrash = Gdx.audio.newSound(Gdx.files.internal("dropTrash.mp3"));
        sndDropMug = Gdx.audio.newSound(Gdx.files.internal("dropInMug.mp3"));
        sndCmert = Gdx.audio.newSound(Gdx.files.internal("minusserdce.mp3"));
        sndYad = Gdx.audio.newSound(Gdx.files.internal("yad.mp3"));
        sndMoney = Gdx.audio.newSound(Gdx.files.internal("monetka.mp3"));
        sndHPPlus = Gdx.audio.newSound(Gdx.files.internal("iscelenie.mp3"));
        mscDozjd = Gdx.audio.newMusic(Gdx.files.internal("dozjd.mp3"));
        mscGrom = Gdx.audio.newMusic(Gdx.files.internal("grom.mp3"));
        mscSvalka = Gdx.audio.newMusic(Gdx.files.internal("svalka.mp3"));
        mscHandShoot = Gdx.audio.newMusic(Gdx.files.internal("handShoot.mp3"));


        mscDozjd.setLooping(true);


        bucket = new Rectangle();
        bucket.x = SCR_WIDTH/2f-128/2f;
        bucket.y = 60;
        bucket.width = 128;
        bucket.height = 96;

        raindrops = new Array<Rectangle>();
        deathRaindrops = new Array<Rectangle>();
        moneyRaindrops = new Array<Rectangle>();
        healthRaindrops = new Array<Rectangle>();

        btnExit = new TextButton(game.font, "ВЫЙТИ", SCR_WIDTH/2f-40, SCR_HEIGHT-20);

    }

    private void spawnRainDrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, SCR_WIDTH-128);
        raindrop.y = SCR_HEIGHT;
        raindrop.width = 76;
        raindrop.height = 8;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnDeathRainDrop() {
        Rectangle deathRaindrop = new Rectangle();
        deathRaindrop.x = MathUtils.random(0, SCR_WIDTH-128);
        deathRaindrop.y = SCR_HEIGHT;
        deathRaindrop.width = 64;
        deathRaindrop.height = 8;
        deathRaindrops.add(deathRaindrop);
        lastDropDKTime = TimeUtils.millis();
    }

    private void spawnMoneyRainDrop() {
        Rectangle moneyRaindrop = new Rectangle();
        moneyRaindrop.x = MathUtils.random(0, SCR_WIDTH-128);
        moneyRaindrop.y = SCR_HEIGHT;
        moneyRaindrop.width = 76;
        moneyRaindrop.height = 8;
        moneyRaindrops.add(moneyRaindrop);
        lastDropMKTime = TimeUtils.millis();
    }

    private void spawnHealthRainDrop() {
        Rectangle healthRaindrop = new Rectangle();
        healthRaindrop.x = MathUtils.random(0, SCR_WIDTH-128);
        healthRaindrop.y = SCR_HEIGHT;
        healthRaindrop.width = 76;
        healthRaindrop.height = 8;
        healthRaindrops.add(healthRaindrop);
        lastDropHKTime = TimeUtils.millis();
    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        if(isChoosedBase == true){
            game.batch.draw(imgBgBase, 0, 0, SCR_WIDTH, SCR_HEIGHT); // ФОН
        } else if (isChoosedTrash == true) {
            game.batch.draw(imgBgTrash, 0, 0, SCR_WIDTH, SCR_HEIGHT); // ФОН2
        } else if (isChoosedMug == true) {
            game.batch.draw(imgBgMug, 0, 0, SCR_WIDTH, SCR_HEIGHT); // ФОН2
        }

        if (isChoosedBase == true) {
            for (int i = 0; i < imgRainGif.length; i++) {
                tt++;
                if(tt > 0 && tt <= 20) {
                    game.batch.draw(imgRainGif[0], 0, 0, SCR_WIDTH, SCR_HEIGHT);
                } else
                if(tt > 20 && tt <= 40) {
                    game.batch.draw(imgRainGif[1], 0, 0, SCR_WIDTH, SCR_HEIGHT);
                } else
                if(tt > 40 && tt <= 61) {
                    game.batch.draw(imgRainGif[2], 0, 0, SCR_WIDTH, SCR_HEIGHT);
                }  else if (tt > 60) tt = 0;
            }
        }

        if(isChoosedBase == true || isChoosedMug == true){
            for (Rectangle raindrop : raindrops) {
                game.batch.draw(imgKapl, raindrop.x, raindrop.y, 76, 128);
            }
        } else if (isChoosedTrash == true) {
            for (Rectangle raindrop : raindrops) {
                game.batch.draw(imgMusor, raindrop.x, raindrop.y,76, 128);
            }
        }

        for (Rectangle raindrop : deathRaindrops) {
            game.batch.draw(imgDeathKapl, raindrop.x, raindrop.y, 64, 76);
        }

        for (Rectangle raindrop : moneyRaindrops) {
            game.batch.draw(imgMoney, raindrop.x, raindrop.y, 76, 76);
        }

        for (Rectangle raindrop : healthRaindrops) {
            game.batch.draw(imgHealthKapl, raindrop.x, raindrop.y, 76, 128);
        }

        game.batch.draw(imgNoHeart, SCR_WIDTH - 90, SCR_HEIGHT - 90, 60, 60);
        game.batch.draw(imgNoHeart, SCR_WIDTH - 180, SCR_HEIGHT - 90, 60, 60);
        game.batch.draw(imgNoHeart, SCR_WIDTH - 270, SCR_HEIGHT - 90, 60, 60);

        if (lifeN <= 3 && lifeN > 0) {
            game.batch.draw(imgHeart, SCR_WIDTH - 90, SCR_HEIGHT - 90, 60, 60);
        }

        if (lifeN <= 3 && lifeN > 1) {
            game.batch.draw(imgHeart, SCR_WIDTH - 180, SCR_HEIGHT - 90, 60, 60);
        }

        if (lifeN == 3) {
            game.batch.draw(imgHeart, SCR_WIDTH - 270, SCR_HEIGHT - 90, 60, 60);
        }

        if (lifeN > 3) lifeN = 3;

        if(isLangRus == true) {
            game.font.draw(game.batch, "СЧЁТ: " + score, 15, SCR_HEIGHT-15);
            game.font.draw(game.batch, "ДЕНЬГИ: " + game.money, 15, SCR_HEIGHT-90);
            game.font.draw(game.batch, "ВЫЙТИ", SCR_WIDTH/2f-40, SCR_HEIGHT-20);
        } else if (isLangEng) {
            game.font.draw(game.batch, "SCORE: " + score, 15, SCR_HEIGHT-15);
            game.font.draw(game.batch, "MONEY: " + game.money, 15, SCR_HEIGHT-90);
            game.font.draw(game.batch, "EXIT", SCR_WIDTH/2f-40, SCR_HEIGHT-20);
        }


        if(isChoosedBase){
            game.batch.draw(imgVedr, bucket.x, bucket.y, 128, 128);
        } else if (isChoosedMug) {
            game.batch.draw(imgMug, bucket.x, bucket.y, 128, 128);
        }else if (isChoosedTrash) {
            game.batch.draw(imgTrash, bucket.x, bucket.y, 128, 128);
        }

        game.batch.end();


        if (Gdx.input.isTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touch);
            bucket.x = (int) (game.touch.x - 128/2);

            if(btnExit.hit(game.touch.x, game.touch.y)) {
                gamestop = true;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            bucket.x += 8000 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            bucket.x -= 8000 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > SCR_WIDTH - 128) bucket.x = SCR_WIDTH - 128;

        if (score < 5) {
            vremyaPoyavleniyaS1 = 1300000000;
        } else if (score >= 15 && score < 25) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS2;
        } else if (score >= 25 && score < 60) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS3;
        } else if (score >= 60 && score < 120) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS4;
        } else if (score >= 120 && score < 180) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS5;
        } else if (score >= 180 && score < 260) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS6;
        } else if (score >= 260 && score < 500) {
            vremyaPoyavleniyaS1 = vremyaPoyavleniyaS7;
        }

//        if(isSoundOn == true) {
//            if(isChoosedBase) {
//                if (score == 5) {
//                    mscGrom.play();
//                } else if (score == 15) {
//                    mscGrom.play();
//                } else if (score == 25) {
//                    mscGrom.play();
//                } else if (score == 150) {
//                    mscGrom.play();
//                } else if (score == 220) {
//                    mscGrom.play();
//                } else if (score == 300) {
//                    mscGrom.play();
//                }
//            } else if(isChoosedMug) {
//                if (score == 5) {
//                    mscHandShoot.play();
//                } else if (score == 15) {
//                    mscHandShoot.play();
//                } else if (score == 25) {
//                    mscHandShoot.play();
//                } else if (score == 150) {
//                    mscHandShoot.play();
//                } else if (score == 220) {
//                    mscHandShoot.play();
//                } else if (score == 300) {
//                    mscHandShoot.play();
//                }
//            } else if(isChoosedTrash) {
//                if (score == 5) {
//                    mscSvalka.play();
//                } else if (score == 15) {
//                    mscSvalka.play();
//                } else if (score == 25) {
//                    mscSvalka.play();
//                } else if (score == 150) {
//                    mscSvalka.play();
//                } else if (score == 220) {
//                    mscSvalka.play();
//                } else if (score == 300) {
//                    mscSvalka.play();
//                }
//            }
//        }


        if (TimeUtils.nanoTime() - lastDropTime > vremyaPoyavleniyaS1) spawnRainDrop();
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();

//            if (score<25) {
//                radrSpeedS1 = 300;
//            } else if (score >= 25 && score < 75) {
//                radrSpeedS1 = radrSpeedS2;
//            } else if (score >= 75 && score < 140) {
//                radrSpeedS1 = radrSpeedS3;
//            } else if (score >= 140 && score < 240) {
//                radrSpeedS1 = radrSpeedS4;
//            } else if (score >= 240 && score < 400) {
//                radrSpeedS1 = radrSpeedS5;
//            } else if (score >= 400 && score < 1000) {
//                radrSpeedS1 = radrSpeedS6;
//            }

            if(score < 20) {
                radrSpeedS1 = 200;
            }  else if (score%10 == 0) {
                radrSpeedS1 = radrSpeedS2+20;
                radrSpeedS3 = radrSpeedS1;
            } else if (score%10 != 0) {
                radrSpeedS2 = radrSpeedS1;
            }

            raindrop.y -= radrSpeedS1 * Gdx.graphics.getDeltaTime();

            if (raindrop.y + 128 < 0) {
                iter.remove();
                if(isSoundOn == true){
                    sndCmert.play();
                }
                lifeN--;

                if (lifeN == 2) {
                    raindrops.clear();
                    deathRaindrops.clear();
                    moneyRaindrops.clear();
                    healthRaindrops.clear();
                }

                if (lifeN == 1) {
                    raindrops.clear();
                    deathRaindrops.clear();
                    moneyRaindrops.clear();
                    healthRaindrops.clear();
                }
            }

            if (raindrop.overlaps(bucket)) {
                score++;
                if(isSoundOn == true){
                    if(isChoosedBase == true){
                        sndDropkapl.play();
                    } else if (isChoosedTrash == true){
                        sndDropTrash.play();
                    } else if (isChoosedMug == true) {
                        sndDropMug.play();
                    }
                }
                iter.remove();
            }
        }

        if (score>15) {
            if (TimeUtils.millis() - lastDropDKTime > vremyaDPoyavleniyaS1) spawnDeathRainDrop();
            Iterator<Rectangle> iterD = deathRaindrops.iterator();
            while (iterD.hasNext()) {
                Rectangle raindrop = iterD.next();

                raindrop.y -= radrSpeedDS1 * Gdx.graphics.getDeltaTime();

                if (raindrop.y + 128 < 0) {
                    iterD.remove();
                }

                if (raindrop.overlaps(bucket)) {
                    lifeN--;
                    if(isSoundOn == true){
                        sndYad.play();
                    }
                    iterD.remove();
                }

            }
        }

        if (score>5) {
            if (TimeUtils.millis() - lastDropMKTime > vremyaMPoyavleniyaS1) spawnMoneyRainDrop();
            Iterator<Rectangle> iterM = moneyRaindrops.iterator();
            while (iterM.hasNext()) {
                Rectangle raindrop = iterM.next();

                raindrop.y -= radrSpeedMS1 * Gdx.graphics.getDeltaTime();

                if (raindrop.overlaps(bucket)) {
                    if (score<=50) {
                        game.money++;
                    } else if (score>=50 && score <= 100) {
                        game.money+=2;
                    } else if (score>100 && score <= 150) {
                        game.money+=3;
                    } else if (score>150 && score <= 200) {
                        game.money+=4;
                    } else if (score>200) {
                        game.money+=6;
                    }

                    if(isSoundOn == true){
                        sndMoney.play();
                    }
                    iterM.remove();
                }
            }
        }

        if (score>25) {
            if (TimeUtils.millis() - lastDropHKTime > vremyaHPoyavleniyaS1) spawnHealthRainDrop();
            Iterator<Rectangle> iterH = healthRaindrops.iterator();
            while (iterH.hasNext()) {
                Rectangle raindrop = iterH.next();

                raindrop.y -= radrSpeedHS1 * Gdx.graphics.getDeltaTime();

                if (raindrop.overlaps(bucket)) {
                    lifeN++;
                    if(isSoundOn == true){
                        sndHPPlus.play();
                    }
                    iterH.remove();
                }
            }
        }

        if (lifeN<=0 || gamestop == true) {
            mscDozjd.stop();
            time++;
            raindrops.clear();
            deathRaindrops.clear();
            moneyRaindrops.clear();
            healthRaindrops.clear();
            game.lastScore = score;
            game.saveScoreFTR();
            game.saveLastScore();
            if (time > 120){
                game.menuMoney = game.menuMoney + game.money;
                game.saveMenuMoney();
                game.money = 0;
                time = 0;
                score = 0;
                gamestop = false;
                lifeN=3;
                game.setScreen(game.menuScreen);
            }
        }
    }

//    void putUron () {
//        radrSpeedS2 = 0;
//        while (radrSpeedS2 != radrSpeedS3) {
//            radrSpeedS2++;
//        }
//    }


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
    public void dispose () {
        imgKapl.dispose();
        imgDeathKapl.dispose();
        imgMoney.dispose();
        imgHealthKapl.dispose();
        sndDropkapl.dispose();
        imgVedr.dispose();
        imgMug.dispose();
        imgTrash.dispose();
        mscDozjd.dispose();
    }

    @Override
    public void show() {
        if(isChoosedBase == true){
            if(isMusicOn == true){
                mscDozjd.play();
            }
        }
    }
}