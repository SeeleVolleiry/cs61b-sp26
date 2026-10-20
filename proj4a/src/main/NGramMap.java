package main;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // static/instance variables.
    public Map<String, TimeSeries> wordHistory;
    public TimeSeries yearHistory;

    /**
     * Constructs an NGramMap from WORDHISTORYFILENAME and YEARHISTORYFILENAME.
     */
    public NGramMap(String wordHistoryFilename, String yearHistoryFilename) {
        // wordHistoryFile: wordHistory; yearHistoryFile: yearHistory
        wordHistory = new TreeMap<String, TimeSeries>();
        yearHistory = new TimeSeries();

        // 读取文件并存储
        In wordIn = new In(wordHistoryFilename);
        int i = 0;
        while (wordIn.hasNextLine()) {
            i += 1;
            String wordline = wordIn.readLine();
            String[] wordlineSplit = wordline.split("\t");
            String word = wordlineSplit[0];
            Integer year = Integer.parseInt(wordlineSplit[1]);
            Double freq = Double.parseDouble(wordlineSplit[2]);

            if (!wordHistory.containsKey(word)) {
                wordHistory.put(word, new TimeSeries());
            }
            wordHistory.get(word).put(year, freq);
        }

        In yearIn = new In(yearHistoryFilename);
        int j = 0;
        while (yearIn.hasNextLine()) {
            j += 1;
            String yearline = yearIn.readLine();
            String[] yearlineSplit = yearline.split(",");
            Integer year = Integer.parseInt(yearlineSplit[0]);
            Double total = Double.parseDouble(yearlineSplit[1]);

            yearHistory.put(year, total);
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
}
