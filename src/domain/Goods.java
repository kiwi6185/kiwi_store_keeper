package domain;

public class Goods {
    /**
     * 依据数据表结构，声明成员变量
     */
    private String gs_name;
    private String gs_description;
    private String gs_pic;
    private String gs_price;

    public String getGs_name() {
        return gs_name;
    }

    public void setGs_name(String gs_name) {
        this.gs_name = gs_name;
    }

    public String getGs_description() {
        return gs_description;
    }

    public void setGs_description(String gs_description) {
        this.gs_description = gs_description;
    }

    public String getGs_pic() {
        return gs_pic;
    }

    public void setGs_pic(String gs_pic) {
        this.gs_pic = gs_pic;
    }

    public String getGs_price() {
        return gs_price;
    }

    public void setGs_price(String gs_price) {
        this.gs_price = gs_price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gs_name='" + gs_name + '\'' +
                ", gs_description='" + gs_description + '\'' +
                ", gs_pic='" + gs_pic + '\'' +
                ", gs_price='" + gs_price + '\'' +
                '}';
    }
}
