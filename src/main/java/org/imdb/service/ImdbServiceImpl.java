package org.imdb.service;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import org.imdb.model.Movie;
import org.imdb.repositories.ElasticsearchEngine;
import org.imdb.util.ImbdReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImdbServiceImpl implements ImdbService{
    private ImbdReader reader;
    private final ElasticsearchEngine elasticsearchEngine;

    private final int NUM_MOVIES = 50000;

    @Autowired
    public ImdbServiceImpl(ElasticsearchEngine elasticsearchEngine) {
        this.elasticsearchEngine = elasticsearchEngine;
    }

    @Async
    @Override
    public void uploadFiles(MultipartFile basics, MultipartFile akas,
                            MultipartFile ratings, MultipartFile crew,
                            MultipartFile participants) throws IOException {
        try{
            if(basics.isEmpty() || akas.isEmpty() || ratings.isEmpty() ||
                    crew.isEmpty() || participants.isEmpty() ){
                throw new IOException("File is empty");
            }

            List<Movie> movies = new ArrayList<>();
            reader = new ImbdReader(basics, akas, ratings, crew, participants);
            int counter = 0;
            int tandas = 0;

            while(reader.lines) {
                reader.getLines();
                Movie movie = reader.getMovie();

                if (movie != null) {
                    movies.add(movie);
                    counter++;
                }
                if(movies.size() == 179){
                    int aux = 0;
                }
                if(counter == NUM_MOVIES){
                    elasticsearchEngine.indexDocuments(movies);
                    counter = 0;
                    tandas++;
                    movies.clear();
                }
            }
            elasticsearchEngine.indexDocuments(movies);

        }catch(IOException exception){
            throw exception;
        }
    }

    @Override
    public void createIndex(InputStream input) {
        elasticsearchEngine.createIndex(input);
    }

    @Override
    public void indexDocument(Movie movie) {

    }

    @Override
    public List<Movie> getDocuments() {
        return elasticsearchEngine.getDocuments();
    }

    @Override
    public void deleteIndex() {
        elasticsearchEngine.deleteIndex();
    }

    @Override
    public GetIndexResponse getIndixes() {
        return elasticsearchEngine.getIndexes();
    }

    @Override
    public List<Movie> getRangedMovies(int from, int size){
        return elasticsearchEngine.getRangedMovies(from, size);
    }
}
