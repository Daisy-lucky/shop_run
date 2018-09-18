package com.example.demo.Repository;

import com.example.demo.entity.Tasking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskingRepository extends JpaRepository<Tasking,Integer> {

    List<Tasking> findByShopid(int shopid);
    void deleteByUserid(int userid);
}
