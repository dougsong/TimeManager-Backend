package com.songzheng.service.impl;

import com.alibaba.fastjson.JSON;
import com.songzheng.constant.Constant;
import com.songzheng.dao.UserDao;
import com.songzheng.domain.FeedBack;
import com.songzheng.domain.Note;
import com.songzheng.domain.User;
import com.songzheng.service.UserService;
import com.songzheng.util.MD5Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by make on 2017/6/23.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) {
//        String md5Password = MD5Utils.MD5Encode(password, "UTF-8", false);    // 在客户端加密
        return userDao.findUserByNameAndPwd(username, password);
    }

    @Override
    public int register(User user) {
        user.setPassword(MD5Utils.MD5Encode(user.getPassword(), "UTF-8", false));

        boolean exist = userDao.isUserExist(user);

        if (exist) {
            return Constant.REGISTER_USERNAME_EXIST;
        }

        return userDao.addUser(user);
    }

    @Override
    public int saveUser(User user) {
        return userDao.saveOrUpdate(user);
    }

    @Override
    public List<Note> syncNotes(String uid, String noteJson) {
//        User user = userDao.findUserByUid(uid);
//        saveNotes(user, noteJson);
//        return findNotes(user);
        saveNotes(uid, noteJson);
        return findNotes(uid);
    }

    @Override
    public void saveNotes(User user, String noteJson) {
        if (noteJson == null || "[]".equals(noteJson)) {
            // 客户端没有笔记
            System.out.println("no note");
            return;
        }
        System.out.println("========================\n" + noteJson);
        List<Note> notes = JSON.parseArray(noteJson, Note.class);
        Set<Note> noteInServer = user.getNotes();
        noteInServer.addAll(notes);
        user.setNotes(noteInServer);
    }

    @Override
    public void saveNotes(String uid, String noteJson) {
        if (noteJson == null || "[]".equals(noteJson)) {
            // 客户端没有笔记
            System.out.println("no note");
            return;
        }
        System.out.println("========================\n" + noteJson);
        List<Note> notes = JSON.parseArray(noteJson, Note.class);
//        userDao.deleteNote(uid);
        userDao.addNotes(notes, uid);
    }

    @Override
    public List<Note> findNotes(User user) {
        return new ArrayList<>(user.getNotes());
    }

    @Override
    public List<Note> findNotes(String uid) {
        return userDao.findNoteByUid(uid);
    }


    @Override
    public int feedBack(FeedBack feedBack) {
        return 0;
    }
}
