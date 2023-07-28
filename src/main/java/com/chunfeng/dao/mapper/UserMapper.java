package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
public interface UserMapper {
    /**
     * 条件查询所有用户
     *
     * @param user 条件
     * @return 符合条件的所有用户信息
     */
    List<User> selectAllUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户信息
     */
    User selectAllByName(@Param("name") String name);

    /**
     * 根据用户ID查询用户信息
     *
     * @param ids 用户ID
     * @return 用户信息
     */
    List<User> selectAllByIds(@Param("ids") String[] ids);

    /**
     * 插入一条用户数据
     *
     * @param user 用户信息
     * @return 影响行数
     */
    Integer insertUser(User user);

    /**
     * 根据用户ID修改一条用户数据
     *
     * @param user 用户信息
     * @return 影响行数
     */
    Integer updateUserById(User user);

    /**
     * 根据用户ID批量删除用户信息
     *
     * @param ids 选择的用户ID
     * @return 影响行数
     */
    Integer deleteUserById(@Param("ids") String[] ids);
}
