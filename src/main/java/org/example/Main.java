package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {

        Scanner sc = new Scanner(System.in);

        int season=0;
        int round=0;

        System.out.println("Please input the season year : ");
        season = sc.nextInt();
        System.out.println("Please input the round : ");
        round = sc.nextInt();



        JsonReader jsonReader = new JsonReader(season, round, JsonReader.DEFAULT_LIMIT, JsonReader.DEFAULT_OFFSET);

        if (season<2000) {
            try {

                System.out.println("There is no data for qualifying this season. So here is the first three winners : ");
                jsonReader.getRaceResults(round).forEach(System.out::println);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(season<1950 || season>2023){
            System.out.println("There is no data for " + season + " season.");
        }else {
            try {
                jsonReader.getQualificationResults(round).forEach(System.out::println);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}