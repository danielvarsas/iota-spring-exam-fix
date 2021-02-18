package com.vizsga.vizsga0218.controller;


import com.vizsga.vizsga0218.entity.Song;
import com.vizsga.vizsga0218.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Song createNewSong(@RequestBody Song song) throws ResponseStatusException {
        try {
            Song newSong = songService.createNewSong(song);
            return newSong;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Song updateSong(@RequestBody Song song, @PathVariable ("id")  String id) {
        try {
            Song updatedSong = songService.updateSong(song, id);
            return updatedSong;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Song> get() {
        List<Song> songlist;
        songlist = songService.listSongs();
        return songlist;
    }

    @PutMapping("/updatesong/{id}")
    public Song updateSong(@PathVariable("id") String songId, @RequestBody Song song) {
        try {
            Song updatedSong = songService.updateSong(songId, song);
            return updatedSong;
        } catch (ValidationException e) {
            return null;
        }
    }



}