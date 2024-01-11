package hello.itemservice.web.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.itemservice.domain.item.DeliveryCode;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.ItemType;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;
    //생성자가 하나만 있으면 Autowired 생략 가능
    // @Autowired
    // RequiredArgsConstructor를 붙이면 final 붙은 객체로 생성자가 자동으로 만들어져 생략 가능
    //    public BasicItemController(ItemRepository itemRepository) {
    //        this.itemRepository = itemRepository;
    //    }


    // 이 컨트롤러 안에 사용되는 모든 메서드들이 실행될 때 해당 model이 자동으로 담김
    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        // Enum의 모든 배열 반환
        ItemType[] values = ItemType.values();
        return values;
    }


    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/item";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());

        return "form/addForm";
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

        return "form/item";
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

        return "form/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {

        /*
        ModelAttribute 의 default
        Item -> item
         */
        itemRepository.save(item);
        //model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "form/item";
    }


    //@PostMapping("/add")
    public String addItemV4(Item item) {

        /*
        ModelAttribute 생략 가능
         */
        itemRepository.save(item);

        return "form/item";
    }

    /*
    PRG - Post/Redirect/Get
        상품 등록 처리 이후 뷰 템플릿이 아니라 상품 상세 화면으로 리다이렉트 하도록
     */
    //@PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);

        return "redirect:/form/items/" + item.getId();
    }

    /*
    RedirectAttributes
    - URL 인코딩
    - pathVariable 쿼리 파라미터까지 처리
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        log.info("item.open={}", item.getOpen());

        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        // http://localhost:8080/basic/items/3?status=true
        //  <h2 th:if="${param.status}" th:text="'저장 완료'"></h2>

        return "redirect:/form/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);


        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/form/items/{itemId}";
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
