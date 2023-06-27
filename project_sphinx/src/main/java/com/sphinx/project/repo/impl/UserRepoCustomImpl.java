//package com.fpt.fis.TGS.repo.impl;
//
//import com.fpt.fis.TGS.dto.ListCount;
//import com.fpt.fis.TGS.dto.UserDTO;
//import com.fpt.fis.TGS.model.User;
//import com.fpt.fis.TGS.repo.UserRepoCustom;
//import com.fpt.fis.TGS.ultis.DataUtil;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//@Repository("userRepoCustom")
//public class UserRepoCustomImpl implements UserRepoCustom {
//
//    @PersistenceContext(unitName = "entityManagerFactoryFormManagement")
//    EntityManager entityManager;
//
//
//    @Override
//    public ListCount findUserByConditation(UserDTO userDTO, Pageable pageable) {
//        ListCount listCount = new ListCount();
//        List<User> list = new ArrayList<>();
//        HashMap hashMap = new HashMap<>();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("SELECT " +
//                "US.ID, " +
//                "US.USERNAME, " +
//                "US.PASSWORD, " +
//                "US.AUTHORITY_NAMES, " +
//                "US.STATUS, " +
//                "US.FULL_NAME, " +
//                "US.DATE_OF_BIRTH, " +
//                "US.EMAIL, " +
//                "US.ADDRESS, " +
//                "US.PHONE, " +
//                " count(1) over () as totals FROM USERS AS US WHERE 1=1 AND STATUS = 1 ");
//        if (!DataUtil.isNullOrEmpty(userDTO.getUsername())){
//            stringBuilder.append("AND USERNAME =:username ");
//            hashMap.put("username", userDTO.getUsername());
//        }
//        if (!DataUtil.isNullOrEmpty(userDTO.getAuthorityNames())){
//            stringBuilder.append("AND AUTHORITY_NAMES =:authorityNames ");
//            hashMap.put("authorityNames", userDTO.getAuthorityNames());
//        }
//        if (!DataUtil.isNullOrEmpty(userDTO.getFullName())){
//            stringBuilder.append("AND FULL_NAME =:fullName ");
//            hashMap.put("fullName", userDTO.getFullName());
//        }
//        stringBuilder.append("LIMIT :size OFFSET :page");
//        hashMap.put("page", DataUtil.getPage(pageable));
//        hashMap.put("size", pageable.getPageSize());
//
//        Query query = entityManager.createNativeQuery(stringBuilder.toString());
//
//        hashMap.forEach((k,v)->{
//            query.setParameter(k.toString(),v);
//        });
//        List<Object[]> listQuery = query.getResultList();
//
//        if (!DataUtil.isNullOrEmpty(listQuery)){
//            listQuery.forEach(objects -> {
//                User user = new User();
//                user.setId(DataUtil.safeToLong(objects[0]));
//                user.setUsername(DataUtil.safeToString(objects[1]));
//                user.setPassword(DataUtil.safeToString(objects[2]));
//                user.setAuthorityNames(DataUtil.safeToString(objects[3]));
//                user.setStatus(DataUtil.safeToInt(objects[4]));
//                user.setFullName(DataUtil.safeToString(objects[5]));
//                user.setDateOfBirth(DataUtil.safeToSqlDate(objects[6]));
//                user.setEmail(DataUtil.safeToString(objects[7]));
//                user.setAddress(DataUtil.safeToString(objects[8]));
//                user.setPhone(DataUtil.safeToString(objects[9]));
//                listCount.setCount(DataUtil.safeToInt(objects[10]));
//                list.add(user);
//                listCount.setListUser(list);
//            });
//        }
//        return listCount;
//    }
//
//    @Override
//    public ListCount findAllUserByStatus(UserDTO userDTO, Pageable pageable) {
//        ListCount listCount = new ListCount();
//        List<User> list = new ArrayList<>();
//        HashMap hashMap = new HashMap<>();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("SELECT " +
//                "US.ID, " +
//                "US.USERNAME, " +
//                "US.PASSWORD, " +
//                "US.AUTHORITY_NAMES, " +
//                "US.STATUS, " +
//                "US.FULL_NAME, " +
//                "US.DATE_OF_BIRTH, " +
//                "US.EMAIL, " +
//                "US.ADDRESS, " +
//                "US.PHONE, " +
//                " count(1) over () as totals FROM USERS AS US WHERE 1=1 ");
//        if (!DataUtil.isNullOrZero(userDTO.getStatus())){
//            stringBuilder.append("AND STATUS =:status ");
//            hashMap.put("status", userDTO.getStatus());
//        }
//        stringBuilder.append("LIMIT :size OFFSET :page");
////        hashMap.put("page", DataUtil.getPage(pageable));
////        hashMap.put("size", pageable.getPageSize());
//
//        hashMap.put("page", DataUtil.getPage(pageable));
//        hashMap.put("size", pageable.getPageSize());
//
//        Query query = entityManager.createNativeQuery(stringBuilder.toString());
//
//        hashMap.forEach((k,v)->{
//            query.setParameter(k.toString(),v);
//        });
//        List<Object[]> listQuery = query.getResultList();
//
//        if (!DataUtil.isNullOrEmpty(listQuery)){
//            listQuery.forEach(objects -> {
//                User user = new User();
//                user.setId(DataUtil.safeToLong(objects[0]));
//                user.setUsername(DataUtil.safeToString(objects[1]));
//                user.setPassword(DataUtil.safeToString(objects[2]));
//                user.setAuthorityNames(DataUtil.safeToString(objects[3]));
//                user.setStatus(DataUtil.safeToInt(objects[4]));
//                user.setFullName(DataUtil.safeToString(objects[5]));
//                user.setDateOfBirth(DataUtil.safeToDate(objects[6]));
//                user.setEmail(DataUtil.safeToString(objects[7]));
//                user.setAddress(DataUtil.safeToString(objects[8]));
//                user.setPhone(DataUtil.safeToString(objects[9]));
//                listCount.setCount(DataUtil.safeToInt(objects[10]));
//                list.add(user);
//                listCount.setListUser(list);
//            });
//        }
//        return listCount;
//    }
//}
