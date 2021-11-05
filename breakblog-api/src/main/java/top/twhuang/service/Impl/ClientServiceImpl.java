package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.twhuang.entity.Client;
import top.twhuang.mapper.ClientMapper;
import top.twhuang.service.ClientService;

@Service
@Slf4j
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}
