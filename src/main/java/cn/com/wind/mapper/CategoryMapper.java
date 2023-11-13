package cn.com.wind.mapper;

import cn.com.wind.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王叶盛
 */
@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     */
    void add(Category category);

    /**
     * 分类列表
     * @param userId
     */
    List<Category> list(Integer userId);
}
