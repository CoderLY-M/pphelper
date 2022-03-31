package com.xiaoli.basicservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author xiaoli
 * @since 2022-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StoreProduct对象", description="商品表")
public class StoreProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "会员ID")
    private String memberId;

    @ApiModelProperty(value = "商品分类ID")
    private String sortId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品描述")
    private String productDesc;

    @ApiModelProperty(value = "商品细节")
    private String productDetail;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "商品状态 0下架  1待审核 2在售")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
