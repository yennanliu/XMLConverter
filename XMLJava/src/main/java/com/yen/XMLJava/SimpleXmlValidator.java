package com.yen.XMLJava;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleXmlValidator {

    public static boolean determineXml(String xml) {
        if (xml == null || xml.isEmpty()) {
            return true;
        }

        Deque<String> stack = new ArrayDeque<>();
        int i = 0;

        while (i < xml.length()) {
            if (xml.charAt(i) == '<') {
                int closeIndex = findClosingBracket(xml, i);
                if (closeIndex == -1) {
                    return false;
                }

                String tagContent = xml.substring(i + 1, closeIndex);
                if (tagContent.isEmpty()) {
                    return false;
                }

                if (tagContent.charAt(0) == '/') {
                    String tagName = tagContent.substring(1);
                    if (stack.isEmpty() || !stack.pop().equals(tagName)) {
                        return false;
                    }
                } else {
                    stack.push(tagContent);
                }

                i = closeIndex + 1;
            } else {
                i++;
            }
        }

        return stack.isEmpty();
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
