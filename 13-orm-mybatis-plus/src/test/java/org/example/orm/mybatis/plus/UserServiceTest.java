package org.example.orm.mybatis.plus;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.example.orm.mybatis.plus.entity.User;
import org.example.orm.mybatis.plus.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceTest extends SpringBootOrmMyBatisPlusApplicationTests {
    @Autowired
    private UserService userService;

    /**
     * 测试 MyBatisPlus 新增
     */
    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User
            .builder()
            .name("testSave3")
            .password(SecureUtil.md5("123456" + salt))
            .salt(salt)
            .email("testSave3@qq.com")
            .phoneNumber("17300000003")
            .status(1).lastLoginTime(new DateTime())
            .build();
        boolean save = userService.save(testSave3);
        Assert.assertTrue(save);
        log.debug("【测试id回显#testSave3.getId()】= {}", testSave3.getId());
    }

    /**
     * 测试 MyBatisPlus 批量新增
     */
    @Test
    public void testSaveList() {
        List<User> userList = Lists.newArrayList();
        for (int i = 4; i < 14; i++) {
            String salt = IdUtil.fastSimpleUUID();
            User user = User
                .builder()
                .name("testSave" + i)
                .password(SecureUtil.md5("123456" + salt))
                .salt(salt)
                .email("testSave" + i + "@qq.com")
                .phoneNumber("1730000000" + i)
                .status(1)
                .lastLoginTime(new DateTime())
                .build();
            userList.add(user);
        }
        boolean batch = userService.saveBatch(userList);
        Assert.assertTrue(batch);
        List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        log.debug("【userList#ids】= {}", ids);
    }

    /**
     * 测试 MyBatisPlus 删除
     */
    @Test
    public void testDelete() {
        boolean remove = userService.removeById(1L);
        Assert.assertTrue(remove);
        User byId = userService.getById(1L);
        Assert.assertNull(byId);
    }

    /**
     * 测试 MyBatisPlus 修改
     */
    @Test
    public void testUpdate() {
        User user = userService.getById(2L);
        Assert.assertNotNull(user);
        user.setName("MyBatisPlus修改名字");
        boolean b = userService.updateById(user);
        Assert.assertTrue(b);
        User update = userService.getById(2L);
        Assert.assertEquals("MyBatisPlus修改名字", update.getName());
        log.debug("【update】= {}", update);
    }

    /**
     * 测试 MyBatisPlus 查询单个
     */
    @Test
    public void testQueryOne() {
        User user = userService.getById(1L);
        Assert.assertNotNull(user);
        log.debug("【user】= {}", user);
    }

    /**
     * 测试 MyBatisPlus 查询全部
     */
    @Test
    public void testQueryAll() {
        List<User> list = userService.list(new QueryWrapper<>());
        Assert.assertTrue(CollUtil.isNotEmpty(list));
        log.debug("【list】= {}", list);
    }

    /**
     * 测试 MyBatisPlus 分页排序查询
     */
    @Test
    public void testQueryByPageAndSort() {
        initData();
        int count = userService.count(new QueryWrapper<>());
        Page<User> userPage = new Page<>(1, 5);
        userPage.setDesc("id");
        IPage<User> page = userService.page(userPage, new QueryWrapper<>());
        Assert.assertEquals(5, page.getSize());
        Assert.assertEquals(count, page.getTotal());
    }

    /**
     * 测试 MyBatisPlus 自定义查询
     */
    @Test
    public void testQueryByCondition() {
        initData();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "Save1").or().eq("phone_number", "17300000001").orderByDesc("id");
        int count = userService.count(wrapper);
        Page<User> userPage = new Page<>(1, 3);
        IPage<User> page = userService.page(userPage, wrapper);
        Assert.assertEquals(3, page.getSize());
        Assert.assertEquals(count, page.getTotal());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        testSaveList();
    }
}
