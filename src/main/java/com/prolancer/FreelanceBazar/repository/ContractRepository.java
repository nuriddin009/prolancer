package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.Contract;
import com.prolancer.FreelanceBazar.repository.custom.CustomContractRepository;

public interface ContractRepository extends BaseRepository<Contract>, CustomContractRepository {
}
