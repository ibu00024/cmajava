package org.nagura.cmajava;

/**
 * The {@code CodeMetrics} class is a utility class for collecting and displaying various metrics
 * related to source code analysis. These metrics include counts of total files, blank lines,
 * comment lines, code lines, classes, method declarations, method invocations, and Javadocs.
 * 
 * <p>This class is designed to be used in conjunction with a source code analysis tool or process
 * where these metrics are gathered during the analysis of Java source files. It provides a static
 * method to display the collected metrics in a formatted table.
 * 
 * <p>All metrics are stored as static variables and are intended to be incremented during the
 * analysis process. The {@code displayCodeMetrics} method outputs the current values of these
 * metrics in a formatted table.
 */
public class CodeMetrics {
    static int totalFiles = 0;
    static int totalBlankLines = 0;
    static int totalCommentLines = 0;
    static int totalCodeLines = 0;

    static int totalClasses = 0;
    static int totalMethodDeclarations = 0;
    static int totalMethodInvocations = 0;
    static int totalJavadocsPerMethod = 0;
    static int totalJavadocs = 0;

    public static void displayCodeMetrics() {
        // Set column width
        int columnWidth = 20;
    
        // Output header line
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "s\n", "Metric", "Count");
        System.out.println(String.format("%" + (columnWidth * 2) + "s", "").replace(' ', '-'));
    
        // Output each metric
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Files", totalFiles);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Blank Lines", totalBlankLines);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Comment Lines", totalCommentLines);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Code Lines", totalCodeLines);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Classes", totalClasses);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Method Declarations", totalMethodDeclarations);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Method Invocations", totalMethodInvocations);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Javadocs Per Method", totalJavadocsPerMethod);
        System.out.printf("%-" + columnWidth + "s%" + columnWidth + "d\n", "Total Javadocs", totalJavadocs);
    }

    public static void displayCodeMetricsCSV() {
        System.out.printf("%d,%d,%d,%d,%d,%d,%d,%d,%d\n",
                        totalFiles,
                        totalBlankLines,
                        totalCommentLines,
                        totalCodeLines,
                        totalClasses,
                        totalMethodDeclarations,
                        totalMethodInvocations,
                        totalJavadocsPerMethod,
                        totalJavadocs);
    }
    
}
