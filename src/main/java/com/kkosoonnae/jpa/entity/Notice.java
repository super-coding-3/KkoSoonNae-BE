package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "NOTICE")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NO")
    private Integer noticeNo;

    @Column(name = "TITLE")
    private String noticeTitle;

    @Column(name = "CONTENT")
    private String noticeContent;

    @Column(name = "CREATE_DT")
    private String noticeDate;

    @Column(name = "VIEW_CNT")
    private String noticeViewCount;

    @Column(name = "IMG")
    private String noticeImg;
}
