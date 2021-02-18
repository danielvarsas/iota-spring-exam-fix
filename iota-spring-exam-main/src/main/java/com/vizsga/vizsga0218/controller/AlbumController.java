package com.vizsga.vizsga0218.controller;


import com.vizsga.vizsga0218.entity.Album;
import com.vizsga.vizsga0218.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Album createNewAlbum(@RequestBody Album album) throws ResponseStatusException {
        try {
            Album newAlbum = albumService.createNewAlbum(album);
            return newAlbum;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album updateAlbum(@RequestBody Album album, @PathVariable ("id")  String id) {
        try {
            Album updatedAlbum = albumService.updateAlbum(album, id);
            return updatedAlbum;
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> get() {
        List<Album> albumlist;
            albumlist = albumService.listAlbums();
        return albumlist;
    }

    @PutMapping("/updatealbum/{id}")
    public Album updateAlbum(@PathVariable("id") String albumId, @RequestBody Album album) {
        try {
            Album updatedAlbum = albumService.updateAlbum(albumId, album);
            return updatedAlbum;
        } catch (ValidationException e) {
            return null;
        }
    }



}


   // Készíts egy controller osztályt a Album-ok kezelésére.
// Fogadjon requesteketa /api/albums path-on és valósítsa meg a következő
// művelteket:1.Új létrehozás (1 pont)2.Módosítás (1 pont)3.Összes
// lekérdezése (1 pont)4.Lekérdezés id alapján (az id legyen a path-ban
// átadva) (1 pont)