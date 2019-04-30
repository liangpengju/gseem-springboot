package com.gseem.lesson04;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.PagingRes;
import com.gseem.lesson04.dto.resp.UserResp;
import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.entity.UserAddress;
import com.gseem.lesson04.repository.UserAddressRepository;
import com.gseem.lesson04.repository.UserRepository;
import com.gseem.lesson04.service.UserService;
import com.gseem.lesson04.util.GsonUtil;
import com.gseem.lesson04.util.ResultRes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private UserService userService;

    @Test
    public void findAllTest() {
        List<User> all = userRepository.findAll();
        System.out.println(all.size());
    }

    @Test
    public void testPageQuery()  {
        int page=1,size=2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> all = userRepository.findALL(pageable);
        log.info("total:"+all.getTotalElements());
        Page<User> byName = userRepository.findByName("feiyue", pageable);
        log.info("size:"+byName.getContent().size());
    }

    @Test
    public void testPageQueryPlus()  {
        UserReq req = new UserReq();
        req.setName("feiyue");
        Page<UserResp> byCondition = userRepository.findByCondition(req);
        System.out.println(GsonUtil.toGsonString(byCondition));
    }

    @Test
    public void testUserService()  {
        UserReq req = new UserReq();
        req.setName("feiyue");
        PagingRes<User> res = userService.queryUserBySpecification(req);
        System.out.println(GsonUtil.toGsonString(res));
    }

    @Test
    public void testUserServiceUpdate()  {
        User user = new User();
        user.setId(1L);
        ResultRes resultRes = userService.updateUser(user);
        System.out.println(GsonUtil.toGsonString(resultRes));
    }

    @Test
    public void testUserAddress()  {
        List<UserAddress> city = userAddressRepository.findByCity("北京");
        System.out.println(GsonUtil.toGsonString(city));
        List<UserAddress> province = userAddressRepository.findByProvinceLike("海淀区");
        System.out.println(GsonUtil.toGsonString(province));
    }



}
