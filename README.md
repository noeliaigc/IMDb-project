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
  
