# Java Source Code Analysis Tool - cmajava
This program is a tool for analyzing Java source code and collecting code metrics.

It calculates various metrics such as the number of classes, method declarations, method invocations, and the number of Javadocs in the code.

[日本語版 README](https://github.com/ibu00024/cmajava/blob/main/README-ja.md)
## Prerequisites

- Java 8 or higher installed
- Maven installed

## Build Instructions

1. Clone or download the project.
2. Run the following command in the command line to build the project:
    ```
    mvn clean install
    ```
    Upon successful build, an executable jar file will be created in the `target` directory.

## How to Run

After building, run the program using the following command:
```
java -jar target/CodeMetricAnalyzer.jar <path> [display format]
```
`<path>` is the path to the Java file or directory to analyze.

The `[display format]` is optional and can be `table` or `csv` (default is `table`).

## Example Output

```
Metric                             Count
----------------------------------------
Files                                 20    // Number of files
Blank Lines                          298    // Number of blank lines
Comment Lines                        693    // Number of comment lines
Code Lines                          1704    // Number of code lines
Classes                               20    // Number of classes
Method Declarations                  229    // Number of method declarations
Method Invocations                   527    // Number of method invocations
Javadocs Of Method                    79    // Number of Javadocs of methods
All Javadocs                          94    // Number of Javadocs
```
