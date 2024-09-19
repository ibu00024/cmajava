package org.nagura.cmajava;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CodeMetricsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        CodeMetrics.resetMetrics();

        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testResetMetrics() {
        CodeMetrics.totalCodeLines = 10;
        CodeMetrics.resetMetrics();
        assertThat(CodeMetrics.totalCodeLines, is(0));
        assertThat(CodeMetrics.totalFiles, is(0));
        assertThat(CodeMetrics.totalBlankLines, is(0));
        assertThat(CodeMetrics.totalCommentLines, is(0));
    }

    @Test
    public void testDisplayCodeMetrics() {
        CodeMetrics.totalFiles = 5;
        CodeMetrics.totalCodeLines = 100;

        CodeMetrics.displayCodeMetrics();
        String output = outContent.toString();
        assertThat(output, containsString("Files"));
        assertThat(output, containsString("5"));
        assertThat(output, containsString("Code Lines"));
        assertThat(output, containsString("100"));
    }

    @Test
    public void testDisplayCodeMetricsCSV() {
        CodeMetrics.totalFiles = 2;
        CodeMetrics.totalBlankLines = 0;
        CodeMetrics.totalCommentLines = 10;
        CodeMetrics.totalCodeLines = 50;
        CodeMetrics.totalClasses = 0;
        CodeMetrics.totalMethodDeclarations = 0;
        CodeMetrics.totalMethodInvocations = 0;
        CodeMetrics.totalJavadocsOfMethod = 0;
        CodeMetrics.totalJavadocs = 0;


        CodeMetrics.displayCodeMetricsCSV();
        String csvOutput = outContent.toString().trim();
        assertThat(csvOutput, is("2,0,10,50,0,0,0,0,0"));
    }
}
