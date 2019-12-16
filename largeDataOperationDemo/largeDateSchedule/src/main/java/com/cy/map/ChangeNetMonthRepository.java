package com.cy.map;
import com.cy.common.BaseRepository;
import com.cy.domain.ChangeNetMonth;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeNetMonthRepository extends BaseRepository<ChangeNetMonth,String> {

}
