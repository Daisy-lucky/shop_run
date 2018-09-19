package com.example.demo.service;

import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CutStringService {

    public List cut(String str){
        String[] ss= str.split(",");
        List list=new ArrayList();
        for (String ll:ss){
            System.out.println("这里是切割现场"+ll);
            list.add(Integer.valueOf(ll));
        }
        return list;
    }


    @Test
    public void test(){

        CutStringService cutStringService=new CutStringService();
        List<Integer> list=cutStringService.cut("1,23,44,5,6,7,8,9");
        for(Integer in:list){
            System.out.println("这里是展示现场"+in);
        }

    }
}

