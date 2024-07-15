package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.SkillRequest;
import com.prolancer.FreelanceBazar.payload.response.SkillResponse;
import com.prolancer.FreelanceBazar.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/skill")
public class SkillController {
    private final SkillService skillService;


    @GetMapping
    public ResponseEntity<ApiResponse> getAllSkills(@RequestParam String search) {
        return ResponseEntity.ok(skillService.getAllSkills(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSkillById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(skillService.getSkillById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createSkill(@RequestBody SkillRequest skill) {
        return ResponseEntity.ok(skillService.createSkill(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSkill(@PathVariable UUID id, @RequestBody SkillRequest skillDetails) {
        return ResponseEntity.ok(skillService.updateSkill(id, skillDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSkill(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(skillService.deleteSkill(id));
    }

}
