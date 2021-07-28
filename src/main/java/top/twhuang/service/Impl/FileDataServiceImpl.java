package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.twhuang.entity.FileData;
import top.twhuang.mapper.FileDataMapper;
import top.twhuang.service.FileDataService;
import org.springframework.stereotype.Service;


@Service
public class FileDataServiceImpl extends ServiceImpl<FileDataMapper, FileData> implements FileDataService {
}
