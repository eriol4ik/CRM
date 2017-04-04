package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.form.Cart;
import web.service.ProductService;

@Controller
public class IndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private Cart cart;

    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(Model model) {
        cart.init();
        model.addAttribute("recentProducts", productService.findInRange(1, 10, "id", false));
        return "index";
    }

    @RequestMapping(value = "/cart", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String cart() {
        return "cart";
    }

    @RequestMapping(value = "/checkout", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String checkout() {
        return "checkout";
    }

    @RequestMapping(value = "/accessories", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String accessories() {
        return "accessories";
    }


    @RequestMapping(value = "/single-product", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String singleProduct() {
        return "single-product";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "registration";
    }

    @RequestMapping(value = "/recover", method = RequestMethod.GET)
    public String recover() {
        return "recover";
    }
}
