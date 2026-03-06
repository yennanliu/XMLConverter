package com.yen.XMLJava;

public class XmlJavaApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java XmlJavaApplication <xml-string>");
            return;
        }

        String result = SimpleXmlValidator.determineXml(args[0]);
        System.out.println(result);
    }
}
