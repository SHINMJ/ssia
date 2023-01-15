package com.example.ssiach6ex;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ssiach6ex.utils.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Sql(scripts = "classpath:scripts/setup.sql")
public class IntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private DatabaseCleanup databaseCleanup;


    @BeforeEach
    void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity())
            .build();

        databaseCleanup.afterPropertiesSet();
        databaseCleanup.execute();
    }

    @Test
    @WithAnonymousUser
    void loginAccessAnonymous_success() throws Exception{
        mvc.perform(get("/login"))
            .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void allAccessAuthentication_failed() throws Exception{
        mvc.perform(get("/all"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test1")
    void allAccessAuthentication_success() throws Exception{
        mvc.perform(get("/all"))
            .andExpect(status().isOk());
    }
}
