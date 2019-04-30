package com.gseem.lesson04.repository.plus.impl;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.UserResp;
import com.gseem.lesson04.repository.plus.UserRepositoryPlus;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangpengju-ds
 */
public class UserRepositoryImpl implements UserRepositoryPlus {

    @PersistenceContext
    private EntityManager em;

    private final String selectSql = "select a.id,a.name,a.age,a.sex,a.birthday,a.create_time,a.update_time,a.`status`," +
            "b.city,b.province,b.street,b.zipcode "+
            " from user a left join user_address b on a.id = b.user_id where 1=1 ";

    private final String countSql = "select count(*) from user a left join user_address b on a.id = b.user_id where 1=1 ";

    /**
     * 根据条件查找用户信息
     *
     * @param req
     * @return
     */
    @Override
    public Page<UserResp> findByCondition(UserReq req) {
        StringBuilder dataSql = new StringBuilder(selectSql);
        StringBuilder countSqlBuilder = new StringBuilder(countSql);
        String where = genWhereSql(req);
        //总记录数目
        long totalSize = new BigInteger(em.createNativeQuery(countSqlBuilder.append(where).toString()).getSingleResult().toString()).longValue();
        //分页
        Query dataQuery = em.createNativeQuery(dataSql.append(where).toString());
        Pageable pageable = new PageRequest(req.getPage() - 1, req.getPageSize());
        //排序
        pageable.getSort().and(new Sort(Sort.Direction.ASC,"id"));
        dataQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        dataQuery.setMaxResults(pageable.getPageSize());
        List<Object[]> objectList = dataQuery.getResultList();
        //组合返回结果
        List<UserResp> resList = objectList.stream().map(item -> getUserResp(item)).collect(Collectors.toList());
        return new PageImpl<>(resList, pageable, totalSize);
    }

    /**
     * where查询条件
     * @param req
     * @return
     */
    private String genWhereSql(UserReq req) {
        StringBuilder where = new StringBuilder();
        //name
        if(!StringUtils.isEmpty(req.getName())){
            where.append(" AND a.name = '").append(req.getName()).append("'");
        }
        //age
        if(!StringUtils.isEmpty(req.getMaxAge()) && !StringUtils.isEmpty(req.getMinAge())){
            where.append(" AND a.age BETWEEN ").append(req.getMinAge()).append(" AND ").append(req.getMaxAge());
        }
        //b city
        if(!StringUtils.isEmpty(req.getCity())){
            where.append(" AND b.city = '").append(req.getCity()).append("'");
        }
        //按照控制组排序
        where.append(" ORDER BY a.id ");
        return where.toString();
    }


    /**
     *  a.id,a.name,a.age,a.sex,a.birthday,a.create_time,a.update_time,a.`status`,
     *  b.city,b.province,b.street,b.zipcode
     * @param item
     * @return
     */
    private UserResp getUserResp(Object[] item) {
        UserResp res = new UserResp();
        //limit
        res.setId(Long.parseLong(String.valueOf(item[0])));
        res.setName(String.valueOf(item[1]));
        res.setAge(Integer.parseInt(String.valueOf(item[2])));
        res.setSex(Integer.parseInt(String.valueOf(item[3])));
        res.setBirthday(String.valueOf(item[4]));
        res.setCreateTime(String.valueOf(item[5]));
        res.setUpdateTime(String.valueOf(item[6]));
        res.setStatus(Integer.parseInt(String.valueOf(item[7])));
        res.setCity(String.valueOf(item[8]));
        res.setProvince(String.valueOf(item[9]));
        res.setStreet(String.valueOf(item[10]));
        res.setZipcode(String.valueOf(item[11]));
        return res;
    }

}
