package com.example.demo.test;


import com.example.demo.Repository.TaskWaitRepository;
import com.example.demo.entity.TaskWait;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class test {

    @Autowired
    TaskWaitRepository taskWaitRepository;
    @Test
    public void testz(){
        List<TaskWait> list=taskWaitRepository.findByShopid(2);
        for(TaskWait ss:list){
            System.out.println(ss.getShopname());
        }

    }

}
