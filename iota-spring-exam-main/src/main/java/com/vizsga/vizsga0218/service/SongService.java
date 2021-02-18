package com.vizsga.vizsga0218.service;


import com.vizsga.vizsga0218.entity.Song;
import com.vizsga.vizsga0218.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song createNewSong(Song fromRequest) throws ValidationException {
        if (fromRequest.getTitle() == null || fromRequest.getTitle() == "") {
            throw new ValidationException("Title can not be null or empty string!");
        } else {
            Song result = songRepository.save(fromRequest);
            return result;
        }
    }

    public Song updateSong(String songId, Song song) throws ValidationException {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isEmpty()) {
            throw new ValidationException("There is no such Id");
        }
        Song actualSong = optionalSong.get();
        actualSong.setTitle(song.getTitle());
        actualSong.setLength(song.getLength());
        actualSong.setLyrics(song.getLyrics());
        actualSong.setYear(song.getYear());
        actualSong.setWriterName(song.getWriterName());
        actualSong.setGenre(song.getGenre());
        actualSong.setArtist(song.getArtist());
        actualSong.setAlbum(song.getAlbum());
        Song updated = songRepository.save(actualSong);
        return updated;
    }

    public List<Song> listSongs() {
        List<Song> songList = songRepository.findAll();
        return songList;
    }

    public Song giveSongById(String heroId) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(heroId);
        if (optionalSong.isEmpty()) {
            throw new ValidationException("There is no song with sutch id. ");
        }
        Song song = optionalSong.get();
        return song;
    }


}