package com.fitplanner.domain.fitSchedule.service;

import com.fitplanner.domain.fitSchedule.model.FitSchedule;
import com.fitplanner.domain.fitSchedule.model.FitSchedulePages;
import com.fitplanner.domain.fitSchedule.repository.FitScheduleRepository;
import com.fitplanner.domain.fitSchedule.repository.specification.FitScheduleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FitScheduleServiceImpl implements FitScheduleService{

    private final FitScheduleRepository fitScheduleRepository;

    @Override
    public List<FitSchedule> getFitScheduleList(FitSchedule fitSchedule) {

        List<FitSchedule> getFitScheduleList;
        Specification<FitSchedule> specification;

        if( ( fitSchedule.getFromDt() != null && fitSchedule.getFromDt() != "" )
                && ( fitSchedule.getToDt() != null && fitSchedule.getToDt() != "" ) ) {
            specification = Specification
              .where(FitScheduleSpecification.matchedMember(fitSchedule.getMemberSeq()))
              .and(FitScheduleSpecification.betweenDate(fitSchedule.getFromDt(), fitSchedule.getToDt()));
        } else {
            specification = Specification
              .where(FitScheduleSpecification.matchedMember(fitSchedule.getMemberSeq()));
        }

        if(fitSchedule.getContentSize() > 0) {

            Pageable pageable = PageRequest.of(0, fitSchedule.getContentSize(), Sort.by("scheduleNo").descending());
            Page<FitSchedule> fitSchedulePage = fitScheduleRepository.findAll(specification, pageable);
            getFitScheduleList = fitSchedulePage.stream().collect(Collectors.toList());

        } else {
            getFitScheduleList = fitScheduleRepository.findAll(specification);
        }

        return getFitScheduleList;
    }

    @Override
    public Optional<FitSchedule> getFitScheduleInfo(FitSchedule fitSchedule) {

        Specification<FitSchedule> specification = Specification
                .where(FitScheduleSpecification.selectScheduleNo(fitSchedule.getScheduleNo()));

        return fitScheduleRepository.findOne(specification);

    }

    @Override
    public Optional<FitSchedulePages> getFitSchedulePages(int scheduleNo) {
        return fitScheduleRepository.fitSchedulePages(scheduleNo);
    }

    @Override
    public int createFitSchedule(FitSchedule fitSchedule) {
        return fitScheduleRepository.save(fitSchedule).getScheduleNo();
    }
}
