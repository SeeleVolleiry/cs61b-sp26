package main;

import browser.NgordnetQueryHandler;
import browser.NgordnetServer;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordHistoryFile, String yearHistoryFile,
            String synsetFile, String hyponymFile) {
        NgordnetServer hns = new NgordnetServer();

        hns.startUp();
        hns.register("hyponyms", new HyponymsHandler(synsetFile, hyponymFile, wordHistoryFile, yearHistoryFile));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");

        throw new RuntimeException("Please fill out AutograderBuddy.java!");
    }
}
