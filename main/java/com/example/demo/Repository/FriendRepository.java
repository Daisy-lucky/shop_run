package com.example.demo.Repository;

import com.example.demo.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository  extends JpaRepository<Friend,Integer> {
}
