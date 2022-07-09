package com.muchbeer.model

data class SMSMessageDataModel(
    val SMSMessageData : SMSMessageData
)

data class SMSMessageData (
    val Message : String,
    val Recipients : List<Recipients>
)

data class Recipients(
    val statusCode : Int,
    val number : String,
    val status : String,
    val cost : String,
    val messageId : String
)