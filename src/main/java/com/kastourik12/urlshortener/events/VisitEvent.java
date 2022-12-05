package com.kastourik12.urlshortener.events;

import com.kastourik12.urlshortener.models.LongUrl;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class VisitEvent extends ApplicationEvent {
    private final LongUrl longUrl;

    public VisitEvent(LongUrl source) {
        super(source);
        this.longUrl = source;
    }


}
