package com.codewithmosh.store.Mappers;

import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.dtos.UserDtoRequest;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "createdAt" , expression = "java(java.time.LocalDateTime.now())")
    UserDto toDtos(User user);
    User todtoRequest(UserDtoRequest userDtoRequest);
    void updateUser(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}
