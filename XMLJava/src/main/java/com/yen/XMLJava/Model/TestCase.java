package com.yen.XMLJava.Model;

public class TestCase {
    public final String input;
    public final boolean expectedOutput;

    public TestCase(String input, boolean expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }
}
