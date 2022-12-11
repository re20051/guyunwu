package com.example.guyunwu.service;

public interface ClockService {

    int getClockDays(Long userId);

    void clock(Long userId);
}
