package com.example.demo.Repository;

import com.example.demo.entity.Tasking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskingRepository extends JpaRepository<Tasking,Integer> {
}
