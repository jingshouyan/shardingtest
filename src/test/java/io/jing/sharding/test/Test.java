package io.jing.sharding.test;


import com.google.common.collect.Maps;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class Test {

    @Autowired
    NamedParameterJdbcTemplate template;

    @org.junit.Test
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS `REPLYNOTICE_INFO` (`id` varchar(32) COMMENT '' , PRIMARY KEY (`id`))  COMMENT=''";
        template.update(sql, Maps.<String,Object>newHashMap());
    }
}
