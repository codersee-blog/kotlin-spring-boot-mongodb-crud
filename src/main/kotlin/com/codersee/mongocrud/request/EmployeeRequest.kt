package com.codersee.mongocrud.request

class EmployeeRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyId: String?
)