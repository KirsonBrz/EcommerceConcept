package com.kirson.ecommerceconcept.components


import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kirson.ecommerceconcept.main.ui.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
internal fun GlobalSearchComponent() {
    val (value, onValueChange) = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val bitmap = remember { mutableStateOf(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        // bitmap.value = it as Nothing?
    }
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    Row {
        CustomTextField(
            placeholderText = "Search",
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    tint = EcommerceConceptAppTheme.colors.primaryColor
                )
            },
            modifier = Modifier
                .padding(start = 19.dp, top = 9.dp)
                .size(300.dp, 34.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        IconButton(
            onClick = {
                if (cameraPermissionState.status.isGranted) {
                    expanded = !expanded
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
                //launcher.launch()
            }, modifier = Modifier
                .padding(start = 8.dp)
                .background(
                    color = EcommerceConceptAppTheme.colors.primaryColor, shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_code_24),
                contentDescription = "qr code",
                contentScale = ContentScale.Crop
            )
        }
    }


    Spacer(modifier = Modifier.height(10.dp))
    Surface(
        color = EcommerceConceptAppTheme.colors.primaryColor,
        modifier = Modifier
            .padding(5.dp)

    ) {
        AnimatedContent(targetState = expanded, transitionSpec = {
            fadeIn(
                animationSpec = tween(
                    150,
                    150
                )
            ) with fadeOut(animationSpec = tween(150)) using SizeTransform { initialSize, targetSize ->
                if (targetState) {
                    keyframes {
                        // Expand horizontally first.
                        IntSize(targetSize.width, initialSize.height) at 150
                        durationMillis = 300
                    }
                } else {
                    keyframes {
                        // Shrink vertically first.
                        IntSize(initialSize.width, targetSize.height) at 150
                        durationMillis = 300
                    }
                }
            }
        }) { targetExpanded ->
            if (targetExpanded) {

                Surface(
                    color = EcommerceConceptAppTheme.colors.primaryColor,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(15.dp)
                ) {
                    CameraPreview(focusOnTap = true)

                }
            } else {
                Surface(
                    color = EcommerceConceptAppTheme.colors.primaryColor,
                    modifier = Modifier
                        .size(40.dp, 1.dp)
                        .padding(15.dp)

                ) {


                }


            }

        }
    }

}


