package web.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.Customer;
import web.entity.Item;
import web.entity.Order;
import web.entity.Product;
import web.enum_types.OrderStatus;
import web.form.Cart;
import web.form.ItemData;
import web.form.OrderData;
import web.service.CustomerService;
import web.service.OrderService;
import web.service.ProductService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@SessionAttributes(types = {Customer.class})
public class OrderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Cart cart;

    @RequestMapping(value = "/addtocart", method = RequestMethod.POST)
    public String addtocart(@RequestBody ItemData data) {
        cart.addItem(productService.readWithPictures(data.getId()), data.getAmount());
        return "embedded/elements/inCart";
    }

    @RequestMapping(value = "/setitemamount", method = RequestMethod.POST)
    public @ResponseBody String setItemAmount(@RequestBody ItemData data) {
        Product product = productService.readWithPictures(data.getId());
        cart.setItemAmount(product, data.getAmount());

        JsonObject object = new JsonObject();

        object.addProperty("summary", cart.getSumFormat());
        object.addProperty("amount", cart.getAmount());
        object.addProperty("itemSum", cart.getItemSum(product));

        return object.toString();
    }

    @RequestMapping(value = "/updatecartdata", method = RequestMethod.POST)
    public @ResponseBody String updateCartData() {
        JsonObject object = new JsonObject();

        object.addProperty("summary", cart.getSumFormat());
        object.addProperty("amount", cart.getAmount());

        return object.toString();
    }

    @RequestMapping(value = "/clearcart", method = RequestMethod.POST)
    public String clearCart() {
        cart.clearCart();
        return "embedded/elements/cartNoItems";
    }

    @RequestMapping(value = "/submitorder", method = RequestMethod.POST)
    public String submitOrder(@RequestBody OrderData orderData,
                              Model model) {
        Customer customer;
        if (!model.containsAttribute("customer")) {
            customer = new Customer();
            customer.setMobile(orderData.getPhone());
            customer.setName(orderData.getName());
            customerService.create(customer);
        } else {
            customer = (Customer) model.asMap().get("customer");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.OPENED);
        order.setDate(Date.valueOf(LocalDate.now()));
        for (Product product : cart.getItems().keySet()) {
            Item item = new Item(product, cart.getItems().get(product), order);
            order.getItems().add(item);
        }
        orderService.create(order);
        cart.clearCart();

        if (!"".equals(orderData.getCity()) && !"".equals(orderData.getStreet()) && !"".equals(orderData.getHouseNumber())) {
            model.addAttribute("containsAddress", true);
        }
        if (!"".equals(orderData.getEmail()) && !"".equals(orderData.getPassword()) && !"".equals(orderData.getConfirmPassword())) {
            if (orderData.getPassword().equals(orderData.getConfirmPassword())) {
                model.addAttribute("containsRegData", true);
            }
        }
        return "embedded/submitted_order";
    }

    @RequestMapping(value = "/submitorderforcustomer", method = RequestMethod.POST)
    public String submitOrderForCustomer(@RequestBody OrderData orderData,
                                         Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        customer.setName(orderData.getName());
        customer.setMobile(orderData.getPhone());
        customer.setCity(orderData.getCity());
        customer.setStreet(orderData.getStreet());
        customer.setHouseNumber(orderData.getHouseNumber());
        customerService.update(customer);
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.OPENED);
        order.setDate(Date.valueOf(LocalDate.now()));
        for (Product product : cart.getItems().keySet()) {
            Item item = new Item(product, cart.getItems().get(product), order);
            order.getItems().add(item);
        }
        orderService.create(order);
        cart.clearCart();

        if (!"".equals(orderData.getCity()) && !"".equals(orderData.getStreet()) && !"".equals(orderData.getHouseNumber())) {
            model.addAttribute("containsAddress", true);
        }
        if (!"".equals(orderData.getEmail()) && !"".equals(orderData.getPassword()) && !"".equals(orderData.getConfirmPassword())) {
            if (orderData.getPassword() != null && orderData.getPassword().equals(orderData.getConfirmPassword())) {
                model.addAttribute("containsRegData", true);
            }
        }
        return "embedded/submitted_order";
    }

}
