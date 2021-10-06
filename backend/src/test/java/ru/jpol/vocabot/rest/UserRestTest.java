package ru.jpol.vocabot.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.jpol.vocabot.VocaBotApplicationTest;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class UserRestTest extends VocaBotApplicationTest {

    @Autowired
    private MockMvc mockMvc;

//    @AfterEach
//    public void cleanByTableName() {
//        super.cleanUp("users");
//    }

    @Test
    public void testGetUser() throws Exception {

        // GET user. Expected 204
        mockMvc.perform(get("/user"))
                .andExpect(status().isNoContent()).andExpect(jsonPath("$").doesNotExist());

        // GET user. Expected 200
        config_01();
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].username", is("username0")))
                .andExpect(jsonPath("$[0].created", notNullValue()))
                .andExpect(jsonPath("$[0].updated", notNullValue()))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].username", is("username1"))).andDo(print());

    }

    @Test
    public void testCreateUpdateUser() throws Exception{
        // POST user. Create user. Expected 400, user id is null
        String request = "{\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

        // POST user. Create user. Expected 409, user already exists
        config_01();
        request = "{\"id\":0,\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isConflict());

        // POST user. Create user. Expected 201
        request = "{\"id\":5,\"firstname\":\"test_firstname\",\"username\":\"test_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated()).andReturn().getRequest();

        mockMvc.perform(get("/user/5"))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.firstname", is("test_firstname")))
                .andExpect(jsonPath("$.username", is("test_username")))
                .andExpect(jsonPath("$.email", is("test0@test.com")))
                .andExpect(jsonPath("$.created", notNullValue()))
                .andExpect(jsonPath("$.updated", notNullValue()));

        // PATCH user/id. Update user. Expected 400 different id
        request = "{\"id\":1,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(patch("/user/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());

        // PATCH user/id. Update user. Expected 404
        request = "{\"id\":6,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(patch("/user/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound());

        // PATCH user/id. Update user. Expected 200
        request = "{\"id\":3,\"firstname\":\"updated_firstname\",\"username\":\"updated_username\",\"email\":\"test0@test.com\"}";
        mockMvc.perform(patch("/user/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // DELETE user/id. Delete user. Expected 404
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNotFound());

        // DELETE user/id. Delete user. Expected 204
        config_01();
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isNoContent());
    }
}
