package com.songzheng.dao.impl;

import com.songzheng.constant.Constant;
import com.songzheng.dao.UserDao;
import com.songzheng.domain.FeedBack;
import com.songzheng.domain.Note;
import com.songzheng.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by make on 2017/6/23.
 */
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    /**
     * 根据用户名密码从数据库查找用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 存在用户，则返回User对象，否则返回null
     */
    @Override
    public User findUserByNameAndPwd(String username, String password) {

        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));

        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public int addUser(User user) {
        try {
            this.getHibernateTemplate().save(user);
            return Constant.REGISTER_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.REGISTER_SERVER_ERROR;
    }

    /**
     * 查找是否存在用户名相同的用户，存在则不能注册
     *
     * @param user 要注册的用户信息
     * @return 用户是否存在
     */
    @Override
    public boolean isUserExist(User user) {
        User checkUser = findUserByUsername(user.getUsername());

        if (checkUser == null) {
            return false;
        }

        return true;
    }

    @Override
    public int saveOrUpdate(User user) {
        try {
            this.getHibernateTemplate().saveOrUpdate(user);
            return Constant.SAVE_USER_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.SAVE_USER_ERROR;
    }

    @Override
    public User findUserByUid(String uid) {
        List<User> users = (List<User>) this.getHibernateTemplate().find("from User u where u.uid = ?", uid);

        if (users.size() > 0) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public List<Note> syncNotes(String uid) {
        return null;
    }

    @Override
    public void addNotes(List<Note> notes, String uid) {
        HibernateTemplate template = this.getHibernateTemplate();
        User user = findUserByUid(uid);
        Note updateNote;
        for (Note note : notes) {
            note.setUser(user);
            if (isNoteExist(note)) {
                updateNote = findNote(note.getNid());
                updateNote.setUser(user);
                updateNote.setNote_title(note.getNote_title());
                updateNote.setNote_content(note.getNote_content());
                updateNote.setNote_lastModifiedTime(note.getNote_lastModifiedTime());
                template.update(updateNote);
            } else {
                template.save(note);
            }
        }
    }

    private boolean isNoteExist(Note note) {
        Note n = findNote(note.getNid());

        if (n == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<Note> findNoteByUid(String uid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Note.class);
        criteria.add(Restrictions.eq("user", findUserByUid(uid)));
        return (List<Note>) getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * 查询笔记信息
     * @param uid 用户id
     * @return 所有笔记
     */
    @Override
    public List<Note> findNotes(User user) {
        return new ArrayList<>(user.getNotes());
    }

    @Override
    public int addFeedBack(FeedBack feedBack) {
        return 0;
    }

    @Override
    public Note findNote(String nid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Note.class);
        criteria.add(Restrictions.eq("nid", nid));
        List<Note> list = (List<Note>) getHibernateTemplate().findByCriteria(criteria);

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public void deleteNote(String uid) {
        HibernateTemplate template = getHibernateTemplate();
        User user = findUserByUid(uid);
        user.setNotes(null);
        template.update(user);
    }

    private User findUserByUsername(String username) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User u where u.username = ?", username);

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

}
