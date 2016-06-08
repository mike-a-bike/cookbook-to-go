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

package ch.zweivelo.ctg.repo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Description of a measuring unit
 *
 * @author <a href="mailto:m.bieri@gmx.net">Michael Bieri</a>
 * @version 0.1
 * @since 28.04.2016
 */
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private String shortName;

    private String description;

    Unit() {
    }

    public Unit(final String name) {
        this.name = name;
    }

    public Unit(final String name, final String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Unit(final String name, final String shortName, final String description) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
