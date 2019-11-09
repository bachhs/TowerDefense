package constants;

import java.io.*;

public class GlobalConstants {
    public static final String GAME_NAME = "Tower Defense";
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = (GAME_WIDTH / 16) * 9;
    public static final int BOUND_X = GAME_WIDTH / 2;
    public static final int BOUND_Y = GAME_HEIGHT / 2;
    public static int ROUND = 1;
    public static int MAIN_MUSIC = 3;
    public static int GAME_MUSIC = 3;
    public static final String GAME_MUSIC_URL = "./src/resources/music/Challengers.mp3";
    public static final int GAME_SPEED = 70000;

    static {
        File GameInfo = new File("TowerDefense.ini");
        try {
            if (!GameInfo.exists()) {
                update(ROUND, MAIN_MUSIC, GAME_MUSIC);
            } else {
                BufferedReader in = new BufferedReader(new FileReader(GameInfo));
                in.readLine();
                String current;
                while ((current = in.readLine()) != null)
                    if (current.contains("Round:")) {
                        String[] roundInfo = current.split(" ");
                        ROUND = Integer.parseInt(roundInfo[roundInfo.length - 1]);
                    } else if (current.contains("MainMenu Music:")) {
                        String[] mainMusicInfo = current.split(" ");
                        MAIN_MUSIC = Integer.parseInt(mainMusicInfo[mainMusicInfo.length - 1]);
                    } else if (current.contains("Game Music: ")) {
                        String[] gameMusicInfo = current.split(" ");
                        GAME_MUSIC = Integer.parseInt(gameMusicInfo[gameMusicInfo.length - 1]);
                    }
                in.close();
            }
        } catch (Exception e) {
            ROUND = 1;
            MAIN_MUSIC = 3;
            GAME_MUSIC = 3;
        }
    }

    public static void update(int round, int mainMusic, int gameMusic) {
        ROUND = round;
        MAIN_MUSIC = mainMusic;
        GAME_MUSIC = gameMusic;
        File GameInfo = new File("TowerDefense.ini");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(GameInfo));
            out.write("/*This is Setting for Tower Defense, do not modify*/\n");
            out.write("Round: " + ROUND + "\n");
            out.write("MainMenu Music: " + MAIN_MUSIC + "\n");
            out.write("Game Music: " + GAME_MUSIC + "\n");
            out.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
