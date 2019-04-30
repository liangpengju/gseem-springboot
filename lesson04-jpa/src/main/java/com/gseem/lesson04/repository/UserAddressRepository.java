package com.gseem.lesson04.repository;

import com.gseem.lesson04.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @author liangpengju-ds
 */
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {

    /**
     * 根据userId查找用户列表
     * @param userId
     * @return
     */
    List<UserAddress> findByUserId(Long userId);

    /**
     * 根据城市查询
     * @param city
     * @return
     */
    @Query(value = "select * from user_address t where t.city = ?1",nativeQuery = true)
    List<UserAddress> findByCity(String city);

    /**
     * 根据省份查找
     * @param province
     * @return
     */
    @Query("select t from UserAddress t where t.province like %?1%")
    List<UserAddress> findByProvinceLike(String province);

}
