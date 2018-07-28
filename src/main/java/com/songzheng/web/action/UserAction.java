package com.songzheng.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.songzheng.constant.Constant;
import com.songzheng.domain.Note;
import com.songzheng.domain.User;
import com.songzheng.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by make on 2017/6/23.
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录使用的Action
     * @return
     * @throws IOException
     */
    public String login() throws IOException {
        User confirm_user = userService.login(this.user.getUsername(), this.user.getPassword());

        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter writer = response.getWriter();

        if (confirm_user == null) {
            writer.print(Constant.LOGIN_USER_NOT_EXIST);
        } else {
            System.out.println(JSON.toJSONString(confirm_user));
            writer.print(JSON.toJSONString(confirm_user));
        }

        return NONE;
    }

    /**
     * 用户注册
     * @return
     * @throws IOException
     */
    public String register() throws IOException {
        int result = userService.register(user);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(result);
        return NONE;
    }

    /**
     * 同步笔记至本地
     * @return
     * @throws IOException
     */
    public String syncNote() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String noteJson = request.getParameter("note_Json");
        String userId = request.getParameter("user_id");

        List<Note> notes = userService.syncNotes(userId, noteJson);

        for (Note note : notes) {
            note.setUser(null);
        }

        ServletActionContext.getResponse().getWriter().println(JSON.toJSONString(notes));

        System.out.println(JSON.toJSONString(notes));

        return NONE;
    }

    /**
     * 同步用户信息
     * @return
     * @throws IOException
     */
    public String saveUserInfo() throws IOException {
        int result = userService.saveUser(user);
        ServletActionContext.getResponse().getWriter().println(result);
        return NONE;
    }

    @Override
    public User getModel() {
        return user;
    }
}
