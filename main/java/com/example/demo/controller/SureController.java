package com.example.demo.controller;

import com.example.demo.Repository.*;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsShop;
import com.example.demo.entity.TaskWait;
import com.example.demo.entity.Tasked;
import com.example.demo.service.NowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SureController {

    @Autowired
    private TaskWaitRepository taskWaitRepository;

    @Autowired
    private TaskedRepository taskedRepository;

    @Autowired
    private TaskingRepository taskingRepository;

    @Autowired
    private NowTime nowTime;

    @Autowired
    private GoodsShopRepository goodsShopRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping("sure.html")
    public String sure(Model model, HttpSession session){
        int id=(int)session.getAttribute("login");
        List<TaskWait> list= taskWaitRepository.findByUserid(id);
        model.addAttribute("wait",list);
        return "sure";
    }

    @RequestMapping("suretask")//确认物品需要
    public String suretask(@RequestParam("id")int id){
        TaskWait taskWait=taskWaitRepository.getOne(id);
        Tasked tasked=new Tasked();
        tasked.setGoodname(taskWait.getGoodname());
        tasked.setGoodsid(taskWait.getGoodsid());
        tasked.setShopid(taskWait.getShopid());
        tasked.setShopname(taskWait.getShopname());
        tasked.setTime(nowTime.getTime());
        tasked.setUserid(taskWait.getUserid());
        tasked.setUsername(taskWait.getUsername());


        taskedRepository.save(tasked);
        taskWaitRepository.delete(taskWait);

        return "redirect:table.html";
    }

    @RequestMapping("canceltask")//不需要物品,删除任务，重建物品
    public String canceltask(@RequestParam("id")int id){

        TaskWait taskWait=taskWaitRepository.getOne(id);
        int userid=taskWait.getUserid();

        taskWaitRepository.deleteByUserid(userid);
        taskedRepository.deleteByUserid(userid);
        taskingRepository.deleteById(userid);

        Goods goodsShop=new Goods();

        GoodsShop goods=goodsShopRepository.getOne(taskWait.getGoodsid());


        goodsShop.setId(goods.getId());
        goodsShop.setBeatowal(goods.getBeatowal());
        goodsShop.setDescribe(goods.getDescribe());
        goodsShop.setMoney(goods.getMoney());
        goodsShop.setName(goods.getName());
        goodsShop.setNumber(goods.getNumber());

        goodsRepository.save(goodsShop);
        goodsShopRepository.delete(goods);

        return "redirect:table.html";
    }
}
