package com.codewithmosh.store.controller;

import com.codewithmosh.store.Mappers.UserMapper;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.dtos.UserDtoRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;


@AllArgsConstructor
@Getter
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("")
    public Iterable<UserDto> getAllUsers(
//            @RequestHeader(required = false, name = "x-auth-token") String authToken ,
            @RequestParam(required = false , defaultValue = "", name = "sort") String sort ){

//        System.out.println(authToken);
        if(!Set.of("name","email").contains(sort))
            sort = "name";


        return userRepository.findAll(Sort.by(sort))
                .stream()
//                .map(user -> new UserDto(user.getId(),user.getName(),user.getEmail()))
//                .map(user -> userMapper.toDtos(user))
                .map(userMapper::toDtos)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user =  userRepository.findById(id).orElse(null);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDtos(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            UriComponentsBuilder builder,
            @RequestBody UserDtoRequest data){
       var user =  userMapper.todtoRequest(data);
       userRepository.save(user);

       var userDto = userMapper.toDtos(user);
        var url = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

       return ResponseEntity.created(url).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        userMapper.updateUser(request,user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDtos(user));

    }
}
