package dev.ta2khu75.java5assignment.services.impls;

import java.util.List;
import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.exceptions.AlreadyExistsException;
import dev.ta2khu75.java5assignment.exceptions.NotFoundException;
import dev.ta2khu75.java5assignment.mappers.UserMapper;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.repositories.UserRepository;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public User login(String email, String password) {
        User user = repository.findByEmail(email);

        if(user!=null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            return user;
        }
        return null;
    }
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User updateUserResp(UserResp userResp) throws NotFoundException {
        User user = repository.findById(userResp.getId()).orElseThrow(() -> new NotFoundException("User not found"));
        mapper.updateUser(user, userResp);
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    @Override
    public User getUserByUserResp(UserResp userResp) {
        return repository.findByEmail(userResp.getEmail());
    }
    @Override
    public User createUser(UserDto userDto) {
        User user=repository.findByEmail(userDto.getEmail());
        if (user!=null){
            throw new AlreadyExistsException("User already exists");
        }
        user = mapper.toUser(userDto);
        user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
        return repository.save(user);
    }
    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }
    @Override
    public boolean isExistUserById(Long id) {
        return repository.existsById(id);
    }

}
