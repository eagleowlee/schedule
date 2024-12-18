package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleWithEmailResponseDto {

    private final String title;

    private final String contents;

    private final String email;

    public ScheduleWithEmailResponseDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }
}
