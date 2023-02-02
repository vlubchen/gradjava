package ru.vlubchen.gradjava.repository.inmemory;

import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    private final Map<Integer, User> usersMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            usersMap.put(user.getId(), user);
            return user;
        }
        return usersMap.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        return usersMap.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return usersMap.get(id);
    }

    @Override
    public List<User> getAll() {
        return usersMap.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(Comparator.comparing(User::getEmail)))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return usersMap.values().stream()
                .filter(user -> email.equalsIgnoreCase(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
}