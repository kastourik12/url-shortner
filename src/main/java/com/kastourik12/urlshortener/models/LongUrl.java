package com.kastourik12.urlshortener.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class LongUrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String longUrl;

    private Integer shortenedTimes;

    private Long visitedTime;

    @CreatedDate
    private Date createdAt;



}
