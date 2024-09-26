package com.csroot.AfterWork.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("Company successfully added",HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<Company> findCompany(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleteState = companyService.deleteCompanyById(id);
        if(deleteState)
            return  new ResponseEntity<>("Company Successfully deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Impossible to delete company",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        boolean updateState = companyService.updateCompanyById(id,company);
        if(updateState)
            return  new ResponseEntity<>("Company Successfully updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Impossible to update company",HttpStatus.NOT_FOUND);
    }
}
