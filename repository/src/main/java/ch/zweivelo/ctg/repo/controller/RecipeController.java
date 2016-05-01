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

package ch.zweivelo.ctg.repo.controller;

import ch.zweivelo.ctg.repo.entities.Recipe;
import ch.zweivelo.ctg.repo.repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

/**
 * REST controller exposing the recipe mappings
 *
 * @author Michael Bieri
 * @since 26.04.16
 */

@Controller
@RequestMapping("/recipes")
@ExposesResourceFor(Recipe.class)
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeRepository recipeRepository;

    private final EntityLinks entityLinks;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, EntityLinks entityLinks) {
        this.recipeRepository = recipeRepository;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resources<Recipe>> findAll() {
        final List<Recipe> recipes = recipeRepository.findAll();
        LOGGER.info("findAll: found {} recipes", recipes.size());
        Resources<Recipe> resources = new Resources<>(recipes);
        resources.add(entityLinks.linkToCollectionResource(Recipe.class));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resource<Recipe>> findOne(@PathVariable Long id) {
        final Optional<Recipe> recipeOptional = recipeRepository.findOne(id);

        LOGGER.info("findOne: found a recipe for id {}: {}", id, recipeOptional.isPresent());

        return recipeOptional.map(
                recipe -> {
                    Resource<Recipe> resource = new Resource<>(recipe);
                    resource.add(entityLinks.linkToSingleResource(Recipe.class, id));
                    return new ResponseEntity<>(resource, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
