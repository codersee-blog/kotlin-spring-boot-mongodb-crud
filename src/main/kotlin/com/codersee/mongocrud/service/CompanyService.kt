package com.codersee.mongocrud.service

import com.codersee.mongocrud.exception.NotFoundException
import com.codersee.mongocrud.model.Company
import com.codersee.mongocrud.repository.CompanyRepository
import com.codersee.mongocrud.repository.EmployeeRepository
import com.codersee.mongocrud.request.CompanyRequest
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository
) {

    fun createCompany(request: CompanyRequest): Company =
        companyRepository.save(
            Company(
                name = request.name,
                address = request.address
            )
        )

    fun findAll(): List<Company> =
        companyRepository.findAll()

    fun findById(id: String): Company =
        companyRepository.findById(id)
            .orElseThrow { NotFoundException("Company with id $id not found") }

    fun updateCompany(id: String, request: CompanyRequest): Company {
        val companyToUpdate = findById(id)

        val updatedCompany = companyRepository.save(
            companyToUpdate.apply {
                name = request.name
                address = request.address
            }
        )

        updateCompanyEmployees(updatedCompany)

        return updatedCompany
    }

    fun deleteById(id: String) {
        val companyToDelete = findById(id)

        companyRepository.delete(companyToDelete)
    }

    private fun updateCompanyEmployees(updatedCompany: Company) {
        employeeRepository.saveAll(
            employeeRepository.findByCompanyId(updatedCompany.id!!)
                .map { it.apply { company = updatedCompany } }
        )
    }
}