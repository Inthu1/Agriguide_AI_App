package com.example.agriguideai.DiseasePrediction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agriguideai.R;
import com.example.agriguideai.ml.PlantDiseaseModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class diseaseprediction extends AppCompatActivity {
    TextView result, demoText, classified, clickHere,precaution,prevention;
    ImageView imageView, arrowImage;
    Button picture;

    int imageSize = 224; // default image size

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseaseprediction);

                result = findViewById(R.id.result);
                imageView = findViewById(R.id.imageView);
                picture = findViewById(R.id.button);

                demoText = findViewById(R.id.demoText);
                clickHere = findViewById(R.id.click_here);
                arrowImage = findViewById(R.id.demoArrow);
                classified = findViewById(R.id.classified);
                prevention=findViewById(R.id.prevention);
                precaution=findViewById(R.id.precaution);

                demoText.setVisibility(View.VISIBLE);
                clickHere.setVisibility(View.GONE);
                arrowImage.setVisibility(View.VISIBLE);
                classified.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                prevention.setVisibility(View.GONE);
                precaution.setVisibility(View.GONE);

                picture.setOnClickListener(view -> {
                    // Launch Camera if we have permission
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    } else {
                        // request camera permission id\f don't have
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                });
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == 1 && resultCode == RESULT_OK) {
                    Bitmap image = (Bitmap) data.getExtras().get("data");
                    int dimension = Math.min(image.getWidth(), image.getHeight());
                    image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                    imageView.setImageBitmap(image);

                    demoText.setVisibility(View.GONE);
                    clickHere.setVisibility(View.VISIBLE);
                    arrowImage.setVisibility(View.GONE);
                    classified.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    prevention.setVisibility(View.VISIBLE);
                    precaution.setVisibility(View.VISIBLE);

                    image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                    classifyImage(image);
                }
                super.onActivityResult(requestCode, resultCode, data);
            }

            private void classifyImage(Bitmap image) {
                try {
                    PlantDiseaseModel model = PlantDiseaseModel.newInstance(getApplicationContext());

                    //create input for reference
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
                    byteBuffer.order(ByteOrder.nativeOrder());

                    // get 1D array of 224 * 224 pixels in image
                    int[] intValue = new int[imageSize * imageSize];
                    image.getPixels(intValue, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

                    // iterate over pixels and extract R, G, B values add to bytebuffer
                    int pixel = 0;
                    for (int i = 0; i < imageSize; i++) {
                        for (int j = 0; j < imageSize; j++) {
                            int val = intValue[pixel++]; // RGB
                            byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                            byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                            byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                        }
                    }

                    inputFeature0.loadBuffer(byteBuffer);

                    // run model interface and gets result
                    PlantDiseaseModel.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] confidence = outputFeature0.getFloatArray();

                    // find the index of the class with the biggest confidence
                    int maxPos = 0;
                    float maxConfidence = 0;
                    for (int i = 0; i < confidence.length; i++) {
                        if (confidence[i] > maxConfidence) {
                            maxConfidence = confidence[i];
                            maxPos = i;
                        }
                    }
                    String[] classes = {"apple apple scab","apple black rot","apple cedar apple rust","apple healthy","blueberry healthy","cherry including sour powdery mildew","cherry including sour healthy", "corn maize cercospora leaf spot gray leaf spot", "corn maize common rust ", "corn maize northern leaf blight", "corn maize healthy", "grape black rot", "grape esca black measles ", "grape leaf blight isariopsis leaf spot ", "grape healthy", "orange haunglongbing citrus greening" , "peach bacterial spot" , "peach healthy" , "pepper bell bacterial spot" , "pepper bell healthy" , "potato early blight" , "potato late blight" , "potato healthy" , "raspberry healthy" , "soybean healthy" , "squash powdery mildew" , "strawberry leaf scorch" , "strawberry healthy" , "tomato bacterial spot" , "tomato early blight" , "tomato late blight" , "tomato leaf mold" , "tomato septoria leaf spot" , "tomato spider mites two spotted spider mite" , "tomato target spot" , "tomato tomato yellow leaf curl virus" , "tomato tomato mosaic virus" , "tomato healthy"};
                    result.setText(classes[maxPos]);
                    result.setOnClickListener(view -> {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/search?q=" + result.getText())));
                    });
                    String searchText = result.getText().toString();
                    if(searchText.equals("tomato bacterial spot")) {
                        String cure=getString(R.string.tomatobacterialspot);
                        precaution.setText(cure);
                    } else if (searchText.equals("apple apple scab")) {
                        String cure=getString(R.string.appleapplescab);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("apple cedar apple rust")) {
                        String cure=getString(R.string.applecedarapplerust);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("apple healthy")) {
                        String cure=getString(R.string.applehealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("apple black rot")) {
                        String cure=getString(R.string.appleblackrot);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("blueberry healthy")) {
                        String cure=getString(R.string.blueberryhealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("cherry including sour powdery mildew")) {
                        String cure=getString(R.string.cherryincludingsourpowderymildew);
                        precaution.setText(cure);
                    } else if (searchText.equals("cherry including sour healthy")) {
                        String cure=getString(R.string.cherryincludingsourhealthy);
                        precaution.setText(cure);
                    } else if (searchText.equals("corn maize cercospora leaf spot gray leaf spot")) {
                        String cure=getString(R.string.cornmaizecercosporaleafspotgrayleafspot);
                        precaution.setText(cure);
                    } else if (searchText.equals("corn maize common rust ")) {
                        String cure=getString(R.string.cornmaizecommonrust);
                        precaution.setText(cure);
                    } else if (searchText.equals("corn maize northern leaf blight")) {
                        String cure=getString(R.string.cornmaizenorthernleafblight);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("corn maize healthy")) {
                        String cure=getString(R.string.cornmaizehealthy);
                        precaution.setText(cure);
                    } else if (searchText.equals("grape black rot")) {
                        String cure=getString(R.string.grapeblackrot);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("grape esca black measles ")) {
                        String cure=getString(R.string.grapeescablackmeasles);
                        precaution.setText(cure);
                    } else if (searchText.equals("grape leaf blight isariopsis leaf spot")) {
                        String cure=getString(R.string.grapeleafblightisariopsisleafspot);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("grape healthy")) {
                        String cure=getString(R.string.grapehealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("orange haunglongbing citrus greening)")) {
                        String cure=getString(R.string.orangehaunglongbingcitrusgreening);
                        precaution.setText(cure);
                    } else if (searchText.equals("peach bacterial spot")) {
                        String cure=getString(R.string.peachbacterialspot);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("peach healthy")) {
                        String cure=getString(R.string.peachhealthy);
                        precaution.setText(cure);
                    } else if (searchText.equals("pepper bell bacterial spot")) {
                        String cure=getString(R.string.pepperbellbacterialspot);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals("pepper bell healthy")) {
                        String cure=getString(R.string.pepperbellhealthy);
                        precaution.setText(cure);
                    } else if (searchText.equals("potato early blight")) {
                        String cure = getString(R.string.potatoearlyblight);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" tomato healthy ")) {
                        String cure=getString(R.string. tomatohealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" tomato tomato mosaic virus ")) {
                        String cure=getString(R.string. tomatotomatomosaicvirus);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" tomato tomato yellow leaf curl virus ")) {
                        String cure=getString(R.string. tomatotomatoyellowleafcurlvirus);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" strawberry healthy ")) {
                            String cure=getString(R.string. strawberryhealthy);
                            precaution.setText(cure);
                        }
                        else if (searchText.equals(" strawberry leaf scorch ")) {
                            String cure=getString(R.string. strawberryleafscorch);
                            precaution.setText(cure);
                        }
                        else if (searchText.equals(" squash powdery mildew ")) {
                            String cure=getString(R.string. squashpowderymildew);
                            precaution.setText(cure);
                        }
                    else if (searchText.equals(" soybean healthy ")) {
                        String cure=getString(R.string. soybeanhealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" raspberry healthy ")) {
                        String cure=getString(R.string. raspberryhealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" potato healthy ")) {
                        String cure=getString(R.string. potatohealthy);
                        precaution.setText(cure);
                    }
                    else if (searchText.equals(" potato late blight ")) {
                        String cure=getString(R.string. potatolateblight);
                        precaution.setText(cure);
                    }
                    else{
                        precaution.setText(null);
                    }
                    model.close();

                } catch (IOException e) {
                    // TODO Handle the exception
                }
    }
}