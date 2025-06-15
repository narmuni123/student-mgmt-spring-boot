package com.munikiran.studentManagementApp.dto.student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {

    private int studentId;

    private String studentName;

    private String address;

    private String phoneNumber;

}
