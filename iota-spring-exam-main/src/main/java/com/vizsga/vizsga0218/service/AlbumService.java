package com.vizsga.vizsga0218.service;


import com.vizsga.vizsga0218.entity.Album;
import com.vizsga.vizsga0218.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album createNewAlbum(Album fromRequest) throws ValidationException {
        if (fromRequest.getTitle() == null || fromRequest.getTitle() == "") {
            throw new ValidationException("name can not be null or empty string!");
        } else {
            Album result = albumRepository.save(fromRequest);
            return result;
        }
    }

    public Album updateAlbum(String albumId, Album album) throws ValidationException {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isEmpty()) {
            throw new ValidationException("There is no such Id");
        }
        Album actualAlbum = optionalAlbum.get();
        actualAlbum.setTitle(album.getTitle());
        actualAlbum.setCount(album.getCount());
        Album updated = albumRepository.save(actualAlbum);
        return updated;
    }

    public List<Album> listAlbums() {
        List<Album> albumList = albumRepository.findAll();
        return albumList;
    }

    public Album giveAlbumById(String heroId) throws Exception {
        Optional<Album> optionalAlbum = albumRepository.findById(heroId);
        if (optionalAlbum.isEmpty()) {
            throw new ValidationException("There is no album with sutch id. ");
        }
        Album album = optionalAlbum.get();
        return album;
    }


}
