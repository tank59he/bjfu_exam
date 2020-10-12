package com.bjfu.exam.service.impl;

import com.bjfu.exam.dto.user.UserDTO;
import com.bjfu.exam.dto.user.UserDetailDTO;
import com.bjfu.exam.entity.user.User;
import com.bjfu.exam.enums.UserTypeEnum;
import com.bjfu.exam.exception.NotAllowOperationException;
import com.bjfu.exam.exception.UserNotExistException;
import com.bjfu.exam.repository.user.UserRepository;
import com.bjfu.exam.request.user.LoginRequest;
import com.bjfu.exam.request.user.UserChangePasswordRequest;
import com.bjfu.exam.request.user.UserRegisterRequest;
import com.bjfu.exam.service.UserService;
import com.bjfu.exam.util.EntityConvertToDTOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailDTO loginCheck(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByAccount(loginRequest.getAccount());
        if(userOptional.isEmpty()) {
            return null;
        } else {
            User user = userOptional.get();
            if(user.getPassword().equals(loginRequest.getPassword())) {
                return EntityConvertToDTOUtil.convertUserToDetail(user);
            } else {
                return null;
            }
        }
    }

    @Override
    @Transactional
    public UserDetailDTO register(UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest.getType().equals(UserTypeEnum.STUDENT.getType())
                || userRegisterRequest.getType().equals(UserTypeEnum.TEACHER.getType())) {
            if(userRepository.existsByAccount(userRegisterRequest.getAccount())) {
                return null;
            } else {
                User user = new User();
                BeanUtils.copyProperties(userRegisterRequest, user);
                user = userRepository.save(user);
                return EntityConvertToDTOUtil.convertUserToDetail(user);
            }
        } else {
            throw new NotAllowOperationException("企图创建其他类型账号");
        }
    }

    @Override
    public UserDTO changePassword(UserChangePasswordRequest userChangePasswordRequest) {
        Optional<User> userOptional = userRepository.findByAccount(userChangePasswordRequest.getAccount());
        if(userOptional.isEmpty()) {
            throw new UserNotExistException(userChangePasswordRequest.getAccount(), "修改密码的用户不存在");
        } else {
            User user = userOptional.get();
            if(user.getPassword().equals(userChangePasswordRequest.getOldPassword())) {
                user.setPassword(userChangePasswordRequest.getNewPassword());
                user = userRepository.save(user);
                return EntityConvertToDTOUtil.convertUser(user);
            } else {
                return null;
            }
        }
    }

    @Override
    public UserDetailDTO getUserDetail(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            return null;
        } else {
            User user = userOptional.get();
            return EntityConvertToDTOUtil.convertUserToDetail(user);
        }
    }

}
