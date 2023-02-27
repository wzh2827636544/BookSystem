package sun.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订 单
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String orderId;
    private Timestamp createTime;
    private BigDecimal price;
    // 0 未发货，1 已发货，2 表示已签收
    private Integer status = 0;
    private Integer userId;
}
