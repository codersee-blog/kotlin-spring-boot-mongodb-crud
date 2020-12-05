package com.codersee.mongocrud.service

import com.codersee.mongocrud.exception.NotFoundException
import com.codersee.mongocrud.model.Employee
import com.codersee.mongocrud.repository.EmployeeRepository
import com.codersee.mongocrud.request.EmployeeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val companyService: CompanyService,
    private val employeeRepository: EmployeeRepository
) {

    fun createEmployee(request: EmployeeRequest): Employee {
        val company = request.companyId?.let { companyService.findById(it) }

        return employeeRepository.save(
            Employee(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                company = company
            )
        )
    }

    fun findAll(): List<Employee> =
        employeeRepository.findAll()

    fun findAllByCompanyId(id: String): List<Employee> =
        employeeRepository.findByCompanyId(id)

    fun findById(id: ObjectId): Employee =
        employeeRepository.findById(id)
            .orElseThrow { NotFoundException("Employee with id $id not found") }

    fun updateEmployee(id: ObjectId, request: EmployeeRequest): Employee {
        val employeeToUpdate = findById(id)
        val foundCompany = request.companyId?.let { companyService.findById(it) }

        return employeeRepository.save(
            employeeToUpdate.apply {
                firstName = request.firstName
                lastName = request.lastName
                email = request.email
                company = foundCompany
            }
        )
    }

    fun deleteById(id: ObjectId) {
        val employeeToDelete = findById(id)

        employeeRepository.delete(employeeToDelete)
    }
}