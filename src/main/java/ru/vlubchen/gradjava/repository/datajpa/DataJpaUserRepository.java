package ru.vlubchen.gradjava.repository.datajpa;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;
import ru.vlubchen.gradjava.to.UserTo;
import ru.vlubchen.gradjava.util.UserUtil;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        crudRepository.save(updatedUser);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        crudRepository.save(user);
    }
}