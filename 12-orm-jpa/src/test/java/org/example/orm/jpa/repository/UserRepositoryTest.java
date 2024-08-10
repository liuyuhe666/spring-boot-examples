package org.example.orm.jpa.repository;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.example.orm.jpa.SpringBootOrmJpaApplicationTests;
import org.example.orm.jpa.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class UserRepositoryTest extends SpringBootOrmJpaApplicationTests {
    @Autowired
    private UserRepository userRepository;

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User
            .builder()
            .name("testSave3")
            .password(SecureUtil.md5("123456" + salt))
            .salt(salt)
            .email("testSave3@xkcoding.com")
            .phoneNumber("17300000003")
            .status(1)
            .lastLoginTime(new DateTime())
            .build();
        userRepository.save(testSave3);

        Assert.assertNotNull(testSave3.getId());
        Optional<User> byId = userRepository.findById(testSave3.getId());
        Assert.assertTrue(byId.isPresent());
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        long count = userRepository.count();
        userRepository.deleteById(1L);
        long left = userRepository.count();
        Assert.assertEquals(count - 1, left);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate() {
        userRepository.findById(2L).ifPresent(user -> {
            user.setName("JPA修改名字");
            userRepository.save(user);
        });
        Assert.assertEquals("JPA修改名字", userRepository.findById(2L).get().getName());
    }

    /**
     * 测试查询单个
     */
    @Test
    public void testQueryOne() {
        Optional<User> byId = userRepository.findById(1L);
        Assert.assertTrue(byId.isPresent());
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testQueryAll() {
        List<User> users = userRepository.findAll();
        Assert.assertNotEquals(0, users.size());
        log.debug("users = {}", users);
    }

    /**
     * 测试分页排序查询
     */
    @Test
    public void testQueryPage() {
        // 初始化数据
        initData();
        // JPA 分页的时候起始页是页码减1
        int currentPage = 0;
        int pageSize = 5;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageRequest);

        Assert.assertEquals(5, userPage.getSize());
        Assert.assertEquals(userRepository.count(), userPage.getTotalElements());
        log.debug("id = {}", userPage.getContent().stream().map(User::getId).collect(Collectors.toList()));
    }

    /**
     * 初始化 10 条数据
     */
    private void initData() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String salt = IdUtil.fastSimpleUUID();
            int index = 3 + i;
            User user = User
                .builder()
                .name("testSave" + index)
                .password(SecureUtil.md5("123456" + salt))
                .salt(salt)
                .email("testSave" + index + "@qq.com")
                .phoneNumber("1730000000" + index)
                .status(1)
                .lastLoginTime(new DateTime())
                .build();
            userList.add(user);
        }
        userRepository.saveAll(userList);
    }
}
