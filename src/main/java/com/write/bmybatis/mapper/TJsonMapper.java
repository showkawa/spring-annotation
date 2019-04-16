package com.write.bmybatis.mapper;

import com.write.bmybatis.domain.TJson;
import com.write.bmybatis.orm.annotaton.BrianInsert;
import com.write.bmybatis.orm.annotaton.BrianParam;
import com.write.bmybatis.orm.annotaton.BrianSelect;

public interface TJsonMapper {


    @BrianInsert("insert into t_json(id,info) values(#{id},#{info})")
    int insertJson(@BrianParam("id") int id,@BrianParam("info") Object info);

    @BrianSelect("select * from t_json where id= #{id}")
    TJson sleectJsonById(@BrianParam("id") int id);
}
