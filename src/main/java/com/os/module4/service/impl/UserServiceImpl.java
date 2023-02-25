package com.os.module4.service.impl;

import com.os.module4.model.dto.SportDto;
import com.os.module4.model.dto.UserDto;
import com.os.module4.model.entity.Sport;
import com.os.module4.model.entity.User;
import com.os.module4.model.exception.ResourceNotFoundException;
import com.os.module4.persistent.UserRepository;
import com.os.module4.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final MongoTemplate mongoTemplate;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.insert(user), UserDto.class);
    }

    @Override
    public UserDto findById(String id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("user not find by id: " + id));
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("user not find by email: " + email));
    }

    @Override
    public UserDto update(String userId, SportDto sportDto) {
        Sport sport = modelMapper.map(sportDto, Sport.class);
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("user not find by id: " + userId));
        user.setSport(sport);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> findBySportName(String sportName) {
        return userRepository.findBySportName(sportName)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findByText(String text) {
        mongoTemplate.indexOps(User.class)
                .ensureIndex(new TextIndexDefinition
                        .TextIndexDefinitionBuilder()
                        .onAllFields()
                        .build());

        var parameters = text.split(",");
        var query = TextQuery.queryText(new TextCriteria().matchingAny(parameters));
        return mongoTemplate.find(query, User.class)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
