package com.example.ricegrow

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun FAB(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(bottom = 100.dp, end = 12.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = {it}) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = {it}) + shrinkVertically()
        ) {
            LazyColumn(Modifier.padding(bottom = 12.dp)) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(all = 6.dp)
                    ) {

                        Text(
                            text = " Camera ",
                            modifier = Modifier
                                .padding(6.dp)
                                .border(8.dp, Color(0xFFF1F1C3), RoundedCornerShape(10.dp))
                                .padding(6.dp)
                                .background(Color(0xFFF1F1C3)
                                )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        TakePicture(navController)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(all = 6.dp)
                    ) {

                        Text(
                            text = " Gallery ",
                            modifier = Modifier
                                .padding(6.dp)
                                .border(8.dp, Color(0xFFF1F1C3), RoundedCornerShape(10.dp))
                                .padding(8.dp)
                                .background(Color(0xFFF1F1C3)
                                )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        UploadImage(navController)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
        val transition = updateTransition(targetState = expanded, label = "transition")
        val rotation by transition.animateFloat(label = "rotation"){
            if (it) 315f else 0f
        }
        FloatingActionButton(onClick = { expanded = !expanded},
            modifier = Modifier
                .rotate(rotation)
                .size(80.dp),
            containerColor = Color(0xFFECD98F)
        ) {
            Icon(
                imageVector = Icons.Filled.Add, contentDescription = "",
                modifier = Modifier.rotate(rotation),
            )
        }
    }
}

@Composable
fun TakePicture(navController: NavController) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    val capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                navController.navigate("image_display/${Uri.encode(uri.toString())}")
            } else {
                Toast.makeText(context, "Image capture failed", Toast.LENGTH_SHORT).show()
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    FloatingActionButton(
        onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                // Request a permission
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        modifier = Modifier.size(50.dp),
        containerColor = Color(0xFFF5F5B0)
    ) {
        Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "")
    }

    // Display Captured Image inside a Card
    if (capturedImageUri.path?.isNotEmpty() == true) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .border(
                    2.dp,
            Color.Gray,
            RoundedCornerShape(12.dp)
        ),
            shape = RoundedCornerShape(12.dp),

        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(capturedImageUri),
                contentDescription = "Captured Image",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun UploadImage(navController: NavController) {
    val context = LocalContext.current // Obtain context in a composable scope
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                selectedImageUri = uri
                navController.navigate("image_display/${Uri.encode(uri.toString())}")
            } else {
                Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
            }
        }
    )

    FloatingActionButton(
        onClick = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        modifier = Modifier.size(50.dp),
        containerColor = Color(0xFFF5F5B0)
    ) {
        Icon(imageVector = Icons.Default.Photo, contentDescription = "Upload Image")
    }
}




//Take Picture
@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
// Create an image file name
val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
val imageFileName = "JPEG_" + timeStamp + "_"
val image = File.createTempFile(
    imageFileName, /* prefix */
    ".jpg", /* suffix */
    externalCacheDir      /* directory */
)
return image
}
