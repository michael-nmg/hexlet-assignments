package exercise.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import static jakarta.persistence.GenerationType.IDENTITY;


// BEGIN
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}
// END
