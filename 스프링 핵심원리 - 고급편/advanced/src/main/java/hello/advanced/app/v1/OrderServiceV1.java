package hello.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderrepository;

    public void orderItem(String itemId) {
        orderrepository.save(itemId);
    }
}
