package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


//Data : getter, setter, requiredArgsConstructor, toString, EqualsAndHashCode를 포함하는 것
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {

    }


    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
