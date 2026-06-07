package monkeysdynamite.wildinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private Music gameMusic;

    private Sound hitPlayer;
    private Sound hitEnemy;

    private Sound shootPlayer;
    private Sound shootFarmer;
    private Sound shootMiner;

    private Sound bonusScore;
    private Sound scoreUp;

    private Sound barrierHit;

    private Sound waveStart;

    public SoundManager() {
        load();
    }

    private void load() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/musics/Mambo_Bambo.mp3"));
        gameMusic.setLooping(true);

        hitPlayer = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/Hit.wav"));
        hitEnemy = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/hitEnemy.wav"));

        shootPlayer = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/shootPlayer1.wav"));
        shootFarmer = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/shootFarmer.wav"));
        shootMiner = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/shootMiner.wav"));

        bonusScore = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/bonusScore.wav"));
        scoreUp = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/scoreUp.wav"));

        barrierHit = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/barrierHit.wav"));

        waveStart = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/waveStart.wav"));
    }

    public void playGameMusic() {
        if (!gameMusic.isPlaying()) {
            gameMusic.play();
            gameMusic.setVolume(0.25f);
        }
    }

    public void stopGameMusic() {
        if (gameMusic.isPlaying()) {
            gameMusic.stop();
        }
    }

    public void pauseGameMusic() {
        if (gameMusic.isPlaying()) {
            gameMusic.pause();
        }
    }

    public void resumeGameMusic() {
        if (!gameMusic.isPlaying()) {
            gameMusic.play();
        }
    }

    public void playHitPlayer() {
        hitPlayer.play();
    }

    public void playHitEnemy() {
        hitEnemy.play();
    }

    public void playShootPlayer() {
        shootPlayer.play();
    }

    public void playShootFarmer() {
        shootFarmer.play();
    }

    public void playShootMiner() {
        shootMiner.play();
    }

    public void playBonusScore() {
        bonusScore.play();
    }

    public void playScoreUp() {
        scoreUp.play();
    }

    public void playBarrierHit() {
        barrierHit.play();
    }

    public void playWaveStart() {
        waveStart.play();
    }

    public void dispose() {
        gameMusic.dispose();
        hitPlayer.dispose();
        hitEnemy.dispose();
        shootPlayer.dispose();
        shootFarmer.dispose();
        shootMiner.dispose();
        bonusScore.dispose();
        scoreUp.dispose();
        barrierHit.dispose();
        waveStart.dispose();
    }

}
