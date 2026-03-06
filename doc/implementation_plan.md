# XML Validator Implementation Plan

## Problem Summary

Build a console application that validates XML strings based on:

1. Every opening tag must have a matching closing tag (exact string match)
2. Tags must be properly nested (LIFO - first opened, last closed)
3. No XML libraries or regex allowed

## Core Algorithm: Stack-Based Validation

This is a classic stack problem:

1. Iterate through the string character by character
2. When encountering `<`:
   - Find the matching `>`
   - If it's a closing tag (starts with `/`):
     - Pop from stack and compare with tag name
     - If mismatch or stack empty, return Invalid
   - If it's an opening tag:
     - Push the tag content onto the stack
3. After processing: stack must be empty for Valid

## Design Decisions

| Scenario | Handling |
|----------|----------|
| `<tag>` vs `</tag>` | Compare full content between `<`/`</` and `>` |
| `<tag attr="x">` vs `</tag>` | Invalid (different strings per requirement) |
| Unclosed `<` bracket | Invalid |
| Empty input / no tags | Valid (no unmatched tags) |
| Self-closing `<br/>` | Treat as opening tag (would be invalid without closing) |

## Class Structure

```
SimpleXmlValidator.java
    - determineXml(String xml): returns "Valid" or "Invalid"
    - helper methods for parsing tags

Main.java
    - main(String[] args): reads CLI arg, calls validator, prints result
```

## Complexity Analysis

- Time: O(n) - single pass through the string
- Space: O(n) - worst case all opening tags stored in stack
