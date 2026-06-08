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

        float buttonWidth = GameConfig.isMobile ? 700f : 300f;
        float buttonHeight = GameConfig.isMobile ? 350f : 150f;
                

        stage.addActor(table);

        //GAME OVER TITLE

       Label.LabelStyle titleStyle = new Label.LabelStyle(game.assets.titleFont, null);
       Label.LabelStyle normalStyle = new Label.LabelStyle(game.assets.mediumFont, null);
       Label.LabelStyle bigStyle = new Label.LabelStyle(game.assets.largeFont, null);

       Label gameOverLabel = new Label ("Game Over",bigStyle);

        gameOverLabel.setFontScale(titleScale);
        table.add(gameOverLabel).padTop(20);
        table.row();


        //SCORE
        Label scoreLabel = new Label("Score: ", bigStyle);
        table.add(scoreLabel).padTop(10);
        table.row();

        Label finalScoreValue = new Label (String.valueOf(stats.finalScore), normalStyle);
        table.add(finalScoreValue).padTop(10);
        table.row();


        Label highScoreLabel = new Label("High Score: ", bigStyle);
        table.add(highScoreLabel).padTop(10);
        table.row();

        Label highScoreValue = new Label(String.valueOf(game.highScore), normalStyle);
        table.add(highScoreValue).padTop(10);
        table.row();

        //TIMER
        Label time = new Label("Total Time: ", bigStyle);
        table.add(time).padTop(10);
        table.row();

        int totalSeconds = (int) stats.totalGameTime;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        Label timeValue = new Label(timeFormatted, normalStyle);
        table.add(timeValue).padTop(10);
        table.row();

        //STATS 
        table.add(new Label("Wave Reached: " + stats.finalWave, normalStyle)).padBottom(5f).padTop(10);
        table.row();

        table.add(new Label("Enemies Defeated: " + stats.totalEnemiesKilled, normalStyle)).padBottom(5f);
        table.row();

        table.add(new Label("Tractors: " + stats.tractorsKilled, normalStyle)).padBottom(5f);
        table.row();

        table.add(new Label("Farmers: " + stats.farmersKilled, normalStyle)).padBottom(5f);
        table.row();

        table.add(new Label ("Miners: " + stats.minersKilled, normalStyle)).padBottom(5f);
        table.row();


        TextureRegionDrawable playDrawable = new TextureRegionDrawable(game.assets.playButtonAnimation.getAnimation().getKeyFrame(0));
        playButton = new ImageButton(playDrawable);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen(game));
            }
        });
        table.add(playButton).width(buttonWidth).height(buttonHeight).padTop(-20);
        table.row();

        TextureRegionDrawable backDrawable = new TextureRegionDrawable(game.assets.exitButtonAnimation.getAnimation().getKeyFrame(0));
        backButton = new ImageButton(backDrawable);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).width(buttonWidth).height(buttonHeight).padTop(-40);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

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

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
