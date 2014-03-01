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
    private Long id;
    private String firstName;
    private String lastName;

    public Author() {
        // noop
    }

    public Author(String first, String last) {
        firstName = first;
        lastName = last;
    }

    // See: http://stackoverflow.com/a/11902460/1031898
    // IDENTITY here causes postgress to use a BIGSERIAL (or SERIAL) type
    // I suspect it does the same in MySQL (using AUTO_INCREMENT)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="first_name", nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    @Column(name="last_name", nullable=true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        lastName = last;
    }
}
