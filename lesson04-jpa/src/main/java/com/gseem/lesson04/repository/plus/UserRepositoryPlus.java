package com.gseem.lesson04.repository.plus;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.UserResp;
import org.springframework.data.domain.Page;

/**
 * @author liangpengju-ds
 */
public interface UserRepositoryPlus {

    /**
     * 根据条件查找用户信息
     * @param userReq
     * @return
     */
    Page<UserResp> findByCondition(UserReq userReq);

}
