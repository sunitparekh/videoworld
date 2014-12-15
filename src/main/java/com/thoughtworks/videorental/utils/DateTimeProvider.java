package com.thoughtworks.videorental.utils;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Created by srideep on 15/12/14.
 */

@Component
public class DateTimeProvider {

    public LocalDateTime getLocalDateTime(){
        return new LocalDateTime();
    }
}
