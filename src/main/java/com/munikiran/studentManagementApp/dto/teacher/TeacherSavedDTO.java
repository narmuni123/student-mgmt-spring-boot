package com.munikiran.studentManagementApp.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSavedDTO {
    private String teacherName;

    private String address;

    private String phoneNumber;
}
