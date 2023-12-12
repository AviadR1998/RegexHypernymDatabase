# Regex Hypernym Database

## Introduction

Welcome to the Regex Hypernym Database, a Java program designed with two key features to enhance your text analysis capabilities.

### Feature 1: Hypernym Relation Aggregation

The first feature empowers the program to traverse through a specified directory, meticulously extracting hypernym relations based on predefined Hearst patterns. Using regular expressions, the program identifies patterns such as:

- 💥 NP {,} such as NP {, NP, ..., {and|or} NP}.
- 💥 such NP as NP {, NP, ..., {and|or} NP}.
- 💥 NP {,} including NP {, NP, ..., {and|or} NP}.
- 💥 NP {,} especially NP {, NP, ..., {and|or} NP}.
- 💥 NP {,} which is {{an example|a kind|a class} of} NP.

The extracted relations are then aggregated and saved in a text file. The resulting format is as follows:

```plaintext
hypernym: hyponym1 (x), hyponym2 (x) ...
hypernym: hyponym1 (x), hyponym2 (x) ...
...
```
where (x) signifies the occurrence count of relations across all patterns in the corpus. The co-hyponyms under each hypernym are sorted in descending order based on their occurrence count.

### Feature 2: Hypernym Search and Display
The second feature allows users to input a lemma, prompting the program to search for all possible hypernyms related to the lemma. The results are then displayed in the console, sorted in descending order based on occurrence count across all patterns. The format is as follows:

```plaintext
hypernym1: (x)
hypernym2: (x)
...
```
where (x) denotes the occurrence count of relations in the corpus.

This implementation leverages the power of Hearst patterns and regular expressions to provide a comprehensive and efficient solution for hypernym extraction and analysis in textual data. Feel free to explore and integrate this tool into your Java projects for enhanced text processing capabilities.
