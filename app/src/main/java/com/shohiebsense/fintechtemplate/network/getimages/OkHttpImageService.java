package com.shohiebsense.fintechtemplate.network.getimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shohiebsense.fintechtemplate.model.CommonImage;
import com.shohiebsense.fintechtemplate.model.CommonImageRequest;
import com.shohiebsense.fintechtemplate.util.Constants;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class OkHttpImageService {


    public List<Bitmap> getImageFileUrl(List<CommonImageRequest> imageRequests){
        List<Bitmap> bitmaps = new ArrayList<>();
        for(CommonImageRequest imageRequest :  imageRequests){
            Response response =  new OkHttpImageResponseService().getImageResource(imageRequest.getWidth(), imageRequest.getHeight());
            if(response == null){
                //do smth
                return null;
            }
            InputStream inputStream = response.body().byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            bitmaps.add(bitmap);
        }
        return bitmaps;
    }

    public List<CommonImage> getImages(List<CommonImageRequest> imageRequests){
        List<CommonImage> bitmaps = new ArrayList<>();
        for(CommonImageRequest imageRequest :  imageRequests){
            bitmaps.add(new CommonImage(Constants.PLACEKITTEN_URL+"/"+imageRequest.getWidth()+"/"+imageRequest.getHeight()));
        }
        return bitmaps;
    }


}
