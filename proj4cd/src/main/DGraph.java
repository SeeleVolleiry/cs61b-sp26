package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class DGraph {

    // attributes
    Map<Integer, List<Integer>> adj;
    Map<String, Set<Integer>> wordToId;
    Map<Integer, Set<String>> idToWord;
    // constructor
    public DGraph(String synsetFilename, String hyponymFilename) { // 读入synset文件和hyponyms文件创建有向图
        // first: initiate the instance variables
        adj = new HashMap<Integer, List<Integer>>();
        wordToId = new HashMap<String, Set<Integer>>();
        idToWord = new HashMap<Integer, Set<String>>();
        // second: read in synset files
        In synsetIn = new In(synsetFilename);
        while (synsetIn.hasNextLine()) {
            String nextLine = synsetIn.readLine();
            String[] splitLine = nextLine.split(",");
            int synsetID = Integer.parseInt(splitLine[0]); // synset id
            String[] words = splitLine[1].split(" "); // 每个同义词组成的数组
            Set<String> synset = new TreeSet<>(Arrays.asList(words)); // 同义词组成的集合
            this.addNode(synsetID, synset);
        }
        // third: read in hyponyms files
        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String nextLine = hyponymIn.readLine();
            String[] splitLine = nextLine.split(",");
            int hyperID = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                int id = Integer.parseInt(splitLine[i]);
                this.addEdge(hyperID, id);
            }
        }
    }
    //methods
    public void addNode(int synsetID, Set<String> synset) { // idToWord and wordToId
        idToWord.put(synsetID, synset);
        for (String word: synset) {
            if (!wordToId.containsKey(word)) {
                wordToId.put(word, new TreeSet<>());
            }
            wordToId.get(word).add(synsetID);
        }
    }

    public void addEdge(int hyperID, int hypoID) { // update adj.
        if (!adj.containsKey(hyperID)) {
            adj.put(hyperID, new ArrayList<>());
        }
        adj.get(hyperID).add(hypoID);
    }

    public Set<Integer> lookWord(String word) {
        if (!wordToId.containsKey(word)) {
            return new TreeSet<>();
        }
        return new TreeSet<Integer>(wordToId.get(word));
    }

    public Set<String> lookID(int synsetID) {
        if (!idToWord.containsKey(synsetID)) {
            return new TreeSet<String>();
        }
        return new TreeSet<>(idToWord.get(synsetID));
    }

    public List<Integer> lookAdj(int hyperID) { // 查询邻居：一代
        if (!adj.containsKey(hyperID)) {
            return new ArrayList<Integer>();
        }
        return new ArrayList<>(adj.get(hyperID));
    }

    public Set<String> findHypos(Set<Integer> synsetID) {
        Deque<Integer> deq = new ArrayDeque<>(); // BFS的fringe——队列
        deq.addAll(synsetID);
        // 已经访问过的id
        Set<Integer> visited = new HashSet<>(synsetID);
        while (!deq.isEmpty()) { // BFS implementation
            int id = deq.pollFirst();
            for (Integer hyponymID: lookAdj(id)) {
                if (!visited.contains(hyponymID)) {
                    deq.addLast(hyponymID);
                    visited.add(hyponymID);
                }
            }
        }

        Set<String> words = new TreeSet<>(); // 所有下位词的集合
        for (Integer id: visited) {
            words.addAll(lookID(id));
        }
        return words;
    }

    public static void main(String[] args) {

    }
}
