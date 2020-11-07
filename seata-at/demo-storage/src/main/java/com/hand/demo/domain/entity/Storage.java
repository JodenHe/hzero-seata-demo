package com.hand.demo.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaofeng.he 2020-11-02 17:17:25
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "demo_storage")
public class Storage extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PRODUCT_ID = "productId";
    public static final String FIELD_TOTAL = "total";
    public static final String FIELD_USED = "used";
    public static final String FIELD_RESIDUE = "residue";

    @ApiModelProperty("")
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "产品id")
    private Long productId;
    @ApiModelProperty(value = "总库存")
    private Long total;
    @ApiModelProperty(value = "已用库存")
    private Long used;
    @ApiModelProperty(value = "剩余库存")
    private Long residue;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 产品id
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return 总库存
     */
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @return 已用库存
     */
    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    /**
     * @return 剩余库存
     */
    public Long getResidue() {
        return residue;
    }

    public void setResidue(Long residue) {
        this.residue = residue;
    }

}
