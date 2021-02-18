package com.vizsga.vizsga0218.service;


import com.vizsga.vizsga0218.entity.Artist;
import com.vizsga.vizsga0218.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createNewArtist(Artist fromRequest) throws ValidationException {
        if (fromRequest.getFirstname() == null || fromRequest.getFirstname() == "") {
            throw new ValidationException("name can not be null or empty string!");
        } else {
            Artist result = artistRepository.save(fromRequest);
            return result;
        }
    }

    public Artist updateArtist(String artistId, Artist artist) throws ValidationException {
        Optional<Artist> optionalArtist = artistRepository.findById(artistId);
        if (optionalArtist.isEmpty()) {
            throw new ValidationException("There is no such Id");
        }
        Artist actualArtist = optionalArtist.get();
        actualArtist.setFirstname(artist.getFirstname());
        actualArtist.setLastname(artist.getLastname());
        Artist updated = artistRepository.save(actualArtist);
        return updated;
    }

    public List<Artist> listArtists() {
        List<Artist> artistList = artistRepository.findAll();
        return artistList;
    }

    public Artist giveArtistById(String heroId) throws Exception {
        Optional<Artist> optionalArtist = artistRepository.findById(heroId);
        if (optionalArtist.isEmpty()) {
            throw new ValidationException("There is no artist with sutch id. ");
        }
        Artist artist = optionalArtist.get();
        return artist;
    }


}