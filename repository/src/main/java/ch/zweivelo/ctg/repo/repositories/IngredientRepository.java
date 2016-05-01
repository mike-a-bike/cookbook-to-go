package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Basic repository for accessing {@link Ingredient}s.
 *
 * @author Michael Bieri
 * @since 01.05.16
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(long id);

    List<Ingredient> findByName(String name);

}
