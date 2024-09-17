package org.nagura.cmajava;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;;

public class ASTCreationTest {
    
    private String testFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        Path tempFile = Files.createTempFile("testFile", ".java");
        testFilePath = tempFile.toString();
        String fileContent = "public class Test {\n" +
                             "    public static void main(String[] args) {\n" +
                             "        // This is a test comment\n" +
                             "        System.out.println(\"Hello World\");\n" +
                             "    }\n" +
                             "}";
        Files.write(tempFile, fileContent.getBytes());

        CodeMetrics.resetMetrics();
    }

    @Test
    public void testCreateAST() {
        CompilationUnit unit = ASTCreation.createAST(testFilePath);
        assertThat(unit, is(notNullValue()));
        assertThat(CodeMetrics.totalFiles, is(1));
    }

    @Test
    public void testCodeMetricsCalculation() throws IOException {
        ASTCreation.createAST(testFilePath);
        assertThat(CodeMetrics.totalCodeLines, is(5));
        assertThat(CodeMetrics.totalCommentLines, is(1));
        assertThat(CodeMetrics.totalBlankLines, is(0));
    }
}
