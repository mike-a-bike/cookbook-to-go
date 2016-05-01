package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Basic repository for Recipes.
 *
 * @author Michael Bieri
 * @since 01.05.16
 */

@Repository
public interface RecipeRepository extends org.springframework.data.repository.Repository<Recipe, Long> {

    List<Recipe> findAll();

    Optional<Recipe> findOne(Long id);

    Optional<Recipe> findByName(String name);

}
