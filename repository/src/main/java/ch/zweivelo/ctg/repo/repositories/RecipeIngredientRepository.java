package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic repository for accessing the saved {@link RecipeIngredient} entities.
 *
 * @author Michael Bieri
 * @since 01.05.16
 */
@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
}
