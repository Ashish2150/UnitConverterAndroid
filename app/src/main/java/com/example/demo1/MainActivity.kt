@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.demo1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo1.ui.theme.Demo1Theme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("meter")
    }
    var outputUnit by remember {
        mutableStateOf("meter")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    var iConversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    fun conversionUnit() {
        val inputDoubleValue = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputDoubleValue * iConversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

   Column(
       modifier = Modifier.fillMaxSize(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(text = "Unit Convertor")
       Spacer(modifier = Modifier.height(16.dp))
       OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
           conversionUnit()
       }, label = { Text("Enter Value")}
           )
       Spacer(modifier = Modifier.height(16.dp))
       val context = LocalContext.current
       Row {

           Box {
              Button(onClick = { iExpanded = true }) {
                  Text(text = inputUnit)
                  Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
              }
               DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           inputUnit = "Centimeters"
                           iExpanded = false
                           iConversionFactor.value = 0.01
                           conversionUnit()
                       }
                   )
                   DropdownMenuItem(text = {Text("meter")}, onClick = {
                       inputUnit = "meter"
                       iExpanded = false
                       iConversionFactor.value = 1.0
                       conversionUnit()
                   })
                   DropdownMenuItem(text = {Text("feet")}, onClick = {
                       inputUnit = "feet"
                       iExpanded = false
                       iConversionFactor.value = 0.3048
                       conversionUnit()
                   })
                   DropdownMenuItem(text = {Text("millimeter")}, onClick = {
                       inputUnit = "millimeter"
                       iExpanded = false
                       iConversionFactor.value = 0.001
                       conversionUnit()
                   })
               }

           }
           Spacer(modifier = Modifier.width(16.dp))
           Box{
               Button(onClick = { oExpanded = true }) {
                   Text(text = outputUnit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

               }
               DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           outputUnit = "Centimeters"
                           oExpanded = false
                           oConversionFactor.value = 0.01
                           conversionUnit()

                       }
                   )
                   DropdownMenuItem(text = {Text("meter")}, onClick = {
                       outputUnit = "meter"
                       oExpanded = false
                       oConversionFactor.value = 1.00
                       conversionUnit()
                   })
                   DropdownMenuItem(text = {Text("feet")}, onClick = {
                       outputUnit = "feet"
                       oExpanded = false
                       oConversionFactor.value = 0.3048
                       conversionUnit()
                   })
                   DropdownMenuItem(text = {Text("millimeter")}, onClick = {
                       outputUnit = "millimeter"
                       oExpanded = false
                       oConversionFactor.value = 0.001
                       conversionUnit()
                   })
               }
           }
       }
       Spacer(modifier = Modifier.height(16.dp))
       Text(text = "Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
   }
}

@Composable
fun CaptionGame(){
    val treasureFound = remember { mutableStateOf(0) }
    val direction = remember {
        mutableStateOf("North")
    }
    Column {
        Text(text = "Treaser found :${treasureFound.value}")
        Text(text = "Direction found : ${direction.value}")
        Button(onClick = {
            direction.value = "East"
            if(Random.nextBoolean()) {
                treasureFound.value += 1
            }
        }) {
            Text(text = "Sail East")
        }
        Button(onClick = {
            direction.value = "West"
            if(Random.nextBoolean()) {
                treasureFound.value += 1
            }
        }) {
            Text(text = "Sail West")
        }
        Button(onClick = {
            direction.value = "North"
            if(Random.nextBoolean()) {
                treasureFound.value += 1
            }
        }) {
            Text(text = "Sail North")
        }
        Button(onClick = {
            direction.value = "South"
            if(Random.nextBoolean()) {
                treasureFound.value += 1
            }
        }) {
            Text(text = "Sail South")
        }
    }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Demo1Theme {
        Greeting("Android")
    }
}