package com.next.apps;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "用户对象")
public class User {

    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "年龄",access = "hidden")
    private int age;
    @ApiModelProperty(value = "性别")
    private int sex;
}