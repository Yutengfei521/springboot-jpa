package com.yufly.dao;

import com.yufly.bean.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    //查询指定用户姓名的用户
    Person findByNameIs(String name);
    //查询指定用户姓名和密码都相同的用户
    Person findByNameIsAndPassword(String name,String password);
    //查询包含指定名字的用户
    List<Person> findByNameContaining(String name);

    //查询指定用户姓名的用户
    @Query("select p from Person p where p.name=:name")
    public Person getPerson(@Param("name") String name);

    //用户登录验证
    @Query("select p from Person p where p.name=?1 and p.password=?2")
    public Person login(@Param("name") String name,@Param("password") String password);

    //模糊查询用户名里面包含指定字符
    @Query("select p from Person p where p.name like %:name%")
    public List<Person> getNamesLike(@Param("name") String name);

    //查询密码位数是5位数的全部用户,使用mysql原始sql语句进行查询
    @Query(value="select * from person where length(password)=5",nativeQuery=true)
    public List<Person> getPasswordisFive();

    //修改用户名称，使用自定义语句来更新,需要显示声明开启写事务
    @Modifying
    @Transactional(readOnly = false)
    @Query("update Person p set p.name=?2 where p.id=?1")
    int UpdateName(Long id,String name);

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete from Person p where p.name=?1")
    int DeleteName(String name);

    // 排序查询，返回list集合
    List<Person> findByNameContaining(String name, Sort sort);

    //分页查询， 查询计算元素总个数、总页数，数据多的情况下，代价是昂贵的
    Page<Person> findByNameContaining(String name , Pageable pageable);
    //分页查询，返回的是一个片段，它只知道下一片段或者上一片段是否可用。
    Slice<Person> getByNameContaining(String name, Pageable pageable);

    //查询指定用户名称，按照id降序排序第一条记录
    Person findFirstByNameOrderByIdDesc(String name);
    //模糊查询指定用户名称，按照id降序排序前10条记录
    List<Person> findFirst10ByNameLikeOrderByIdDesc(String name);

    //查询指定用户名称，按照id升序排序第一条记录
    Person findTopByNameOrderByIdAsc(String name);
    //模糊查询指定用户名称，按照id升序排序前10条记录
    List<Person> findTop10ByNameLikeOrderByIdAsc(String name);



}
