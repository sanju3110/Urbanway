package com.urbanway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            Main app = new Main();
            app.runMetroApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMetroApp() throws IOException {
        Graph_M metroMap = new Graph_M();
        Graph_M.Create_Metro_Map(metroMap);

        System.out.println("\n\t\t\t****WELCOME TO THE METRO APP*****");

        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("\t\t\t\t~~LIST OF ACTIONS~~\n\n");
                System.out.println("1. LIST ALL THE STATIONS IN THE MAP");
                System.out.println("2. SHOW THE METRO MAP");
                System.out.println("3. GET SHORTEST DISTANCE FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("4. GET SHORTEST TIME TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("5. GET SHORTEST PATH (DISTANCE WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("6. GET SHORTEST PATH (TIME WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("7. EXIT THE MENU");
                System.out.print("\nENTER YOUR CHOICE FROM THE ABOVE LIST (1 to 7) : ");

                int choice = Integer.parseInt(inputReader.readLine());

                System.out.print("\n***********************************************************\n");

                if (choice == 7) {
                    System.out.println("Exiting the Metro App. Goodbye!");
                    System.exit(0);
                }

                switch (choice) {
                    case 1:
                        metroMap.display_Stations();
                        break;

                    case 2:
                        metroMap.display_Map();
                        break;

                    case 3:
                        System.out.print("ENTER THE SOURCE STATION: ");
                        String sourceStation = inputReader.readLine();
                        System.out.print("ENTER THE DESTINATION STATION: ");
                        String destinationStation = inputReader.readLine();

                        HashMap<String, Boolean> processed = new HashMap<>();
                        if (!metroMap.containsVertex(sourceStation) || !metroMap.containsVertex(destinationStation) ||
                                !metroMap.hasPath(sourceStation, destinationStation, processed)) {
                            System.out.println("THE INPUTS ARE INVALID");
                        } else {
                            System.out.println("SHORTEST DISTANCE FROM " + sourceStation + " TO " + destinationStation +
                                    " IS " + metroMap.dijkstra(sourceStation, destinationStation, false) + " KM\n");
                        }
                        break;

                    case 4:
                        System.out.print("ENTER THE SOURCE STATION: ");
                        String sourceTimeStation = inputReader.readLine();
                        System.out.print("ENTER THE DESTINATION STATION: ");
                        String destinationTimeStation = inputReader.readLine();

                        HashMap<String, Boolean> processedTime = new HashMap<>();
                        System.out.println("SHORTEST TIME FROM (" + sourceTimeStation + ") TO (" + destinationTimeStation +
                                ") IS " + metroMap.dijkstra(sourceTimeStation, destinationTimeStation, true) / 60 + " MINUTES\n\n");
                        break;

                    case 5:
                        System.out.println("ENTER THE SOURCE AND DESTINATION STATIONS");
                        String sourceDistStation = inputReader.readLine();
                        String destinationDistStation = inputReader.readLine();

                        HashMap<String, Boolean> processedDist = new HashMap<>();
                        if (!metroMap.containsVertex(sourceDistStation) || !metroMap.containsVertex(destinationDistStation) ||
                                !metroMap.hasPath(sourceDistStation, destinationDistStation, processedDist)) {
                            System.out.println("THE INPUTS ARE INVALID");
                        } else {
                            ArrayList<String> interchanges = metroMap.get_Interchanges(metroMap.Get_Minimum_Distance(sourceDistStation, destinationDistStation));
                            int len = interchanges.size();
                            System.out.println("SOURCE STATION : " + sourceDistStation);
                            System.out.println("DESTINATION STATION : " + destinationDistStation);
                            System.out.println("DISTANCE : " + interchanges.get(len - 1));
                            System.out.println("NUMBER OF INTERCHANGES : " + interchanges.get(len - 2));
                            System.out.println("~~~~~~~~~~~~~");
                            System.out.println("START  ==>  " + interchanges.get(0));
                            for (int i = 1; i < len - 3; i++) {
                                System.out.println(interchanges.get(i));
                            }
                            System.out.print(interchanges.get(len - 3) + "   ==>    END");
                            System.out.println("\n~~~~~~~~~~~~~");
                        }
                        break;

                    case 6:
                        System.out.print("ENTER THE SOURCE STATION: ");
                        String sourceTimePathStation = inputReader.readLine();
                        System.out.print("ENTER THE DESTINATION STATION: ");
                        String destinationTimePathStation = inputReader.readLine();

                        HashMap<String, Boolean> processedTimePath = new HashMap<>();
                        if (!metroMap.containsVertex(sourceTimePathStation) || !metroMap.containsVertex(destinationTimePathStation) ||
                                !metroMap.hasPath(sourceTimePathStation, destinationTimePathStation, processedTimePath)) {
                            System.out.println("THE INPUTS ARE INVALID");
                        } else {
                            ArrayList<String> interchanges = metroMap.get_Interchanges(metroMap.Get_Minimum_Time(sourceTimePathStation, destinationTimePathStation));
                            int len = interchanges.size();
                            System.out.println("SOURCE STATION : " + sourceTimePathStation);
                            System.out.println("DESTINATION STATION : " + destinationTimePathStation);
                            System.out.println("TIME : " + interchanges.get(len - 1) + " MINUTES");
                            System.out.println("NUMBER OF INTERCHANGES : " + interchanges.get(len - 2));
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.print("START  ==>  " + interchanges.get(0) + " ==>  ");
                            for (int i = 1; i < len - 3; i++) {
                                System.out.println(interchanges.get(i));
                            }
                            System.out.print(interchanges.get(len - 3) + "   ==>    END");
                            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        }
                        break;

                    default:
                        System.out.println("Please enter a valid option! ");
                        System.out.println("The options you can choose are from 1 to 7. ");
                }
            }
        }
    }
}
