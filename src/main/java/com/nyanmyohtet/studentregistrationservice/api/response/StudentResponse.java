package com.nyanmyohtet.studentregistrationservice.api.response;

import com.nyanmyohtet.studentregistrationservice.api.request.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private List<StudentDto> content;
    // TODO: separate pagination
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
