package ru.vlubchen.gradjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;

import java.util.List;

import static ru.vlubchen.gradjava.util.ValidationUtil.assureIdConsistent;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNew;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFound;
import static ru.vlubchen.gradjava.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        repository.enable(id, enabled);
    }
}