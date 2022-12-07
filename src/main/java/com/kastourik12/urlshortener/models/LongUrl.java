package com.kastourik12.urlshortener.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class LongUrl extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "url should not be empty")
    private String longUrl;

    private Integer shortenedTimes;


    @CreatedDate
    private Date createdAt;





}
