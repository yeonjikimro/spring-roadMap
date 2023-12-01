package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;
    //생성자가 하나만 있으면 Autowired 생략 가능
    // @Autowired
    // RequiredArgsConstructor를 붙이면 final 붙은 객체로 생성자가 자동으로 만들어져 생략 가능
    //    public BasicItemController(ItemRepository itemRepository) {
    //        this.itemRepository = itemRepository;
    //    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }


    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }



   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        /*
        ModelAttribute
        - 요청 파라미터 처리
        - Model 추가
         */
        itemRepository.save(item);
        //model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {

        /*
        ModelAttribute 의 default
        Item -> item
         */
        itemRepository.save(item);
        //model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "basic/item";
    }


    //@PostMapping("/add")
    public String addItemV4(Item item) {

        /*
        ModelAttribute 생략 가능
         */
        itemRepository.save(item);

        return "basic/item";
    }

    /*
    PRG - Post/Redirect/Get
        상품 등록 처리 이후 뷰 템플릿이 아니라 상품 상세 화면으로 리다이렉트 하도록
     */
    //@PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    /*
    RedirectAttributes
    - URL 인코딩
    - pathVariable 쿼리 파라미터까지 처리
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        // http://localhost:8080/basic/items/3?status=true
        //  <h2 th:if="${param.status}" th:text="'저장 완료'"></h2>

        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }


}
