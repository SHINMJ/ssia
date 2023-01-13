package com.example.ssiach6ex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:scripts/setup.sql")
class UserRepositoryTest {
    static String TEST_USER_NAME = "테스트유저";
    static String TEST_PASSWORD = "1234";

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        User user = User.of(TEST_USER_NAME, TEST_PASSWORD);
        User saved = userRepository.save(user);

        //쿼리 확인
        userRepository.flush();

        Optional<User> optionalUser = userRepository.findById(saved.getId());

        assertTrue(optionalUser.isPresent());

        User findUser = optionalUser.get();

        assertAll(
            () -> assertThat(findUser.getUsername()).isEqualTo(user.getUsername()),
            () -> assertThat(findUser.getPassword()).isEqualTo(user.getPassword())
        );
    }

    @Test
    void findByUsername() {
        Optional<User> test1 = userRepository.findByUsername("test1");

        assertTrue(test1.isPresent());
    }
}