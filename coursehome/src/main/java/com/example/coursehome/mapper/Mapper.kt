package com.example.coursehome.mapper

interface Mapper<I,O> {
    fun map(input:I):O
}