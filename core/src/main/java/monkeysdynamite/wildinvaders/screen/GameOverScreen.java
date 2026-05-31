package monkeysdynamite.wildinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import monkeysdynamite.wildinvaders.Main;
import monkeysdynamite.wildinvaders.game.RunStats;

public class GameOverScreen implements Screen {

    private Main game;
    private RunStats stats;
    private Stage stage;
    private Skin skin;

    public GameOverScreen(Main game,  RunStats stats) {
        this.game = game;
        this.stats = stats;

        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        stage.addActor(table);

        //GAME OVER TITLE

        Label gameOverLabel = new Label("Game Over", skin);
        gameOverLabel.setFontScale(2.5f);

        table.add(gameOverLabel).padBottom(15);
        table.row();

        Label titleLine = new Label("================================", skin);
        
        table.add(titleLine).padBottom(15);
        table.row();

        //SCORE
        Label scoreLabel = new Label("Score: ", skin);
        scoreLabel.setFontScale(1.3f);
        table.add(scoreLabel).padTop(15);
        table.row();

        Label finalScoreValue = new Label (String.valueOf(stats.finalScore), skin);
        finalScoreValue.setFontScale(2f);
        table.add(finalScoreValue).padTop(15);
        table.row();


        Label highScoreLabel = new Label("High Score: ", skin);
        highScoreLabel.setFontScale(1.3f);
        table.add(highScoreLabel).padTop(15);
        table.row();

        Label highScoreValue = new Label(String.valueOf(stats.highScore), skin);
        highScoreValue.setFontScale(2f);
        table.add(highScoreValue).padTop(15);
        table.row();

        //DIVIDER
        Label divider1 = new Label("--------------------------------", skin);
        table.add(divider1).padTop(15);
        table.row();

        //TIMER
        Label time = new Label("Total Time: ", skin);
        time.setFontScale(1.3f);
        table.add(time).padTop(15);
        table.row();

        int totalSeconds = (int) stats.totalGameTime;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        Label timeValue = new Label(timeFormatted, skin);
        timeValue.setFontScale(2f);
        table.add(timeValue).padTop(10);
        table.row();

        //STATS 
        table.add(new Label("Wave Reached: " + stats.finalWave, skin)).padBottom(15).padTop(15);
        table.row();

        table.add(new Label("Enemies Defeated:" + stats.totalEnemiesKilled, skin)).padBottom(15);
        table.row();

        table.add(new Label("Tractors: " + stats.tractorsKilled, skin)).padBottom(5f);
        table.row();

        table.add(new Label("Farmers: " + stats.farmersKilled, skin)).padBottom(5f);
        table.row();

        table.add(new Label ("Miners: " + stats.minersKilled, skin)).padBottom(5f);
        table.row();

        // DIVIDER
        Label divider2 = new Label("--------------------------------", skin);
        table.add(divider2).padTop(20);
        table.row();

        TextButton playButton = new TextButton("Play Again", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen(game));
            }
        });
        table.add(playButton).width(250).height(60).padBottom(15);
        table.row();

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).width(250).height(60);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
