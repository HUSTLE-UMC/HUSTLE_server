package com.sporthustle.hustle.sport;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Sport", description = "스포츠 API")
@Slf4j
@RestController
@RequestMapping("/api/sport")
@RequiredArgsConstructor
public class SportController {}
