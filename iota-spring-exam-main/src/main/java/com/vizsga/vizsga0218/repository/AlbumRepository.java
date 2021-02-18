package com.vizsga.vizsga0218.repository;

import com.vizsga.vizsga0218.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, String>{
}
