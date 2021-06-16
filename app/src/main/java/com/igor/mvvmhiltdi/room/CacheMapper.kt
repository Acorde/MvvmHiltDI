package com.igor.mvvmhiltdi.room

import com.igor.mvvmhiltdi.modules.Blog
import com.igor.mvvmhiltdi.utils.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(id = entity.id, title = entity.title, body = entity.body, image = entity.body, category = entity.category)
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
         return BlogCacheEntity(id = domainModel.id, title = domainModel.title, body = domainModel.body, image = domainModel.body, category = domainModel.category)
    }

    fun  mapFromEntityList(entities : List<BlogCacheEntity>) : List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}