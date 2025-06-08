package com.example.counterapp

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import java.nio.file.WatchEvent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    background = Color(0xFFFF6B35),
                    onBackground = Color.White
                )

            ) {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    //Step 1: State to Store count

    val context=LocalContext.current
    val sharedPreferences=context.getSharedPreferences("TapZenPrefs",Context.MODE_PRIVATE)

    var count by remember { mutableStateOf(sharedPreferences.getInt("Saved_count",0)) }

    //Step 2: UI layout

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //for header "TapZen"

       Row(
           modifier=Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ){
           Text(
               text = "TapZen",
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               color=Color.White
           )
           Text(
               text = "\uD83D\uDD0A",
               fontSize = 24.sp
           )
       }

        Spacer(modifier = Modifier.height(60.dp))


        //Text to show the count number and Count word
        Text(
            text = "${count}.00",
            fontSize = 42.sp,
            color = Color.White
        )

        Text(
            text = "COUNT",
            fontSize = 14.sp,
            color = Color.White
        )


        Spacer(modifier = Modifier.height(40.dp))
        //space between text & button

        //Button to increase Count
        Box(
            modifier = Modifier
                .size(160.dp)
                .shadow(elevation = 16.dp, shape = CircleShape)
                .background(
                    color = Color(0xFF2D2D2D),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable { count++ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                fontSize = 48.sp,
                color = Color.White
            )
        }

       Spacer(modifier=Modifier.height(50.dp))

        Text(
            text = "TAP TO COUNT",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color=Color.White
        )

        Spacer(modifier=Modifier.height(60.dp))

        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //Save Button

            Button(
                onClick={
                    sharedPreferences.edit().putInt("Saved_count",count).apply()
                },
                modifier=Modifier
                    .width(100.dp)
                    .height(40.dp),
                shape= RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor=Color.Transparent
                ),
                border=androidx.compose.foundation.BorderStroke(1.dp,Color.White)
            ){
                Text(
                    text="SAVE",
                    color=Color.White,
                    fontSize = 14.sp,
                    fontWeight= FontWeight.Bold
                )
            }


            //RESET BUTTON
            Button(
                onClick = {count=0},
                modifier= Modifier.width(100.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(20.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                border=androidx.compose.foundation.BorderStroke(1.dp,Color.White)
            ){
                Text(
                    text="RESET",
                    color=Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }

        }

        Spacer(modifier= Modifier.height(80.dp))




    }

}



