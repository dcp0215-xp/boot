package com.mybatis.boot.util;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author LX
 * @Date 2020/1/10 19:29
 * @Description
 */
@Component
@Slf4j
public class FastDfsUtils {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    /**
     * �ϴ��ļ�
     *
     * @param file �ļ�����
     * @return �ļ����ʵ�ַ
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * �ϴ��ļ�
     *
     * @param file �ļ�����
     * @return �ļ����ʵ�ַ
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * �ϴ�ͼƬ������������ͼ
     * <p>
     * <pre>
     * ֧�ֵ�ͼƬ��ʽ����"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     * </pre>
     */
    public String uploadFileThumb(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(storePath);
    }

    public String uploadFileThumb(MultipartFile file) throws IOException {
        ;
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * ��һ���ַ�������һ���ļ��ϴ�
     *
     * @param content       �ļ�����
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(storePath);
    }

    // ��װͼƬ����URL��ַ
    public String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }

    /**
     * ɾ���ļ�
     *
     * @param fileUrl �ļ����ʵ�ַ
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * �����ļ�
     * @param fileUrl
     * @return
     */
    public byte[] downLoadFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return null;
        }
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), downloadByteArray);
        return bytes;
    }

}
