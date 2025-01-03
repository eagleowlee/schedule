package com.example.schedule.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleWithEmailResponseDto;
import com.example.schedule.entity.Member;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.MemberRepository;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String username) {
        // 입력값 검증
        validateTitle(title);
        validateContents(contents);
        validateUsername(username);

        // 사용자 확인
        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        // Schedule 생성 및 저장
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
        // ID 검증
        validateId(id);

        // Schedule 조회
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        Member writer = findSchedule.getMember();

        return new ScheduleWithEmailResponseDto(findSchedule.getTitle(), findSchedule.getContents(), writer.getEmail());
    }

    public void delete(Long id) {
        // ID 검증
        validateId(id);

        // Schedule 조회 및 삭제
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }

    // 유효성 검사 메서드
    private void validateTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Title cannot be empty or null.");
        }
    }

    private void validateContents(String contents) {
        if (!StringUtils.hasText(contents)) {
            throw new IllegalArgumentException("Contents cannot be empty or null.");
        }
    }

    private void validateUsername(String username) {
        if (!StringUtils.hasText(username) || username.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
    }
}
