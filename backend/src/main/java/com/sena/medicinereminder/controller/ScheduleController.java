package com.sena.medicinereminder.controller;

import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.DTO.ScheduleDTO;
import com.sena.medicinereminder.model.Schedule;
import com.sena.medicinereminder.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {


    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> res = scheduleService.getAllSchedules();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> addSchedule(@RequestBody ScheduleDTO scheduleDTO ) {
        ResponseDTO res = scheduleService.addSchedule(scheduleDTO);
        return ResponseEntity.ok(res);
    }
}
