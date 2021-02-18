package com.vizsga.vizsga0218.repository;

import com.vizsga.vizsga0218.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String>{
}
