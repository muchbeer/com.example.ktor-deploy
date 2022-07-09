package com.muchbeer.service

import com.muchbeer.model.School
import com.muchbeer.model.USSDModel

interface UserService {
   suspend fun retrieveAllSchool(): List<School>

  suspend  fun findSchoolByName(name : String): School?

  suspend fun insertSchool(mSchool: School): School

   suspend fun retrieveAllUSSD() : List<USSDModel>

   suspend fun findUSSDSessionById(msessionID : String) : USSDModel?

   suspend fun updateSessionId(msessionID: String, mUSSD : USSDModel) : USSDModel

   suspend fun insertUSSD(ussdModel: USSDModel) : USSDModel
}