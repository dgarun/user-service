package com.microservice.user.userservice.VO;

import com.microservice.user.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private User user;
    private Department department;

}
