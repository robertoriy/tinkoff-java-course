package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.nio.file.StandardOpenOption.APPEND;

public final class NiceWriter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_CONTENT = "Programming is learned by writing programs. ― Brian Kernighan";

    private NiceWriter() {
    }

    public static void println(Path path) {
        println(path, DEFAULT_CONTENT);
    }

    public static void println(Path path, String content) {
        createPathIfNotExist(path);

        try (OutputStream fos = Files.newOutputStream(path, APPEND);
             CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
             BufferedOutputStream bos = new BufferedOutputStream(cos);
             OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(osw)) {
            LOGGER.info("NiceWriter is built");
            printWriter.println(content);
        } catch (IOException error) {
            LOGGER.error("Something went wrong - {}", error.getMessage());
            throw new UncheckedIOException(error);
        }
    }

    private static void createPathIfNotExist(Path path) {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException error) {
            LOGGER.info("File already exist");
        } catch (IOException error) {
            LOGGER.error(error.getMessage());
            throw new BadPathException(error);
        }
    }
}
