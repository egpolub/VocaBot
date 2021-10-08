package ru.jpol.vocabot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.jpol.vocabot.VocaBotApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class UserRestImplTest extends VocaBotApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUpdateUser() throws Exception {
        // POST user. Create user. Expected 400, user id is null
        String request = "{\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

        // POST user. Create user. Expected 409, user already exists
        config_01();
        request = "{\"userId\":0,\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test5@test.com\"}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isConflict());

        // POST user. Create user. Expected 201
        request = "{\"userId\":5,\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test5@test.com\"}";
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated()).andReturn().getRequest();

        mockMvc.perform(get("/user/5"))
                .andExpect(jsonPath("$.userId", is(5)))
                .andExpect(jsonPath("$.firstname", is("test_firstname")))
                .andExpect(jsonPath("$.username", is("test_username")))
                .andExpect(jsonPath("$.email", is("test5@test.com")))
                .andExpect(jsonPath("$.created", notNullValue()))
                .andExpect(jsonPath("$.updated", notNullValue()));

        // PATCH user/id. Update user. Expected 400 different id
        request = "{\"userId\":1,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(patch("/user/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());

        // PATCH user/id. Update user. Expected 404
        request = "{\"userId\":6,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(patch("/user/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound());

        // PATCH user/id. Update user. Expected 200
        request = "{\"userId\":3,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test3@test.com\"}";
        mockMvc.perform(patch("/user/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }
}
