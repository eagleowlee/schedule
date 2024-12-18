package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleWithEmailResponseDto;
import com.example.schedule.entity.Member;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.MemberRepository;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();


    }

    public ScheduleWithEmailResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        Member writer = findSchedule.getMember();

        return new ScheduleWithEmailResponseDto(findSchedule.getTitle(), findSchedule.getContents(), writer.getEmail());

    }

    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);

    }


}
