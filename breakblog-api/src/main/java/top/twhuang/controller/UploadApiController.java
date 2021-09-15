package top.twhuang.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.twhuang.config.UploadConfig;
import top.twhuang.entity.FileData;
import top.twhuang.service.FileDataService;
import top.twhuang.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: tw.huang
 * @DateTime: 2021-03-18 14:46
 * @Description: 文件上传
 */
@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class UploadApiController {

    private UploadConfig uploadConfig;

    private FileDataService fileDataService;

    @PostMapping("/file")
    public Result postFile(@RequestParam(required = false, defaultValue = "default") String path,
                           @RequestParam("file") MultipartFile uploadFile) {
        String type = uploadConfig.getType();
        String uploadPath = uploadConfig.getPath();
        String host = uploadConfig.getHost();
        // 生成唯一 uuid
        String uuid = IdUtil.simpleUUID();

        String oldFileName = uploadFile.getOriginalFilename();
        if (StringUtils.isNotEmpty(oldFileName)) {
            //后缀名
            String suffixName = oldFileName.substring(oldFileName.lastIndexOf("."));
            //文件名 uuid+后缀名
            String newFileName = uuid + suffixName;

            if (type.equals(UploadConfig.TYPE_LOCAL)) {
                //完整路径
                String finalPath = uploadPath + File.separator + path + File.separator + DateUtil.today() + File.separator + newFileName;
                //url
                String urlPath = host + File.separator + path + File.separator + DateUtil.today() + File.separator + newFileName;
                try {
                    File file = new File(finalPath);
                    // 检测是否存在目录
                    if (!file.getParentFile().exists()) {
                        // 新建文件夹
                        file.getParentFile().mkdirs();
                    }
                    // 文件写入
                    FileCopyUtils.copy(uploadFile.getBytes(), file);

                    //入库
                    FileData fileData = new FileData();
                    fileData.setName(oldFileName);
                    fileData.setUuid(uuid);
                    fileData.setType(uploadFile.getContentType());
                    fileData.setSize(uploadFile.getSize());
                    fileData.setUrl(urlPath);
                    this.fileDataService.save(fileData);
                    return Result.success(fileData, "上传文件成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.failure();
    }

}

