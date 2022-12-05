package com.kastourik12.urlshortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity @Getter @Setter @NoArgsConstructor
public class Visit implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_long_url")
    private LongUrl longUrl;


}
