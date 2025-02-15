package com.learn.calendar.wish.domain.repository;

import com.learn.calendar.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Integer> {

}
