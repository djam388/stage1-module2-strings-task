package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
    String text = source;
        for (String delimiter : delimiters) {
            text = text.replace(delimiter, " ");
        }
        String[] splittedSource = text.split(" ");
        List<String> listOfSource = new ArrayList<>();
        for (String s : splittedSource) {
            if (!s.equals("")) {
                listOfSource.add(s);
            }
        }
        return listOfSource;
    }
}
