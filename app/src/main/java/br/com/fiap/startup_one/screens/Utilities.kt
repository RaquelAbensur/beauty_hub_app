package br.com.fiap.startup_one.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.startup_one.R
import br.com.fiap.startup_one.database.repository.getAllPosts
import br.com.fiap.startup_one.database.repository.getPostsByCategory
import br.com.fiap.startup_one.database.repository.getPostsByStudio
import br.com.fiap.startup_one.model.Posts
import br.com.fiap.startup_one.model.Schedule


///////////////////////////// Header /////////////////////////////

// Header Background
@Composable
fun HeaderBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF56C5B4), Color(0xFF64D795))
                ),
                shape = RoundedCornerShape(bottomEnd = 18.dp)
            ),
        contentAlignment = Alignment.Center
    ){content()}
}

// Icone Menu
@Composable
fun IconMenu(){
    Image(
        painter = painterResource(id = R.drawable.icon_menu),
        contentDescription = "icone menu",
        modifier = Modifier
            .size(36.dp)
            .padding(end = 14.dp)
            .clickable { /* Lógica do clique para abrir o menu */ }
    )
}

// Icone Plus
@Composable
fun IconPlus() {
    Image(
        painter = painterResource(id = R.drawable.icon_plus),
        contentDescription = "icone plus",
        modifier = Modifier
            .size(32.dp)
            .clickable { /* Lógica do clique para adicionar */ }
    )
}

//Click Search -> Profile
@Composable
fun SearchButton(navController: NavController) {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(40.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { navController.navigate("search") },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.search),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    color = Color(0xFF5CCDA6),
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            val iconColor = Color(0xFF5CCDA6)
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp)
            )
        }

    }
}

// Search
@Composable
fun Search() {
    var searchTextState by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var postsListState by remember {
        mutableStateOf(getAllPosts())
    }
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(40.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { keyboardController?.show() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp)
        ) {
            BasicTextField(
                value = searchTextState,
                onValueChange = { searchTextState = it },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    letterSpacing = 1.sp,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            val iconColor = Color(0xFF5CCDA6)
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp)
                    .clickable {
                        postsListState = getPostsByStudio(searchTextState)
                    }
            )
        }

        if (searchTextState.isEmpty()) {
            Text(
                text = stringResource(id = R.string.search),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    color = Color(0xFF5CCDA6),
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 9.5.dp)
            )
        }
    }
}

//Header Studio Profile
@Composable
fun HeaderStudioProfile(post: Posts) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(118.dp)
    ){
        Row(modifier = Modifier.padding(top = 31.dp, start = 15.dp)) {
            Image(
                modifier = Modifier
                    .height(93.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                painter = painterResource(id = post.icon),
                contentDescription = "logo Service"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.padding(top = 15.dp)) {
                Text(
                    text = post.studioName,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                        color = Color(0xFF1E2022),
                        letterSpacing = 0.75.sp,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = post.studio,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        color = Color(0xFF77838F),
                        letterSpacing = 0.5.sp,
                    )
                )
            }
        }

    }
}


///////////////////////////// Posts /////////////////////////////

@Composable
fun PostItem(navController: NavController, posts: Posts) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .shadow(
                elevation = 14.dp,
                spotColor = Color(0x0F000000),
                ambientColor = Color(0x0F000000)
            )
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Cabeçalho
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 15.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(end = 12.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    painter = painterResource(id = posts.icon),
                    contentDescription = "logo Service"
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = posts.studioName,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = Color(0xFF1E2022),
                            letterSpacing = 1.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = posts.studio,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = Color(0xFF77838F),
                            letterSpacing = 1.sp,
                        )
                    )
                }

                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .padding(bottom = 16.dp),
                    painter = painterResource(id = R.drawable.ic_circle_menu),
                    contentDescription = "Icon menu"
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            // Images
            Row(
                modifier = Modifier
                    .padding(14.dp, 0.dp)
            ) {
                    Image(
                        modifier = Modifier
                            .size(117.dp, 173.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        painter = painterResource(id = posts.imageCard0),
                        contentDescription = "Imagem 0"
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(
                        modifier = Modifier.width(110.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(120.dp, 80.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            painter = painterResource(id = posts.imageCard1),
                            contentDescription = "Imagem 1"
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Image(
                            modifier = Modifier
                                .size(120.dp, 80.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            painter = painterResource(id = posts.imageCard2),
                            contentDescription = "Imagem 2"
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.width(110.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(120.dp, 80.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            painter = painterResource(id = posts.imageCard3),
                            contentDescription = "Imagem 3"
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Image(
                            modifier = Modifier
                                .size(120.dp, 80.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val postId = posts.id
                                    navController.navigate("profile/${postId}")
                                },
                            painter = painterResource(id = posts.imageCard4),
                            contentDescription = "Imagem 4"
                        )
                    }

            }
            // Text
            Row(
                modifier = Modifier
                    .height(85.dp)
                    .padding(20.dp, 20.dp)
            ) {
                Text(
                    text = posts.description,
                    style = TextStyle(
                        fontSize = 11.3.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        color = Color(0xFF77838F),
                        letterSpacing = 1.sp,
                    )
                )

            }

            // Feedbacks
            Row(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        7.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.Top,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "like"
                    )
                    Text(
                        text = posts.likes.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = Color(0xFF77838F),
                            letterSpacing = 1.sp,
                        )
                    )
                }

                Spacer(modifier = Modifier.width(27.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Spacer(modifier = Modifier.width(27.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )

                Spacer(modifier = Modifier.width(150.dp))

                Row(
                    modifier = Modifier
                        .width(80.dp)
                        .height(27.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.icon_user_00),
                        contentDescription = "User 0"
                    )
                    Image(
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.icon_user_01),
                        contentDescription = "User 1"
                    )
                    Image(
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.icon_user_02),
                        contentDescription = "User 2"
                    )
                    Image(
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.icon_user_03),
                        contentDescription = "User 3"
                    )
                }

            }
        }
    }
}

@Composable
fun PostsCard(navController: NavController, posts: List<Posts>) {
    LazyColumn {
        items(posts) { post ->
            PostItem(navController, post)
        }
    }
}

@Composable
fun PostProductItem(navController: NavController, post: Posts) {
    LazyRow(modifier = Modifier.padding(10.dp)) {
        items(post.products) { productInfo ->
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 14.dp,
                        clip = false
                    )
                    .width(355.dp)
                    .height(231.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp, end = 15.dp, top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape),
                            painter = painterResource(id = post.icon),
                            contentDescription = "image description",
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = post.studioName,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    color = Color(0xFF1E2022),
                                    letterSpacing = 1.sp,
                                )
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = productInfo.timeProduct,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    color = Color(0xFF77838F),
                                    letterSpacing = 1.sp,
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // Text
                    Text(
                        text = productInfo.descriptionProduct,
                        style = TextStyle(
                            fontSize = 11.3.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = Color(0xFF77838F),
                            letterSpacing = 1.sp,
                        ),
                        modifier = Modifier.padding(20.dp)
                    )

                    // Feedbacks
                    Row(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp,),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "like"
                            )
                            Text(
                                text = post.likes.toString(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    color = Color(0xFF77838F),
                                    letterSpacing = 1.sp,
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Image(
                            modifier = Modifier
                                .size(15.dp),
                            painter = painterResource(id = R.drawable.ic_comment_product),
                            contentDescription = "image description"
                        )

                        Spacer(modifier = Modifier.width(150.dp))

                            Box(
                                modifier = Modifier
                                    .width(89.dp)
                                    .height(39.dp)
                                    .background(
                                        color = Color(0xFF5CCDA6),
                                        shape = RoundedCornerShape(size = 7.dp)
                                    )
                                    .padding(
                                        start = 16.dp,
                                        top = 12.dp,
                                        end = 16.dp,
                                        bottom = 12.dp
                                    )
                            ) {
                                Text(
                                    text = "Agendar",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        color = Color(0xFFFFFFFF),
                                        letterSpacing = 1.sp,
                                    ),
                                    modifier = Modifier.clickable { navController.navigate("schedule") }
                                )
                            }

                    }
                }
            }
        }
    }
}


///////////////////////////// Carousel /////////////////////////////

@Composable
fun Carrossel() {
    val imagesRectangles = listOf(
        "rectangle",
        "rectangle1",
        "rectangle3",
        "rectangle2",
        "rectangle4",

        )

    val itemWidth = 100.dp
    val itemHeight = 160.dp

    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(imagesRectangles) { imageRectangle ->
            CarrosselItem(imageRectangle, itemWidth, itemHeight)
        }
    }
}

@Composable
fun CarrosselItem(imageRectangle: String, itemWidth: Dp, itemHeight: Dp) {
    val context = LocalContext.current

    val resourceId = context.resources.getIdentifier(
        imageRectangle, "drawable", context.packageName
    )

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            val basePainter: Painter = painterResource(id = R.drawable.ic_circle_perfil)
            Image(
                painter = basePainter,
                contentDescription = "base description",
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = "Hilary\nOuse",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.inter_thin)),
                fontWeight = FontWeight(100),
                color = Color.White,
                letterSpacing = 1.sp
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun TextCarrossel() {
    Text(
        text = stringResource(id = R.string.titleCarousel),
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.inter_thin)),
            color = Color(0xFF1E2022),
            letterSpacing = 1.sp,
        ),
        modifier = Modifier
            .padding(start = 16.dp)
    )
}

@Composable
fun CarrosselCategory(postsList: List<Posts>, onCategorySelected: (String) -> Unit, navController: NavController) {
    var selectedCategory by remember { mutableStateOf("") }
    val categories = postsList.flatMap { it.categories }.distinct()

    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->
            CarrosselCategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onItemClick = {
                    selectedCategory = category
                    onCategorySelected(category)
                }
            )
        }
    }

    val itemsInSelectedCategory = getPostsByCategory(postsList, selectedCategory)
    PostsCard(navController = navController, posts = itemsInSelectedCategory)
}

@Composable
fun CarrosselCategoryItem(category: String, isSelected: Boolean, onItemClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFF5CCDA6) else Color.White
    val textColor = if (isSelected) Color.White else Color(0xFF5CCDA6)

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFF5CCDA6),
                shape = RoundedCornerShape(size = 17.dp)
            )
            .fillMaxWidth()
            .height(40.dp)
            .background(backgroundColor, shape = RoundedCornerShape(size = 17.dp))
            .clickable(
                onClick = onItemClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
    ) {
        Text(
            text = category,
            color = textColor,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
fun CarrosselSpecialtiesItem(post: Posts) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(post.imagesCarrossel) {index, imageItem ->
                Column(
                    modifier = Modifier
                        .width(80.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterHorizontally)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        painter = painterResource(id = imageItem.image),
                        contentDescription = "image description",
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = post.specialties.getOrNull(index) ?: "",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = Color(0xFF1E2022),
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.sp,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


///////////////////////////// Schedule /////////////////////////////

@Composable
fun Schedule(scheduleList: List<Schedule>, isSelected: Boolean) {
    val selectedColor = Color(0.37f, 0.8f, 0.69f, 0.24f)
    val backgroundColor = Color.White

    var selectedDayIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.background(backgroundColor)) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Seus \nagendamentos",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 26.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    color = Color(0xFF1E293B),
                    letterSpacing = 0.3.sp,
                )
            )
        }
        LazyRow(modifier = Modifier.padding(16.dp)) {
            itemsIndexed(scheduleList) { index, schedule ->
                val isSelectedState = rememberUpdatedState(isSelected)
                val isDaySelected = index == selectedDayIndex
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(50.dp)
                        .background(
                            if (isDaySelected) selectedColor else backgroundColor,
                            shape = RoundedCornerShape(size = 17.dp)
                        )
                        .clickable {
                            selectedDayIndex = index
                        },
                ) {
                    Text(
                        text = schedule.day.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 26.sp,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            color = if (isDaySelected) Color(0xFF504DE5) else if (isSelectedState.value) Color(0xFF1E293B) else Color(0xFF1E293B),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.3.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = schedule.dayOfWeek,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 26.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            color = if (isDaySelected) Color(0xFF504DE5) else Color(0xFF94A3B8),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.3.sp,
                        )
                    )
                }
            }
        }

        val selectedSchedule = scheduleList.getOrNull(selectedDayIndex)


        if (selectedDayIndex == 3 && selectedSchedule != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Qui, dia 4 de Setembro",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 26.sp,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            color = Color(0xFF1E293B),
                            letterSpacing = 0.3.sp,
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Horários
                        Column(
                            verticalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier
                                .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                        ) {
                            Text(
                                text = "08.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "10.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "12.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "14.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "16.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                        }

                        Spacer(modifier = Modifier
                            .width(50.dp)
                            .height(10.dp))

                        Column {
                            // Card com texto e imagens
                            Column(
                                modifier = Modifier
                                    .width(242.dp)
                                    .height(64.dp)
                                    .background(
                                        color = Color(0xFF5CCDA6),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Text(
                                    text = "Corte em Nala",
                                    modifier = Modifier
                                        .padding(start = 16.dp, top = 24.dp),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                                        color = Color(0xFFFFFFFF),
                                        letterSpacing = 0.3.sp,
                                    )
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 8.dp, bottom = 8.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .aspectRatio(1f)
                                            .clip(CircleShape),
                                        painter = painterResource(id = R.drawable.icon_salon_01),
                                        contentDescription = "User 0"
                                    )
                                    Image(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .aspectRatio(1f)
                                            .clip(CircleShape),
                                        painter = painterResource(id = R.drawable.icon_salon_02),
                                        contentDescription = "User 1"
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                            // Card com texto e imagens
                            Column(
                                modifier = Modifier
                                    .width(242.dp)
                                    .height(112.dp)
                                    .background(
                                        color = Color(0xFF5CCDA6),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Text(
                                    text = "Coloração e \nhidratação em Cruffin \n Glamourize",
                                    modifier = Modifier
                                        .padding(start = 16.dp, top = 24.dp),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                                        color = Color(0xFFFFFFFF),
                                        letterSpacing = 0.3.sp,
                                    )
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 8.dp, bottom = 8.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Image(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .aspectRatio(1f)
                                            .clip(CircleShape),
                                        painter = painterResource(id = R.drawable.icon_salon_01),
                                        contentDescription = "User 0"
                                    )
                                    Image(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .aspectRatio(1f)
                                            .clip(CircleShape),
                                        painter = painterResource(id = R.drawable.icon_salon_02),
                                        contentDescription = "User 1"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (selectedSchedule != null) {
            val selectedDateText = "${selectedSchedule.dayOfWeek}, dia ${selectedSchedule.day} de Setembro"

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = selectedDateText,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 26.sp,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            color = Color(0xFF1E293B),
                            letterSpacing = 0.3.sp,
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Horários
                        Column(
                            verticalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier
                                .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                        ) {
                            Text(
                                text = "08.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "10.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "12.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "14.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                            Text(
                                text = "16.00",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 26.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_thin)),
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 0.3.sp,
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}



///////////////////////////// Footer /////////////////////////////

@Composable
fun FooterBackground(content: @Composable () -> Unit) {
    Box(

        modifier = Modifier
            .shadow(
                elevation = 15.dp,
                spotColor = Color.Gray,
                ambientColor = Color.Gray
            )
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

@Composable
fun IconMenuON() {
    Image(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id = R.drawable.ic_menu_on),
        contentDescription = "Icon Menu")
}

@Composable
fun IconSearchOn() {
    val iconColor = Color(0xFF5CCDA6)

    Image(
        modifier = Modifier
            .size(24.dp),
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Icon Search On",
        colorFilter = ColorFilter.tint(iconColor)
    )
}

@Composable
fun IconCalendarOn() {
    val iconColor = Color(0xFF5CCDA6)

    Image(
        modifier = Modifier
            .size(24.dp),
        painter = painterResource(id = R.drawable.ic_calendar),
        contentDescription = "Icon Calendar On",
        colorFilter = ColorFilter.tint(iconColor)
    )
}

@Composable
fun IconProfileOn() {
    val iconColor = Color(0xFF5CCDA6)

    Image(
        modifier = Modifier
            .size(24.dp),
        painter = painterResource(id = R.drawable.ic_profile),
        contentDescription = "Icon Profile On",
        colorFilter = ColorFilter.tint(iconColor)
    )
}

@Composable
fun IconMenuOff(navController: NavController) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                onClick = {
                    navController.navigate("home")
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        painter = painterResource(id = R.drawable.ic_menu_off),
        contentDescription = "Icon Menu")
}

@Composable
fun IconSearchOff(navController: NavController) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                onClick = {
                    navController.navigate("search")
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Icon Menu",

    )
}

@Composable
fun IconCalendarOff(navController: NavController) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                onClick = {
                    navController.navigate("schedule")
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        painter = painterResource(id = R.drawable.ic_calendar),
        contentDescription = "Icon Menu")
}

@Composable
fun IconProfileOff(navController: NavController) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                onClick = {
                    navController.navigate("profileUser")
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        painter = painterResource(id = R.drawable.ic_profile),
        contentDescription = "Icon Menu")
}

