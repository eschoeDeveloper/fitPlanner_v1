package com.fitplanner.domain.fitSchedule.service;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import com.fitplanner.domain.fitSchedule.repository.FitScheduleRepository;
import com.fitplanner.domain.fitSchedule.repository.specification.FitScheduleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FitScheduleServiceImpl implements FitScheduleService{

    private final FitScheduleRepository fitScheduleRepository;

    @Override
    public List<FitSchedule> getFitScheduleList(FitSchedule fitSchedule) {
        Specification<FitSchedule> specification = Specification
                .where(FitScheduleSpecification.betweenDate(fitSchedule.getFromDt(), fitSchedule.getToDt()))
                .and(FitScheduleSpecification.matchedMember(fitSchedule.getMemberSeq()));
        return fitScheduleRepository.findAll(specification);
    }

    @Override
    public Optional<FitSchedule> getFitScheduleInfo(FitSchedule fitSchedule) {

        Specification<FitSchedule> specification = Specification
                .where(FitScheduleSpecification.selectScheduleNo(fitSchedule.getScheduleNo()));

        return fitScheduleRepository.findOne(specification);

    }

    @Override
    public int createFitSchedule(FitSchedule fitSchedule) {
        return fitScheduleRepository.save(fitSchedule).getScheduleNo();
    }
}
