package characters.enemy;

public class WaveManage {
    public static long LastTime, TotalTime;

    public static long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static float getdis() {
        long currentTime = getTime();
        int dis = (int) (currentTime - LastTime);
        LastTime = getTime();
        return dis * 0.01f;
    }
}
