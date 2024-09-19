package org.nagura.cmajava;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.io.FileNotFoundException;

public class FileFinderTest {

    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("testDir");

        Files.createFile(tempDir.resolve("TestFile1.java").toAbsolutePath());
        Files.createFile(tempDir.resolve("TestFile2.txt").toAbsolutePath());
        Files.createFile(tempDir.resolve("TestFile3.java").toAbsolutePath());

        Path subDir = Files.createDirectory(tempDir.resolve("subDir"));
        Files.createFile(subDir.resolve("SubTestFile.java").toAbsolutePath());
        Files.createFile(subDir.resolve("SubTestFile.txt").toAbsolutePath());
    }

    @Test
    public void testGetFileFullNameValidSuffix() throws FileNotFoundException {
        List<String> result = FileFinder.getFileFullName(tempDir.toString(), ".java");
        
        assertThat(result.size(), is(3));
        assertThat(result.get(0), containsString(".java"));
        assertThat(result.get(1), containsString(".java"));
        assertThat(result.get(2), containsString(".java"));
    }

    @Test
    public void testGetFileFullNameInvalidDirectory() {
        Exception exception = null;
        try {
            FileFinder.getFileFullName("invalidPath", ".java");
        } catch (FileNotFoundException e) {
            exception = e;
        }

        assertThat(exception, is(notNullValue()));
        assertThat(exception.getMessage(), containsString("Directory not found"));
    }

    @Test
    public void testGetFileFullNameDifferentSuffix() throws FileNotFoundException {
        List<String> result = FileFinder.getFileFullName(tempDir.toString(), ".txt");
        
        assertThat(result.size(), is(2));
        assertThat(result.get(0), containsString(".txt"));
        assertThat(result.get(1), containsString(".txt"));
    }
}
