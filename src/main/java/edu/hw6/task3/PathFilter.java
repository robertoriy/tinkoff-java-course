package edu.hw6.task3;

import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface PathFilter extends DirectoryStream.Filter<Path> {
    static PathFilter largerThan(int size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (Exception error) {
                throw new FilterException(error);
            }
        };
    }

    static PathFilter magicNumber(int... magicNumbers) {
        return path -> {
            try (InputStream is = Files.newInputStream(path)) {
                for (int magicNumber : magicNumbers) {
                    if (is.read() != magicNumber) {
                        return false;
                    }
                }
            } catch (Exception error) {
                throw new FilterException(error);
            }
            return true;
        };
    }

    static PathFilter globMatches(String glob) {
        return path -> {
            try {
                PathMatcher pathMatcher = path.getParent().getFileSystem().getPathMatcher("glob:" + glob);
                return pathMatcher.matches(path.getFileName());
            } catch (Exception error) {
                throw new FilterException(error);
            }
        };
    }

    static PathFilter regexContains(String regex) {
        return path -> {
            try {
                return Pattern.compile(regex).matcher(path.toString()).find();
            } catch (Exception error) {
                throw new FilterException(error);
            }
        };
    }

    default PathFilter and(PathFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }
}
