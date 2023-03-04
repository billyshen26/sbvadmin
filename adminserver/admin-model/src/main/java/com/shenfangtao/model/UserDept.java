package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户机构
 * </p>
 *
 * @author billy
 * @since 2023-03-03
 */
@Getter
@Setter
@TableName("sys_user_dept")
public class UserDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户外键
     */
    private Long uid;

    /**
     * 机构外键
     */
    private Long did;


}
