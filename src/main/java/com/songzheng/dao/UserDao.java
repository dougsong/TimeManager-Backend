package com.songzheng.dao;

import com.songzheng.domain.FeedBack;
import com.songzheng.domain.Note;
import com.songzheng.domain.User;

import java.util.List;

/**
 * Created by make on 2017/6/23.
 */
public interface UserDao {

    /**
     * 根据用户名密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User findUserByNameAndPwd(String username, String password);

    /**
     * 新增一个用户
     * @param user 用户对象
     * @return 结果
     */
    int addUser(User user);

    /**
     * 用户是否存在
     * @param user 用户对象
     * @return 是否存在
     */
    boolean isUserExist(User user);

    /**
     * 保存或更新用户
     * @param user 用户对象
     * @return 结果
     */
    int saveOrUpdate(User user);

    /**
     * 根据uid查找用户
     * @param uid
     * @return
     */
    User findUserByUid(String uid);

    /**
     * 查找笔记
     * @param users 用户对象
     * @return 笔记信息
     */
    List<Note> findNotes(User users);

    /**
     * 查找笔记
     * @param uid 用户id
     * @return 笔记信息
     */
    List<Note> findNoteByUid(String uid);

    /**
     * 同步笔记
     * @param uid 用户uid
     * @return 笔记信息
     */
    List<Note> syncNotes(String uid);

    /**
     * 新增笔记
     * @param notes 笔记
     * @param uid 用户id
     */
    void addNotes(List<Note> notes, String uid);

    /**
     * 新增反馈信息
     * @param feedBack 反馈对象
     * @return 结果
     */
    int addFeedBack(FeedBack feedBack);

    /**
     * 查找笔记
     * @param nid 笔记id
     * @return 笔记对象
     */
    Note findNote(String nid);

    /**
     * 删除笔记
     * @param uid 笔记id
     */
    void deleteNote(String uid);
}
