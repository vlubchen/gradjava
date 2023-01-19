package ru.vlubchen.gradjava.web.user;

import ru.vlubchen.gradjava.model.User;

import static ru.vlubchen.gradjava.web.SecurityUtil.authUserId;

public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}