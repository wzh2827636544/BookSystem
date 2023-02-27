package sun.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* 订单项
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    private Integer id;//商品id
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String orderId;
}
