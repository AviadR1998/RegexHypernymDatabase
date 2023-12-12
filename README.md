# 𝓡𝓮𝓰𝓮𝔁 𝓗𝔂𝓹𝓮𝓻𝓷𝔂𝓶 𝓓𝓪𝓽𝓪𝓫𝓪𝓼𝓮


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


# Installation

## 🚀 Quick Start (Method 1)

- Clone the repository:
    ```bash
    $ git clone https://github.com/AviadR1998/RegexHypernymDatabase.git
    ```

- Download the corpus from [this location](https://drive.google.com/drive/folders/11aVnX9r-k5iy2GafZd-o5lBBgeNRuFDN?usp=sharing).

- Launch your preferred integrated development environment (IDE), such as IntelliJ or Eclipse.

- Confirm that an up-to-date JDK is configured in the project settings.

- Set up a configuration with `CreateHypernymDatabase` as the Main Class and provide two arguments:
   - First argument: The directory path of the corpus.
   - Second argument: Choose a name for the new text file.

- Execute the configuration, and you're all set to explore the Regex Hypernym Database!

## 🛠 Advanced Setup (Method 2)

1. Clone the repository:
    ```bash
    $ git clone https://github.com/AviadR1998/RegexHypernymDatabase.git
    ```

2. **Download the corpus** from [this location](https://drive.google.com/drive/folders/11aVnX9r-k5iy2GafZd-o5lBBgeNRuFDN?usp=sharing).

3. Install [Apache Ant](https://ant.apache.org/bindownload.cgi). Refer to [this video guide](https://www.youtube.com/watchv=3eaW81yYIqY&t=353s&ab_channel=xscourse) for assistance with Apache Ant installation on Windows.

4. Open the command prompt (CMD) in the cloned directory and execute the following commands:
    ```bash
    ant compile
    ant run1 (corpus directory, output filename)
    ant run2 (corpus directory, lemma)
    ```

## 🤝 Get Involved
Feel free to contribute, report issues, or suggest new features – your input is highly valued!

If you find this project helpful or interesting, show your support by giving it a ⭐️!

