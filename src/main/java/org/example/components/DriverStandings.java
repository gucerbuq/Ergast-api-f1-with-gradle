package org.example.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class DriverStandings {

    private int position;
    private String positionText;
    private Drivers driver;

    @Override
    public String toString() {
        return "DriverStandings{" +
                "position=" + position +
                ", positionText='" + positionText + '\'' +
                ", driver=" + driver +
                '}';
    }

}
