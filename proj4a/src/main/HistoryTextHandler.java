package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap htMap;

    public HistoryTextHandler(NGramMap map) {
        htMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder response = new StringBuilder();
        for (String word: words) {
            response.append(word + ": " + htMap.weightHistory(word, startYear, endYear).toString());
            response.append("\n");
        }

        return response.toString();
    }
}
