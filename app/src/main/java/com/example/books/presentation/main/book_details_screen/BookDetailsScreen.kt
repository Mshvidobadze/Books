package com.example.books.presentation.main.book_details_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.books.data.remote.responses.BookResponse
import com.example.books.util.BooksEvent
import com.example.books.util.Response

@Composable
fun BookDetailsScreen(
    bookId: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    bookImageSize: Dp = 200.dp,
    viewModel: BookDetailsViewModel = hiltViewModel()
) {
    val bookDetails = produceState<Response<BookResponse>>(initialValue = Response.Loading()){
        value = viewModel.getBookDetails(bookId)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 15.dp)
    ){
        BookDetailsToolbar(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(TopCenter)
        )

        SaveButton(
            bookDetails = bookDetails,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(TopCenter)
        )

        BookDetailsStateWrapper(
            bookDetails = bookDetails,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + bookImageSize / 2f,
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 50.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(15.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + bookImageSize / 2f,
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 15.dp
                )
        )

        Box(
            contentAlignment = TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ){
            if(bookDetails is Response.Success){
                bookDetails.data?.let { viewModel.getBookById(it.isbn13) }
                bookDetails.data?.image?.let {
                    Image(
                        painter = rememberImagePainter(data = bookDetails.data.image),
                        contentDescription = bookDetails.data.title,
                        modifier = Modifier
                            .size(bookImageSize)
                            .offset(y = topPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BookDetailsToolbar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            )
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(15.dp, 15.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun BookDetailsStateWrapper(
    bookDetails: Response<BookResponse>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(bookDetails){
        is Response.Success -> {
            BookDetailsSection(
                bookDetails.data!!,
                modifier = modifier
                    .offset(y = (-20).dp)
            )
        }
        is Response.Error -> {
            Text(
                text = bookDetails.message!!,
                color = Color.Black,
                modifier = modifier
            )
        }
        is Response.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}

@Composable
fun BookDetailsSection(
    bookDetails: BookResponse,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
    ) {
        Text(
            text = bookDetails.title,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        BookDetailsColumn(bookDetails)
    }
}

@Composable
fun BookDetailsColumn(
    bookDetails: BookResponse
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BookDetailsRow(key = "Authors:", value = bookDetails.authors)

        BookDetailsRow(key = "Publisher:", value = bookDetails.publisher)

        BookDetailsRow(key = "Rating:", value = bookDetails.rating)

        BookDetailsRow(key = "Year:", value = bookDetails.year)

        BookDetailsRow(key = "Description:", value = bookDetails.desc)
    }
}

@Composable
fun BookDetailsRow(
    key: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = key,
            color = Color.Black
        )
        Spacer(
            modifier = Modifier
                .width(15.dp))
        Text(
            text = value,
            color = Color.Black
        )
    }
}

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    bookDetails: Response<BookResponse>,
    viewModel: BookDetailsViewModel = hiltViewModel()

) {
    var text by remember { mutableStateOf("Save") }
    val isSaved by remember { viewModel.isSaved }

    if(isSaved){
        text = "Unsave"
    }

    Box(
        contentAlignment = TopEnd,
        modifier = modifier
            .offset(x = (-10).dp, y = 10.dp)
    ){
        Button(
            onClick = {
                if(bookDetails is Response.Success){
                    text = if(text == "Save"){
                        viewModel.onEvent(BooksEvent.Add(bookDetails.data!!))
                        "Unsave"
                    } else {
                        viewModel.onEvent(BooksEvent.Delete(bookDetails.data!!))
                        "Save"
                    }
                }
            },
            modifier = Modifier
                .width(100.dp)
        ) {
            Text(text = text)
        }
    }
}