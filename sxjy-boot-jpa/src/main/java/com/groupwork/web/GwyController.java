package com.groupwork.web;

import com.groupwork.dao.G04Jpa;
import com.groupwork.entity.G04;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/gwy")
public class GwyController {

    @Autowired
    G04Jpa g04jpa;


    //实体管理者
    @Autowired
    private EntityManager entityManager;

    //JPA查询工厂
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
        System.out.println("init JPAQueryFactory successfully");
    }

    /**
     * qrcode
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        List<G04> g04List=g04jpa.findAll();
        HashMap map=new HashMap();
        map.put("g04list",g04List);
        model.addAllAttributes(map);
        return "gwy/list";
    }

}