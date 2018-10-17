package com.example.demo.controller;

import com.example.demo.Repository.*;
import com.example.demo.entity.*;
import com.example.demo.service.NowTime;
import com.example.demo.service.UserNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private TaskingRepository taskingRepository;
    @Autowired
    private TaskedRepository taskedRepository;
    @Autowired
    private TaskWaitRepository taskWaitRepository;
    @Autowired
    private GoodsShopRepository goodsShopRepository;
    @Autowired
    private UserNameService userNameService;
    @Autowired
    private NowTime nowTime;


    @RequestMapping("task.html")//进入任务页面,
    public String task(Model model, HttpSession session) {
        int login = (int) session.getAttribute("login");

        List<Tasking> ing = taskingRepository.findByShopid(login);
        List<Tasked> ed = taskedRepository.findByShopid(login);
        List<TaskWait> wait = taskWaitRepository.findByShopid(login);
        int count=ing.size()+wait.size();

        for (TaskWait ss : wait) {
            System.out.println("这里是：" + ss.getShopname());
        }

        model.addAttribute("count",count);
        model.addAttribute("ing", ing);
        model.addAttribute("ed", ed);
        model.addAttribute("wait", wait);
        return "task";
    }


    @RequestMapping("shop")//查看物品详细信息
    public String goods(@RequestParam("id") int id, Model model) {
        Goods goods = goodsRepository.getOne(id);
        model.addAttribute("goods", goods);
        return "goods";
    }


    @RequestMapping("sure")//确认任务
    public String sure(@RequestParam("id") int id, Model model, HttpSession session) {

        Goods goods = goodsRepository.getOne(id);
        int login = (int) session.getAttribute("login");
        if (goods.getUserId() == login) {
            return "other-user";
        } else {
            Tasking tasking = new Tasking();
            tasking.setShopid(login);
            tasking.setGoodsid(id);
            tasking.setGoodname(goods.getName());
            tasking.setUserid(goods.getUserId());
            tasking.setShopname(userNameService.getName(login));
            tasking.setUsername(userNameService.getName(goods.getUserId()));
            tasking.setTime(nowTime.getTime());

            GoodsShop goodsShop = new GoodsShop();
            goodsShop.setId(goods.getId());
            goodsShop.setBeatowal(goods.getBeatowal());
            goodsShop.setDescribe(goods.getDescribe());
            goodsShop.setMoney(goods.getMoney());
            goodsShop.setName(goods.getName());
            goodsShop.setNumber(goods.getNumber());
            goodsShop.setUserid(goods.getUserId());

            goodsShopRepository.save(goodsShop);
            goodsRepository.deleteById(id);
            return "redirect:task.html";
        }
    }

    @RequestMapping("taskrun")//任务完成,ing->wait
    public String done(@RequestParam("id") int id) {
        Tasking tasking = taskingRepository.getOne(id);
        TaskWait taskWait = new TaskWait();
        taskWait.setGoodname(tasking.getGoodname());
        taskWait.setGoodsid(tasking.getGoodsid());
        taskWait.setShopid(tasking.getShopid());
        taskWait.setTime(nowTime.getTime());
        taskWait.setUserid(tasking.getUserid());
        taskWait.setUsername(tasking.getUsername());
        taskWait.setShopname(tasking.getShopname());

        taskWaitRepository.save(taskWait);
        taskingRepository.deleteById(id);
        return "redirect:task.html";
    }
}
