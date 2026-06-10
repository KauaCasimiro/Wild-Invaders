package monkeysdynamite.wildinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import monkeysdynamite.wildinvaders.Main;
import monkeysdynamite.wildinvaders.game.RunStats;
import monkeysdynamite.wildinvaders.config.GameConfig;

public class GameOverScreen implements Screen {

    private Main game;
    private RunStats stats;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;

    private ImageButton playButton;
    private ImageButton backButton;

    private float playTimer;
    private float backTimer;

    public GameOverScreen(Main game,  RunStats stats) {
        this.game = game;
        this.stats = stats;
        batch = new SpriteBatch();

        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));


        Table table = new Table();
        table.setFillParent(true);
        table.center();

        float fontScale = GameConfig.isMobile ? 2.0f : 1.0f;
        float titleScale = GameConfig.isMobile ? 2.5f : 1.3f;

        float buttonWidth = GameConfig.isMobile ? 630f : 270f;
        float buttonHeight = GameConfig.isMobile ? 270f : 150f;

        float padTop = GameConfig.isMobile ? 15f : 10f;
        float padBottom = GameConfig.isMobile ? 7f : 5f;

        float padTopButton = GameConfig.isMobile ? -46f : -20f;
        float padTopButton2 = GameConfig.isMobile ? -96f : -60f;
        float padBottomButton = GameConfig.isMobile ? -50f : -25f;


        stage.addActor(table);

        //GAME OVER TITLE

       Label.LabelStyle titleStyle = new Label.LabelStyle(game.assets.titleFont, null);
       Label.LabelStyle normalStyle = new Label.LabelStyle(game.assets.mediumFont, null);
       Label.LabelStyle middleStyle = new Label.LabelStyle(game.assets.middleFont, null);
       Label.LabelStyle bigStyle = new Label.LabelStyle(game.assets.largeFont, null);
       Label.LabelStyle largeSytle = new Label.LabelStyle(game.assets.titleFont, null);

       Label.LabelStyle font = GameConfig.isMobile ? largeSytle : middleStyle;

       Label gameOverLabel = new Label ("Game Over", font);

        gameOverLabel.setFontScale(titleScale);
        table.add(gameOverLabel).padTop(padTop);
        table.row();


        //SCORE
        Label scoreLabel = new Label("Score: ", font);
        table.add(scoreLabel).padTop(padTop);
        table.row();

        Label finalScoreValue = new Label (String.valueOf(stats.finalScore), font);
        table.add(finalScoreValue).padTop(padTop);
        table.row();


        Label highScoreLabel = new Label("High Score: ", font);
        table.add(highScoreLabel).padTop(padTop);
        table.row();

        Label highScoreValue = new Label(String.valueOf(game.highScore), font);
        table.add(highScoreValue).padTop(padTop);
        table.row();

        //TIMER
        Label time = new Label("Total Time: ", font);
        table.add(time).padTop(padTop);
        table.row();

        int totalSeconds = (int) stats.totalGameTime;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        Label timeValue = new Label(timeFormatted, font);
        table.add(timeValue).padTop(padTop);
        table.row();

        //STATS
        table.add(new Label("Wave Reached: " + stats.finalWave, font)).padBottom(padBottom).padTop(padTop);
        table.row();

        table.add(new Label("Enemies Defeated: " + stats.totalEnemiesKilled, font)).padBottom(padBottom);
        table.row();

        table.add(new Label("Tractors: " + stats.tractorsKilled, font)).padBottom(padBottom);
        table.row();

        table.add(new Label("Farmers: " + stats.farmersKilled, font)).padBottom(padBottom);
        table.row();

        table.add(new Label ("Miners: " + stats.minersKilled, font)).padBottom(padBottom);
        table.row();


        TextureRegionDrawable playDrawable = new TextureRegionDrawable(game.assets.playButtonAnimation.getAnimation().getKeyFrame(0));
        playButton = new ImageButton(playDrawable);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sound.stopMenuMusic();
                game.setScreen(new FirstScreen(game));
            }
        });
        table.add(playButton).width(buttonWidth).height(buttonHeight).padTop(padTopButton);
        table.row();

        TextureRegionDrawable backDrawable = new TextureRegionDrawable(game.assets.exitButtonAnimation.getAnimation().getKeyFrame(0));
        backButton = new ImageButton(backDrawable);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sound.resumeMenuMusic();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).width(buttonWidth).height(buttonHeight).padTop(padTopButton2).padBottom(padBottomButton);
        game.sound.playMenuMusic();
        

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (playButton.isPressed()) {

            playTimer += delta;

            TextureRegion frame = game.assets.playButtonAnimation.getAnimation().getKeyFrame(playTimer, false);
            playButton.getStyle().imageUp = new TextureRegionDrawable(frame);
        } else {

            playTimer = 0;

            playButton.getStyle().imageUp = new TextureRegionDrawable(game.assets.playButtonAnimation.getAnimation().getKeyFrame(0));
        }

        if (backButton.isPressed()) {

            backTimer += delta;

            TextureRegion frame = game.assets.exitButtonAnimation.getAnimation().getKeyFrame(backTimer, false);
            backButton.getStyle().imageUp = new TextureRegionDrawable(frame);

        } else {

            backTimer = 0;

            backButton.getStyle().imageUp = new TextureRegionDrawable(game.assets.exitButtonAnimation.getAnimation().getKeyFrame(0));
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(game.assets.bgMenus, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
