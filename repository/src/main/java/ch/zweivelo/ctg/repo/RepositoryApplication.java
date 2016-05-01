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

package ch.zweivelo.ctg.repo;

import ch.zweivelo.ctg.repo.entities.Ingredient;
import ch.zweivelo.ctg.repo.entities.Recipe;
import ch.zweivelo.ctg.repo.entities.RecipeIngredient;
import ch.zweivelo.ctg.repo.entities.State;
import ch.zweivelo.ctg.repo.entities.Unit;
import ch.zweivelo.ctg.repo.repositories.IngredientRepository;
import ch.zweivelo.ctg.repo.repositories.RecipeIngredientRepository;
import ch.zweivelo.ctg.repo.repositories.RecipeRepository;
import ch.zweivelo.ctg.repo.repositories.StateRepository;
import ch.zweivelo.ctg.repo.repositories.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * Boot application to start up.
 *
 * @author Michael Bieri
 * @since 26.04.16
 */

@SpringBootApplication
public class RepositoryApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryApplication.class);

    public static void main(String[] arguments) {
        LOGGER.info("Starting CTG Repository");
        SpringApplication.run(RepositoryApplication.class, arguments);
    }

    @Bean
    public CommandLineRunner demo(
            StateRepository stateRepository,
            UnitRepository unitRepository,
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            RecipeIngredientRepository recipeIngredientRepository)
    {
        return (args) -> {

            final State newState = new State("new");
            final State publicState = new State("public");
            final State privateState = new State("private");

            stateRepository.save(newState);
            stateRepository.save(publicState);
            stateRepository.save(privateState);

            final Ingredient cervelat = new Ingredient("Cervelat", null);
            final Recipe recipe = new Recipe("Wurstsalat", null, newState);
            final Unit unit = new Unit("Stück", null);

            unitRepository.save(unit);
            ingredientRepository.save(cervelat);
            recipeRepository.save(recipe);
            recipeIngredientRepository.save(new RecipeIngredient(BigDecimal.ONE, null, unit, recipe, cervelat));

        };
    }

}
