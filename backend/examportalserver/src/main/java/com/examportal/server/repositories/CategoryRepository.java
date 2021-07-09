package com.examportal.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.server.models.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
