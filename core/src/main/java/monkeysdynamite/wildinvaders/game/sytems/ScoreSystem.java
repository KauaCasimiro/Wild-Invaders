package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.game.tools.FloatingScore;

public class ScoreSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {
      updateScoreTier(db);

      updateScoreRanges(db);

      updateFormationCache(db);

      updateExtraLives(db);
    }

    public void onEnemyKilled(Enemy enemy, GameDatabase db) {
        int score = rollEnemyScore(enemy, db);

        String bonusText = "";

        boolean rowBonus = db.alivesEnemiesByRow.get(enemy.rowIndex) == 1;
        boolean columnBonus = db.alivesEnemiesByColumn.get(enemy.columnIndex) == 1;

        //Apply bonus 50%
        if (columnBonus) {
            score = (int) (score * 1.5f);
            bonusText += "+50%";
        }

        //Apply bonus 2x
        if (rowBonus) {
            score *= 2;
            bonusText += "x2";
        }

        db.sound.playScoreUp();
        db.score += score;
        db.runStats.finalScore = db.score;
        db.lastScoreGain = score;

        if (db.score > db.highScore) {
            db.highScore += score;
            db.runStats.highScore = db.highScore;
        }

        switch (enemy.getType()) {
            case TRACTOR:
                db.runStats.tractorsKilled++;
                break;
            case FARMER:
                db.runStats.farmersKilled++;
                break;
            case MINER:
                db.runStats.minersKilled++;
                break;
            default:
                break;
        }

        db.runStats.totalEnemiesKilled++;

        FloatingScore floatingScore = new FloatingScore();

        floatingScore.x = enemy.x;
        floatingScore.y = enemy.y;

        floatingScore.value = score;

        floatingScore.bonusText = bonusText;

        db.floatingScores.add(floatingScore);
    }

    private int rollEnemyScore(Enemy enemy, GameDatabase db) {
        switch (enemy.getType()) {
            case TRACTOR:
                return db.tractorScoreRange.roll();
            case FARMER:
                return db.farmerScoreRange.roll();
            case MINER:
                return db.minerScoreRange.roll();
            default:
                return 0;
        }
    }

    private void updateScoreTier(GameDatabase db) {
        if (db.difficultyMultiplier >= 2.5f) {
            db.scoreTier = 3;
        } else if (db.difficultyMultiplier >= 1.5f) {
            db.scoreTier = 2;
        } else {
            db.scoreTier = 1;
        }
    }

    private void updateScoreRanges (GameDatabase db) {

        switch (db.scoreTier) {
            case 1:
                db.tractorScoreRange.set(10, 80);
                db.farmerScoreRange.set(90, 150);
                db.minerScoreRange.set(160, 220);

            break;

             case 2:
                db.tractorScoreRange.set(50, 120);
                db.farmerScoreRange.set(140, 210);
                db.minerScoreRange.set(220, 280);

            break;

             case 3:
                db.tractorScoreRange.set(90, 150);
                db.farmerScoreRange.set(180, 240);
                db.minerScoreRange.set(280, 350);

            break;

            default:

            break;
        }
    }

    private void updateFormationCache(GameDatabase db) {
        db.alivesEnemiesByRow.clear();
        db.alivesEnemiesByColumn.clear();

        for (Enemy enemy : db.enemies) {
            if (!enemy.isAlive) {
                continue;
            }

            int row = enemy.rowIndex;

            int column = enemy.columnIndex;

            Integer rowCount = db.alivesEnemiesByRow.get(row);
            if (rowCount == null) {
                rowCount = 0;
            }
            db.alivesEnemiesByRow.put(row, rowCount + 1);
            Integer columnCount = db.alivesEnemiesByColumn.get(column);

            if (columnCount == null) {
                columnCount = 0;
            }

            db.alivesEnemiesByColumn.put(column, columnCount + 1);
        }

    }

    private void updateExtraLives(GameDatabase db) {
        if (db.score >= db.nextLifeScore) {
            db.life.healPlayer(db, 1);
            db.sound.playBonusScore();
            db.scoreBonusFlash = true;
            db.scoreBonusFlashTimer = 0f;
            db.nextLifeScore += 2000;
        }
    }
}
