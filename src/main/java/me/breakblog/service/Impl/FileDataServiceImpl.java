package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.FileData;
import me.breakblog.mapper.FileDataMapper;
import me.breakblog.service.FileDataService;
import org.springframework.stereotype.Service;


@Service
public class FileDataServiceImpl extends ServiceImpl<FileDataMapper, FileData> implements FileDataService {
}
