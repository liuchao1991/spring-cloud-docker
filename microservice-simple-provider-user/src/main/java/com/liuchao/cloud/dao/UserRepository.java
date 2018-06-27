package com.liuchao.cloud.dao;

import com.liuchao.cloud.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户仓库
 *
 * @author liuchao
 * @date 2018/6/27 21:23
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据ID查找用户
     *
     * @param id 主键
     * @return user 用户
     */
    User findById(Long id);
}
