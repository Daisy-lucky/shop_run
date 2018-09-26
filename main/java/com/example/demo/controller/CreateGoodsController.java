package com.example.demo.controller;

import com.example.demo.Repository.GoodsRepository;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CreateGoodsController {


    @Autowired
    private GoodsRepository goodsRepository;


    @RequestMapping("creategoods.html")//跳转到新建物品页面
    public String gotopage() {
        return "creategoods";
    }


    @RequestMapping("goods")
    public String goods(@RequestParam("name") String name, @RequestParam("number") String number,
                        @RequestParam("money") String money, @RequestParam("describe") String describe, HttpSession session) {
        int login = (int) session.getAttribute("login");
        Goods goods = new Goods();
        goods.setDescribe(describe);
        goods.setBeatowal("4");
        goods.setMoney(money);
        goods.setName(name);
        goods.setNumber(number);
        goods.setUserId(login);

        goodsRepository.save(goods);
        return "redirect:table.html";
    }
}
