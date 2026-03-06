package com.yen.XMLJava;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleXmlValidator {

    public static String determineXml(String xml) {
        if (xml == null || xml.isEmpty()) {
            return "Valid";
        }

        Deque<String> stack = new ArrayDeque<>();
        int i = 0;

        while (i < xml.length()) {
            if (xml.charAt(i) == '<') {
                int closeIndex = findClosingBracket(xml, i);
                if (closeIndex == -1) {
                    return "Invalid";
                }

                String tagContent = xml.substring(i + 1, closeIndex);
                if (tagContent.isEmpty()) {
                    return "Invalid";
                }

                if (tagContent.charAt(0) == '/') {
                    String tagName = tagContent.substring(1);
                    if (stack.isEmpty() || !stack.pop().equals(tagName)) {
                        return "Invalid";
                    }
                } else {
                    stack.push(tagContent);
                }

                i = closeIndex + 1;
            } else {
                i++;
            }
        }

        return stack.isEmpty() ? "Valid" : "Invalid";
    }

    private static int findClosingBracket(String xml, int start) {
        for (int i = start + 1; i < xml.length(); i++) {
            if (xml.charAt(i) == '>') {
                return i;
            }
        }
        return -1;
    }
}
