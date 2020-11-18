package com.dist.pas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/10/27 16:30
 * @desc 集合测试
 */
public class CollectionTest {

    @Before
    public void before() {
        System.out.println("============================== 开始测试 ==============================");
    }

    @After
    public void after() {
        System.out.println("============================== 结束测试 ==============================");
    }

    /**
     * 简单分组
     */
    @Test
    public void group() {
        List<Product> productList = init();
        Map<String, List<Product>> collect =
                productList.stream().collect(Collectors.groupingBy(Product::getCategory));
        for (Map.Entry<String, List<Product>> map : collect.entrySet()) {
            String category = map.getKey();
            List<Product> listProduct = map.getValue();
            System.out.println("[" + category + "] : [" + listProduct.toString() + "]");
        }
    }

    /**
     * 多条件分组
     */
    @Test
    public void groupBySelect() {
        List<Product> productList = init();
        Map<String, List<Product>> collect =
                productList.stream().collect(Collectors
                        .groupingBy(item -> {
                            double price = Double.parseDouble(item.getPrice());
                            if (price < 20) {
                                return "便宜货";
                            } else if (price < 40) {
                                return "值钱货";
                            } else {
                                return "买不起的";
                            }
                        }));
        for (Map.Entry<String, List<Product>> map : collect.entrySet()) {
            String category = map.getKey();
            List<Product> listProduct = map.getValue();
            System.out.println("[" + category + "] : [" + listProduct.toString() + "]");
        }
    }

    /**
     * 数据初始化
     *
     * @return 初始化后的Product集合
     */
    public List<Product> init() {
        Product product = new Product("1001", "12", "12.5", "香蕉", "水果");
        Product product1 = new Product("1002", "14", "23", "苹果", "水果");
        Product product2 = new Product("1003", "15", "34", "西瓜", "水果");
        Product product3 = new Product("1004", "18", "8", "大枣", "水果");
        Product product4 = new Product("1005", "20", "37", "五花肉", "肉类");
        Product product5 = new Product("1006", "32", "42", "排骨", "肉类");
        Product product6 = new Product("1007", "43", "68", "牛肉", "肉类");
        Product product7 = new Product("1008", "75", "18", "鸡腿", "肉类");
        Product product8 = new Product("1009", "9", "6", "苦瓜", "蔬菜");
        Product product9 = new Product("1010", "60", "3", "白菜", "蔬菜");
        Product product10 = new Product("1011", "63", "5", "花菜", "蔬菜");
        ArrayList<Product> products = new ArrayList<>(12);
        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        return products;
    }
}

/**
 * 内部类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class Product {
    private String id;

    private String num;

    private String price;

    private String name;

    private String category;
}
