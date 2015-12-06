package com.example.pooria.bestoonchi;
import com.facebook.common.util.UriUtil;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class help2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_help2);


        //uri builder for  resource drawable
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                .path(String.valueOf(R.id.help2_imageView))
                .build();
        // uri looks like res:/123456789

        //using fresco library to compress images ->more details: http://frescolib.org/docs/
        int width = 50, height = 50;
        ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(R.id.help2_imageView)
                .setResizeOptions(new ResizeOptions(width, height))
                .setAutoRotateEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE)
                .setProgressiveRenderingEnabled(false)
                .build();

        DraweeView mSimpleDraweeView = (DraweeView) findViewById(R.id.help2_imageView);
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(mSimpleDraweeView.getController())
                .setImageRequest(request)
                .build();
        mSimpleDraweeView.setController(controller);

        Button btnnext = (Button) findViewById(R.id.button3);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(help2.this, MainActivity.class);
                startActivity(intent);
                help2.this.finish();
            }
        });
        Button btnback = (Button) findViewById(R.id.button4);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(help2.this, help.class);
                startActivity(intent);
                help2.this.finish();

            }
        });
    }
}
