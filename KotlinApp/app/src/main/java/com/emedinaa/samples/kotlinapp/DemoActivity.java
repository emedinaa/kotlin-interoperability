package com.emedinaa.samples.kotlinapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.emedinaa.samples.kotlinapp.camera.CameraPreviewActivity;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author Eduardo Medina https://github.com/android/permissions-samples
 */
public class DemoActivity extends AppCompatActivity implements
    ActivityCompat.OnRequestPermissionsResultCallback {

  private static final int PERMISSION_REQUEST_CAMERA = 0;

  private View mLayout;
  private SuperKotlinClass superKotlinClass = new SuperKotlinClass();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    mLayout = findViewById(R.id.main_layout);
    findViewById(R.id.buttonCamera).setOnClickListener(view -> {
      showCameraPreview();
    });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    // BEGIN_INCLUDE(onRequestPermissionsResult)
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION_REQUEST_CAMERA) {
      // Request for camera permission.
      if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // Permission has been granted. Start camera preview Activity.
        Snackbar.make(mLayout, R.string.camera_permission_granted,
            Snackbar.LENGTH_SHORT)
            .show();
        startCamera();
      } else {
        // Permission request was denied.
        Snackbar.make(mLayout, R.string.camera_permission_denied,
            Snackbar.LENGTH_SHORT)
            .show();
      }
    }
    // END_INCLUDE(onRequestPermissionsResult)
  }

  private void showCameraPreview() {
    // BEGIN_INCLUDE(startCamera)
    // Check if the Camera permission has been granted
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED) {
      // Permission is already available, start camera preview
      Snackbar.make(mLayout,
          R.string.camera_permission_available,
          Snackbar.LENGTH_SHORT).show();
      startCamera();
    } else {
      // Permission is missing and must be requested.
      //requestCameraPermissionWithJava();
      requestCameraPermissionWithKotlin();
    }
    // END_INCLUDE(startCamera)
  }

  /**
   * Requests the {@link android.Manifest.permission#CAMERA} permission. If an additional rationale
   * should be displayed, the user has to launch the request from a SnackBar that includes
   * additional information.
   */
  private void requestCameraPermissionWithJava() {
    // Permission has not been granted and must be requested.
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.CAMERA)) {
      // Provide an additional rationale to the user if the permission was not granted
      // and the user would benefit from additional context for the use of the permission.
      // Display a SnackBar with cda button to request the missing permission.
      Snackbar.make(mLayout, R.string.camera_access_required,
          Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, view -> {
        // Request the permission
        ActivityCompat.requestPermissions(DemoActivity.this,
            new String[]{Manifest.permission.CAMERA},
            PERMISSION_REQUEST_CAMERA);
      }).show();

    } else {
      Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
      // Request the permission. The result will be received in onRequestPermissionResult().
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
    }
  }

  private void requestCameraPermissionWithKotlin() {
    superKotlinClass.requestCameraPermission(this, mLayout);
  }

  private void startCamera() {
    Intent intent = new Intent(this, CameraPreviewActivity.class);
    startActivity(intent);
  }

}