package com.example.ricegrow

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

@Suppress("DEPRECATION")
class IdentifyDisease {

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
            var classificationResult by remember { mutableStateOf<List<Pair<String, Float>>>(emptyList()) }
            var isClassifying by remember { mutableStateOf(false) }
            var currentBitmap: Bitmap? by remember { mutableStateOf(null) }

            LaunchedEffect(imageUri) {
                isClassifying = true
                val bitmap = getBitmapFromUri(context, decodedUri)
                if (bitmap != null) {
                    currentBitmap = bitmap
                    try {
                        classificationResult = classifyImage(context, bitmap)
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
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                        .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (currentBitmap != null) {
                        // Use currentBitmap directly as it is now resized
                        Image(
                            bitmap = currentBitmap!!.asImageBitmap(), // Assuming you're using Jetpack Compose
                            contentDescription = "Captured Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                if (classificationResult.isNotEmpty()) {
                    val topResult = classificationResult.maxByOrNull { it.second }
                    if (topResult != null) {
                        val (className, confidence) = topResult
                        val message = when (className) {
                            "Unknown" -> "Unknown image. Not a rice disease."
                            "Bacterialblight" -> "Bacterial Blight"
                            "Blast" -> "Rice Blast"
                            "Brownspot" -> "Brown Spot"
                            "Tungro" -> "Tungro"
                            else -> "Unrecognized classification."
                        }
                        Text(
                            text = message,
                            fontSize = 20.sp,
                            color = if (className == "Unknown") Color(0xFFBB0707) else Color(0xFF2b2b2b),
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        classificationResult.forEach { (className, percentage) ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable {
                                        when (className) {
                                            "Bacterialblight" -> navController.navigate(Routes.riceBacterialblight)
                                            "Blast" -> navController.navigate(Routes.riceblast)
                                            "Brownspot" -> navController.navigate(Routes.brownspot)
                                            "Tungro" -> navController.navigate(Routes.tungro)
                                            else -> {
                                                Toast.makeText(context, "Page not found for $className", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    },
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(text = "$className", color = Color(0xFF2b2b2b), fontSize = 24.sp)
                                    Text(
                                        text = "Confidence: ${"%.2f".format(percentage * 100)}%",
                                        color = Color(0xFF575555),
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                try {
                                    val classificationText = classificationResult.joinToString("\n") { (className, confidence) ->
                                        "Class: $className, Confidence: ${"%.2f".format(confidence * 100)}%"
                                    }
                                    val resizedBitmap = getBitmapFromUri(context, decodedUri)
                                    if (resizedBitmap != null) {
                                        saveImageToDownloads(context, resizedBitmap) // Save the resized image
                                        saveTextToDownloads(context, classificationText)
                                    } else {
                                        Toast.makeText(context, "Resized Bitmap is null, cannot save!", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: Exception) {
                                    Log.e("SaveResultError", "Error saving results: ${e.message}", e)
                                    Toast.makeText(context, "Failed to save results. Please try again.", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 30.dp)
                                .shadow(
                                    elevation = 10.dp,
                                    spotColor = Color.DarkGray,
                                    shape = RoundedCornerShape(30.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBDA98)),
                        ) {
                            Text(text = "Save Results", color = Color(0xFF2b2b2b), fontSize = 20.sp)
                        }
                    }
                } else {
                    Text(
                        text = "No classification results available.",
                        modifier = Modifier.padding(8.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }

    private fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            // Resize the bitmap to 150x150
            originalBitmap?.let { resizeBitmap(it, 1080, 1080 ) }?.also {
                Log.d("BitmapInfo", "Loaded and resized bitmap size: ${it.width}x${it.height}")
            }
        } catch (e: Exception) {
            Log.e("BitmapError", "Failed to decode bitmap from URI: ${e.message}")
            null
        }
    }

    private lateinit var interpreter: Interpreter

    @Suppress("DEPRECATION")
    private fun initInterpreter(context: Context) {
        if (!::interpreter.isInitialized) {
            interpreter = Interpreter(loadModelFile(context))
        }
    }

    private fun classifyImage(context: Context, bitmap: Bitmap): List<Pair<String, Float>> {
        initInterpreter(context)

        val inputShape = interpreter.getInputTensor(0).shape()
        val outputShape = interpreter.getOutputTensor(0).shape()

        val inputWidth = inputShape[1]
        val inputHeight = inputShape[2]

        val byteBuffer = convertBitmapToByteBuffer(bitmap, inputWidth, inputHeight)

        val outputArray = Array(1) { FloatArray(outputShape[1]) }
        interpreter.run(byteBuffer, outputArray)

        val confidences = outputArray[0]
        return classNames.zip(confidences.toList())
            .sortedByDescending { it.second }
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("rice_diseaseII.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap, width: Int, height: Int): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(1 * width * height * 3 * 4).order(ByteOrder.nativeOrder())
        val resizedBitmap = resizeBitmap(bitmap, width, height)

        val intValues = IntArray(width * height)
        resizedBitmap.getPixels(intValues, 0, resizedBitmap.width, 0, 0, resizedBitmap.width, resizedBitmap.height)

        for (pixelValue in intValues) {
            byteBuffer.putFloat((pixelValue shr 16 and 0xFF) / 255f) // Red
            byteBuffer.putFloat((pixelValue shr 8 and 0xFF) / 255f)  // Green
            byteBuffer.putFloat((pixelValue and 0xFF) / 255f)        // Blue
        }
        return byteBuffer
    }

    private fun saveImageToDownloads(context: Context, image: Bitmap) {
        try {
            val contentResolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "classification_result.png")
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/YourAppFolder") // Save to Pictures/YourAppFolder
            }

            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri == null) {
                throw Exception("Failed to insert image into MediaStore")
            }
            uri?.let {
                contentResolver.openOutputStream(uri).use { outputStream ->
                    if (outputStream != null) {
                        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        Toast.makeText(context, "Image saved to Pictures/YourAppFolder", Toast.LENGTH_SHORT).show()
                    } else {
                        throw Exception("OutputStream is null")
                    }
                }
            } ?: throw Exception("Failed to insert image into MediaStore")
        } catch (e: Exception) {
            Log.e("SaveImageError", "Error saving image: ${e.message}", e)
            Toast.makeText(context, "Error saving image: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTextToDownloads(context: Context, classificationText: String) {
        try {
            val contentResolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Files.FileColumns.DISPLAY_NAME, "classification_result.txt")
                put(MediaStore.Files.FileColumns.MIME_TYPE, "text/plain")
                put(MediaStore.Files.FileColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
            uri?.let {
                contentResolver.openOutputStream(uri).use { writer ->
                    writer?.write(classificationText.toByteArray()) ?: throw Exception("OutputStream is null")
                }
                Toast.makeText(context, "Text file saved to Downloads", Toast.LENGTH_SHORT).show()
            } ?: throw Exception("Failed to insert text file into MediaStore")
        } catch (e: Exception) {
            Log.e("SaveTextFileError", "Error saving image: ${e.message}", e)
            Toast.makeText(context, "Error saving text file: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private val classNames = listOf(
        "Bacterialblight", "Blast", "Brownspot", "Tungro","Unknown"
    )
}
