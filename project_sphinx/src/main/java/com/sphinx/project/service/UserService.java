package com.sphinx.project.service;

import com.sphinx.project.dto.UserDTO;
import com.sphinx.project.repo.UserRepo;
import com.sphinx.project.repo.UserRepoCustom;
import com.sphinx.project.useCase.UserUseCase;
import com.sphinx.project.useCase.impl.UserUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepo userRepo;

    @Autowired
    private UserRepoCustom userRepoCustom;

    private MultipartFile fileRequest;


    public List<UserDTO> getAllUsers(Pageable pageable) throws Exception {
        UserUseCase userUseCase = new UserUseCaseImpl(userRepo, userRepoCustom);
        return userUseCase
                .applyRequest(pageable)
                .listUsers();
    }

    public UserDTO getUserById(Integer id) throws  Exception{
        UserUseCase userUseCase = new UserUseCaseImpl(userRepo, userRepoCustom);
        return userUseCase
                .applyRequest(id)
                .getUserDTOById()
                .userDTOResponse();
    }

    public String saveUserToDB(UserDTO userDTO) throws Exception{
            UserUseCase userUseCase = new UserUseCaseImpl(userRepo, userRepoCustom);
        return userUseCase
                .applyRequest(userDTO)
                .saveUserToDB()
                .resultResponse();
    }

    public String updateUserToDB(UserDTO userDTO) throws Exception{
            UserUseCase userUseCase = new UserUseCaseImpl(userRepo, userRepoCustom);
            return userUseCase
                    .applyRequest(userDTO)
                    .getUserById()
                    .updateUserToDB()
                    .resultResponse();
    }

    public String deleteUserToDB(Integer id) throws Exception{
        UserUseCase userUseCase = new UserUseCaseImpl(userRepo, userRepoCustom);
        return userUseCase
                .applyRequest(id)
                .getUser()
                .deleteUserToDB()
                .resultResponse();
    }

}
