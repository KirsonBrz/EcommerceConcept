package com.kirson.ecommerceconcept.components

import androidx.camera.core.CameraSelector
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.guava.await

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    implementationMode: PreviewView.ImplementationMode = PreviewView.ImplementationMode.COMPATIBLE,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    imageAnalysis: ImageAnalysis? = null,
    imageCapture: ImageCapture? = null,
    preview: Preview = remember { Preview.Builder().build() },
    enableTorch: Boolean = false,
    focusOnTap: Boolean = false
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraProvider by produceState<ProcessCameraProvider?>(initialValue = null) {
        value = ProcessCameraProvider.getInstance(context).await()
    }

    // TODO: add cameraSelector
    val camera = remember(cameraProvider) {
        cameraProvider?.let {
            it.unbindAll()
            it.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                *listOfNotNull(imageAnalysis, imageCapture, preview).toTypedArray()
            )
        }
    }

    LaunchedEffect(camera, enableTorch) {
        camera?.let {
            if (it.cameraInfo.hasFlashUnit()) {
                it.cameraControl.enableTorch(enableTorch).await()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

    AndroidView(modifier = modifier.pointerInput(camera, focusOnTap) {
        if (!focusOnTap) return@pointerInput

        detectTapGestures {
            val meteringPointFactory = SurfaceOrientedMeteringPointFactory(
                size.width.toFloat(), size.height.toFloat()
            )

            val meteringAction = FocusMeteringAction.Builder(
                meteringPointFactory.createPoint(it.x, it.y), FocusMeteringAction.FLAG_AF
            ).disableAutoCancel().build()

            camera?.cameraControl?.startFocusAndMetering(meteringAction)
        }
    }, factory = { _ ->
        PreviewView(context).also {
            it.scaleType = scaleType
            it.implementationMode = implementationMode
            preview.setSurfaceProvider(it.surfaceProvider)
        }
    })
}

