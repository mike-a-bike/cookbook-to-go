package ch.zweivelo.ctg.repo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Additional meta data associated with a recipe
 *
 * @author Michael Bieri
 * @since 04.05.16
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Lob
    private String description;

    Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
