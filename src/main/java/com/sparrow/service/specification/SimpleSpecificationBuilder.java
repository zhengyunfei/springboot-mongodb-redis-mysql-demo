package com.sparrow.service.specification;

import com.sparrow.service.specification.SpecificationOperator.Join;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SimpleSpecificationBuilder<T> {

    /**
     * 条件列表
     */
    private List<SpecificationOperator> opers;

    /**
     * 构造函数，初始化的条件是and
     */
    public SimpleSpecificationBuilder(String key,String oper,Object value) {
        SpecificationOperator so = new SpecificationOperator();
        so.setJoin(Join.and.name());
        so.setKey(key);
        so.setOper(oper);
        so.setValue(value);
        opers = new ArrayList<SpecificationOperator>();
        opers.add(so);
    }

    public SimpleSpecificationBuilder() {
        opers = new ArrayList<SpecificationOperator>();
    }

    /**
     * 完成条件的添加
     * @return
     */
    public SimpleSpecificationBuilder<T> add(String key,String oper,Object value,String join) {
        SpecificationOperator so = new SpecificationOperator();
        so.setKey(key);
        so.setValue(value);
        so.setOper(oper);
        so.setJoin(join);
        opers.add(so);
        return this;
    }


    /**
     * 添加or条件的重载
     * @return this，方便后续的链式调用
     */
    public SimpleSpecificationBuilder<T> addOr(String key,String oper,Object value) {
        return this.add(key,oper,value,Join.or.name());
    }

    /**
     * 添加and的条件
     * @return
     */
    public SimpleSpecificationBuilder<T> add(String key,String oper,Object value) {
        return this.add(key,oper,value,Join.and.name());
    }


    public Specification<T> generateSpecification() {
        Specification<T> specification = new SimpleSpecification<T>(opers);
        return specification;
    }

    /**
     * 调用的时候使用SimpleSortBuilder.generateSort("name","xh_d");表示先以name升序，之后以xh降序
     */
    public static Sort generateSort(String... fields) {
        List<Order> orders = new ArrayList<Order>();
        for(String f:fields) {
            orders.add(generateOrder(f));
        }
        return new Sort(orders);
    }

    /**
     * Encapsulation sort method
     * @param f
     * @return
     */
    private static Order generateOrder(String f) {
        Order order = null;
        String[] ff = f.split("_");
        if(ff.length>=2) {
            if(ff[1].equals("d")) {
                order = new Order(Direction.DESC,ff[0]);
            } else {
                order = new Order(Direction.ASC,ff[0]);
            }
            return order;
        }
        order = new Order(f);
        return order;
    }
}
