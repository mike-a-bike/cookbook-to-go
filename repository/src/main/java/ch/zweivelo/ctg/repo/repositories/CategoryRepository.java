package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Basic repository for accessing the {@link Category} entities.
 *
 * @author Michael Bieri
 * @since 04.05.16
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Optional<Category> findById(long id);

    List<Category> findByName(String name);

}
