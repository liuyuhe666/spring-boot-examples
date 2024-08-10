package org.example.orm.jpa.repository;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.example.orm.jpa.SpringBootOrmJpaApplicationTests;
import org.example.orm.jpa.entity.Department;
import org.example.orm.jpa.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Slf4j
public class DepartmentRepositoryTest extends SpringBootOrmJpaApplicationTests {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 测试保存 ,根节点
     */
    @Test
    @Transactional
    public void testSave() {
        Collection<Department> departmentList = departmentRepository.findDepartmentsByLevels(0);

        if (departmentList.isEmpty()) {
            Department testSave1 = Department.builder().name("testSave1").orderNo(0).levels(0).superior(null).build();
            Department testSave1_1 = Department.builder().name("testSave1_1").orderNo(0).levels(1).superior(testSave1).build();
            Department testSave1_2 = Department.builder().name("testSave1_2").orderNo(0).levels(1).superior(testSave1).build();
            Department testSave1_1_1 = Department.builder().name("testSave1_1_1").orderNo(0).levels(2).superior(testSave1_1).build();
            departmentList.add(testSave1);
            departmentList.add(testSave1_1);
            departmentList.add(testSave1_2);
            departmentList.add(testSave1_1_1);
            departmentRepository.saveAll(departmentList);

            Collection<Department> departmentCollection = departmentRepository.findAll();
            log.debug("部门 = {}", JSONArray.toJSONString((List) departmentCollection));
        }


        userRepository.findById(1L).ifPresent(user -> {
            user.setName("添加部门");
            user.setDepartmentList(departmentList);
            userRepository.save(user);
        });

        log.debug("用户部门 = {}", JSONUtil.toJsonStr(userRepository.findById(1L).get().getDepartmentList()));


        departmentRepository.findById(2L).ifPresent(dept -> {
            Collection<User> userList = dept.getUserList();
            // 关联关系由 user 维护中间表，department userList 不会发生变化，可以增加查询方法来处理  重写 getUserList 方法
            log.debug("部门下的用户 = {}", JSONUtil.toJsonStr(userList));
        });

        userRepository.findById(1L).ifPresent(user -> {
            user.setName("清空部门");
            user.setDepartmentList(null);
            userRepository.save(user);
        });
        log.debug("用户部门 = {}", userRepository.findById(1L).get().getDepartmentList());
    }
}
