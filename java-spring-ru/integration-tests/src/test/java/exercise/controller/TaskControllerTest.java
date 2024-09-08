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
        var data = generatedTask();
        taskRepository.save(data);

        var request = get("/tasks/{id}", data.getId());
        var result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        var body = result.getResponse().getContentAsString(); 
        assertThatJson(body).and(
            rsp -> rsp.node("title").isEqualTo(data.getTitle()),
            rsp -> rsp.node("description").isEqualTo(data.getDescription())
        );
    }

    @Test
    public void testGetTaskNotFound() throws Exception {
        var notId = Long.MAX_VALUE;
        var request = get("/tasks/{id}", notId);
        var result = mockMvc.perform(request)
            .andExpect(status().isNotFound())   
            .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Task with id " + notId + " not found");
    }
    
    @Test
    public void testCreateTask() throws Exception {
        var data = generatedTask();
        var taskJson = om.writeValueAsString(data);

        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskJson);

        var result = mockMvc.perform(request)
            .andExpect(status().isCreated());

        var task = taskRepository.findByTitle(data.getTitle()).get();
        assertThat(task.getDescription()).isEqualTo(data.getDescription());
    }

    @Test
    public void testUpdateTask() throws Exception {
        var data = generatedTask();
        taskRepository.save(data);

        var newTitle = faker.lorem().word();
        var newDescription = faker.lorem().paragraph();
        Map<String, String> map = new HashMap<>();
        map.put("title", newTitle);
        map.put("description", newDescription);
        var taskJson = om.writeValueAsString(map);

        var request = put("/tasks/{id}", data.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskJson);

        mockMvc.perform(request).andExpect(status().isOk());

        var task = taskRepository.findByTitle(newTitle).get();
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
