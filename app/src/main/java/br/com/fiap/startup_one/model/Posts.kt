package br.com.fiap.startup_one.model


data class Posts(
    val id: Long = 0,
    val studioName: String,
    val studio: String,
    val categories: List<String>,
    val description: String,
    val likes: Int,
    val icon: Int,
    val imageCard0: Int,
    val imageCard1: Int,
    val imageCard2: Int,
    val imageCard3: Int,
    val imageCard4: Int,
    val imagesCarrossel: List<ImagesCarrossel>,
    val specialties: List<String>,
    val products: List<ProductInfo>
)

data class ProductInfo(
    val titleProduct: String,
    val valueProduct: Double,
    val descriptionProduct: String,
    val timeProduct: String
)

data class ImagesCarrossel(
    val image: Int,
)