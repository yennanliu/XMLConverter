package com.yen.XMLJava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleXmlValidatorTest {

    @Test
    void validNestedTags() {
        String xml = "<Design><Code>hello world</Code></Design>";
        assertEquals("Valid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void unclosedTag() {
        String xml = "<Design><Code>hello world</Code></Design><People>";
        assertEquals("Invalid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void wrongNesting() {
        String xml = "<People><Design><Code>hello world</People></Code></Design>";
        assertEquals("Invalid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void attributeMismatch() {
        String xml = "<People age=\"1\">hello world</People>";
        assertEquals("Invalid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void emptyString() {
        assertEquals("Valid", SimpleXmlValidator.determineXml(""));
    }

    @Test
    void nullInput() {
        assertEquals("Valid", SimpleXmlValidator.determineXml(null));
    }

    @Test
    void textOnly() {
        assertEquals("Valid", SimpleXmlValidator.determineXml("hello world"));
    }

    @Test
    void singleTag() {
        assertEquals("Valid", SimpleXmlValidator.determineXml("<tag></tag>"));
    }

    @Test
    void unclosedBracket() {
        assertEquals("Invalid", SimpleXmlValidator.determineXml("<tag"));
    }

    @Test
    void emptyTagName() {
        assertEquals("Invalid", SimpleXmlValidator.determineXml("<>test</>"));
    }

    @Test
    void mismatchedTagNames() {
        assertEquals("Invalid", SimpleXmlValidator.determineXml("<open>text</close>"));
    }

    @Test
    void extraClosingTag() {
        assertEquals("Invalid", SimpleXmlValidator.determineXml("</extra>"));
    }

    @Test
    void deeplyNested() {
        String xml = "<a><b><c><d>content</d></c></b></a>";
        assertEquals("Valid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void multipleSiblings() {
        String xml = "<root><a>1</a><b>2</b><c>3</c></root>";
        assertEquals("Valid", SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void matchingAttributes() {
        String xml = "<People age=\"1\">hello world</People age=\"1\">";
        assertEquals("Valid", SimpleXmlValidator.determineXml(xml));
    }
}
