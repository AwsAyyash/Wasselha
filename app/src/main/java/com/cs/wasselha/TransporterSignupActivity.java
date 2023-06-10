package com.cs.wasselha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class TransporterSignupActivity extends AppCompatActivity {
    public static int transporterID,vehicleID;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText nationalIDEditText;
    private EditText phoneNumberEditText;
    private EditText vehicleNumberEditText;
    private Spinner vehicleTypeSpinner;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button vehiclePhotoUploadBtn;
    private Button vehicleLicenseUploadPhotoBtn;
    private Button personalLicenseUploadPhotoBtn;
    private Button signupTransporterBtn;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_VEHICLE_IMAGE = 1;
    private static final int PICK_VEHICLE_LICENSE = 2;
    private static final int PICK_DRIVING_LICENSE = 3;
    private Uri selectedVehicleImageUri;
    private Uri selectedVehicleLicenseUri;
    private Uri selectedDrivingLicenseUri;
    private Bitmap selectedVehicleImagebitmap;
    private Bitmap selectedVehicleLicensebitmap;
    private Bitmap selectedDrivingLicensebitmap;
    private String transportersUrl="http://176.119.254.198:8000/wasselha/transporters/";
    private String vehiclesUrl="http://176.119.254.198:8000/wasselha/vehicles/";
    RequestQueue requestQueue;
    String boundary = "*****";
    String lineEnd = "\r\n";
    String twoHyphens = "--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_signup);
        requestQueue = Volley.newRequestQueue(this);
        firstNameEditText = findViewById(R.id.firstNameTransporter);
        lastNameEditText = findViewById(R.id.lastNameTransporter);
        nationalIDEditText = findViewById(R.id.nationalIDTransporterSignup);
        phoneNumberEditText = findViewById(R.id.phoneNumberTransporterSignup);
        vehicleNumberEditText = findViewById(R.id.vehicleNumberTransporterSignup);
        vehicleTypeSpinner = findViewById(R.id.vehicleTypeSpinner);
        emailEditText = findViewById(R.id.emailTransporterSignup);
        passwordEditText = findViewById(R.id.passwordTransporterSignup);
        repeatPasswordEditText = findViewById(R.id.repeatPasswordTransporterSignup);
        vehiclePhotoUploadBtn = findViewById(R.id.vehiclePhotoUploadBtn);
        vehicleLicenseUploadPhotoBtn = findViewById(R.id.vehicleLicenseUploadPhotoBtn);
        personalLicenseUploadPhotoBtn = findViewById(R.id.personalLicenseUploadPhotoBtn);
        signupTransporterBtn=findViewById(R.id.signupTransporterBtn);
        vehiclePhotoUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_VEHICLE_IMAGE);
                // save the selected uri for further use
                selectedVehicleImageUri = intent.getData();
            }
        });

        vehicleLicenseUploadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_VEHICLE_LICENSE);
                // save the selected uri for further use
                selectedVehicleLicenseUri = intent.getData();
            }
        });

        personalLicenseUploadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_DRIVING_LICENSE);
                // save the selected uri for further use
                selectedDrivingLicenseUri = intent.getData();
            }
        });
        signupTransporterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    createTransporter();
                new CreateTransporterTask().execute();
                Toast.makeText(TransporterSignupActivity.this, "result", Toast.LENGTH_SHORT).show();
                Log.d("idddd","id:"+transporterID);
//                    createVehicle(transporterID);
                Toast.makeText(TransporterSignupActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            String fileName = getImageName(imageUri);

            switch (requestCode) {
                case PICK_VEHICLE_IMAGE:
                    selectedVehicleImageUri = imageUri;
                    vehiclePhotoUploadBtn.setText(fileName);
                    break;
                case PICK_VEHICLE_LICENSE:
                    selectedVehicleLicenseUri = imageUri;
                    vehicleLicenseUploadPhotoBtn.setText(fileName);
                    break;
                case PICK_DRIVING_LICENSE:
                    selectedDrivingLicenseUri = imageUri;
                    personalLicenseUploadPhotoBtn.setText(fileName);
                    break;
            }
        }
    }

    private String getImageName(Uri uri) {
        String fileName = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex >= 0) {
                fileName = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return fileName;
    }
    private String getFilePathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        // Use the ContentResolver to query for the actual file path
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        String filePath = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Get the index of the DATA column
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // Retrieve the file path from the cursor
            if(columnIndex>=0)
                filePath = cursor.getString(columnIndex);
            cursor.close();
        }

        return filePath;
    }


    private String createTransporter() {
        try {
            try {
                selectedDrivingLicensebitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedDrivingLicenseUri);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(TransporterSignupActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedDrivingLicensebitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
//            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//            selectedDrivingLicensebitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
//            byte[] bytes=byteArrayOutputStream.toByteArray();
//            final String base64Image= Base64.encodeToString(bytes,Base64.DEFAULT);
            URL url = new URL(transportersUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"first_name\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(firstNameEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"last_name\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(lastNameEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"email\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(emailEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"password\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(passwordEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"phone_number\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(phoneNumberEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"is_verified\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes("false");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"status\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes("Available");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"national_id\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(nationalIDEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"review\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes("0");
            outputStream.writeBytes(lineEnd);

            // Repeat for other text fields: last_name, email, etc...

            // File part
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"driving_license\";filename=\"driving_license.png\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            Log.d("imageee",byteArray.toString());
            outputStream.write(byteArray);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
                String responseString = response.toString();
                Log.d("responseee",responseString);
                JSONObject jsonResponse = new JSONObject(responseString);


                // Extract transporter id from the JSON response
                // Note: You have to know the exact key under which the id is stored in the JSON response
                String transporterId = jsonResponse.getString("id"); // replace 'transporter_id' with the correct key
                Log.d("iddddd",transporterId);
                TransporterSignupActivity.transporterID=Integer.parseInt(transporterId.trim());
                return transporterId;
            }
        } catch (IOException | JSONException e) {
            Log.e("UploadTransporterTask", "Error: " + e.getMessage());
        }
        return null;
    }


    private void createVehicle(int transporterId) {
        try {
            try {
                selectedVehicleImagebitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedVehicleImageUri);
                selectedVehicleLicensebitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedVehicleLicenseUri);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(TransporterSignupActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedVehicleImagebitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            selectedVehicleLicensebitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
            URL url = new URL(vehiclesUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"transporter\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(transporterId+"");
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"vehicle_number\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(vehicleNumberEditText.getText().toString().trim());
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"vehicle_type\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(vehicleTypeSpinner.getSelectedItem().toString().trim().toLowerCase());
            outputStream.writeBytes(lineEnd);

            // Repeat for other text fields: last_name, email, etc...

            // File part
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"vehicle_image\";filename=\"driving_license.png\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.write(byteArray);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"vehicle_license\";filename=\"driving_license.png\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.write(byteArray2);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
                String responseString = response.toString();
                Log.d("responseee",responseString);
                JSONObject jsonResponse = new JSONObject(responseString);


                // Extract transporter id from the JSON response
                // Note: You have to know the exact key under which the id is stored in the JSON response
                String vehicleId = jsonResponse.getString("id"); // replace 'transporter_id' with the correct key
                Log.d("iddddd",vehicleId);
            }
        } catch (IOException | JSONException e) {
            Log.e("UploadTransporterTask", "Error: " + e.getMessage());
        }



    }

    private class CreateTransporterTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String id=createTransporter();
            int transporterId=Integer.parseInt(id);
            createVehicle(transporterId);

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(TransporterSignupActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
        }
    }




}