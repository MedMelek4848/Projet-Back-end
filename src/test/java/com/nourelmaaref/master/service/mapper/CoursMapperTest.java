package com.nourelmaaref.master.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoursMapperTest {

    private CoursMapper coursMapper;

    @BeforeEach
    public void setUp() {
        coursMapper = new CoursMapperImpl();
    }
}
