package ch.zweivelo.ctg.repo.repositories;

import ch.zweivelo.ctg.repo.entities.Category;
import ch.zweivelo.ctg.repo.entities.Ingredient;
import ch.zweivelo.ctg.repo.entities.Recipe;
import org.springframework.data.repository.CrudRepository;
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
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findAll();

    Optional<Recipe> findById(long id);

    List<Recipe> findByNameIgnoreCase(String name);

    List<Recipe> findByCategoriesIn(Category... categories);

    List<Recipe> findByRecipeIngredients_Ingredient(Ingredient ingredients);

}
