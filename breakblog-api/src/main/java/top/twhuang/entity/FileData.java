package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "file_data")
public class FileData extends BaseEntity {

    private String name;

    private String uuid;

    private String type;

    private Long size;

    private String url;

}
