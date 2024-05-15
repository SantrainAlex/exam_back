package barApp.barApp.repositories;

import barApp.barApp.models.Barmaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarmakerRepository extends JpaRepository<Barmaker, Long> {

    Optional<Barmaker> findByEmail(String email);

    Boolean existsByEmail(String email);

}
