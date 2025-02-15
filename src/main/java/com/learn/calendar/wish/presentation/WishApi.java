package com.learn.calendar.wish.presentation;

import com.learn.calendar.wish.presentation.dto.request.WishRequest;
import com.learn.calendar.wish.presentation.dto.request.WishUpdateRequest;
import com.learn.calendar.wish.presentation.dto.response.WishDetailResponse;
import com.learn.calendar.wish.presentation.dto.response.WishResponse;
import com.learn.calendar.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/wish")
public class WishApi {

    private final WishService wishService;

    // 전체 조회
    @GetMapping("")
    public ResponseEntity<List<WishResponse>> getAllWishList() {
        List<WishResponse> wishResponseList = wishService.getAllWishList();
        return ResponseEntity.ok(wishResponseList);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> getWishDetail(@PathVariable Integer id) {
        WishDetailResponse wishDetail = wishService.findById(id);
        return ResponseEntity.ok(wishDetail);
    }

    // 위시리스트 생성
    @PostMapping("")
    public ResponseEntity<WishDetailResponse> createWish(@RequestBody WishRequest wishRequest) {
        WishDetailResponse createdWish = wishService.createWish(wishRequest);
        return ResponseEntity.ok(createdWish);
    }

    // 위시리스트 수정
    @PatchMapping("/{id}")
    public ResponseEntity<WishDetailResponse> updateWish(
            @PathVariable Integer id,
            @RequestBody WishUpdateRequest wishUpdateRequest) {
        WishDetailResponse updatedWish = wishService.updateWish(id, wishUpdateRequest);
        return ResponseEntity.ok(updatedWish);
    }

    // 위시리스트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Integer id) {
        wishService.deleteWish(id);
        return ResponseEntity.noContent().build();
    }

    // 위시 금액 입금
    @PutMapping("/amount/{id}")
    public ResponseEntity<WishDetailResponse> updateCurrentAmount(@PathVariable Integer id) {
        WishDetailResponse updatedWish = wishService.updateCurrentAmount(id);
        return ResponseEntity.ok(updatedWish);
    }
}
