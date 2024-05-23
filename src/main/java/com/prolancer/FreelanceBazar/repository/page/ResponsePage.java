package com.prolancer.FreelanceBazar.repository.page;

import java.util.List;

public interface ResponsePage<T> {

  List<T> getElements();

  RequestPage getRequestPage();

  long getTotalElements();

}
