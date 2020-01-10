package com.mybatis.boot.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mybatis.boot.util.FastDfsUtils;
import com.mybatis.boot.vo.LayuiResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author LX
 * @Date 2020/1/10 18:13
 * @Description
 */
@Controller
@RequestMapping("/fileService")
public class FileUploadController {

    private final FastFileStorageClient fastFileStorageClient;
    private final FastDfsUtils fastDfsUtils;

    public FileUploadController(FastFileStorageClient fastFileStorageClient, FastDfsUtils fastDfsUtils) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fastDfsUtils = fastDfsUtils;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name","lisi");
        return "index";
    }

    /**
     * �ļ��ϴ�
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public LayuiResult test(@RequestParam MultipartFile file) throws IOException {

        // �����ļ���Ϣ
        Set<MetaData> mataData = new HashSet<>();
        mataData.add(new MetaData("author", "lx"));
        mataData.add(new MetaData("description", "xxx�ļ����ٺٺ�"));

        // �ϴ�   ���ļ��ϴ��ɲ����ļ���Ϣ������null���ɣ�
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), mataData);
        String fileUrl = fastDfsUtils.getResAccessUrl(storePath);
        System.out.println(fileUrl);
        return new LayuiResult(0, StringUtils.isEmpty(fileUrl)?"�ϴ�ʧ��":"�ϴ��ɹ�",fileUrl) ;
    }


}
