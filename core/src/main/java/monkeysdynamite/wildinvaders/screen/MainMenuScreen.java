package monkeysdynamite.wildinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import monkeysdynamite.wildinvaders.Main;
import monkeysdynamite.wildinvaders.config.GameConfig;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private Main game;
    private SpriteBatch batch;

    private ImageButton playButton;
    private ImageButton exitButton;

    private final float logoSize = 120f;

    private float logoX;
    private float logoY;

    private float playTimer;
    private float exitTimer;

    public MainMenuScreen(Main game) {
        this.game = game;

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());

        // ----- CONFIG MOBILE / DESKTOP -----

        float buttonWidth = 512;
        float buttonHeight = 256;

        float titlePadTop = 90;
        float playPadTop = 30;

        if (GameConfig.isMobile) {

            buttonWidth = 700;
            buttonHeight = 350;

            titlePadTop = 30;
            playPadTop = 20;
        }

        
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        stage.addActor(table);

        Label.LabelStyle titleStyle = new Label.LabelStyle();

        BitmapFont titleFont = game.assets.mainTitleFont;

        titleStyle.font = titleFont;

        Label title = new Label("Wild Invaders", titleStyle);
        
        table.add(title).padTop(titlePadTop);

        TextureRegionDrawable playDrawable = new TextureRegionDrawable(game.assets.playButtonAnimation.getAnimation().getKeyFrame(0));
        playButton = new ImageButton(playDrawable);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen(game));
            }
        });

        table.row();
        table.add(playButton).width(buttonWidth).height(buttonHeight).padTop(playPadTop);

        TextureRegionDrawable exitDrawable = new TextureRegionDrawable(game.assets.exitButtonAnimation.getAnimation().getKeyFrame(0));
        exitButton = new ImageButton(exitDrawable);
        
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.row();
        table.add(exitButton).width(buttonWidth).height(buttonHeight);

        logoX = Gdx.graphics.getWidth() - logoSize - 20f;
        logoY = 20f;

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (touchX >= logoX &&
                touchX <= logoX + logoSize &&
                touchY >= logoY &&
                touchY <= logoY + logoSize) {

                Gdx.net.openURI("https://monkeys-dynamite.itch.io/");
            }
        }

        if (playButton.isPressed()) {

            playTimer += delta;

            TextureRegion frame = game.assets.playButtonAnimation.getAnimation().getKeyFrame(playTimer, false);
            playButton.getStyle().imageUp = new TextureRegionDrawable(frame);

        } else {

            playTimer = 0;

            playButton.getStyle().imageUp = new TextureRegionDrawable(game.assets.playButtonAnimation.getAnimation().getKeyFrame(0));

        }

        if (exitButton.isPressed()) {

            exitTimer += delta;

            TextureRegion frame = game.assets.exitButtonAnimation.getAnimation().getKeyFrame(exitTimer, false);
            exitButton.getStyle().imageUp = new TextureRegionDrawable(frame);
            
        } else {

            exitTimer = 0;

            exitButton.getStyle().imageUp = new TextureRegionDrawable(game.assets.exitButtonAnimation.getAnimation().getKeyFrame(0));
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(game.assets.bgMenus, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.assets.mediumFont.getData().setScale(0.8f);

        game.assets.mediumFont.draw(batch,"Visit our site",logoX - 5f,logoY + logoSize + 25f);

        batch.setColor(1f, 1f, 1f, 1f);

        batch.draw(game.assets.mdLogo,logoX,logoY,logoSize,logoSize);

        batch.setColor(1f, 1f, 1f, 1f);
        
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
