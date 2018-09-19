package com.example.demo.controller;


import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.FriendRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Friend;
import com.example.demo.service.CutStringService;
import com.example.demo.service.NowTime;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendCircleController {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CutStringService cutStringService;
    @Autowired
    private NowTime nowTime;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("friendcircle")
    public String gopage(Model model){

        Sort sort=new Sort(Sort.Direction.DESC,"id");
        List<Friend> list=friendRepository.findAll(sort);
        model.addAttribute("list",list);
        return "friendcircle";
    }

    @RequestMapping("newword")
    public String word(@RequestParam("word")String word, HttpSession session){
        int id=(int)session.getAttribute("login");


        Friend friend=new Friend();
        friend.setComment("");
        friend.setlikes(0);
        friend.setTime(nowTime.getTime());
        friend.setuserid(id);
        friend.setUsername(userRepository.getOne(id).getName());
        friend.setWord(word);

        friendRepository.save(friend);

        return "redirect:friendcircle";

    }
}
