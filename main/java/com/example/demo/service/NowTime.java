package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NowTime {

    public String getTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(new Date());
    }
    public String getTimeFile(){//获得文件名
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HHmmss");

        return simpleDateFormat.format(new Date());
    }
}
