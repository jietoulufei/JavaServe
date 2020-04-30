package com.yiwan.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * JpaRepository<映射类，映射类主键数据类型> 简单crud查询
 * JpaSpecificationExecutor<映射类> 复杂，动态查询(分页)
 */

public interface UserDao extends JpaRepository<UserList,Integer>, JpaSpecificationExecutor<UserList> {
    /**
     * 根据用户名称查询
     * @param username
     * @return
     * jqpl: from UserList where username = ?  “？”后面一定要写形参坐标不然会报错！！
     * sql: select * from user_list where username = ?  sql语句使用的是数据库表名，jpql用的是映射类名！
     * 配置jpql语句，使用@Query注解
     * @Query 表示该方法为查询操作
     *      value: jpql语句|sql语句
     *      nativeQuery: false（默认值，使用jqpl）|true(使用sql)
     * @Modifying 表示该方法为更新操作 （需要@Transactional事务注解）
     */
    @Query(value = "select * from user_list where username = ?1 and password = ?2",nativeQuery = true)
    public UserList findusername(String username,String password);



}
