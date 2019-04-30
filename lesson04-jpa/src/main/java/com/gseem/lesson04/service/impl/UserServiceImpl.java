package com.gseem.lesson04.service.impl;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.PagingRes;
import com.gseem.lesson04.dto.resp.UserResp;
import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.entity.UserAddress;
import com.gseem.lesson04.repository.UserRepository;
import com.gseem.lesson04.service.UserService;
import com.gseem.lesson04.util.ResultRes;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author liangpengju-ds
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    private Connection getConnection(){
        SessionImplementor sessionImplementor = em.unwrap(SessionImplementor.class);
        return sessionImplementor.connection();
    }


    /**
     * 将对象从持久态变为游离态，防止使用set方法自动持久化
     */
    private void evictObject(Object obj){
        //(1)获得hibernate的session管理对象
//        HibernateEntityManager hEntityManager = (HibernateEntityManager)em;
//        Session session = hEntityManager.getSession();
        //(2) unwrap获取Session
        //Session session = em.unwrap(Session.class);
        //(3) getDelegate获取Session
        Session session = (Session) em.getDelegate();
        // 将该对象从持久态变为游离态
        boolean contains = em.contains(obj);
        if(contains){
            session.evict(obj);
        }
    }

    /**
     * 批量更新
     * @param list
     */
    private void batchUpate(List<User> list) {
        for(int i = 0; i < list.size(); i++) {
            em.merge(list.get(i));
            if(i % 30 == 0) {
                em.flush();
                em.clear();
            }
        }
    }

    /**
     * 批量保存
     * @param list
     */
    private void batchInsert(List<User> list) {
        for(int i = 0; i < list.size(); i++) {
            em.persist(list.get(i));
            if(i % 30 == 0) {
                em.flush();
                em.clear();
            }
        }
    }

    @Autowired
    private UserRepository userRepository;

    /**
     * 更新，事务
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ResultRes updateUser(User user){
        if(user.getId() == null){
            return ResultRes.fail("更新用户ID参数不能为空");
        }
        Optional<User> dbUserOptional = userRepository.findById(user.getId());
        if(!dbUserOptional.isPresent()){
            return ResultRes.fail("用户信息不存在");
        }
        User dbUser = dbUserOptional.get();
        //禁用自动持久化
        evictObject(dbUser);

        dbUser.setUpdateTime(new Date());
        userRepository.saveAndFlush(dbUser);
        return ResultRes.ok("用户数据更新成功",dbUser);
    }

    @Override
    public PagingRes<User> queryUserBySpecification(UserReq req) {
        //拼接where条件
        Specification<User> specification = getWhereClause(req);
        //排序
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 100, sort);
        Page<User> limits = userRepository.findAll(specification, pageable);
        PagingRes<User> res = new PagingRes<>();
        if (!StringUtils.isEmpty(limits.getContent())) {
            res.setData(limits.getContent());
        }
        res.setTotal(limits.getTotalElements());
        res.setPageSize(100);
        return res;
    }

    private Specification<User> getWhereClause(final UserReq req){
        return new Specification<User>(){
            /**
             * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
             * {@link Root} and {@link CriteriaQuery}.
             *
             * @param root            must not be {@literal null}.
             * @param query           must not be {@literal null}.
             * @param criteriaBuilder must not be {@literal null}.
             * @return a {@link Predicate}, may be {@literal null}.
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //equal
                if(!StringUtils.isEmpty(req.getName())){
                    predicates.add(criteriaBuilder.equal(root.get("name"),req.getName()));
                }
                //between 示例
                if (req.getMinAge() != null && req.getMaxAge() != null) {
                    Predicate agePredicate = criteriaBuilder.between(root.get("age"), req.getMinAge(), req.getMaxAge());
                    predicates.add(agePredicate);
                }
                //greaterThan 大于等于示例
                if (req.getMinAge() != null){
                    predicates.add(criteriaBuilder.greaterThan(root.get("age"),req.getMinAge()));
                }

                //关联1张表
                //like 示例
//                if (!StringUtils.isEmpty(req.getCity())){
//                    Join<User, UserAddress> user = root.join(root.getModel()
//                            .getSingularAttribute("user", UserAddress.class), JoinType.LEFT);
//                    predicates.add(criteriaBuilder.like(root.get("city").as(String.class),"%" + req.getCity() + "%"));
//                }
                Predicate[] pre = new Predicate[predicates.size()];
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }



}
