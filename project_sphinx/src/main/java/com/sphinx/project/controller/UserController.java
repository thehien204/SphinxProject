package com.sphinx.project.controller;

import com.sphinx.project.constant.Constants;
import com.sphinx.project.controller.base.BaseResource;
import com.sphinx.project.dto.UserDTO;
import com.sphinx.project.hepler.ApiResponse;
import com.sphinx.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 create_by : tienhv14
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> findAllUser(HttpServletRequest request, Pageable pageable){
        try {
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.SUCCESS, true, "Success", userService.getAllUsers(pageable)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.ERROR, true, "Success", null), HttpStatus.OK);
        }
    }

    @GetMapping(value = "find-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> findUserById(HttpServletRequest request, @PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.SUCCESS, true, "Success", userService.getUserById(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.ERROR, true, "Success", null), HttpStatus.OK);
        }
    }

    @PutMapping (value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> saveUserToDB(HttpServletRequest request, @RequestBody UserDTO userDTO){
        try {
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.SUCCESS, true, "Success", userService.saveUserToDB(userDTO)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.ERROR, true, "Success", null), HttpStatus.OK);
        }
    }

    @PutMapping (value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateUserToDB(HttpServletRequest request, @RequestBody UserDTO userDTO){
        try {
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.SUCCESS, true, "Success", userService.updateUserToDB(userDTO)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.ERROR, true, "Success", null), HttpStatus.OK);
        }
    }

    @DeleteMapping (value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> deleteUserToDB(HttpServletRequest request,  @PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.SUCCESS, true, "Success", userService.deleteUserToDB(id)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ApiResponse.build(Constants.CODE.ERROR, true, "Success", null), HttpStatus.OK);
        }
    }
}
