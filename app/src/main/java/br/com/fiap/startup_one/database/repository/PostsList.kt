package br.com.fiap.startup_one.database.repository

import br.com.fiap.startup_one.R
import br.com.fiap.startup_one.model.ImagesCarrossel
import br.com.fiap.startup_one.model.Posts
import br.com.fiap.startup_one.model.ProductInfo

fun getAllPosts(): List<Posts> {
    return listOf(
        Posts(
            id = 1,
            studioName = "Nala",
            studio = "Cortes femininos",
            categories = listOf("Cabelo", "Maquiagem"),
            description = "Agende sua consulta hoje mesmo e descubra o brilho que há em você. \uD83D\uDC87\u200D♀️",
            likes = 27,
            icon = R.drawable.logo_post1,
            imageCard0 = R.drawable.post1_card_image_00,
            imageCard1 = R.drawable.post1_card_image_01,
            imageCard2 = R.drawable.post1_card_image_02,
            imageCard3 = R.drawable.post1_card_image_03,
            imageCard4 = R.drawable.post1_card_image_04,
            imagesCarrossel = listOf(
                ImagesCarrossel(R.drawable.post5_card_image_00),
                ImagesCarrossel(R.drawable.post5_card_image_01),
                ImagesCarrossel(R.drawable.post5_card_image_03),
            ),
            specialties = listOf("Corte", "Maquiagem","Hidratação","Penteados","Coloração"),
            products = listOf(
                ProductInfo("Corte", 80.00, "Descrição do Produto 1", "Tempo de Duração: 30 min"),
            )
        ),
        Posts(
            id = 2,
            studioName = "Cruffin Glamourize",
            studio = "Cortes femininos",
            categories = listOf("Cabelo", "Unhas"),
            description = "Transforme-se em uma estrela! Cortes modernos e tratamentos de spa de alta qualidade.",
            likes = 50,
            icon = R.drawable.logo_post2,
            imageCard0 = R.drawable.post2_card_image_00,
            imageCard1 = R.drawable.post2_card_image_01,
            imageCard2 = R.drawable.post3_card_image_02,
            imageCard3 = R.drawable.post3_card_image_03,
            imageCard4 = R.drawable.post3_card_image_04,
            imagesCarrossel = listOf(
                ImagesCarrossel(R.drawable.post5_card_image_00),
                ImagesCarrossel(R.drawable.post5_card_image_01),
                ImagesCarrossel(R.drawable.post5_card_image_03),
            ),
            specialties = listOf("Corte", "Unhas","Hidratação","Penteados","Coloração"),
            products = listOf(
                ProductInfo("Corte", 75.00, "Descrição do Produto 1", "Tempo de Duração: 30 min"),
            )
        ),
        Posts(
            id = 3,
            studioName = "Sibaristica Spa",
            studio = "Cortes femininos",
            categories = listOf("Bem estar", "Cabelo", "Unhas"),
            description = "Mime-se e revele a sua verdadeira beleza interior. \uD83D\uDC86\u200D♀️\uD83C\uDF3F",
            likes = 27,
            icon = R.drawable.logo_post3,
            imageCard0 = R.drawable.post3_card_image_00,
            imageCard1 = R.drawable.post3_card_image_01,
            imageCard2 = R.drawable.post2_card_image_02,
            imageCard3 = R.drawable.post2_card_image_03,
            imageCard4 = R.drawable.post2_card_image_04,
            imagesCarrossel = listOf(
                ImagesCarrossel(R.drawable.post5_card_image_00),
                ImagesCarrossel(R.drawable.post5_card_image_01),
                ImagesCarrossel(R.drawable.post5_card_image_03),
            ),
            specialties = listOf("Corte", "Unhas", "Maquiagem", "Hidratação", "Penteados", "Coloração"),
            products = listOf(
                ProductInfo("Corte", 85.00, "Descrição do Produto 1", "Tempo de Duração: 30 min"),
                )
        ),

        Posts(
            id = 4,
            studioName = "Old School Legends",
            studio = "Barbearia",
            categories = listOf("Barba", "Cabelo"),
            description = "Tradição e estilo se encontram aqui. Venha para uma experiência masculina autêntica.\uD83D\uDCAA",
            likes = 27,
            icon = R.drawable.logo_post4,
            imageCard0 = R.drawable.post4_card_image_00,
            imageCard1 = R.drawable.post4_card_image_01,
            imageCard2 = R.drawable.post4_card_image_02,
            imageCard3 = R.drawable.post4_card_image_03,
            imageCard4 = R.drawable.post4_card_image_04,
            imagesCarrossel = listOf(
                ImagesCarrossel(R.drawable.post5_card_image_00),
                ImagesCarrossel(R.drawable.post5_card_image_01),
                ImagesCarrossel(R.drawable.post5_card_image_03),
            ),
            specialties = listOf("Barba", "Corte", "Pele", "Tratamentos", "Coloração"),
            products = listOf(
                ProductInfo("Barba", 50.00, "Descrição do Produto 1", "Tempo de Duração: 30 min"),
                ProductInfo("Corte Masculino", 80.00, "Descrição do Produto 2", "Tempo de Duração: 30 min")
                )
        ),

        Posts(
            id = 5,
            studioName = "Barber",
            studio = "Barbearia",
            categories = listOf("Barba", "Cabelo"),
            description = "Mime-se e revele a sua verdadeira beleza interior. \uD83D\uDC86\u200D♀️\uD83C\uDF3F",
            likes = 27,
            icon = R.drawable.logo_post5,
            imageCard0 = R.drawable.post5_card_image_00,
            imageCard1 = R.drawable.post5_card_image_01,
            imageCard2 = R.drawable.post5_card_image_02,
            imageCard3 = R.drawable.post5_card_image_03,
            imageCard4 = R.drawable.post5_card_image_04,
            imagesCarrossel = listOf(
                ImagesCarrossel(R.drawable.post5_card_image_00),
                ImagesCarrossel(R.drawable.post5_card_image_01),
                ImagesCarrossel(R.drawable.post5_card_image_03),
            ),
            specialties = listOf("Barba", "Corte", "Pele"),
            products = listOf(
                ProductInfo("Barba", 45.00, "Descrição do Produto 1", "Tempo de Duração: 30 min"),
            )
        ),
    )
}

fun getPostsByCategory(postsList: List<Posts>, category: String): List<Posts> {
    return postsList.filter { post ->
        post.categories.any { it.equals(category, ignoreCase = true) }
    }
}


fun getPostsByStudio(studio: String): List<Posts> {
    return getAllPosts().filter {
        it.studio.startsWith(prefix = studio, ignoreCase = true)
    }
}