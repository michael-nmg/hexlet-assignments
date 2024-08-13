package exercise.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import exercise.model.Person;

// BEGIN
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
// END
