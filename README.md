# IMDb Project

## Learning project for the Empathy.co Academy

Search project based on IMDb using its data sets.


### Tech Stack

<div align="left">
<img src="https://img.shields.io/badge/Java%20-%234cd137" alt="Java">
<img src="https://img.shields.io/badge/Spring Boot%20-%23e74c3c" alt="Spring Boot">
<img src="https://img.shields.io/badge/Elasticsearch%20-%239b59b6" alt="Elasticsearch">
<img src="https://img.shields.io/badge/Docker%20-%231c93e4" alt="Docker">
<img src="https://img.shields.io/badge/Maven%20-%e5923b" alt="Maven">
</div>


### Installation 

  1. Clone the repository
  ```
  git clone https://github.com/noeliaigc/IMDb-project.git
  ```
  
  2. Start the project
  ```
  docker compose up --build -d
  ```
  
  3. Shut down the project
   ```
  docker compose down
  ```
 
### Docker images
  
  1. Elastic image:
  ```
  docker pull noeliai/imdb:version3
  ```

### Endpoints

  #### Index:
  
  1. `POST /index/uploadMovies`
  
    Parameters:
    - basics -> File which contains the basic information about the movies.
    - akas -> File which contains the akas that a film has.
    - ratings -> File which contains the ratings of the films.
    - crew -> File which contains information about the director of the film.
    - principals -> File which contains the actors that participate on the films.
    
    This endpoint is used for uploading and index the specified files.
    
  2. `DELETE /index/delete`
  
    Parameters:
    - indexName -> String that contains the name of the index.
    
    This endpoint is used for deleting the specified index.
  
  
  
  #### Queries:
  
  1. `GET /imdb/_search/range`
  
    Parameters:
    - from -> Year of start of the movies wanted to be searched
    - size -> Size of the hits of the search
    
    This endpoint is used to search movies within a range of the start year of the movie.
    
  2. `GET /imdb/_search/title`
  
    Parameters:
    - title -> Text that want to be searched in order to find a movie
    - type -> Type of the movie that want to be searched (MOVIE, EPISODE, ALL)
    
    This endpoint is used to search movies by its title and filtered by type.
    
  3. `GET /imdb/_search/recommended`
  
    Parameters:
    - year -> Year of start of the movies wanted to be searched
    - size -> Size of the hits of the search
    
    This endpoint is used to search the recommended movies with a good rating in the year specified.
    
  4. `GET /imdb/_search`
  
    Parameters:
    - minYear -> Minimum year of the movies to be searched
    - maxYear -> Maximum year of the movies to be searched
    - maxRuntimeMin -> Maximum runtime minutes of the movies to be searched
    - minRuntimeMin -> Minimum runtime minutes of the movies to be searched
    - minAvgRating -> Minimum average rating of the movies to be searched
    - maxAvgRating -> Maximum average rating of the movies to be searched
    - type -> Type of the movies to be searched (MOVIE, EPISODE, ALL)
    - genres -> Array of genres of the movies to be searched (Drama, Action, Thriller)
    
    This endpoint is used to perform a filtered search of movies indicating any value of every field.
    
  5. `GET /imdb/_search/not-to-watch`
  
    This endpoint is used to search the must NOT to watch movies.
    
  6. `GET /imdb/_search/recommended-all-times`
  
    This endpoint is used to search the all times recommended movies that have a good rating.

  7. `GET /imdb/_search/genres`
  
    Parameters:
    - mustGenres -> String array which has the genres that must contain the movies to be searched.
    - mustNotGenres -> String array which has the gernes that must not contain the movies to be searched.
    - excludedIds -> String array which has the movies ids that must not be searched.
    - types -> String with the type of the movies to be searched (ALL, EPISODE, MOVIE)
