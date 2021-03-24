package com.ray.coding.common.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ray
 * @version V1.0.0
 * @description 文件操作工具类
 * @since 2021/3/15 17:58
 */
public class FileUtil
{

    /**
     * 判断文件夹是否存在
     *
     * @param filePath 文件夹绝对路径
     * @return 结果
     */
    public static boolean validateFileDir(String filePath)
    {
        // 判断文件路径是否存在
        File fileDir = new File(filePath);
        if (fileDir.exists())
        {
            return true;
        }
        return false;
    }

    /**
     * 创建文件夹
     *
     * @param dirPath 路径
     * @return 路径
     */
    public static String mkDir(String dirPath)
    {
        try
        {
            File file = new File(dirPath);
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dirPath;
    }

    /**
     * 删除文件（夹）
     *
     * @param filePath 路径
     * @return 结果
     */
    public static boolean deleteFile(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if (file.isDirectory())
            {
                for (File f : file.listFiles())
                {
                    deleteFile(f.getAbsolutePath());
                }
            }
            return file.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件大小
     * 文件不存在返回0.0
     *
     * @param filePath 文件绝对路径
     * @return 文件大小（单位:KB）
     */
    public static Double getFileSize(String filePath)
    {
        File file = new File(filePath);
        double fileLen = Double.valueOf(file.length()) / 1000;
        BigDecimal bigDecimal = new BigDecimal(fileLen).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 路径
     * @return 结果
     */
    public static boolean isExists(String filePath)
    {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断是否是文件夹
     *
     * @param filePath 路径
     * @return 结果
     */
    public static boolean isDirectory(String filePath)
    {
        File file = new File(filePath);
        if (file.exists())
        {
            return file.isDirectory();
        }
        return false;
    }

    /**
     * 文件（夹）重命名
     *
     * @param oldFilePath 原文件（夹）路径
     * @param newName     新文件名
     * @return 结果
     */
    public static boolean fileRename(String oldFilePath, String newName)
    {
        try
        {
            File oldFile = new File(oldFilePath);
            // 判断文件是否存在
            if (oldFile.exists())
            {
                // 判断是全路径还是文件名
                if (newName.indexOf(StrUtil.SLASH) < 0 && newName.indexOf(StrUtil.SLASH) < 0)
                {
                    // 判断是windows还是linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if (newName.indexOf(StrUtil.SLASH) > 0)
                    {
                        // linux
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf(StrUtil.SLASH) + 1) + newName;
                    }
                    else
                    {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf(StrUtil.BACKSLASH) + 1) + newName;
                    }
                }
                // 判断重命名后的文件是否存在
                File file = new File(newName);
                if (file.exists())
                {
                    System.out.println(StrUtil.format("{} 已存在，不能重命名"));
                }
                else
                {
                    return oldFile.renameTo(file);
                }
            }
            else
            {
                System.out.println(StrUtil.format("{} 文件不存在", oldFilePath));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取文本文件
     *
     * @param filePath 路径
     * @return 文本
     */
    public static String readText(String filePath)
    {
        String text = "";
        BufferedReader bufferedReader = null;
        try (FileReader fileReader = new FileReader(filePath))
        {
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                text += line + "\n";
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件类型
     */
    public static String getFileType(File file) throws Exception
    {
        if (file.isDirectory())
        {
            throw new Exception("file不是文件");
        }
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 返回前台文件流
     *
     * @param filePath 待下载文件路径
     * @param fileName 下载文件名称
     * @param delete   下载后是否删除源文件
     * @param response HttpServletResponse
     */
    public static void downloadFile(String filePath, String fileName, Boolean delete, HttpServletResponse response) throws Exception
    {
        if (!isExists(filePath))
        {
            throw new Exception(StrUtil.format("文件:{} 未找到", filePath));
        }

        // 设置响应
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");

        // 文件流操作
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream())
        {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        finally
        {
            if (delete)
            {
                deleteFile(filePath);
            }
        }

    }
}
