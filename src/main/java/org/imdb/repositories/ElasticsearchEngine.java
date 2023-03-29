package org.imdb.repositories;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import org.imdb.model.Movie;


import java.io.InputStream;
import java.util.List;

public interface ElasticsearchEngine {
    void createIndex(InputStream input);

    void indexDocuments(List<Movie> movies);

    List<Movie> getDocuments();

    void deleteIndex();

    GetIndexResponse getIndexes();
}
