package api

data class NewsJason(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int

)