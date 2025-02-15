package com.learn.calendar.wish.service;

import com.learn.calendar.wish.service.util.DateUtil;
import com.learn.calendar.wish.domain.Wish;
import com.learn.calendar.wish.domain.repository.WishRepository;
import com.learn.calendar.wish.presentation.dto.request.WishRequest;
import com.learn.calendar.wish.presentation.dto.request.WishUpdateRequest;
import com.learn.calendar.wish.presentation.dto.response.WishDetailResponse;
import com.learn.calendar.wish.presentation.dto.response.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    // 위시 전체 조회
    public List<WishResponse> getAllWishList() {
        return wishRepository.findAll().stream()
                .map(wish -> WishResponse.from(wish))
                .collect(Collectors.toList());
    }

    // 위시 단일 조회
    public WishDetailResponse findById(Integer id) {
        return wishRepository.findById(id)
                .map(WishDetailResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));
    }

    // 위시 생성
    @Transactional
    public WishDetailResponse createWish(WishRequest wishRequest) {
        LocalDate expirationDate = calculateEndDate(wishRequest);

        Wish wish = Wish.builder()
                .title(wishRequest.getTitle())
                .content(wishRequest.getContent())
                .image(wishRequest.getImage())
                .price(wishRequest.getPrice())
                .currentAmount(wishRequest.getPrice())
                .dayDeposit(wishRequest.getDayDeposit())
                .frequency(wishRequest.getFrequency())
                .startAt(wishRequest.getStartAt())
                .expirationAt(expirationDate)
                .createAt(LocalDate.now())
                .build();

        Wish saveWish = wishRepository.save(wish);
        return WishDetailResponse.of(saveWish);
    }

    // 위시 수정
    @Transactional
    public WishDetailResponse updateWish(Integer id, WishUpdateRequest wishUpdateRequest) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));

        if (wishUpdateRequest.getDayDeposit() != null &&
                wishUpdateRequest.getDayDeposit() > wish.getCurrentAmount()) {

            throw new IllegalArgumentException("입금액은 현재 금액보다 클 수 없습니다.");
        }

        wish.updateWish(wishUpdateRequest);
        return WishDetailResponse.of(wish);

    }

    // 위시 삭제
    public void deleteWish(Integer id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));

        wishRepository.delete(wish);
    }

    // 위시 금액 입금
    public WishDetailResponse updateCurrentAmount(Integer id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));

        Long newCurrentAmount = wish.getCurrentAmount() - wish.getDayDeposit();
        wish.updateCurrentAmount(newCurrentAmount);

        return WishDetailResponse.of(wishRepository.save(wish));
    }

    // endDate 계산 로직
    private LocalDate calculateEndDate(WishRequest wishRequest) {
        long totalDays = DateUtil.calculateTotalDays(
                wishRequest.getPrice(),
                wishRequest.getDayDeposit(),
                wishRequest.getFrequency()
        );
        return DateUtil.calculateEndDate(wishRequest.getStartAt(), totalDays, wishRequest.getFrequency());
    }
}
