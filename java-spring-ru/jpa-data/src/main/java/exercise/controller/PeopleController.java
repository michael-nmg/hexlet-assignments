package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping("")
    public List<Person> showAll() {
        return personRepository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Person showAll(@RequestBody Person data) {
        personRepository.save(data);
        return data;
    }

    @DeleteMapping("/{id}")
    public void showAll(@PathVariable("id") Long personId) {
        personRepository.deleteById(personId);
    }
    // END
}

// curl -X POST \
// -H "Content-Type: application/json" \
// -d '{"firstName":"xyz","lastName":"xyz"}' \
// localhost:8080/people