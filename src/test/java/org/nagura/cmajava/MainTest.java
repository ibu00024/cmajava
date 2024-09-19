package org.nagura.cmajava;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainTest {

    private Path tempDir;

    @BeforeEach
    public void setUp() throws Exception {
        tempDir = Files.createTempDirectory("testDir");
        Files.createFile(tempDir.resolve("TestFile1.java").toAbsolutePath());
        Files.createFile(tempDir.resolve("TestFile2.java").toAbsolutePath());
    }

    @Test
    public void testParsePathArgument_withValidArgument() throws Exception {
        String[] args = {"path/to/source"};

        Method method = Main.class.getDeclaredMethod("parsePathArgument", String[].class);
        method.setAccessible(true);

        String result = (String) method.invoke(null, (Object) args);
        assertThat(result, is("path/to/source"));
    }

    @Test
    public void testParsePathArgument_withNoArgument() throws Exception {
        String[] args = {};

        Method method = Main.class.getDeclaredMethod("parsePathArgument", String[].class);
        method.setAccessible(true);

        Throwable exception = null;
        try {
            method.invoke(null, (Object) args);
        } catch (Exception e) {
            exception = e.getCause();
        }

        assertThat(exception, is(notNullValue()));
        assertThat(exception instanceof IllegalArgumentException, is(true));
        assertThat(exception.getMessage(), is("No path provided. Please provide the file or directory path as an argument."));
    }

    @Test
    public void testResolveSourcePaths_withDirectory() throws Exception {
        Method method = Main.class.getDeclaredMethod("resolveSourcePaths", String.class);
        method.setAccessible(true);

        List<String> result = (List<String>) method.invoke(null, tempDir.toString());
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(2));
    }

    @Test
    public void testResolveSourcePaths_withFile() throws Exception {
        Path tempFile = Files.createTempFile("TestFile", ".java");

        Method method = Main.class.getDeclaredMethod("resolveSourcePaths", String.class);
        method.setAccessible(true);

        List<String> result = (List<String>) method.invoke(null, tempFile.toString());
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(tempFile.toString()));
    }

    @Test
    public void testResolveSourcePaths_withInvalidPath() throws Exception {
        Method method = Main.class.getDeclaredMethod("resolveSourcePaths", String.class);
        method.setAccessible(true);

        Throwable exception = null;
        try {
            method.invoke(null, "invalidPath");
        } catch (Exception e) {
            exception = e.getCause();
        }

        assertThat(exception, is(notNullValue()));
        assertThat(exception instanceof FileNotFoundException, is(true));
        assertThat(exception.getMessage(), is("The provided path does not exist: invalidPath"));
    }
}
