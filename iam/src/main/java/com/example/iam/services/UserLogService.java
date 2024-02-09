package com.example.iam.services;

import com.example.iam.dto.AuthRequestDTO;
import com.example.iam.dto.UserLogDTO;
import com.example.iam.entity.UserLog;

public interface UserLogService {

    String registerUser(UserLogDTO userLogDTO);



    void validateToken(String token);

    String AddRole(String Role);
}
