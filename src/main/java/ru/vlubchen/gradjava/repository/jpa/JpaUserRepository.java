package ru.vlubchen.gradjava.repository.jpa;

import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;

import java.util.List;

public class JpaUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
