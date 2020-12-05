package com.codersee.mongocrud.repository

import com.codersee.mongocrud.model.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String>