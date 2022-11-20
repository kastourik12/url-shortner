package com.kastourik12.urlshortener.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class LongUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "url should not be empty")
    private String longUrl;

    private Integer shortenedTimes;

    private Long accessedTime;

    @CreatedDate
    private Date createdAt;



}
