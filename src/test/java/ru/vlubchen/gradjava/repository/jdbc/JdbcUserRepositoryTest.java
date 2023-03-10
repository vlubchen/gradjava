package ru.vlubchen.gradjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vlubchen.gradjava.UserTestData;
import ru.vlubchen.gradjava.model.Role;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;
import ru.vlubchen.gradjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.vlubchen.gradjava.UserTestData.*;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcUserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void save() {
        User created = repository.save(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(repository.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                repository.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        repository.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(USER_ID), USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.delete(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void get() {
        User user = repository.get(USER_ID);
        assertMatch(user, UserTestData.user);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(repository.get(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        assertMatch(user, admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        repository.save(updated);
        assertMatch(repository.get(USER_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = repository.getAll();
        assertMatch(all, admin, guest, user);
    }
}