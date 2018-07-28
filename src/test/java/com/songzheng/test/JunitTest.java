package com.songzheng.test;

import com.alibaba.fastjson.JSON;
import com.songzheng.domain.Note;
import com.songzheng.domain.Task;
import com.songzheng.domain.TaskGroup;
import com.songzheng.domain.User;
import com.songzheng.service.UserService;
import com.songzheng.util.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by make on 2017/6/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JunitTest {

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void f1() {
        User user = userService.login("1234", "123");
        if (user == null) {
            System.out.println("Login Failed");
        } else {
            System.out.println("Login Success");
        }
    }

    @Test
    public void testjson() {
        User user = userService.login("123", "123");
        user.setNotes(new HashSet<>());
        user.setTaskGroups(new HashSet<>());
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testMD5() {
        String password = "121";
        System.out.println(MD5Utils.MD5Encode(password, "UTF-8", false));
    }

    public void addUser() {
        User user = new User();
        user.setUsername("111");
        user.setPassword("121");

        user.setNotes(getTestNotes());

        user.setTaskGroups(getTaskGroups());

        userService.register(user);
    }

    private Set<TaskGroup> getTaskGroups() {

        Set<TaskGroup> groups = new HashSet<>();

        TaskGroup group = new TaskGroup();
        group.setTasks(getTasks());
        group.setCount(getTasks().size());
        group.setGroup_title("test");

        groups.add(group);

        group = new TaskGroup();
        group.setTasks(getTasks());
        group.setCount(getTasks().size());
        group.setGroup_title("test");

        groups.add(group);

        return groups;

    }

    private Set<Task> getTasks() {
        Set<Task> tasks = new HashSet<>();

        Task task = new Task();
        task.setTask_content("11");

        tasks.add(task);

        task = new Task();
        task.setTask_content("22");

        tasks.add(task);

        return tasks;

    }

    private Set<Note> getTestNotes() {
        Set<Note> notes = new HashSet<>();

        Note note = new Note();
        note.setNote_content("haha");
        note.setNote_title("66");

        notes.add(note);

        note = new Note();
        note.setNote_content("hiehie");
        note.setNote_title("55");

        notes.add(note);

        return notes;
    }

}
