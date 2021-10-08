package ru.jpol.vocabot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.jpol.vocabot.VocaBotApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class AdminRestImplTest extends VocaBotApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetListUsers() throws Exception {
        // GET user. Expected 204
        mockMvc.perform(get("/admin/user"))
                .andExpect(status().isNoContent()).andExpect(jsonPath("$").doesNotExist());

        // GET user. Expected 200
        config_01();
        mockMvc.perform(get("/admin/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is(0)))
                .andExpect(jsonPath("$[0].username", is("username0")))
                .andExpect(jsonPath("$[0].created", notNullValue()))
                .andExpect(jsonPath("$[0].updated", notNullValue()))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(jsonPath("$[1].username", is("username1"))).andDo(print());

    }
    @Test
    public void testDeleteUser() throws Exception {
        // DELETE user/id. Delete user. Expected 404
        mockMvc.perform(delete("/admin/user/1"))
                .andExpect(status().isNotFound());

        // DELETE user/id. Delete user. Expected 204
        config_01();
        mockMvc.perform(delete("/admin/user/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isNoContent());
    }
}
