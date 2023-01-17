package shop.mtcoding.orange.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // new product.xml
public interface ProductRepository {
    public List<Product> findAll(); // finAll = select id값

    public Product findOne(int id);

    // -1 DB에러(시스템적 에러), (양수) 변경된 행이 (양수)건, 0 변경된 행이 없음(에러가 아님)
    public int insert(@Param("name") String name, @Param("price") int price, @Param("qty") int qty);
    // @param("") 지정을 해줌

    public int delete(@Param("id") int id);

    public int update(@Param("id") int id, @Param("name") String name,
            @Param("price") int price, @Param("qty") int qty);
}
