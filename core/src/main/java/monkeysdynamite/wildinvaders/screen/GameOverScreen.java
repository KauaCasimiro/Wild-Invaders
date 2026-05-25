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
        stage.addActor(table);

        Label gameOverLabel = new Label("Game Over", skin);

        table.add(gameOverLabel);
        table.row();

        Label scoreLabel = new Label("Score: " + stats.finalScore, skin);
        table.add(scoreLabel).padTop(20);
        table.row();

        Label highScoreLabel = new Label("High Score: " + stats.highScore, skin);
        table.add(highScoreLabel).padTop(20);
        table.row();

        Label wave = new Label("Wave achieved: " + stats.finalWave, skin);
        table.add(wave).padTop(20);
        table.row();

        Label time = new Label("Total Time: " + stats.totalGameTime, skin);
        table.add(time).padTop(20);
        table.row();

        Label totalKills = new Label("Total enemies killed: " + stats.totalEnemiesKilled, skin);
        table.add(totalKills).padTop(20);
        table.row();

        Label tractorsKilled = new Label("Tractors killed: " + stats.tractorsKilled, skin);
        table.add(tractorsKilled).padTop(20);
        table.row();

        Label farmersKilled = new Label("Farmers killed: " + stats.farmersKilled, skin);
        table.add(farmersKilled).padTop(20);
        table.row();

        Label minersKilled = new Label("Miners killed: " + stats.minersKilled, skin);
        table.add(minersKilled).padTop(20);
        table.row();

        TextButton playButton = new TextButton("Play Again", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen(game));
            }
        });
        table.row();
        table.add(playButton).padTop(20);

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).padTop(20);

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
