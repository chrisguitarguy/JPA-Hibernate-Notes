package org.chrisguitarguy.hibernateplay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authors")
class Author {
    // See: http://stackoverflow.com/a/11902460/1031898
    // IDENTITY here causes postgress to use a BIGSERIAL (or SERIAL) type
    // I suspect it does the same in MySQL (using AUTO_INCREMENT)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=true)
    private String lastName;

    public Author() {
        // noop
    }

    public Author(String first, String last) {
        firstName = first;
        lastName = last;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        lastName = last;
    }
}
