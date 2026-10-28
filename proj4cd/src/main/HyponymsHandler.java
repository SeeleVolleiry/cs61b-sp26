package main;

import browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HyponymsHandler extends NgordnetQueryHandler {
    DGraph wordnet;
    NGramMap ngMap;

    // constructor
    public HyponymsHandler(String synsetFilename, String hyponymsFilename,
                           String wordHistoryFilename, String yearHistoryFilename) {
        wordnet = new DGraph(synsetFilename, hyponymsFilename);
        ngMap = new NGramMap(wordHistoryFilename, yearHistoryFilename);
    }

    @Override
    public String handle(browser.NgordnetQuery q) {
        List<String> words = q.words();
        Set<Integer> hyponymsIDs = new TreeSet<>();
        Set<String> hyponymsWords = new TreeSet<>(wordnet.findHypos(wordnet.lookWord(words.getFirst())));
        for (int i = 1; i < words.size(); i++) {
            hyponymsWords.retainAll( wordnet.findHypos(wordnet.lookWord(words.get(i))) );
        }

        return hyponymsWords.toString();
    }
}
