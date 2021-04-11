package com.efimchick.ifmo.collections.countwords;


import java.util.*;

public class Words {
    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        if (res > 0) {
                            return -1;
                        } else if (res < 0) {
                            return 1;
                        } else {
                            String temp1 = (String) e1.getKey();
                            String temp2 = (String) e2.getKey();
                            int compKeys = temp1.compareTo(temp2);
                            if (compKeys > 0) {
                                return 1;
                            } else if (compKeys < 0) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public String countWords(List<String> lines) {
        Map<String, Integer> wordMap = new TreeMap<String, Integer>();

        for (String line : lines) {
            line = line.toLowerCase().replaceAll("[^А-Яа-яA-Za-z\\s]", " ");
            String[] words = line.split("\\s++");
            for (int i = 0; i < words.length; i++) {
                String temp = words[i].trim();
                if (temp.length() >= 4) {
                    if (wordMap.containsKey(temp)) {
                        wordMap.put(temp, wordMap.get(temp) + 1);
                    } else {
                        wordMap.put(temp, 1);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder("");

        for (Map.Entry<String, Integer> word : entriesSortedByValues(wordMap)) {
            if (word.getValue() >= 10) {
                sb.append(word.getKey() + " - " + word.getValue() + "\n");
            }
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}


