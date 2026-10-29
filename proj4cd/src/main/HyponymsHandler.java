package main;

import browser.NgordnetQueryHandler;

import java.util.*;

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
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> hyponymsWords = new TreeSet<>(wordnet.findHypos(wordnet.lookWord(words.get(0))));
        for (int i = 1; i < words.size(); i++) {
            hyponymsWords.retainAll( wordnet.findHypos(wordnet.lookWord(words.get(i))) );
        }

        if (k > 0) {
            //countHistory 返回 TreeMap<Integer, Double>类型
            // method of sort是搜索得到的。
            TreeMap<String, Double> wordsCount = new TreeMap<>(); // 记录词与次数之间的映射关系
            for (String word: hyponymsWords) {
                 double wordCounts = 0.0;
                 TreeMap<Integer, Double> wordTimes = ngMap.countHistory(word, startYear, endYear);
                for (Double count: wordTimes.values()) {
                    wordCounts += count;
                }
                if (wordCounts > 0) {
                    wordsCount.put(word, wordCounts);
                }
            }
            //搜索得到的排序解决办法
            List<Map.Entry<String, Double>> countsList = new ArrayList<>(wordsCount.entrySet());
            countsList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

            Set<String> topK = new TreeSet<>();
            for (int i = 0; i < Math.min(k, countsList.size()); i++) {
                topK.add(countsList.get(i).getKey());
            }

            return topK.toString();
        }
        else {
            return hyponymsWords.toString();
        }
    }
}
