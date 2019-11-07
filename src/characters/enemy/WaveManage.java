package characters.enemy;

import characters.turret.Turret;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class WaveManage {
    private Chaser CenemyType;
    private int CenemyPerTurn;
    private Hunk HenemyType;
    private int HenemyPerTurn;
    private MeatHarvester MenemyType;
    private int MenemyPerTurn;
    private PeaceEnvog PenemyType;
    private int PenemyPerTurn;
    private StackPane pane;
    private Path path;

    private ArrayList<Enemy> enemies;

   public WaveManage(Chaser CenemyType, int CenemyPerTurn, Hunk HenemyType, int HenemyPerTurn, MeatHarvester MenemyType, int MenemyPerTurn, PeaceEnvog PenemyType, int PenemyPerTurn, StackPane pane, Path path) {
       this.CenemyType = CenemyType;
       this.CenemyPerTurn = CenemyPerTurn;
       this.HenemyType = HenemyType;
       this.HenemyPerTurn = HenemyPerTurn;
       this.MenemyType = MenemyType;
       this.MenemyPerTurn = MenemyPerTurn;
       this.PenemyType = PenemyType;
       this.PenemyPerTurn = PenemyPerTurn;

       this.pane = pane;
       this.path = path;
       enemies = new ArrayList<>();
       Update();
   }

    public void RenderC() {
        enemies.add(new Chaser());
    }

    public void RenderH() {
        enemies.add(new Hunk());
    }

    public void RenderM() {
        enemies.add(new MeatHarvester() );
    }

    public void RenderP() {
        enemies.add(new PeaceEnvog());
    }



    public void Update() {
        int count = 0;
        enemies.add(CenemyType);
        while (enemies.size() != CenemyPerTurn) {
            RenderC();
        }

        while (count != HenemyPerTurn) {
            RenderH();
            count++;
        }

        count = 0;
        while (count != MenemyPerTurn) {
            RenderM();
            count++;
        }

        count = 0;
        while (count != PenemyPerTurn) {
            RenderP();
            count++;
        }
    }

    public void move(Turret turret) {
        AnimationTimer h = new AnimationTimer() {
            int i = 0;
            int j = 1;
            private long lastUpdate = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                if (elapsedSeconds == 10) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < CenemyPerTurn) {
                        enemies.get(i++).move(pane,path);
                    }
                }

                if (elapsedSeconds == 3) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < HenemyPerTurn + CenemyPerTurn) {
                        enemies.get(i++).move(pane,path);
                    }
                }

                if (elapsedSeconds == 4) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < HenemyPerTurn + MenemyPerTurn + CenemyPerTurn) {
                        enemies.get(i++).move(pane,path);
                    }
                }

                if (elapsedSeconds == 2) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < PenemyPerTurn + HenemyPerTurn + MenemyPerTurn + CenemyPerTurn) {
                        enemies.get(i++).move(pane,path);
                    }
                }
                turret.checkingEnemy(enemies);
            }
        };
        h.start();
    }



}
