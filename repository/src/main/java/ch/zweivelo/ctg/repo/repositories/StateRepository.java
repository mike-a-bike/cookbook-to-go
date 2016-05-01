package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Crud repository for accessing the possible recipe states.
 *
 * @author Michael Bieri
 * @since 01.05.16
 */
@Repository
public interface StateRepository extends CrudRepository<State, Long> {
}
