package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Schedule() {
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;




    public Schedule(String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
