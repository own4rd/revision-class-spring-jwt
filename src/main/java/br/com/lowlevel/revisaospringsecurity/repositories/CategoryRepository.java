package br.com.lowlevel.revisaospringsecurity.repositories;

import br.com.lowlevel.revisaospringsecurity.domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
