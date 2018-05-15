package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 * 180510 Modified by Megan Hart
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine().toLowerCase();

                /*
                String firstLetter = searchTerm.substring(0, 0);
                String upper = firstLetter.toUpperCase();
                String lower = firstLetter.toLowerCase();
                String upperTerm = upper.concat(searchTerm.substring(1));
                String lowerTerm = lower.concat(searchTerm.substring(1));
                */

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {

                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.size() != 0){  //results
            // HashMap<String, String> bothMaps =  new HashMap<>();
            for (HashMap h : someJobs) {  // I have a hashmap in someJobs
                //Set key = h.keySet();
                //Collection value = h.values();
                System.out.println("\n*****");  //print me a start line
                for (Object name : h.keySet()){ //for some odd reason, name won't work as anything but an object, loop me some keys
                    String key = name.toString(); //name is the key value...woot!
                    String value = h.get(name).toString(); //h.get(keyvalue) works!
                    System.out.println(key + " : " + value); //print pretty
                }
                System.out.println("*****\n"); //and close each section
            }
                /* I tried so many ways to get this to work:
            for (Integer j = 0; j < someJobs.size(); j++) { // arraylist to pull out hashmaps
                for (Map.Entry<String, String> job : someJobs(j).entrySet()){ //through the hashmap
                    //I need the column names as a loop to be the key and match with the value this loop is outputing.
                    System.out.println(job);

                   // System.out.println("\n*****\n All " + columnChoices.get(columnChoice) + " Values \n*****\n");
                }
            }
*/
        } else { // no results
        System.out.println("No results found please try again");
    }


    }
}
