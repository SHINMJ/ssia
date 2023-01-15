package com.example.ssiach6ex.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:scripts/setup.sql")
class UserAuthorityRepositoryTest {

    @Autowired
    private UserAuthorityRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    User testUser1;
    Authority admin;

    @BeforeEach
    void setUp() {
        testUser1 = userRepository.findById(1L).get();
        admin = authorityRepository.findById(1L).get();
    }

    @Test
    void createUserAuthority() {
        UserAuthority userAuthority = UserAuthority.of(testUser1, admin);
        UserAuthority saved = repository.save(userAuthority);

        repository.flush();

        Optional<UserAuthority> optionalUserAuthority = repository.findById(saved.getId());

        assertTrue(optionalUserAuthority.isPresent());

        UserAuthority findAuthority = optionalUserAuthority.get();

        assertAll(
            () -> assertTrue(findAuthority.equalsUser(testUser1)),
            () -> assertTrue(findAuthority.equalsAuthority(admin))
        );

    }
}