package com.emedinaa.samples.kotlinapp

import android.Manifest
import android.app.Activity
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

/**
 * @author Eduardo Medina
 */
private const val PERMISSION_REQUEST_CAMERA = 0

class SuperKotlinClass {

    fun requestCameraPermission(activity: Activity, view: View) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.CAMERA
            )
        ) {
            showMessage(activity, view)
        } else {
            showError(activity, view)
        }
    }

    private fun showMessage(activity: Activity, view: View) {
        Snackbar.make(
            view, R.string.camera_access_required,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.ok) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CAMERA
            )
        }.show()
    }

    private fun showError(activity: Activity, view: View) {
        Snackbar.make(
            view, R.string.camera_unavailable,
            Snackbar.LENGTH_SHORT
        ).show()
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CAMERA
        )
    }
}