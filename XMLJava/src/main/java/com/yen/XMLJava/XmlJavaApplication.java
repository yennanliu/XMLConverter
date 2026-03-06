package com.yen.XMLJava;

import com.yen.XMLJava.Model.TestCase;
import com.yen.XMLJava.Validator.SimpleXmlValidator;

import java.util.ArrayList;
import java.util.List;

public class XmlJavaApplication {

    public static void main(String[] args) {
        List<TestCase> testCases = buildTestCases();
        runTests(testCases);
    }

    private static List<TestCase> buildTestCases() {
        List<TestCase> testCases = new ArrayList<>();

        // Examples from requirements
        testCases.add(new TestCase("<Design><Code>hello world</Code></Design>", true));
        testCases.add(new TestCase("<Design><Code>hello world</Code></Design><People>", false));
        testCases.add(new TestCase("<People><Design><Code>hello world</People></Code></Design>", false));
        testCases.add(new TestCase("<People age=\"1\">hello world</People>", false));

        // Edge cases
        testCases.add(new TestCase("", true));
        testCases.add(new TestCase("hello world", true));
        testCases.add(new TestCase("<tag></tag>", true));
        testCases.add(new TestCase("<tag", false));
        testCases.add(new TestCase("<>test</>", false));
        testCases.add(new TestCase("<open>text</close>", false));
        testCases.add(new TestCase("</extra>", false));
        testCases.add(new TestCase("<a><b><c><d>content</d></c></b></a>", true));
        testCases.add(new TestCase("<root><a>1</a><b>2</b><c>3</c></root>", true));
        testCases.add(new TestCase("<People age=\"1\">hello world</People age=\"1\">", true));

        return testCases;
    }

    private static void runTests(List<TestCase> testCases) {
        int failedCount = 0;

        for (TestCase testCase : testCases) {
            boolean result = SimpleXmlValidator.determineXml(testCase.input);
            boolean passed = result == testCase.expectedOutput;

            if (!passed) {
                failedCount++;
            }

            String mark = passed ? "OK " : "NG ";
            String resultStr = result ? "Valid" : "Invalid";
            System.out.println(mark + " " + testCase.input + ": " + resultStr);
        }

        System.out.println("Result: " + (testCases.size() - failedCount) + "/" + testCases.size());
    }
}
