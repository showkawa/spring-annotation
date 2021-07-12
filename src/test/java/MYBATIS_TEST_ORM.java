import com.write.bmybatis.domain.TJson;
import com.write.bmybatis.mapper.TJsonMapper;
import com.write.bmybatis.sql.SqlSession;

/**
 *
 */
public class MYBATIS_TEST_ORM {

    public static void main(String[] args) {
        TJsonMapper mapper = SqlSession.getMapper(TJsonMapper.class);
       // int insertResult = mapper.insertJson(2, "{\"name\":\"张三毛\"}");

     //   System.out.println("返回影响行数:" + insertResult);

        TJson tJson = mapper.sleectJsonById(1);
        System.out.println("查询结果: " + tJson);

    }
}
