package ru.slloc.voteforalunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.slloc.voteforalunch.AuthorizedUser;
import ru.slloc.voteforalunch.model.User;
import ru.slloc.voteforalunch.repository.UserRepository;
import ru.slloc.voteforalunch.util.exception.NotFoundException;


import java.util.List;

import static ru.slloc.voteforalunch.util.UserUtil.prepareToSave;
import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFound;
import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.trim().toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User getWithVotes(int id) {
        return checkNotFoundWithId(repository.getWithVotes(id), id);
    }
}