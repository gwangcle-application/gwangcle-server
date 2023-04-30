package com.gwangcle.study.board.infra;

import com.gwangcle.study.board.domain.Week;
import com.gwangcle.study.board.domain.WeekRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaWeekRepository extends JpaRepository<Week, Long>, WeekRepository {

    @Override
    Week save(Week week);
}
