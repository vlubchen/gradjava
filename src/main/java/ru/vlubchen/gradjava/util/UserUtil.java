package ru.vlubchen.gradjava.util;

import ru.vlubchen.gradjava.model.Role;
import ru.vlubchen.gradjava.model.User;
import ru.vlubchen.gradjava.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }
}