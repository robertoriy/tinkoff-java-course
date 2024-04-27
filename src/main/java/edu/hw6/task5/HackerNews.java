package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HackerNews {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MOST_DISCUSSED_ARTICLES_URI =
        "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ARTICLE_INFO_URI =
        "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Pattern TITLE_PATTERN = Pattern.compile(".*\"title\":\"([^\"]*)\".*");

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        Optional<HttpResponse<String>> response = makeRequest(MOST_DISCUSSED_ARTICLES_URI);
        if (response.isEmpty()) {
            return new long[0];
        }
        return parseIDs(response.get());
    }

    public static String news(long id) {
        Optional<HttpResponse<String>> response = makeRequest(ARTICLE_INFO_URI.formatted(id));
        if (response.isEmpty()) {
            return "N/A";
        }
        return parseTitle(response.get());
    }

    private static Optional<HttpResponse<String>> makeRequest(String uri) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatusCode.OK.getValue()) {
                return Optional.of(response);
            } else {
                LOGGER.error("Status code for request {} is {}", uri, response.statusCode());
            }
        } catch (Exception error) {
            LOGGER.error("Request error: {} - with message: {}", error, error.getMessage());
        }
        return Optional.empty();
    }

    private static long[] parseIDs(HttpResponse<String> response) {
        try {
            String[] longStrings = response.body().replaceAll("\\[|\\]", "").split(",");
            return Arrays.stream(longStrings)
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (Exception error) {
            LOGGER.error("Parse error: {} - with message: {}", error, error.getMessage());
            throw new InvalidContentException(error);
        }
    }

    private static String parseTitle(HttpResponse<String> response) {
        Matcher matcher = TITLE_PATTERN.matcher(response.body());
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            LOGGER.error("Parse error: No title in content");
            throw new InvalidContentException();
        }
    }
}
