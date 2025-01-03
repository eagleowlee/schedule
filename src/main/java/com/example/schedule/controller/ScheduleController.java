package com.example.schedule.controller;

import com.example.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleWithEmailResponseDto;
import com.example.schedule.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {
        try {
            ScheduleResponseDto ScheduleResponseDto = scheduleService.save(requestDto.getTitle(),
                requestDto.getContents(), requestDto.getUsername());

            return new ResponseEntity<>(ScheduleResponseDto, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // 잘못된 요청 데이터
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 일반 오류
        }
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        try {
            List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

            return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithEmailResponseDto> findById(@PathVariable Long id) {

        try {

            ScheduleWithEmailResponseDto scheduleWithEmailResponseDto = scheduleService.findById(
                id);

            return new ResponseEntity<>(scheduleWithEmailResponseDto, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // ID가 없을 때
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {

            scheduleService.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ID가 없을 때
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
