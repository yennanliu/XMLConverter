package com.yen.XMLJava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SimpleXmlValidatorTest {

    @Test
    void validNestedTags() {
        String xml = "<Design><Code>hello world</Code></Design>";
        assertTrue(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void unclosedTag() {
        String xml = "<Design><Code>hello world</Code></Design><People>";
        assertFalse(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void wrongNesting() {
        String xml = "<People><Design><Code>hello world</People></Code></Design>";
        assertFalse(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void attributeMismatch() {
        String xml = "<People age=\"1\">hello world</People>";
        assertFalse(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void emptyString() {
        assertTrue(SimpleXmlValidator.determineXml(""));
    }

    @Test
    void nullInput() {
        assertTrue(SimpleXmlValidator.determineXml(null));
    }

    @Test
    void textOnly() {
        assertTrue(SimpleXmlValidator.determineXml("hello world"));
    }

    @Test
    void singleTag() {
        assertTrue(SimpleXmlValidator.determineXml("<tag></tag>"));
    }

    @Test
    void unclosedBracket() {
        assertFalse(SimpleXmlValidator.determineXml("<tag"));
    }

    @Test
    void emptyTagName() {
        assertFalse(SimpleXmlValidator.determineXml("<>test</>"));
    }

    @Test
    void mismatchedTagNames() {
        assertFalse(SimpleXmlValidator.determineXml("<open>text</close>"));
    }

    @Test
    void extraClosingTag() {
        assertFalse(SimpleXmlValidator.determineXml("</extra>"));
    }

    @Test
    void deeplyNested() {
        String xml = "<a><b><c><d>content</d></c></b></a>";
        assertTrue(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void multipleSiblings() {
        String xml = "<root><a>1</a><b>2</b><c>3</c></root>";
        assertTrue(SimpleXmlValidator.determineXml(xml));
    }

    @Test
    void matchingAttributes() {
        String xml = "<People age=\"1\">hello world</People age=\"1\">";
        assertTrue(SimpleXmlValidator.determineXml(xml));
    }
}
