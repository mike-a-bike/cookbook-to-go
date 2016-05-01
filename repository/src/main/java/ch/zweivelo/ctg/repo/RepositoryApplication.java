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

import ch.zweivelo.ctg.repo.entities.Recipe;
import ch.zweivelo.ctg.repo.repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public CommandLineRunner demo(RecipeRepository recipeRepository) {
        return (args) -> {

            recipeRepository.save(new Recipe("Wurstsalat", null));

            recipeRepository.findAll().forEach(recipe -> LOGGER.info("Recipe {}: {}", recipe.getId(), recipe.getName()));

        };
    }

}
