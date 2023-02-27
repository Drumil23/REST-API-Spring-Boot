package com.javadrumil.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;

import com.javadrumil.demo.entity.Expense;

@Repository 
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	//SELECT * from tbl_expenses WHERE category=?
	List<Expense> findByUserIdAndCategory(Long userId,String category, org.springframework.data.domain.Pageable page);
	
	//SELECT * from tbl_expenses WHERE name LIKE '%keyword%'
	List<Expense> findByUserIdAndNameContaining(Long userId, String keyword, org.springframework.data.domain.Pageable page);
	
	//SELECT * from tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate'
	List<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, org.springframework.data.domain.Pageable page);
	
	Page<Expense> findByUserId(Long userId, org.springframework.data.domain.Pageable page);
	
	Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
