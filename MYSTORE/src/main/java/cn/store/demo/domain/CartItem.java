package cn.store.demo.domain;

/**
 * 购物项封装对象
 */
public class CartItem {
    private Product product;//每个购物项的信息
    private int num;//当前类别数量
    private double subTotal;//当前购物项的总记

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /**
     * 计算后得到小计
     * @return
     */
    public double getSubTotal() {
        return product.getShop_price()*num;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
