package com.example.rssreader.data

// fixme this class looks unnecessary
// how to fix: 1) remove it; 2) adapt it to separate layers (i.e. convert data to entity)
class RssSourceRepository {
    private val sourceDao = RssDatabase.instance.sourceDao()

    fun getSources() = sourceDao.all()

    fun getById(id: Int) = sourceDao.getById(id)

    fun add(source: RssSource) = sourceDao.insert(source)

    fun update(source: RssSource) = sourceDao.update(source)

    fun delete(source: RssSource) = sourceDao.delete(source)
}