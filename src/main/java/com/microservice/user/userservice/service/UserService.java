package com.microservice.user.userservice.service;

import com.microservice.user.userservice.VO.Department;
import com.microservice.user.userservice.VO.UserVO;
import com.microservice.user.userservice.entity.User;
import com.microservice.user.userservice.exceptions.NotFoundException;
import com.microservice.user.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user){
       return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public UserVO getUserWithDepartment(Long userId){

        UserVO vo = new UserVO();
        //User user = userRepository.getById(userId);
        User user = userRepository.findById(userId).orElse(null);
        if(user == null)
            throw new NotFoundException("User Not Found for id="+userId);
        log.info("User Found, get department with id="+user.getDepartmentId());
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(),Department.class);
        if(department == null)
            throw new NotFoundException("Department Not Found for id="+user.getDepartmentId());
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;

    }

}
