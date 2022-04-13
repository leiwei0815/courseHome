package com.example.coursehome.mapper

import com.example.coursehome.entity.courseBrandEntity
import com.example.coursehome.model.CourseBrandItemModel

class Entity2ItemModelMapper : Mapper<courseBrandEntity, CourseBrandItemModel> {

    override fun map(input: courseBrandEntity): CourseBrandItemModel {
        return CourseBrandItemModel(
            goodsCode = input.id.toString(),
            goodsName = input.name,
            cover = input.icon
        )
    }
}