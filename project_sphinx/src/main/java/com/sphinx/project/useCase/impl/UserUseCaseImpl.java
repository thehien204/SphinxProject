package com.sphinx.project.useCase.impl;

import com.sphinx.project.constant.Constants;
import com.sphinx.project.dto.UserDTO;
import com.sphinx.project.mapper.BaseMapper;
import com.sphinx.project.repo.UserRepo;
import com.sphinx.project.model.User;
import com.sphinx.project.repo.UserRepoCustom;
import com.sphinx.project.ultis.DataUtil;
import com.sphinx.project.useCase.UserUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public class UserUseCaseImpl implements UserUseCase {

    private Integer id;

    private User user = new User();

    private UserDTO userDTO;

    private List<User> users;

    private List<UserDTO> userDTOList;

    private UserRepo userRepo;
    private UserRepoCustom userRepoCustom;

    private Pageable pageable;

    private Integer size;

    private Integer page;

    private MultipartFile fileRequest;

    private String messageError = "";

    private String realPath;

    private final BaseMapper<User, UserDTO> userDTOBaseMapper = new BaseMapper<>(User.class, UserDTO.class);

    public UserUseCaseImpl(UserRepo userRepo, UserRepoCustom userRepoCustom){
        this.realPath = realPath;
        this.userRepo = userRepo;
        this.userRepoCustom = userRepoCustom;
    }


    @Override
    public UserUseCase applyRequest(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public UserUseCase applyRequest(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }

    @Override
    public UserUseCase applyRequest(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    @Override
    public UserUseCase applyRequest(UserDTO userDTO, Pageable pageable) {
        this.pageable = pageable;
        this.userDTO = userDTO;
        return this;
    }

    @Override
    public List<UserDTO> listUsers() {
        this.size = this.pageable.getPageSize();
        this.page = DataUtil.getPage(this.pageable);
        this.users = userRepo.findAllByStatus(1, this.size, this.page );
        return userDTOBaseMapper.toDto(this.users);
    }


    @Override
    public UserUseCase getUserDTOById() {
        this.userDTO = userDTOBaseMapper.toDto(userRepo.findUserById(this.id));
        return this;
    }


    @Override
    public UserUseCase getUserById(){
        this.user = userRepo.findUserById(this.userDTO.getId());
        return this;
    }

    public UserUseCase getUser(){
        this.user = userRepo.findUserById(this.id);
        return this;
    }
    @Override
    public UserUseCase saveUserToDB() {
        this.user = userDTOBaseMapper.toEntity(userDTO);
        userRepo.save(this.user);
        return this;
    }

    @Override
    public UserUseCase updateUserToDB() {
        this.user = userRepo.findUserById(this.userDTO.getId());
        userRepo.save(user);
        return this;
    }

    @Override
    public UserUseCase deleteUserToDB() {
        userRepo.save(user);
        return this;
    }

    @Override
    public String resultResponse() {
        return Constants.MESSAGE.SUCCESS;
    }

    @Override
    public UserDTO userDTOResponse() {
        return this.userDTO;
    }

    @Override
    public UserUseCase applyRequestFile(MultipartFile file, UserDTO userDTO) {
        this.userDTO = userDTO;
        this.fileRequest = file;
        return this;
    }

    @Override
    public UserUseCase checkFilePath() {
        if (DataUtil.isNullOrEmpty(this.messageError)){
            if (! new File(this.realPath).exists()){
                new File(this.realPath).mkdir();
            }
        }
        return this;
    }
    private String fileName;
    private String filePath;
    @Override
    public UserUseCase saveToServer() throws Exception {
        if (DataUtil.isNullOrEmpty(this.messageError)){
            this.fileName = this.fileRequest.getOriginalFilename();
            this.filePath = this.realPath+this.fileName;
            File dest = new File(filePath);
            this.fileRequest.transferTo(dest);
        }
        return this;
    }

    @Override
    public String returnResponesUploadFile() {
        if (!DataUtil.isNullOrEmpty(this.messageError)){
            return this.messageError;
        }
        return "ss";
    }
}
