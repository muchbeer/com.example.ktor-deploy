package com.muchbeer.service

import com.muchbeer.model.School
import com.muchbeer.model.USSDModel
import com.muchbeer.repository.Repository

class UserServiceImpl(private val repository: Repository) : UserService {
    override suspend fun retrieveAllSchool(): List<School> {
        return repository.retrieveAllSchool()
    }

    override suspend fun findSchoolByName(name: String): School? {
        return repository.findSchoolByName(name)
    }

    override suspend fun insertSchool(mSchool: School): School {
        return repository.insertSchool(mSchool)
    }

    override suspend fun retrieveAllUSSD(): List<USSDModel> {
       return repository.retrieveAllUSSD()
    }

    override suspend fun findUSSDSessionById(msessionID: String): USSDModel? {
       return repository.findUSSDSessionById(msessionID)
    }

    override suspend fun updateSessionId(msessionID: String, mUSSD: USSDModel): USSDModel {
       return repository.updateSessionId(msessionID, mUSSD)
    }

    override suspend fun insertUSSD(ussdModel: USSDModel): USSDModel {
        return repository.insertUSSD(ussdModel)
    }
}