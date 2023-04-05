package Util;

import org.imdb.model.Movie;
import org.imdb.util.ImbdReader;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMDbReaderTest {

    @Test
    public void givenFiles_whenProcessing_returnMovies() throws IOException {
        MultipartFile basics = new MockMultipartFile("basics.tsv",
                new FileInputStream(
                        "/Users/noeliaiglesias/Documents/Sessions/" +
                                "imdb-project/src/test/java/Util/Files/" +
                                "basics-test.tsv"));
        MultipartFile akas = new MockMultipartFile("akas.tsv",
                new FileInputStream(
                        "/Users/noeliaiglesias/Documents/Sessions/" +
                                "imdb-project/src/test/java/Util/Files/" +
                                "akas-test.tsv"));
        MultipartFile ratings =
                new MockMultipartFile("ratings.tsv",
                        new FileInputStream(
                                "/Users/noeliaiglesias/Documents/Sessions/" +
                                        "imdb-project/src/test/java/Util/Files/" +
                                        "ratings-test.tsv"));
        MultipartFile crew = new MockMultipartFile("crew.tsv",
                new FileInputStream(
                        "/Users/noeliaiglesias/Documents/Sessions/" +
                                "imdb-project/src/test/java/Util/Files/" +
                                "crew-test.tsv"));
        MultipartFile principals =
                new MockMultipartFile("principals.tsv",
                        new FileInputStream(
                                "/Users/noeliaiglesias/Documents/Sessions/" +
                                        "imdb-project/src/test/java/Util/Files/" +
                                        "principals-test.tsv"));
        ImbdReader imbdReader = new ImbdReader(basics, akas, ratings, crew,
                principals);

        imbdReader.getLines();
        Movie movie = imbdReader.getMovie();
        assertEquals(movie.getPrimaryTitle(), "Carmencita");
    }

}
