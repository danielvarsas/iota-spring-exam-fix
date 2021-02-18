package com.vizsga.vizsga0218.controller;


import com.vizsga.vizsga0218.entity.Artist;
import com.vizsga.vizsga0218.entity.Artist;
import com.vizsga.vizsga0218.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

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
    public List<Artist> getArtist() {
        List<Artist> artistlist;
        artistlist = artistService.listArtists();
        return artistlist;
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable ("id") String id) {
        Optional<Artist> foundArtistById = artistService.getArtistById(id);
        if (foundArtistById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundArtistById.get();
    }



}