package com.sphinx.project.useCase;

import com.sphinx.project.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserUseCase {

    UserUseCase applyRequest(Integer id);

    UserUseCase applyRequest(UserDTO userDTO);
    UserUseCase applyRequest(Pageable pageable);
    UserUseCase applyRequest(UserDTO userDTO, Pageable pageable);

    List<UserDTO> listUsers();

    UserUseCase saveUserToDB();

    UserUseCase getUserDTOById();

    UserUseCase getUserById();

    UserUseCase getUser();

    UserUseCase updateUserToDB();

    UserUseCase deleteUserToDB();

    String resultResponse();

    UserDTO userDTOResponse();

    UserUseCase applyRequestFile(MultipartFile file, UserDTO userDTO);

    UserUseCase checkFilePath();


    UserUseCase saveToServer() throws Exception;

    String returnResponesUploadFile();
}
