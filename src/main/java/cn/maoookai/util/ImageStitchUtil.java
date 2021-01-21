package cn.maoookai.util;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageStitchUtil {
    /**
     * @param fileUrl �ļ�����·�������·��
     * @return ��ȡ���Ļ���ͼ��
     * @throws IOException ·��������߲����ڸ��ļ�ʱ�׳�IO�쳣
     */
    public static BufferedImage getBufferedImage(String fileUrl)
            throws IOException {
        File f = new File(fileUrl);
        return ImageIO.read(f);
    }

    /**
     * ���ͼƬ
     *
     * @param buffImg  ͼ��ƴ�ӵ���֮���BufferedImage����
     * @param savePath ͼ��ƴ�ӵ���֮��ı���·��
     */
    public static void generateSaveFile(BufferedImage buffImg, String savePath) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            File outFile = new File(savePath);
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            ImageIO.write(buffImg, savePath.substring(temp), outFile);
            System.out.println("ImageIO write...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���ϲ�������ͼ��������������ǰ�ᣬ���ˮƽ����ϲ�����߶ȱ�����ȣ�����Ǵ�ֱ����ϲ�����ȱ�����ȡ�
     * mergeImage���������жϣ��Լ��жϡ�      * @param img1 ���ϲ��ĵ�һ��ͼ
     *
     * @param img2         ���ϲ��ĵڶ���ͼ
     * @param isHorizontal Ϊtrueʱ��ʾˮƽ����ϲ���Ϊfalseʱ��ʾ��ֱ����ϲ�
     * @return ���غϲ����BufferedImage����
     * @throws IOException
     */
    @NotNull
    public static BufferedImage mergeImage(@NotNull BufferedImage img1,
                                           @NotNull BufferedImage img2, boolean isHorizontal) throws IOException {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // ��ͼƬ�ж�ȡRGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // ����ɨ��ͼ���и������ص�RGB��������
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

        // ������ͼƬ
        BufferedImage DestImage = null;
        if (isHorizontal) { // ˮƽ����ϲ�
            DestImage = new BufferedImage(w1 + w2, h1, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // �����ϰ벿�ֻ���벿�ֵ�RGB
            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
        } else { // ��ֱ����ϲ�
            DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // �����ϰ벿�ֻ���벿�ֵ�RGB
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // �����°벿�ֵ�RGB
        }

        return DestImage;
    }

}
