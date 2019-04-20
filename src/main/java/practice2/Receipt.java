package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {

        BigDecimal subTotal = calculateSubtotal(products, items);
        subTotal = reduceDiscount(subTotal,products,items);
        BigDecimal grandTotal =  addTax(subTotal);
        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private  BigDecimal addTax(BigDecimal total){
        return  total.add(total.multiply(tax));
    }

    private BigDecimal reduceDiscount(BigDecimal total,List<Product> products, List<OrderItem> items){

        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);
            total = total.subtract(reduceOneItemDiscount(curItem,product));
        }
        return  total;
    }

    private BigDecimal reduceOneItemDiscount(OrderItem item,Product product){

        BigDecimal oneItemReduce = product.getPrice().multiply(product.getDiscountRate());
        BigDecimal allOrderItemReduce = oneItemReduce.multiply(new BigDecimal(item.getCount()));
        return allOrderItemReduce;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                return  item;
            }
        }
        return null;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}

