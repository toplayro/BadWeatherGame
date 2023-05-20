package com.mygdx.game;

import static com.mygdx.game.GameScreen.SCR_HEIGHT;
import static com.mygdx.game.GameScreen.SCR_WIDTH;
import static com.mygdx.game.MainMenuScreen.isMissionOneComplite;
import static com.mygdx.game.MainMenuScreen.isMissionOneMoneyPut;
import static com.mygdx.game.MainMenuScreen.isMissionTwoComplite;
import static com.mygdx.game.MainMenuScreen.isMissionTwoMoneyPut;
import static com.mygdx.game.SettingsScreen.isLangEng;
import static com.mygdx.game.SettingsScreen.isLangRus;
import static com.mygdx.game.SettingsScreen.isMusicOn;
import static com.mygdx.game.SettingsScreen.isSoundOn;
import static com.mygdx.game.ShopScreen.isBuyedMug;
import static com.mygdx.game.ShopScreen.isBuyedTrash;
import static com.mygdx.game.ShopScreen.isChoosedBase;
import static com.mygdx.game.ShopScreen.isChoosedMug;
import static com.mygdx.game.ShopScreen.isChoosedTrash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MainRain extends Game {

	SpriteBatch batch;
	BitmapFont font;
	BitmapFont fontSettings;
	BitmapFont fontVersion;
	Vector3 touch;
	OrthographicCamera camera;

	MainMenuScreen menuScreen;
	GameScreen gameScreen;
	SettingsScreen settingsScreen;
	ShopScreen shopScreen;

	int scoreFTR;
	int lastScore;
	int money;
	int menuMoney;

	@Override
	public void create() {

		camera = new OrthographicCamera();

		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

		batch = new SpriteBatch();

		touch = new Vector3();

		font = new BitmapFont();

		generateFont();
		generateFontSett();
		generateFont1();


		menuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		settingsScreen = new SettingsScreen(this);
		shopScreen = new ShopScreen(this);
		setScreen(menuScreen);
	}

	@Override
	public void render() {
		super.render();

		if (lastScore >= scoreFTR) {
			scoreFTR = lastScore;
		}
	}

	void generateFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("shrift1.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 56;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.color = new Color().set(1, 0.9f, 0.4f, 1);
		parameter.borderColor = new Color().set(0, 0, 0, 1);
		parameter.borderWidth = 2;
		font = generator.generateFont(parameter);
		generator.dispose();
	}

	void generateFontSett() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("shrift1.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 56;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.color = new Color().set(1, 0.9f, 0.4f, 1);
		parameter.borderColor = new Color().set(0, 0, 0, 1);
		parameter.borderWidth = 2;
		fontSettings = generator.generateFont(parameter);
		generator.dispose();
	}

	void generateFont1() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("shrift.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 28;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.color = new Color().set(0, 0, 1, 0.5f);
		parameter.borderColor = new Color().set(0, 0, 0, 1);
		parameter.borderWidth = 1;
		fontVersion = generator.generateFont(parameter);
		generator.dispose();
	}

	void saveScoreFTR() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedScoreFTR");
			pref.putInteger("savedScoreFTR", scoreFTR);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMenuMoney() {
		try {
			Preferences pref = Gdx.app.getPreferences("saveMoney");
			pref.putInteger("saveMoney", menuMoney);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveLastScore() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedLastScore");
			pref.putInteger("savedLastScore", lastScore);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveBuyingMug() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedBuyedMug");
			pref.putBoolean("savedBuyedMug", isBuyedMug);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveChoosedMug() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedChoosedMug");
			pref.putBoolean("savedChoosedMug", isChoosedMug);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveChoosedTrash() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedChoosedTrash");
			pref.putBoolean("savedChoosedTrash", isChoosedTrash);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveBuyingTrash() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedBuyedTrash");
			pref.putBoolean("savedBuyedTrash", isBuyedTrash);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveChoosedBase() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedChoosedBase");
			pref.putBoolean("savedChoosedBase", isChoosedBase);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMusic() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedMusic");
			pref.putBoolean("savedMusic", isMusicOn);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveSound() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedSound");
			pref.putBoolean("savedSound", isSoundOn);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveLangRus() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedLangRus");
			pref.putBoolean("savedLangRus", isLangRus);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveLangEng() {
		try {
			Preferences pref = Gdx.app.getPreferences("savedLangEng");
			pref.putBoolean("savedLangEng", isLangEng);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void savePromoFUsed() {
		try {
			Preferences pref = Gdx.app.getPreferences("savePromoFused");
			pref.putBoolean("savePromoFused", settingsScreen.isPromoFUsed);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMission1() {
		try {
			Preferences pref = Gdx.app.getPreferences("saveMission1");
			pref.putBoolean("saveMission1", isMissionOneComplite);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMission2() {
		try {
			Preferences pref = Gdx.app.getPreferences("saveMission2");
			pref.putBoolean("saveMission2", isMissionTwoComplite);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMission1Money() {
		try {
			Preferences pref = Gdx.app.getPreferences("saveMission1Money");
			pref.putBoolean("saveMission1Money", isMissionOneMoneyPut);
			pref.flush();
		} catch (Exception e) {

		}
	}

	void saveMission2Money() {
		try {
			Preferences pref = Gdx.app.getPreferences("saveMission2Money");
			pref.putBoolean("saveMission2Money", isMissionTwoMoneyPut);
			pref.flush();
		} catch (Exception e) {

		}
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
	}
}