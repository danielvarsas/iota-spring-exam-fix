package com.vizsga.vizsga0218.repository;

import com.vizsga.vizsga0218.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, String>{
}
