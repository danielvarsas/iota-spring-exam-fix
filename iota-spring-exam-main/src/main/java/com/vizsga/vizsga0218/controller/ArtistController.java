package com.vizsga.vizsga0218.controller;


import com.vizsga.vizsga0218.entity.Artist;
import com.vizsga.vizsga0218.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createNewArtist(@RequestBody Artist artist) throws ResponseStatusException {
        try {
            Artist newArtist = artistService.createNewArtist(artist);
            return newArtist;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist updateArtist(@RequestBody Artist artist, @PathVariable ("id")  String id) {
        try {
            Artist updatedArtist = artistService.updateArtist(artist, id);
            return updatedArtist;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> get() {
        List<Artist> artistlist;
        artistlist = artistService.listArtists();
        return artistlist;
    }

    @PutMapping("/updateartist/{id}")
    public Artist updateArtist(@PathVariable("id") String artistId, @RequestBody Artist artist) {
        try {
            Artist updatedArtist = artistService.updateArtist(artistId, artist);
            return updatedArtist;
        } catch (ValidationException e) {
            return null;
        }
    }



}