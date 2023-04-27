package com.goutham.examples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


@interface NonNull {}

/**
 * Unit test for simple App.
 */
public class Java11FeaturesTest {


    @BeforeEach
    public void doEach(){

    }
    @Test
    public void testFindDuplicatesArray() {

    }    @Test
    public void testStringOperations() {
        assertEquals("abcabc","abc".repeat(2));
        assertTrue("".isBlank());
        assertTrue(" ".isBlank());

        assertEquals("abc"," abc ".strip());
        assertEquals("abc "," abc ".stripLeading());
        assertEquals(" abc"," abc ".stripTrailing());

        String  sample = "This\nis\na\nmultiline\ntext.";
        List<String> lines = new ArrayList<>();

        sample.lines().forEach(line -> lines.add(line));
        assertEquals(5,lines.size());

    }

    @Test
    public void testAarrays() {
        List<String> namesList = Arrays.asList("Joe", "Julie");
        String[]   names = namesList.toArray(String[]::new);
        assertEquals(2,names.length);
    }


    @Test
    public void testFiles() throws IOException {
        Path tempFilePath = Files.writeString(
                Path.of(File.createTempFile("tempFile", ".tmp").toURI()),
                "HelloWorld",
                Charset.defaultCharset(), StandardOpenOption.WRITE);

        String fileContent = Files.readString(tempFilePath);
    assertEquals("HelloWorld",fileContent);
    }

    @Test
    public void testOptional(){
        String name = null;

        assertTrue(!Optional.ofNullable(name).isPresent());
        assertTrue(Optional.ofNullable(name).isEmpty());

        name = "Joe";
        assertFalse(!Optional.ofNullable(name).isPresent());
        assertFalse(Optional.ofNullable(name).isEmpty());
    }

    @Test
    public void testNotPredicate(){
        List<String> tutorialsList = Arrays.asList("Java", "\n", "HTML", " ");

        List<String> tutorials = tutorialsList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        assertEquals(2,tutorials.size());

    }

    @Test
    public void testVarInLambda(){
        List<String> tutorialsList = Arrays.asList("Java", "HTML");

        String tutorials = tutorialsList.stream()
                .map((@NonNull var tutorial) -> tutorial.toUpperCase())
                .collect(Collectors.joining(","));
        assertEquals("JAVA,HTML",tutorials);

        /*
        * There are certain limitations on using var in lambda expressions.

var parameters cannot be mixed with other parameters. Following will throw compilation error.

(var v1, v2) -> v1 + v2
var parameters cannot be mixed with other typed parameters. Following will throw compilation error.

(var v1, String v2) -> v1 + v2
var parameters can only be used with parenthesis. Following will throw compilation error.

var v1 -> v1.toLowerCase()
        * */

    }
}
