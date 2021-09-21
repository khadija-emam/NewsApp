package com.newsapp.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsResponse (

	@SerializedName("status") val status : String?,
	@SerializedName("totalResults") val totalResults : Int?,
	@SerializedName("articles") val articles : List<Article>
)
@Parcelize
data class Article (

	@SerializedName("source") val source : Source?,
	@SerializedName("author") val author : String?,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String?,
	@SerializedName("url") val url : String?,
	@SerializedName("urlToImage") val urlToImage : String?,
	@SerializedName("publishedAt") val publishedAt : String?,
	@SerializedName("content") val content : String?
):Parcelable

@Parcelize
data class Source (

	@SerializedName("id") val id : String?,
	@SerializedName("name") val name : String?
):Parcelable