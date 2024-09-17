package org.nagura.cmajava;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class BlockVisitorTest {

    private String testFilePath;

    @BeforeEach
    public void setUp() throws Exception {
        Path tempFile = Files.createTempFile("testBlockVisitorFile", ".java");
        testFilePath = tempFile.toString();
        String fileContent = "public class TestClass {\n" +
                             "    /** This is a javadoc for the method */\n" +
                             "    public void testMethod() {\n" +
                             "        System.out.println(\"Hello World\");\n" +
                             "    }\n" +
                             "}" + 
                             "class TestClass {\n" +
                             "    // This is a line comment\n" +
                             "    public void testMethod() {\n" +
                             "        System.out.println(\"Hello World\");\n" +
                             "    }\n" +
                             "}" ;
        Files.write(tempFile, fileContent.getBytes());

        CodeMetrics.resetMetrics();
    }

    @Test
    public void testBlockVisitorMetrics() {
        CompilationUnit unit = ASTCreation.createAST(testFilePath);
        assertThat(unit, is(notNullValue()));

        BlockVisitor visitor = new BlockVisitor();
        unit.accept(visitor);

        assertThat(CodeMetrics.totalClasses, is(2));
        assertThat(CodeMetrics.totalMethodDeclarations, is(2));
        assertThat(CodeMetrics.totalMethodInvocations, is(2));
        assertThat(CodeMetrics.totalJavadocsPerMethod, is(1));
        assertThat(CodeMetrics.totalJavadocs, is(1));
    }
}
