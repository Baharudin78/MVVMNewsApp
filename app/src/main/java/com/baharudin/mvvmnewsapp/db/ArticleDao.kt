package com.baharudin.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.baharudin.mvvmnewsapp.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    @Query("SELECT * FROM article")
    suspend fun getArticle() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}