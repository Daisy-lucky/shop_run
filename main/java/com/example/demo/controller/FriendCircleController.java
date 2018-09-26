package com.example.demo.controller;


import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.FriendRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Friend;
import com.example.demo.entity.User;
import com.example.demo.service.CutStringService;
import com.example.demo.service.FileService;
import com.example.demo.service.NowTime;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
    @Autowired
    private FileService fileService;

    @RequestMapping("friendcircle")//进入页面
    public String gopage(Model model,HttpSession session) {

        int id=(int)session.getAttribute("login");
        User user=userRepository.getOne(id);
        model.addAttribute("user",user);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Friend> list = friendRepository.findAll(sort);
        model.addAttribute("list", list);
        return "friendcircle";
    }

    @RequestMapping("newword")//新建信息
    public String word(@RequestParam("word") String word, @RequestParam("file") MultipartFile file, HttpSession session) {
        int id = (int) session.getAttribute("login");
        String type=file.getContentType();
        String str=file.getOriginalFilename();
        int x=str.lastIndexOf(".");
        str=str.substring(x,str.length());
        str=nowTime.getTimeFile()+str;
        String path = "D:/Testdownload/" +str;
        try {
            byte[] ff = file.getBytes();
            fileService.file(ff, path);
            System.out.println("这里正常");
        } catch (Exception e) {
            System.out.println("文件上传发生异常");
        }


        Friend friend = new Friend();
        friend.setComment("");
        friend.setlikes(0);
        friend.setTime(nowTime.getTime());
        friend.setuserid(id);
        friend.setUsername(userRepository.getOne(id).getName());
        friend.setWord(word);
        if (!file.isEmpty()) {
            friend.setPhoto("http://localhost:8080/"+str);
        }
        friendRepository.save(friend);

        return "redirect:friendcircle";

    }
}
