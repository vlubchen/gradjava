package ru.vlubchen.gradjava.repository.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.vlubchen.gradjava.model.Role;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.AbstractRepositoryTest;
import ru.vlubchen.gradjava.repository.UserRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.vlubchen.gradjava.UserTestData.*;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;

public class DataJpaUserRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    void save() {
        User created = repository.save(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(repository.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                repository.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        repository.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(USER_ID), USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.delete(NOT_FOUND), NOT_FOUND));
    }

    @Test
    void get() {
        User user = repository.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void update() {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.get(USER_ID), getUpdated());
    }

    @Test
    void getAll() {
        List<User> all = repository.getAll();
        USER_MATCHER.assertMatch(all, admin, guest, user);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new User(null, " ", "mail@yandex.ru", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new User(null, "User", "  ", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> repository.save(new User(null, "User", "mail@yandex.ru", "pass", true, new Date(), Set.of())));
    }

    @Test
    void enable() {
        repository.enable(USER_ID, false);
        assertFalse(repository.get(USER_ID).isEnabled());
        repository.enable(USER_ID, true);
        assertTrue(repository.get(USER_ID).isEnabled());
    }
}