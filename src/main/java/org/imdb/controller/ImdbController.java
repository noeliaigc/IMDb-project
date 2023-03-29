package org.imdb.controller;

import org.imdb.model.Movie;
import org.imdb.service.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class ImdbController {
    private final ImdbService imdbService;

    @Autowired
    public ImdbController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @PostMapping("/uploadMovies")
    public ResponseEntity uploadFiles(@RequestParam("basics") MultipartFile basics,
                                      @RequestParam("akas") MultipartFile akas,
                                      @RequestParam("ratings") MultipartFile ratings,
                                      @RequestParam("crew") MultipartFile crew,
                                      @RequestParam("principals") MultipartFile principals){
        try {
            imdbService.uploadFiles(basics, akas, ratings,crew, principals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.accepted().build();
    }

    @PutMapping("")
    public void createIndex(@RequestBody MultipartFile file){
        try {
            InputStream input = file.getInputStream();
            imdbService.createIndex(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/movies/_doc")
    public void indexDocument(@RequestBody Movie movie){
        imdbService.indexDocument(movie);
    }

    @GetMapping("/_search")
    public ResponseEntity<List<Movie>> getDocuments(){
        return ResponseEntity.ok(imdbService.getDocuments());
    }

    @DeleteMapping("/delete")
    public void deleteIndex(){
        imdbService.deleteIndex();
    }

    @GetMapping("/_cat/indices")
    public String getIndices(){
        return imdbService.getIndixes().toString();
    }
}
