package sit.int221.kaofood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.kaofood.model.Size;

import javax.persistence.criteria.CriteriaBuilder;

public interface SizeRepository extends JpaRepository<Size, CriteriaBuilder.In> {
}
