package org.imdb.util;

import org.imdb.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImbdReader {

    private BufferedReader basics;
    private BufferedReader akas;
    private BufferedReader ratings;
    private BufferedReader crew;
    private BufferedReader participants;

    private String basicsLine;
    private String akasLine;
    private String ratingsLine;
    private String crewLine;
    private String participantLine;

    public boolean lines = true;



    public ImbdReader(MultipartFile basics, MultipartFile akas,
                      MultipartFile ratings, MultipartFile crew,
                      MultipartFile participants) throws IOException {
        this.basics = getBufferedReader(basics);
        this.akas = getBufferedReader(akas);
        this.ratings = getBufferedReader(ratings);
        this.crew = getBufferedReader(crew);
        this.participants = getBufferedReader(participants);
        getHeaders();

    }

    public BufferedReader getBufferedReader(MultipartFile file){
        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(file.getInputStream()));
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getHeaders() throws IOException {
        String headers =
                basics.readLine() + akas.readLine() + ratings.readLine() +
                 crew.readLine() + participants.readLine();
    }

    public void getLines() throws IOException {
        basicsLine = basics.readLine();
        akasLine = akas.readLine();
        ratingsLine = ratings.readLine();
        crewLine = crew.readLine();
        participantLine = participants.readLine();
    }


    public Movie getMovie() throws IOException {
        if(noLines()){
            lines = false;
            return null;
        }
        String[] parts = basicsLine.split("\t");
        String titleId = check(parts[0]);
        String titleType = check(parts[1]);
        String primaryTitle = check(parts[2]);
        String originalTitle = check(parts[3]);
        int isAdult = Integer.parseInt(check(parts[4]));
        if(isAdult == 1){
            return null;
        }
        int startYear = Integer.parseInt(check(parts[5]));
        int endYear = Integer.parseInt(check(parts[6]));
        int runtimeMinutes = Integer.parseInt(check(parts[7]));
        String genres[]= parts[8].split(",");

        //ratings file
        //set ratings
        String[] rtngs = doRatings(titleId);
        double avgRating = Double.parseDouble(check(rtngs[1]));
        int numVotes = Integer.parseInt(check(rtngs[2]));

        //get akas
        List<Akas> akas = doAkas(titleId);
        
        //get directors
        List<Directors> directors = doDirectors(titleId);

        //getActors
        List<Starring> starrings = doActors(titleId);

        return new Movie(titleId, titleType, primaryTitle, originalTitle,false,
                startYear, endYear, runtimeMinutes, genres, avgRating,
                numVotes, akas,
                directors, starrings);
    }

    private boolean noLines() {
        return basicsLine == null;
    }

    private List<Starring> doActors(String titleId) throws IOException {
        List<Starring> starrings = new ArrayList<>();
        String title;
        while((title = participantLine.split("\t")[0]).equals(titleId)){
            String[] parts = participantLine.split("\t");
            Starring starring = new Starring(new Name(parts[2]), parts[5]);
            starrings.add(starring);

            participantLine = participants.readLine();
        }

        return starrings;
    }

    private List<Directors> doDirectors(String titleId) {
        List<Directors> directors = new ArrayList<>();
        String[] parts = crewLine.split("\t");
        if(parts[0].equals(titleId)){
            if(parts[1].contains(",")) {
                String[] dir = parts[1].split(",");
                addDirectors(directors, dir);

            }else{
                Directors d = new Directors(parts[1]);
                directors.add(d);
            }
        }
        return directors;
    }

    private void addDirectors(List<Directors> directors, String[] dir) {
        for(int i = 0; i < dir.length; i++){
            Directors d = new Directors(dir[i]);
            directors.add(d);
        }
    }

    private List<Akas> doAkas(String titleId) throws IOException {
        List<Akas> akasList = new ArrayList<>();
        String title;
        while((title = akasLine.split("\t")[0]).equals(titleId)){
            String[] parts = akasLine.split("\t");
            boolean isOriginal = true;
            if(parts[7].equals("0")){
                isOriginal = false;
            }
            Akas aka = new Akas(parts[2], parts[3], parts[4], isOriginal);
            akasList.add(aka);
            akasLine = akas.readLine();
        }
        return akasList;
    }

    private String[] doRatings(String titleId) {
        if (ratingsLine == null) {
            return new String[]{"id", "0.0", "0"};
        }else{
            String[] parts = ratingsLine.split("\t");
            if (parts[0].equals(titleId)) {
                return parts;
            }
        }
        return new String[]{titleId, "0.0", "0"};
    }

    private String check(String part) {
        if(part.equals("\\N")){
            return "0";
        }
        return part;
    }
}
