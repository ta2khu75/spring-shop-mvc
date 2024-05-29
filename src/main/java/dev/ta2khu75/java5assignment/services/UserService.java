package dev.ta2khu75.java5assignment.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.resps.UserResp;
public interface UserService {   
    public User login(String email, String password);
    public Page<User> getAllUsers(Pageable pageable);
    public User getUserById(Long id);
    public User updateUserResp(UserResp user);
    public void deleteUser(Long id);
    public User getUserByUserResp(UserResp userResp);
    public User createUser(UserDto userDto);
    public User updateUser(User user);
    
}
