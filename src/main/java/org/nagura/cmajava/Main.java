package org.nagura.cmajava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = parsePathArgument(args);
            List<String> srcPathList = resolveSourcePaths(path);
            performAnalysis(srcPathList);
            displayResults(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String parsePathArgument(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No path provided. Please provide the file or directory path as an argument.");
        }
        return args[0];
    }

    private static List<String> resolveSourcePaths(String path) throws FileNotFoundException {
        File pathFile = new File(path);
        if (pathFile.isDirectory()) {
            return FileFinder.getFileFullName(path, ".java");
        } else if (pathFile.isFile()) {
            return Collections.singletonList(path);
        } else {
            throw new FileNotFoundException("The provided path does not exist: " + path);
        }
    }

    private static void performAnalysis(List<String> srcPathList) {
        BlockVisitor blockVisitor = new BlockVisitor();
        for (String srcPath : srcPathList) {
            ASTCreation.createAST(srcPath).accept(blockVisitor);
        }
    }

    private static void displayResults(String[] args) {
        String displayFormat = "table";
        if (args.length > 1) {
            displayFormat = args[1].toLowerCase();
        }
        switch (displayFormat) {
            case "csv":
                CodeMetrics.displayCodeMetricsCSV();
                break;
            case "table":
            default:
                CodeMetrics.displayCodeMetrics();
                break;
        }
    }
}
