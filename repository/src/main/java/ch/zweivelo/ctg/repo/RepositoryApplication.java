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

import ch.zweivelo.ctg.repo.entities.Category;
import ch.zweivelo.ctg.repo.entities.Ingredient;
import ch.zweivelo.ctg.repo.entities.Recipe;
import ch.zweivelo.ctg.repo.entities.RecipeIngredient;
import ch.zweivelo.ctg.repo.entities.State;
import ch.zweivelo.ctg.repo.entities.Unit;
import ch.zweivelo.ctg.repo.entities.User;
import ch.zweivelo.ctg.repo.entities.SecurityDetails;
import ch.zweivelo.ctg.repo.repositories.CategoryRepository;
import ch.zweivelo.ctg.repo.repositories.IngredientRepository;
import ch.zweivelo.ctg.repo.repositories.RecipeIngredientRepository;
import ch.zweivelo.ctg.repo.repositories.RecipeRepository;
import ch.zweivelo.ctg.repo.repositories.StateRepository;
import ch.zweivelo.ctg.repo.repositories.UnitRepository;
import ch.zweivelo.ctg.repo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigDecimal;
import java.security.SecureRandom;

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
            RecipeIngredientRepository recipeIngredientRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository)
    {
        return (args) -> {

            final State newState = new State("new");
            final State publicState = new State("public");
            final State privateState = new State("private");

            stateRepository.save(newState);
            stateRepository.save(publicState);
            stateRepository.save(privateState);

            final Category saladCategory = new Category("Salad", "Cold dish");
            final Category swissCategory = new Category("Swiss", null);
            final Category italianCategory = new Category("Italian", null);
            categoryRepository.save(saladCategory);
            categoryRepository.save(swissCategory);
            categoryRepository.save(italianCategory);

            final Ingredient cervelat = new Ingredient("Cervelat", null);
            final Ingredient cheese = new Ingredient("Gruy\u00E8re", null);
            final Ingredient salad = new Ingredient("Salat", null);
            final Recipe recipe = new Recipe("Wurstsalat", null, publicState);
            final Unit piece = new Unit("Stück", "Stk");
            final Unit gramm = new Unit("Gramm", "gr");

            unitRepository.save(piece);
            unitRepository.save(gramm);
            ingredientRepository.save(cervelat);
            ingredientRepository.save(cheese);
            ingredientRepository.save(salad);
            recipe.getCategories().add(saladCategory);
            recipe.getCategories().add(swissCategory);
            recipeRepository.save(recipe);
            recipeIngredientRepository.save(new RecipeIngredient(BigDecimal.ONE, null, false, piece, recipe, cervelat));
            recipeIngredientRepository.save(new RecipeIngredient(BigDecimal.valueOf(200), null, false, gramm, recipe, cheese));
            recipeIngredientRepository.save(new RecipeIngredient(BigDecimal.ONE, null, true, piece, recipe, salad));

            final byte[] salt = new byte[64];
            final SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            secureRandom.nextBytes(salt);

            final PBEKeySpec keySpec = new PBEKeySpec("1234 - What else?".toCharArray(), salt, 1000, 512);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            final SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            final byte[] passwordHash = secretKey.getEncoded();

            User michael = new User("Michi", "Bieri", "michi", "michi@somefancydomain.whatever");
            SecurityDetails securityDetails = new SecurityDetails(salt, passwordHash);
            michael.setSecurityDetails(securityDetails);
            michael.addToFavorites(recipe);
            userRepository.save(michael);

        };
    }

}
