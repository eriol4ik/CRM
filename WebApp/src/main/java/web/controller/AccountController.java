package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import web.entity.Customer;
import web.form.Cart;
import web.form.LoginData;
import web.service.CustomerAccountService;
import web.service.CustomerService;

@Controller
@SessionAttributes(types = {Customer.class})
public class AccountController {
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private Cart cart;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginData data, Model model) {
        String pass = customerAccountService.findPass(data.getEmail());

        if (data.getPassword().equals(pass)) {
            model.addAttribute("customer", customerService.find(data.getEmail()));
            return "embedded/user-form";
        }
        return "";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        cart.clearCart();

        return "embedded/login-form";
    }

    @RequestMapping(value = "/backoffice", method = RequestMethod.GET)
    public String backoffice(Model model) {
        Customer customer = (Customer) model.asMap().get("customer");
        if (customer != null) {
            model.addAttribute("orders", customerService.findOrders(customer));
            return "backoffice";
        } else {
            return "unsignined";
        }
    }
}
