package com.chunfeng.dao.domain;

import com.chunfeng.dao.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户权限对象
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
public class UserDetail implements UserDetails {

    /**
     * 用户对象
     */
    private User user;

    /**
     * 存入Redis的权限列表
     */
    private List<String> permission;

    /**
     * 真正使用的权限列表(排除序列化)
     */
    private transient List<GrantedAuthority> authorities;

    /**
     * 权限列表配置
     *
     * @return 权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //如果权限列表已存在
        if (authorities != null) {
            return authorities;
        }
        //遍历并将String集合转换为SimpleGrantedAuthority
        authorities = permission.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 用户密码设置
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 用户名配置
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * 是否未过期
     *
     * @return true则为有效, false无效
     */
    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus() == 0;
    }

    /**
     * 是否未锁定
     *
     * @return true则为未锁状态, false为锁定状态
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 0;
    }

    /**
     * token是否未过期
     *
     * @return true则为未过期, false为过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否有效
     *
     * @return true则有效, false为无效
     */
    @Override
    public boolean isEnabled() {
        return user.getStatus() == 0;
    }
}
