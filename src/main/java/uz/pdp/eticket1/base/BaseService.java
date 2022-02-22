package uz.pdp.eticket1.base;

import java.util.List;

public interface BaseService<T, R> {

    String add(T t);
    R get(String id);
    List<R> getList();
}
