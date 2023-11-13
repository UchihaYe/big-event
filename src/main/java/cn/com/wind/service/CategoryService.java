package cn.com.wind.service;

import cn.com.wind.pojo.Category;

import java.util.List;

/**
 * @author 王叶盛
 */
public interface CategoryService {

    /**
     * 添加分类
     * @param category
     */
    void add(Category category);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> list();
}
