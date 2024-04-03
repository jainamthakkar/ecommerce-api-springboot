package com.jainam.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jainam.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public Category findByName(String name);
	
	@Query("Select c from Category c where c.name=:name And c.parentCategory.name=:parentCategory")
	public Category findByNameAndParent(@Param("name") String name,@Param("parentCategory") String parentCategory);

}
