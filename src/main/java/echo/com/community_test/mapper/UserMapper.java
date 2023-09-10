package echo.com.community_test.mapper;

import echo.com.community_test.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into users (name,account_id,token,gmt_create,gmt_modified) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified}))")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);
}
