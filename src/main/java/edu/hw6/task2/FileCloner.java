package edu.hw6.task2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class FileCloner {
    private static final String EXTENSION_DELIMITER = ".";
    private static final String FIRST_CLONE_PATTERN = "%s — копия%s";
    private static final String N_CLONE_PATTERN = "%s — копия (%d)%s";
    private static final int INITIAL_EXPLICIT_ID = 2;

    private FileCloner() {
    }

    public static void cloneFile(Path path) {
        Objects.requireNonNull(path);
        if (Files.notExists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Path does not match any regular file");
        }
        try {
            Files.copy(path, getSuitableCopy(path));
        } catch (IOException error) {
            throw new UncheckedIOException(error);
        }
    }

    private static Path getSuitableCopy(Path path) {
        FileNameAttributes fileNameAttributes = getFileNameAttributes(path);
        return selectRightCopyId(fileNameAttributes);
    }

    private static FileNameAttributes getFileNameAttributes(Path path) {
        String fullName = path.getFileName().toString();
        String parentPath = path.getParent().toString();

        if (hasExtension(fullName)) {
            int extensionIndex = fullName.indexOf(EXTENSION_DELIMITER);
            return new FileNameAttributes(
                fullName.substring(0, extensionIndex),
                fullName.substring(extensionIndex),
                parentPath
            );
        } else {
            return new FileNameAttributes(
                fullName,
                "",
                parentPath
            );
        }
    }

    private static boolean hasExtension(String fileName) {
        return fileName.contains(EXTENSION_DELIMITER);
    }

    private static Path selectRightCopyId(FileNameAttributes attributes) {
        Path copyPath = Path.of(
            attributes.parent(),
            FIRST_CLONE_PATTERN.formatted(attributes.name(), attributes.extension())
        );
        int cloneId = INITIAL_EXPLICIT_ID;
        while (Files.exists(copyPath)) {
            copyPath = Paths.get(
                attributes.parent(),
                N_CLONE_PATTERN.formatted(attributes.name(), cloneId, attributes.extension())
            );
            cloneId++;
        }
        return copyPath;
    }

    private record FileNameAttributes(String name, String extension, String parent) {
    }
}
