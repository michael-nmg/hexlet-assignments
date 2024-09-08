package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testGetTask() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .ignore(Select.field(Task::getUpdatedAt))
            .ignore(Select.field(Task::getCreatedAt))
            .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
            .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
            .create();
        var id = taskRepository.save(task).getId();

        var result = mockMvc.perform(get("/tasks/" + id))
            .andExpect(status().isOk())
            .andReturn();

        var body = result.getResponse().getContentAsString(); 
        assertThatJson(body).and(
            request -> request.node("title").isEqualTo(task.getTitle()),
            request -> request.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testGetTaskNotFound() throws Exception {
        var notId = 1;
        var result = mockMvc.perform(get("/tasks/" + notId))
            .andExpect(status().isNotFound())   
            .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Task with id " + notId + " not found");
    }
    
    @Test
    public void testCreateTask() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().paragraph();
        Map<String, String> data = new HashMap<>();
        data.put("title", title);
        data.put("description", description);
        var taskJson = om.writeValueAsString(data);

        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskJson);

        var result = mockMvc.perform(request)
            .andExpect(status().isCreated());

        var task = taskRepository.findByTitle(title).get();
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
    }

    @Test
    public void testUpdateTask() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .ignore(Select.field(Task::getUpdatedAt))
            .ignore(Select.field(Task::getCreatedAt))
            .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
            .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
            .create();
        var id = taskRepository.save(task).getId();

        var newTitle = faker.lorem().word();
        var newDescription = faker.lorem().paragraph();
        Map<String, String> data = new HashMap<>();
        data.put("title", newTitle);
        data.put("description", newDescription);
        var taskJson = om.writeValueAsString(data);

        var request = put("/tasks/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskJson);
            
        mockMvc.perform(request).andExpect(status().isOk());
        task = taskRepository.findByTitle(newTitle).get();
        assertThat(task.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void testDeleteTask() throws Exception {
        var data = generatedTask();
        var title = data.getTitle();
        taskRepository.save(data);

        var request = delete("/tasks/{id}", data.getId());
        mockMvc.perform(request).andExpect(status().isOk());

        var result = taskRepository.findByTitle(title);
        assertThat(result.isEmpty()).isEqualTo(true);
    }

    private Task generatedTask() {
        return Instancio.of(Task.class)
        .ignore(Select.field(Task::getId))
        .ignore(Select.field(Task::getUpdatedAt))
        .ignore(Select.field(Task::getCreatedAt))
        .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
        .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
        .create();
    }
    // END
}