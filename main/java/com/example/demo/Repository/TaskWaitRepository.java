package com.example.demo.Repository;

import com.example.demo.entity.TaskWait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskWaitRepository extends JpaRepository<TaskWait,Integer> {
    List<TaskWait> findByShopid(int shopid);
    List<TaskWait> findByUserid(int userid);
    void deleteByUserid(int userid);
}
