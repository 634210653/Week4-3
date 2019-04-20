package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;
    private BigDecimal subTotal;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts,BigDecimal tax ){
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = tax;
        this.subTotal  = new BigDecimal(0);
    }

    public BigDecimal getFinalTotal(){
        getSubTotal();
        reduceDiscounts();
        addTax();
        return  subTotal;
    }

    private void getSubTotal(){
        orderLineItemList.forEach(lineItem->subTotal = subTotal.add(lineItem.getPrice()));
    }

    private void reduceDiscounts(){
        discounts.forEach(discount->subTotal = subTotal.subtract(discount));
    }

    private void addTax(){
        subTotal = subTotal.add( subTotal.multiply(tax));
    }

}
