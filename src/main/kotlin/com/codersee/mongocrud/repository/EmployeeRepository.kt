package com.codersee.mongocrud.repository

import com.codersee.mongocrud.model.Employee
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, ObjectId> {
    fun findByCompanyId(id: String): List<Employee>
}