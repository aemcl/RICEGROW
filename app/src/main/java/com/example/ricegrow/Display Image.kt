package com.example.ricegrow

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

@Composable
fun ImageDisplayScreen(imageUri: String?, navController: NavController) {
    if (imageUri.isNullOrEmpty()) {
        Text(
            text = "No image to display",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    } else {
        val context = LocalContext.current
        val decodedUri = Uri.parse(imageUri)
        var classificationResult by remember { mutableStateOf("") }
        var isClassifying by remember { mutableStateOf(false) }

        LaunchedEffect(imageUri) {
            isClassifying = true
            val bitmap = getBitmapFromUri(context, decodedUri)
            if (bitmap != null) {
                try {
                    val result = classifyImage(context, bitmap)
                    classificationResult = result
                } catch (e: Exception) {
                    Log.e("ModelError", "Classification failed: ${e.message}")
                    Toast.makeText(context, "Error classifying image", Toast.LENGTH_SHORT).show()
                } finally {
                    isClassifying = false
                }
            } else {
                Toast.makeText(context, "Failed to process image", Toast.LENGTH_SHORT).show()
                isClassifying = false
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                pageTitle = "Identify Rice Disease",
                iconRoute = "",
                navController = navController
            )
            Card(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(
                        2.dp,
                        Color.Gray,
                        RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(decodedUri),
                    contentDescription = "Captured Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            if (isClassifying) {
                Text(
                    text = "Classifying...",
                    modifier = Modifier.padding(8.dp),
                    color = Color.Gray
                )
            } else if (classificationResult == "Unknown") {
                Toast.makeText(context, "Image could not be classified. Please try again.", Toast.LENGTH_SHORT).show()
            } else if (classificationResult.isNotEmpty()) {
                Text(
                    text = "Classification: $classificationResult",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            when (classificationResult.split(" ")[0]) {
                                "Bacterialblight" -> navController.navigate(Routes.riceBacterialblight)
                                "Blast" -> navController.navigate(Routes.riceblast)
                                "Brownspot" -> navController.navigate(Routes.brownspot)
                                "Tungro" -> navController.navigate(Routes.tungro)
                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Unknown classification result",
                                         Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                )
            }
        }
    }
}



fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream).also {
            Log.d("BitmapInfo", "Loaded bitmap size: ${it?.width}x${it?.height}")
            inputStream?.close()
        }
    } catch (e: Exception) {
        Log.e("BitmapError", "Failed to decode bitmap from URI: ${e.message}")
        null
    }
}

private lateinit var interpreter: Interpreter

@Suppress("DEPRECATION")
fun initInterpreter(context: Context) {
    if (!::interpreter.isInitialized) {
        interpreter = Interpreter(loadModelFile(context))
    }
}

fun classifyImage(context: Context, bitmap: Bitmap): String {
    initInterpreter(context)

    val inputShape = interpreter.getInputTensor(0).shape()
    val outputShape = interpreter.getOutputTensor(0).shape()

    val inputWidth = inputShape[1]
    val inputHeight = inputShape[2]

    val byteBuffer = convertBitmapToByteBuffer(bitmap, inputWidth, inputHeight)

    // Log input buffer details
    Log.d("InputBuffer", "Input shape: ${inputShape.contentToString()}, ByteBuffer: ${byteBuffer.array().contentToString()}")

    val outputArray = Array(1) { FloatArray(outputShape[1]) }

    interpreter.run(byteBuffer, outputArray)

    // Log raw output
    Log.d("ModelOutput", "Raw output: ${outputArray[0].contentToString()}")

    val predictedIndex = outputArray[0].indexOfMaxOrNull()
    return if (predictedIndex in classNames.indices) {
        "${classNames[predictedIndex]} (${(outputArray[0][predictedIndex] * 100).toInt()}%)"
    } else {
        "Unknown"
    }
}

fun loadModelFile(context: Context): MappedByteBuffer {
    val fileDescriptor = context.assets.openFd("rice_disease.tflite")
    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
    val fileChannel = inputStream.channel
    val startOffset = fileDescriptor.startOffset
    val declaredLength = fileDescriptor.declaredLength
    return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
}

fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
    return Bitmap.createScaledBitmap(bitmap, width, height, true)
}

fun convertBitmapToByteBuffer(bitmap: Bitmap, width: Int, height: Int): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(1 * width * height * 3 * 4).order(ByteOrder.nativeOrder())
    val resizedBitmap = resizeBitmap(bitmap, width, height)

    val intValues = IntArray(width * height)
    resizedBitmap.getPixels(intValues, 0, resizedBitmap.width, 0, 0, resizedBitmap.width, resizedBitmap.height)

    for (pixelValue in intValues) {
        // Normalize pixel values to [0, 1]
        byteBuffer.putFloat((pixelValue shr 16 and 0xFF) / 255f) // Red
        byteBuffer.putFloat((pixelValue shr 8 and 0xFF) / 255f)  // Green
        byteBuffer.putFloat((pixelValue and 0xFF) / 255f)        // Blue
    }

    return byteBuffer
}

fun FloatArray.indexOfMaxOrNull(): Int {
    return this.indices.maxByOrNull { this[it] } ?: -1
}

// List of class names
val classNames = listOf("Bacterialblight", "Blast","Brownspot","Tungro")