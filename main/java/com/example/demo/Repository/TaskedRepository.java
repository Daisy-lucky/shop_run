package com.example.demo.Repository;

import com.example.demo.entity.Tasked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskedRepository  extends JpaRepository<Tasked,Integer> {
    List<Tasked>  findByShopid(int shopid);
    void deleteByUserid(int userid);
}
