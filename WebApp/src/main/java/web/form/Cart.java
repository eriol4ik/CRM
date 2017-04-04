package web.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import web.entity.Product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private Integer amount = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    private Map<Product, Integer> items = new LinkedHashMap<>();

    private DecimalFormat format = new DecimalFormat("#0.00");

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getSumFormat() {
        return format.format(sum);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void addItem(Product product, Integer amount) {
        if (!items.containsKey(product)) {
            items.put(product, amount);
        } else {
            items.replace(product, items.get(product) + amount);
        }
        this.amount = this.amount + amount;
        sum = sum.add(product.getPriceVAT().multiply(BigDecimal.valueOf(amount))); // sum = sum + price * amount
    }

    public Boolean removeItem(Product product) {
        if (!items.containsKey(product)) return false;

        Integer removedAmount = items.remove(product);
        amount = amount - removedAmount;
        sum = sum.subtract(product.getPriceVAT().multiply(BigDecimal.valueOf(removedAmount)));
        return true;
    }

    public Boolean setItemAmount(Product product, Integer newAmount) {
        if (!items.containsKey(product)) return false;

        if (newAmount == 0) {
            removeItem(product);
            return true;
        }

        Integer oldAmount = items.replace(product, newAmount);
        amount = amount + (newAmount - oldAmount);
        sum = sum.add(product.getPriceVAT().multiply(BigDecimal.valueOf(newAmount - oldAmount)));
        return true;
    }

    public void clearCart() {
        items.clear();
        amount = 0;
        sum = BigDecimal.ZERO;
    }

    public String getItemSum(Product product) {
        if (!items.containsKey(product)) return "";
        return format.format(product.getPriceVAT().multiply(BigDecimal.valueOf(items.get(product))));
    }

    public void init() {}
}
