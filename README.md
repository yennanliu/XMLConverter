# XML Validator

A simple XML tag validator that checks if an XML string has properly matched and nested tags.

## What This App Does

This console application validates XML strings based on two rules:

1. **Tag Matching**: Every opening tag must have a corresponding closing tag with an identical string
2. **Proper Nesting**: Tags must be well-nested (first opened = last closed)

Key behavior:
- Tags are matched by exact string comparison (including attributes)
- `<tag attr="1">` does NOT match `</tag>` (different strings)
- `<tag attr="1">` DOES match `</tag attr="1">` (identical strings)
- No XML libraries or regex are used

## How to Run

### Prerequisites

- Java 17+
- Maven 3.6+ (or use the included Maven wrapper)

### Run Tests

```bash
# Using Maven wrapper
./mvnw compile exec:java -Dexec.mainClass="com.yen.XMLJava.XmlJavaApplication" -q

# Or with Maven
mvn compile exec:java -Dexec.mainClass="com.yen.XMLJava.XmlJavaApplication" -q
```

Expected output:
```
OK  <Design><Code>hello world</Code></Design>: Valid
OK  <Design><Code>hello world</Code></Design><People>: Invalid
OK  <People><Design><Code>hello world</People></Code></Design>: Invalid
OK  <People age="1">hello world</People>: Invalid
...
Result: 14/14
```

### Run Unit Tests

```bash
./mvnw test
```

## Development Guide

### Project Structure

```
src/main/java/com/yen/XMLJava/
    SimpleXmlValidator.java   # Core validation logic
    TestCase.java             # Test case data holder
    XmlJavaApplication.java   # Main entry point with test runner

src/test/java/com/yen/XMLJava/
    SimpleXmlValidatorTest.java   # JUnit 5 unit tests
```

### Core Algorithm

The validator uses a stack-based approach:

1. Iterate through the string character by character
2. When `<` is found, extract content until `>`
3. If closing tag (starts with `/`): pop from stack and compare
4. If opening tag: push onto stack
5. Valid if stack is empty at the end

### Adding Test Cases

Edit `XmlJavaApplication.java`:

```java
testCaseList.add(new TestCase("<your>xml</your>", true));  // expected: valid
testCaseList.add(new TestCase("<broken>xml", false));       // expected: invalid
```

### Debugging

1. Run with verbose Maven output:
   ```bash
   ./mvnw compile exec:java -Dexec.mainClass="com.yen.XMLJava.XmlJavaApplication"
   ```

2. Add debug output in `SimpleXmlValidator.java`:
   ```java
   System.out.println("Tag found: " + tagContent);
   System.out.println("Stack: " + stack);
   ```

## Further Improvements

1. **Better Error Messages**: Return specific error info (line number, mismatched tag names) instead of just true/false

2. **Self-Closing Tags**: Handle `<br/>` style tags if needed

3. **Performance**: For very large XML strings, could optimize by avoiding substring allocations

4. **Edge Cases**: Consider handling:
   - CDATA sections
   - Comments `<!-- -->`
   - Processing instructions `<?xml ?>`
   - Nested angle brackets in attribute values

5. **Streaming Parser**: For very large files, implement a streaming approach instead of loading entire string
