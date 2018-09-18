package com.example.demo.controller;

import com.example.demo.Repository.GoodsRepository;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TableController {

    @Autowired
    private GoodsRepository goodsRepository;



    @RequestMapping("table.html")//物品页面
    public String table(Model model){
        List<Goods> list=goodsRepository.findAll();
        model.addAttribute("list",list);
        return "table";
    }
}
