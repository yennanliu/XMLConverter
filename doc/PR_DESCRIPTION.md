# Pull Request: XML Validator Implementation

## Summary

Implemented a stack-based XML tag validator that checks for proper tag matching and nesting without using any XML libraries or regular expressions.

## Changes

### New Files

- `SimpleXmlValidator.java` - Core validation logic with `determineXml(String xml)` method
- `TestCase.java` - Simple data class for holding test input and expected output
- `SimpleXmlValidatorTest.java` - JUnit 5 unit tests (16 test cases)
- `README.md` - Project documentation

### Modified Files

- `XmlJavaApplication.java` - Added test runner in main() method
- `pom.xml` - Added JUnit 5 dependency

## Implementation Details

### Algorithm

Uses a stack-based approach with O(n) time complexity:

```
for each character in xml:
    if '<' found:
        extract tag content until '>'
        if closing tag (starts with '/'):
            pop from stack, compare with tag name
            if mismatch -> invalid
        else:
            push tag content onto stack

return stack.isEmpty()
```

### Design Decisions

| Decision | Rationale |
|----------|-----------|
| Return `boolean` | Matches template specification |
| `ArrayDeque` for stack | More efficient than `Stack` class |
| Exact string matching | Per requirements, `<tag attr="">` != `</tag>` |
| Empty/null input = valid | No unmatched tags means valid |
| Text without tags = valid | Plain text has no tag violations |

### Edge Cases Handled

- Empty string
- Null input
- Plain text (no tags)
- Unclosed `<` bracket
- Empty tag names `<>`
- Mismatched tag names
- Extra closing tags with no opener
- Deeply nested tags
- Multiple sibling tags
- Tags with attributes

## Testing

All 14 test cases in main() pass:

```
OK  <Design><Code>hello world</Code></Design>: Valid
OK  <Design><Code>hello world</Code></Design><People>: Invalid
OK  <People><Design><Code>hello world</People></Code></Design>: Invalid
OK  <People age="1">hello world</People>: Invalid
...
Result: 14/14
```

JUnit tests also pass:

```bash
./mvnw test
# 16 tests, 0 failures
```

## Not Implemented (Out of Scope)

- Self-closing tags (`<br/>`)
- XML comments (`<!-- -->`)
- CDATA sections
- Processing instructions (`<?xml ?>`)
- Detailed error messages with line numbers

These could be added in future iterations if needed.
