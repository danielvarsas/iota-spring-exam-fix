package com.vizsga.vizsga0218.service;


import com.vizsga.vizsga0218.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public SuperHero createSuperHero(SuperHero fromRequest) throws ValidationException {
        if (fromRequest.getName() == null || fromRequest.getName() == "") {
            throw new ValidationException("name can not be null or empty string!", HttpStatus.BAD_REQUEST);
        } else {
            log.info("Creating SH based on: {} ...", fromRequest);
            SuperHero result = superHeroRepository.save(fromRequest);
            return result;
        }
    }

    public SuperHero updateSuperHero(String superHeroId, SuperHero superHero) throws ValidationException {
        log.info("Updating team on team id: {}, team: {}", superHeroId, superHero);
        Optional<SuperHero> optionalSuperHero = superHeroRepository.findById(superHeroId);
        if (optionalSuperHero.isEmpty()) {
            throw new ValidationException("There is no such Id", HttpStatus.BAD_REQUEST);
        }
        SuperHero actualHero = optionalSuperHero.get();
        log.debug("Original SH was: {}", actualHero.getName());
        actualHero.setName(superHero.getName());
        actualHero.setUniverse(superHero.getUniverse());
        actualHero.setTeam(superHero.getTeam());
        actualHero.setHero(superHero.getHero());
        SuperHero updated = superHeroRepository.save(actualHero);
        log.debug("Updated SuperHero is: {}", updated);
        return updated;
    }



}
