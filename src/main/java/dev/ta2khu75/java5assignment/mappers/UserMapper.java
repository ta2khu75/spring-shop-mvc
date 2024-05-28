package dev.ta2khu75.java5assignment.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.resps.UserResp;

@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
    User toUser(UserDto userDto);
    
    UserResp toUserResp(User user);
    void updateUser(@MappingTarget User user, UserResp userResp);
    void updateUser(@MappingTarget User user, UserDto userDto);
}