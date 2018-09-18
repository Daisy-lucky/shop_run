package com.example.demo.Repository;

import com.example.demo.entity.Tasked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskedRepository  extends JpaRepository<Tasked,Integer> {
}
