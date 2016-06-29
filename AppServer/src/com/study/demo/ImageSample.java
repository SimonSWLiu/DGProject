package com.study.demo;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FilenameUtils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.ImageUtils;

public class ImageSample {

    private static String bucketName = "s3-onemenu";
    private static String keyName = "resources/restaurant-res/test/test.jpg";


    // public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
    public static BufferedImage resizeByTargetWidth(BufferedImage source, int targetWidth) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        System.out.println(source.getWidth() + " x " + source.getHeight());

        double ratioHeightWidth = (double) source.getHeight() / source.getWidth();
        System.out.println("ratioHW: " + ratioHeightWidth);

        int targetHeight = (int) (ratioHeightWidth * targetWidth);
        System.out.println(targetWidth + " x " + targetHeight);

        double sx = (double) targetWidth / source.getWidth();
        double sy = (double) targetHeight / source.getHeight();

        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetWidth, targetHeight);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetWidth, targetHeight, type);
        }

        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();

        return target;
    }

    public static BufferedImage saveImageAsJpg(String fromFilePath, String toFilePath, int width,
            int hight) throws Exception {
        BufferedImage bi;

        File toFile = new File(toFilePath);
        File fromFile = new File(fromFilePath);

        // String imgType = FilenameUtils.getExtension(fromFile.getName());
        String imgType = "jpeg";
        bi = ImageIO.read(fromFile);

        if (width > 0) {
            bi = resizeByTargetWidth(bi, width);
        }
        ImageIO.write(bi, imgType, toFile);

        return bi;
    }

    public static void main(String argv[]) {
        try {

            String fromPath =
                    System.getProperty("user.dir") + File.separator + "OneMenu" + File.separator
                            + "demo" + File.separator + "test.png";
            String toPath =
                    System.getProperty("user.dir") + File.separator + "OneMenu" + File.separator
                            + "demo" + File.separator + "result.jpg";

            File file = new File(fromPath);
            String extension = FilenameUtils.getExtension(file.getName());
            System.out.println(extension);
            // 参数1(from),参数2(to),参数3(宽),参数4(高)
            BufferedImage bi = saveImageAsJpg(fromPath, toPath, 540, 300);
            System.out.println(bi.getWidth() + " x " + bi.getHeight());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(os);
            ImageIO.write(bi, extension, imOut);
            System.out.println("os.size(): " + os.size());

            InputStream is = new ByteArrayInputStream(os.toByteArray());
            System.out.println("is.available(): " + is.available());

            AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider("onemenuadmin"));

            try {

                System.out.println("Uploading a new object to S3 from a file\n");

                String mimetype =
                        AwsS3Utils.getMineType(ImageUtils.getBase64FromInputStream(is, extension));

                ObjectMetadata metaddata = new ObjectMetadata();
                metaddata.setContentType(mimetype);
                metaddata.setContentLength(is.available());
                PutObjectResult objectResult =
                        s3client.putObject(new PutObjectRequest(bucketName, keyName, is, metaddata));
                System.out.println(objectResult.toString());
            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which "
                        + "means your request made it "
                        + "to Amazon S3, but was rejected with an error response"
                        + " for some reason.");
                System.out.println("Error Message: " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code: " + ase.getErrorCode());
                System.out.println("Error Type: " + ase.getErrorType());
                System.out.println("Request ID: " + ase.getRequestId());
            } catch (AmazonClientException ace) {
                System.out
                        .println("Caught an AmazonClientException, which "
                                + "means the client encountered "
                                + "an internal error while trying to " + "communicate with S3, "
                                + "such as not being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
            }
            System.out.println("Done\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
