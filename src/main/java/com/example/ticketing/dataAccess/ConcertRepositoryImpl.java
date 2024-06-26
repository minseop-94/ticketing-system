package com.example.ticketing.dataAccess;
import com.example.ticketing.dataAccess.entity.ConcertEntity;
import com.example.ticketing.dataAccess.orm.ConcertJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    @Autowired
    private ConcertJpaRepository ConcertJpaRepository;

    @Override
    public Optional<ConcertEntity> findConcertById(Long concertId) {
        return ConcertJpaRepository.findById(concertId);
    }

    @Override
    public List<ConcertEntity> findAll() {
        return ConcertJpaRepository.findAll();
    }

    @Override
    public ConcertEntity save(ConcertEntity concert) {
        return ConcertJpaRepository.save(concert);
    }
}
