package com.monojit.companyms.Company;

import java.util.List;

public interface CompanyService {
    List<Company>getAllCompanies();
    Boolean updateCompany(Long id,Company company);
    void createCompany(Company company);
    Boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
