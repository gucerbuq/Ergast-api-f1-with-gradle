package org.example.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Season {

    private int season;
    private String url;

    @Override
    public String toString() {
        return "Season{" +
                "season=" + season +
                '}';
    }

}
