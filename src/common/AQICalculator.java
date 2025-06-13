package common;

public class AQICalculator {

    public static int getSO2Level(double concentration) {
        if (concentration < 0) return -1; // 无效值

        int level;
        if (concentration <= 50) {
            level = 1;
        } else if (concentration <= 150) {
            level = 2;
        } else if (concentration <= 475) {
            level = 3;
        } else if (concentration <= 800) {
            level = 4;
        } else if (concentration <= 1600) {
            level = 5;
        } else {
            level = 6;
        }

        return level;
    }

    public static int getCOLevel(double concentration) {
        if (concentration < 0) return -1; // 无效值

        int level;
        if (concentration <= 5) {
            level = 1;
        } else if (concentration <= 10) {
            level = 2;
        } else if (concentration <= 35) {
            level = 3;
        } else if (concentration <= 60) {
            level = 4;
        } else if (concentration <= 90) {
            level = 5;
        } else if (concentration <= 150) {
            level = 6;
        } else {
            return -1; // 超过最大值
        }

        return level;
    }

    public static int getPM25Level(double concentration) {
        if (concentration < 0) return -1; // 无效值

        int level;
        if (concentration <= 35) {
            level = 1;
        } else if (concentration <= 75) {
            level = 2;
        } else if (concentration <= 115) {
            level = 3;
        } else if (concentration <= 150) {
            level = 4;
        } else if (concentration <= 250) {
            level = 5;
        } else if (concentration <= 500) {
            level = 6;
        } else {
            return -1; // 超过最大值
        }

        return level;
    }

    public static int getLevelDescription(int so2Level, int coLevel, int pm25Level) {
        int overallAQI = Math.max(Math.max(so2Level, coLevel), pm25Level);
        return overallAQI;
    }
}
