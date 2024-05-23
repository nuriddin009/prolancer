package com.prolancer.FreelanceBazar.repository.page;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePageImpl<T> implements ResponsePage<T> {
    private List<T> elements;
    private RequestPage requestPage;
    private long totalElements;
}
