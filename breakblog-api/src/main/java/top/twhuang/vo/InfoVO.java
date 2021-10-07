package top.twhuang.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InfoVO implements Serializable {

    private String avatar;

    private String blogTitle;

    private String blogSubtitle;

    private String name;

    private String email;

}
