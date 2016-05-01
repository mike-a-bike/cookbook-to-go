package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic access to the saved {@link Unit} entities.
 *
 * @author Michael Bieri
 * @since 01.05.16
 */
@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
}
