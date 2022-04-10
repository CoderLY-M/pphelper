package com.xiaoli.clientservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 聊天详情表
 * </p>
 *
 * @author xiaoli
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StoreChartDetail对象", description="聊天详情表")
@Component
public class StoreChartDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "聊天主表id")
    private String chartId;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "聊天内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    @TableField(fill = FieldFill.INSERT)
    private Date time;

    @ApiModelProperty(value = "消息类型 0:普通消息， 1：音频， 2：媒体文件")
    private Integer type;

    @ApiModelProperty(value = "是否是最后一条消息 1:是 0：不是")
    private Integer isLatest;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
