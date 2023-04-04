package org.imdb.repositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.imdb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryEngineImpl {
    private ElasticsearchClient elasticsearchClient;
    private static final String INDEX = "imdb";

    public QueryEngineImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movies = new ArrayList<>();
        try {
            MultiMatchQuery multiMatchQuery = MultiMatchQuery.of(m -> m.fields(
                    "primaryTitle", "originalTitle").query(title));
            Query query = multiMatchQuery._toQuery();

            SearchResponse searchResponse =
                    elasticsearchClient.search(i -> i
                                    .index(INDEX)
                                    .query(query),
                            Movie.class);
            List<Hit<Movie>> hits = searchResponse.hits().hits();
            for(Hit<Movie> movie : hits){
                movies.add(movie.source());
            }
            return movies;
        }catch(IOException e){
            System.out.println("error");
        }
        return movies;
    }

    public List<Movie> getRangedMovies(int from, int size){
        List<Movie> movies = new ArrayList<>();
        try {
            SearchResponse searchResponse =
                    elasticsearchClient.search(i -> i
                                    .index(INDEX)
                                    .from(from)
                                    .size(size),
                            Movie.class);
            List<Hit<Movie>> hits = searchResponse.hits().hits();

            for (Hit<Movie> object : hits) {
                movies.add(object.source());
            }
            return movies;
        }catch(IOException e){
            System.out.println("error");
        }
        return movies;
    }

    public List<Movie> getRecommended(int year, int size){
        List<Movie> movies = new ArrayList<>();
        try {
            SortOptions sort = new SortOptions.Builder().field(f -> f.field(
                    "avgRating").order(SortOrder.Asc)).build();

            SearchResponse searchResponse =
                    elasticsearchClient.search(i -> i
                                    .index(INDEX)
                                    .size(size)
                                    .sort(sort),
                                    Movie.class);
            List<Hit<Movie>> hits = searchResponse.hits().hits();

            for (Hit<Movie> object : hits) {
                movies.add(object.source());
            }
            return movies;
        }catch(IOException e){
            System.out.println("error");
        }
        return movies;
    }
}
