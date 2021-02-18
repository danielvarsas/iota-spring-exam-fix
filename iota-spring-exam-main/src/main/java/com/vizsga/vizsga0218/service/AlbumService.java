package com.vizsga.vizsga0218.service;


import com.vizsga.vizsga0218.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class AlbumService {

private final AlbumRepository albumRepository;


    public AlbumService() {
    }
}
