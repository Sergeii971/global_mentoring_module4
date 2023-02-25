package com.os.module4.web.controller;

import com.os.module4.model.dto.SportDto;
import com.os.module4.model.dto.UserDto;
import com.os.module4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto uploadUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public UserDto getById(@PathVariable(value = "id") String id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public UserDto getByEmail(@PathVariable(value = "email") String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(value = "/{id}/sports", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SportDto update(@PathVariable(value = "id") String id, @RequestBody SportDto sportDto) {
        return userService.update(id, sportDto).getSport();
    }

    @RequestMapping(value = "sports/{sportName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getBySportName(@PathVariable(value = "sportName") String sportName) {
        return userService.findBySportName(sportName);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> findByText(@RequestParam(value = "q") String q) {
        return userService.findByText(q);
    }
}
