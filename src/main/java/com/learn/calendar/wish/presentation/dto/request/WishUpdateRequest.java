package com.learn.calendar.wish.presentation.dto.request;

import com.learn.calendar.wish.domain.Frequency;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WishUpdateRequest {

    private final String title;
    private final String content;
    private final String image;
    private final Long price;
    private final Long dayDeposit;
    private final LocalDate startAt;
    private final Frequency frequency;

    @Builder
    public WishUpdateRequest(String title, String content, String image, Long price, Long dayDeposit, LocalDate startAt, Frequency frequency) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.price = price;
        this.dayDeposit = dayDeposit;
        this.startAt = startAt;
        this.frequency = frequency;
    }
}
