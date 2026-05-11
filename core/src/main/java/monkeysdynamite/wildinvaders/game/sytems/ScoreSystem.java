package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class ScoreSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {
      updateScoreTier(db);

      updateScoreRanges(db);

      updateFormationCache(db);
    }

    public void onEnemyKilled(Enemy enemy, GameDatabase db) {
        int score = rollEnemyScore(enemy, db);

        boolean rowBonus = db.alivesEnemiesByRow.get(enemy.rowIndex) == 1;
        boolean columnBonus = db.alivesEnemiesByColumn.get(enemy.columnIndex) == 1;

        //Apply bonus 50%
        if (columnBonus) {
            score = (int) (score * 1.5f);
        }

        //Apply bonus 2x
        if (rowBonus) {
            score *= 2;
        }

        db.score += score;
        db.lastScoreGain = score;
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

            db.alivesEnemiesByRow.put(row, db.alivesEnemiesByRow.getOrDefault(row, 0) + 1);

            db.alivesEnemiesByColumn.put(column, db.alivesEnemiesByColumn.getOrDefault(column, 0) + 1);
    }
    
}
}