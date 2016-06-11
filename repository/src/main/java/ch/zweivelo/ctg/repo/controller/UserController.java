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

import ch.zweivelo.ctg.repo.entities.User;
import ch.zweivelo.ctg.repo.repositories.UserRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Simple REST controller providing access to the {@link User} resource.
 *
 * @author <a href="mailto:m.bieri@gmx.net">Michael Bieri</a>
 * @version 0.1
 * @since 07.06.2016
 */
@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    private final EntityLinks entityLinks;

    @Autowired
    public UserController(final UserRepository userRepository, final EntityLinks entityLinks) {
        this.userRepository = userRepository;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    HttpEntity<Resources<User>> findAll() {
        final List<User> users = userRepository.findAll();
        LOGGER.info("findAll: found {} recipes", users.size());
        Resources<User> resources = new Resources<>(users);
        resources.add(entityLinks.linkToCollectionResource(User.class));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    HttpEntity<Resource<User>> findForUsername(@PathVariable("username") String username) {
        final Optional<User> userOptional = userRepository.findByUsername(username);

        LOGGER.info("findOne: found a user with  username {}: {}", username, userOptional.isPresent());

        return userOptional.map(
            user -> {
                Resource<User> resource = new Resource<>(user);
                resource.add(entityLinks.linkToSingleResource(User.class, username));
                return new ResponseEntity<>(resource, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
