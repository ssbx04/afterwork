package com.csroot.AfterWork.company;


import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    void addCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);

    boolean updateCompanyById(Long id,Company updatedCompany);
}
