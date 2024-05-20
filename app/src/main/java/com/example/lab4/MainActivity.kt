package com.example.lab4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab4.ui.theme.Lab4Theme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bai3()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(){
    val context= LocalContext.current
    var  username by remember {
        mutableStateOf("")
    }
    var  password by remember {
        mutableStateOf("")
    }
    Column(
        modifier= Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription ="Logo" )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = username, onValueChange = {username=it}, label = { Text(text = "Username")})
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (username.length==0 || password.length==0 ){
                Toast.makeText(context, "Vui lòng nhập đủ thông tin ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun HorizontalImageList(imageList:List<Int>){
    val scrollState= rememberScrollState()
    Row(modifier= Modifier
        .horizontalScroll(scrollState)
        .padding(16.dp)
    ) {
        imageList.forEachIndexed { index, image ->
            Image(painter = painterResource(id = image),
                contentDescription = "Image Description",
                contentScale = ContentScale.FillWidth,
                modifier= Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 8.dp
                    )
            )
        }
    }
}

@Composable
fun VerticalImageList(imageList: List<Int>){
    val scrollState= rememberScrollState()
    Column(modifier= Modifier
        .padding(16.dp)
        .verticalScroll(scrollState)) {
        imageList.forEachIndexed { index, image ->
            Image(painter = painterResource(id = image) ,
                contentDescription = "Image Description",
                contentScale = ContentScale.FillWidth,
                modifier= Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(
                        top = if (index == 0) 0.dp else 8.dp,
                        bottom = 8.dp
                    )
                    .width(500.dp),


            )

        }
    }
}

@Composable
fun Bai2(){
    val images= listOf(R.drawable.anh1, R.drawable.anh1)
    Column {
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Icon")
        HorizontalImageList(imageList =images )
        VerticalImageList(imageList = images)
    }
}

@Composable
fun NoteApp(paddingValues:PaddingValues){
    val notes= listOf("Note 1", "Note 2", "Note 3", "Note 4", "Note5")
    Column(
        modifier= Modifier
            .padding(paddingValues)
            .padding(8.dp)
    ) {
        notes.forEach { note->
            NoteCard(noteText = note)
        }
    }
}

@Composable
fun NoteCard(noteText:String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(
            color = Color.LightGray,
            shape = MaterialTheme.shapes.medium
        )){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = noteText,
                modifier= Modifier
                    .weight(1f)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Expand Note",
                    modifier= Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                )
        }
    }
}

@Composable
fun Bai3(){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {innerPadding->
        NoteApp(paddingValues = innerPadding)
    }
}