package org.imdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.elasticsearch.http.HttpStats;
import org.imdb.model.Movie;
import org.imdb.service.ImdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/index")
public class ImdbController {
    private final ImdbService imdbService;

    @Autowired
    public ImdbController(ImdbService imdbService) {
        this.imdbService = imdbService;
    }

    @Operation(description = "Uploads the tsv files, process and index them",
            responses = {
            @ApiResponse(responseCode = "202", description = "Files has been " +
                    "uploaded")})
    @PostMapping("/uploadMovies")
    public ResponseEntity uploadFiles(@RequestParam("basics") MultipartFile basics,
                                      @RequestParam("akas") MultipartFile akas,
                                      @RequestParam("ratings") MultipartFile ratings,
                                      @RequestParam("crew") MultipartFile crew,
                                      @RequestParam("principals") MultipartFile principals){
        try {
            imdbService.uploadFiles(basics, akas, ratings,crew, principals);
            return ResponseEntity.accepted().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Operation(description = "Creates the index with the specific mapping", responses = {
            @ApiResponse(responseCode = "202", description = "Index created " +
                    "successfully")})
    @PutMapping("")
    public ResponseEntity createIndex(@RequestBody MultipartFile file){
        try {
            InputStream input = file.getInputStream();
            imdbService.createIndex(input);
            return ResponseEntity.ok("indexed");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/_doc")
    public void indexDocument(@RequestBody Movie movie){
        imdbService.indexDocument(movie);
    }

    @GetMapping("/_search")
    public ResponseEntity<List<Movie>> getDocuments(){
        return ResponseEntity.ok(imdbService.getDocuments());
    }

    @Operation(description = "Deletes the index",
            responses = {
            @ApiResponse(responseCode = "202", description = "Index deleted " +
                    "successfully")})
    @DeleteMapping("/delete")
    public ResponseEntity deleteIndex(){
        imdbService.deleteIndex();
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/_cat/indices")
    public String getIndices(){
        return imdbService.getIndixes().toString();
    }

    @GetMapping("/_search/range")
    public ResponseEntity<List<Movie>> getRangedMovies(@RequestParam int from
            , @RequestParam int size){
        return ResponseEntity.ok(imdbService.getRangedMovies(from, size));
    }
}
