package com.example.jeremie.javaproject5777;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ExtractFavicon{

    private static Random rand = new Random();

    public static int randomColor() {
        // generate the random integers for r, g and b value
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return Color.rgb(r,g,b);
    }

    private static Bitmap defaultIcon(Character latter, int size) throws IOException {
        String hexColor = String.format("%06X", (0xFFFFFF & randomColor()));
        URL default_url = new URL("http://icons.better-idea.org/lettericons/" + Character.toUpperCase(latter) + "-"+new Integer(size)+"-" + hexColor + ".png");
        HttpURLConnection connection = (HttpURLConnection) default_url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        return BitmapFactory.decodeStream(inputStream);
    }

    private static Bitmap download(URL src){
        try {
            HttpURLConnection connection = (HttpURLConnection) src.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static URL findIcon(JsonReader reader, int size) {
        try {
            String name = null;
            String url = null;
            int width = 0, height = 0;
            reader.beginObject();
            while (reader.hasNext()) {
                name = reader.nextName();
                if (name.equals("icons"))
                    break;
                else
                    reader.skipValue();
            }
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {
                    name = reader.nextName();
                    if (name.equals("url"))
                        url = reader.nextString();
                    else if (name.equals("width"))
                        width = reader.nextInt();
                    else if (name.equals("height"))
                        height = reader.nextInt();
                    else
                        reader.skipValue();
                }
                if (width >= size && height >= size) {
                    reader.close();
                    return new URL(url);
                } else
                    reader.endObject();
            }
            reader.close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getFavicon(Bitmap defaultIcon,String src, int size) {
        try {
            URL url = new URL("http://icons.better-idea.org/allicons.json?url=" + src);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            URL favicon = findIcon(new JsonReader(new InputStreamReader(inputStream)), size);
            Bitmap img = download(favicon);
            if(img == null) {
                String domain = new URL(src).getHost();
                if(domain.startsWith("www."))
                    domain = domain.substring(4);
                return defaultIcon(domain.charAt(0),size*2);
            }
            return img;
        }catch (IOException e) {
            e.printStackTrace();
            return defaultIcon;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}