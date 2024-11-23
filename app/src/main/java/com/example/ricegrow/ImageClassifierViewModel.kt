//package com.example.ricegrow
//
//import android.app.Application
//import android.content.Context
//import android.graphics.Bitmap
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.room.jarjarred.org.antlr.v4.gui.Interpreter
//import org.tensorflow.lite.DataType
//import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
////import java.io.File
//import java.nio.ByteBuffer
//import java.nio.ByteOrder
//class ImageClassifierViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val _classificationResult = MutableLiveData<String>()
//    val classificationResult: LiveData<String> get() = _classificationResult
//
//    fun classifyImage(bitmap: Bitmap) {
//        val context = getApplication<Application>().applicationContext
//        val result = processImageWithTFLite(context, bitmap)  // TensorFlow Lite function
//        _classificationResult.postValue(result)
//    }
//
//    private fun processImageWithTFLite(context: Context, bitmap: Bitmap): String {
//        try {
//            val modelFile = context.assets.open("riceplantdetectionmodel.tflite")
//            val interpreter = Interpreter(modelFile.readBytes())
//
//            val labels = context.assets.open("labels.txt").bufferedReader().readLines()
//
//            // Preprocess the image
//            val byteBuffer = preprocessImage(bitmap)
//
//            // Prepare the output buffer
//            val outputShape = interpreter.getOutputTensor(0).shape()
//            val outputBuffer = TensorBuffer.createFixedSize(outputShape, DataType.FLOAT32)
//
//            // Run inference
//            interpreter.run(byteBuffer, outputBuffer.buffer)
//
//            // Post-process results
//            val predictions = outputBuffer.floatArray
//            val maxIndex = predictions.indices.maxByOrNull { predictions[it] } ?: -1
//
//            return if (maxIndex >= 0) labels[maxIndex] else "Unknown"
//        } catch (e: Exception) {
//            return "Error: ${e.message}"
//        }
//    }
//
//    private fun preprocessImage(bitmap: Bitmap): ByteBuffer {
//        val imageSize = 224 // Expected size for the model
//        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false)
//        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
//        byteBuffer.order(ByteOrder.nativeOrder())
//
//        val intValues = IntArray(imageSize * imageSize)
//        scaledBitmap.getPixels(intValues, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)
//
//        for (pixel in intValues) {
//            byteBuffer.putFloat(((pixel shr 16) and 0xFF) / 255.0f) // Red
//            byteBuffer.putFloat(((pixel shr 8) and 0xFF) / 255.0f)  // Green
//            byteBuffer.putFloat((pixel and 0xFF) / 255.0f)         // Blue
//        }
//        return byteBuffer
//    }
//}
//
