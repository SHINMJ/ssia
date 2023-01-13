package com.example.ssiach6ex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

    @ParameterizedTest
    @CsvSource(value = {"신명진;1234", "32123;11122", "shinmj;sdkfi2@s"}, delimiter = ';')
    void createUserSuccess(String username, String password) {
        User user = User.of(username, password);

        assertAll(() -> assertThat(user.getUsername()).isEqualTo(username),
            () -> assertThat(user.getPassword()).isEqualTo(password));
    }

    @ParameterizedTest
    @CsvSource(value = { ";11122", "shinmj;"}, delimiter = ';')
    void createUserFailed(String username, String password) {
        assertThatThrownBy(() -> User.of(username, password))
            .isInstanceOf(IllegalArgumentException.class);
    }
}