package ma.houssam.clientservice.repository;


import jakarta.transaction.Transactional;
import ma.houssam.clientservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Car c SET c.status = NOT c.status WHERE c.card_id = :cardId")
    void toggleCarStatusByCardId(Long cardId);

    @Transactional
    @Modifying
    @Query("SELECT c FROM Car c WHERE c.card_id = :cardId")
    Car getCarsByCardId(Long cardId);
}
