package com.muchbeer

import com.muchbeer.model.School
import com.muchbeer.model.USSDModel

interface Repository {

    fun retrieveAllSchool(): List<School>

    fun findSchoolByName(name : String): School?

    fun insertSchool(mSchool: School): School

    fun retrieveAllUSSD() : List<USSDModel>

    fun findUSSDSessionById(msessionID : String) : USSDModel?

    fun updateSessionId(msessionID: String, mUSSD : USSDModel) : USSDModel

    fun insertUSSD(ussdModel: USSDModel) : USSDModel
}