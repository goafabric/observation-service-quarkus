package org.goafabric.observationservice.laboratory.controller.dto

interface ValueQuantity {
    val code: String
    val unit: String
    val value: Number
    val system: String
}