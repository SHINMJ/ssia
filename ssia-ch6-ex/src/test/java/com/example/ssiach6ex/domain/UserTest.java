package com.example.ssiach6ex.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

    @ParameterizedTest
    @CsvSource(value = {"신명진;1234", "32123;11122", "shinmj;sdkfi2@s"}, delimiter = ';')
    void createUserSuccess(String username, String password) {
        User user = User.of(username, password, List.of(Authority.from("ROLE_ADMIN")));

        assertAll(() -> assertThat(user.getUsername()).isEqualTo(username),
            () -> assertThat(user.getPassword()).isEqualTo(password));
    }

    @ParameterizedTest
    @CsvSource(value = {"신명진;1234;", ";11122;admin", "shinmj;;admin"}, delimiter = ';')
    void createUserFailed(String username, String password, String authority) {
        assertThatThrownBy(() -> User.of(username, password, List.of(Authority.from(authority))))
            .isInstanceOf(IllegalArgumentException.class);
    }
}