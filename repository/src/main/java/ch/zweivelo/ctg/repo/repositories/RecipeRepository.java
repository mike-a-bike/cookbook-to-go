/*
 * Copyright 2016 Michael Bieri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
