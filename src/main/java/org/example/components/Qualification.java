package org.example.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Qualification {

    private int number;
    private int position;
    private Drivers driver;
    private String q1;
    private String q2;
    private String q3;

    @Override
    public String toString() {
        return "Qualification{" +
                "Position = " + position +
                " => " + driver +
                '}';
    }

}
