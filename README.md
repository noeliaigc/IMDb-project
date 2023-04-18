# IMDb Project

## Learning project for the Empathy.co Academy

Search project based on IMDb using its data sets.

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
  
### Endpoints:

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
  
    This endpoint is used to search the all times recommended movies with a good rating.
