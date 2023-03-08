package org.example.components;

/**
 * Date: 10.03.17
 *
 * @author olerom
 */
public class RaceResult {
    private int number;
    private int position;
    private String positionText;
    private Drivers driver;

    private int grid;
    private int laps;
    private String status;

    public RaceResult(int number, int position, String positionText,
                      int points, Drivers driver,
                      int grid, int laps, String status) {
        this.number = number;
        this.position = position;
        this.positionText = positionText;
        this.driver = driver;
        this.grid = grid;
        this.laps = laps;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionText() {
        return positionText;
    }

    public Drivers getDriver() {
        return driver;
    }

    public int getGrid() {
        return grid;
    }

    public int getLaps() {
        return laps;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RaceResult{" +
                "number=" + number +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", driver=" + driver +
                ", status='" + status + '\'' +
                '}';
    }
}
