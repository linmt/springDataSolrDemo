package cn.itcast.test;

import cn.itcast.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-solr.xml")
public class TestIndexManager {

    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testIndexCreatAndUpdate() {
        List<Item> itemList = new ArrayList<Item>();
        for(long i= 1; i < 100; i++) {
            Item item = new Item();
            item.setId(i);
            item.setTitle("三星手机" + i);
            item.setCategory("手机");
            item.setPrice(new BigDecimal("9999"));
            item.setBrand("三星");

            itemList.add(item);
        }
        //保存
        solrTemplate.saveBeans(itemList);
        //提交
        solrTemplate.commit();
    }

    @Test
    public void testIndexDelte() {
        //根据主键域id删除
//        solrTemplate.deleteById("1");

        //创建查询对象
        Query query = new SimpleQuery("*:*");
        //根据查询条件删除
        solrTemplate.delete(query);
        //提交
        solrTemplate.commit();
    }
}
