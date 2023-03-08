package org.example.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class Circuits {

    private String circuitId;
    private String url;
    private String circuitName;

    @Override
    public String toString() {
        return "Circuit{" +
                "circuitId='" + circuitId + '\'' +
                ", circuitName='" + circuitName + '\'' +
                '}';
    }

}
