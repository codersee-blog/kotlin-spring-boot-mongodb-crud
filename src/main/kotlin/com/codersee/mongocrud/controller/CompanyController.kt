package com.codersee.mongocrud.controller

import com.codersee.mongocrud.request.CompanyRequest
import com.codersee.mongocrud.response.CompanyResponse
import com.codersee.mongocrud.service.CompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/company")
class CompanyController(
    private val companyService: CompanyService
) {

    @PostMapping
    fun createCompany(@RequestBody request: CompanyRequest): ResponseEntity<CompanyResponse> {
        val createdCompany = companyService.createCompany(request)

        return ResponseEntity
            .ok(
                CompanyResponse.fromEntity(createdCompany)
            )
    }

    @GetMapping
    fun findAllCompanies(): ResponseEntity<List<CompanyResponse>> {
        val companies = companyService.findAll()

        return ResponseEntity
            .ok(
                companies.map { CompanyResponse.fromEntity(it) }
            )
    }

    @GetMapping("/{id}")
    fun findCompanyById(@PathVariable id: String): ResponseEntity<CompanyResponse> {
        val company = companyService.findById(id)

        return ResponseEntity
            .ok(
                CompanyResponse.fromEntity(company)
            )
    }

    @PutMapping("/{id}")
    fun updateCompany(
        @PathVariable id: String,
        @RequestBody request: CompanyRequest
    ): ResponseEntity<CompanyResponse> {
        val updatedCompany = companyService.updateCompany(id, request)

        return ResponseEntity
            .ok(
                CompanyResponse.fromEntity(updatedCompany)
            )
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(@PathVariable id: String): ResponseEntity<Void> {
        companyService.deleteById(id)

        return ResponseEntity.noContent().build()
    }
}