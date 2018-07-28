package com.songzheng.service;

import com.songzheng.domain.FeedBack;
import com.songzheng.domain.Note;
import com.songzheng.domain.User;

import java.util.List;

/**
 * Created by make on 2017/6/23.
 */
public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);

    /**
     * 注册
     * @param user 用户对象
     * @return 注册结果
     */
    int register(User user);

    /**
     * 保存用户信息
     * @param user 用户对象
     * @return 结果
     */
    int saveUser(User user);

    /**
     * 同步笔记
     * @param uid 用户id
     * @param noteJson json笔记
     * @return
     */
    List<Note> syncNotes(String uid, String noteJson);

    /**
     * 保存笔记
     * @param user 用户对象
     * @param noteJson 笔记的json数据
     */
    void saveNotes(User user, String noteJson);

    /**
     * 保存笔记
     * @param uid 用户id
     * @param noteJson 笔记json数据
     */
    void saveNotes(String uid, String noteJson);

    /**
     * 查找笔记
     * @param user 用户对象
     * @return 笔记信息
     */
    List<Note> findNotes(User user);

    /**
     * 查找笔记
     * @param uid 用户id
     * @return 笔记信息
     */
    List<Note> findNotes(String uid);

    /**
     * 反馈
     * @param feedBack 反馈对象
     * @return 结果
     */
    int feedBack(FeedBack feedBack);
}
