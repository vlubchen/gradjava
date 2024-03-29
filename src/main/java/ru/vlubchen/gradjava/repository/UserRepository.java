package ru.vlubchen.gradjava.repository;

import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.to.UserTo;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default void enable(int id, boolean enabled){
    };

    default void update(UserTo userTo){
    };
}