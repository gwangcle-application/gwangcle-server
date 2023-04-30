package com.gwangcle.study.board.infra;

import com.gwangcle.study.board.domain.Time;
import com.gwangcle.study.board.domain.TimeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTimeRepository extends JpaRepository<Time, Long>, TimeRepository {

    @Override
    Time save(Time time);
}
