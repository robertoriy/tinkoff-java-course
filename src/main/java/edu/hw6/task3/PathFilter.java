package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface PathFilter extends DirectoryStream.Filter<Path> {

    PathFilter and(PathFilter filter);

    PathFilter regularFile();

    PathFilter readable();

    PathFilter writable();

    PathFilter largerThan(int size);

    PathFilter magicNumber(int... magicNumbers);

    PathFilter globMatches(String glob);

    PathFilter regexContains(String regex);
}
