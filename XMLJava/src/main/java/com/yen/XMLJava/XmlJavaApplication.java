package com.yen.XMLJava;

import java.util.ArrayList;

public class XmlJavaApplication {

    public static void main(String[] args) {
        ArrayList<TestCase> testCaseList = new ArrayList<>();

        // Examples from requirements
        testCaseList.add(new TestCase("<Design><Code>hello world</Code></Design>", true));
        testCaseList.add(new TestCase("<Design><Code>hello world</Code></Design><People>", false));
        testCaseList.add(new TestCase("<People><Design><Code>hello world</People></Code></Design>", false));
        testCaseList.add(new TestCase("<People age=\"1\">hello world</People>", false));

        // Additional edge cases
        testCaseList.add(new TestCase("", true));
        testCaseList.add(new TestCase("hello world", true));
        testCaseList.add(new TestCase("<tag></tag>", true));
        testCaseList.add(new TestCase("<tag", false));
        testCaseList.add(new TestCase("<>test</>", false));
        testCaseList.add(new TestCase("<open>text</close>", false));
        testCaseList.add(new TestCase("</extra>", false));
        testCaseList.add(new TestCase("<a><b><c><d>content</d></c></b></a>", true));
        testCaseList.add(new TestCase("<root><a>1</a><b>2</b><c>3</c></root>", true));
        testCaseList.add(new TestCase("<People age=\"1\">hello world</People age=\"1\">", true));

        int failedCount = 0;
        for (int i = 0; i < testCaseList.size(); i++) {
            TestCase testCase = testCaseList.get(i);
            boolean result = SimpleXmlValidator.determineXml(testCase.input);
            String resultStr = result ? "Valid" : "Invalid";

            String mark;
            if (result == testCase.expectedOutput) {
                mark = "OK ";
            } else {
                mark = "NG ";
                failedCount++;
            }
            System.out.println(mark + " " + testCase.input + ": " + resultStr);
        }
        System.out.println("Result: " + (testCaseList.size() - failedCount) + "/" + testCaseList.size());
    }
}
