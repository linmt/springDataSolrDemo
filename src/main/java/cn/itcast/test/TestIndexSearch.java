package cn.itcast.test;

import cn.itcast.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-solr.xml")
public class TestIndexSearch {

    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testSearch() {
        //创建查询对象
        //Query query = new SimpleQuery("*:*");

        //创建查询对象
        Query query = new SimpleQuery();
        //创建查询条件对象, 注意这里的Criteria对象和mybatis中的那个不是同一个, 只是同名而已
        Criteria criteria = new Criteria("item_title").contains("手机");
        //查询对象中放入查询条件
        query.addCriteria(criteria);

        //从第几条开始查询
        query.setOffset(11);
        //设置每页查询多少条数据
        query.setRows(20);
        //查询并返回结果
        ScoredPage<Item> items = solrTemplate.queryForPage(query, Item.class);

        //总页数
        int totalPages = items.getTotalPages();
        //查询到的总记录数
        long totalElements = items.getTotalElements();
        //查询到的数据集合
        List<Item> content = items.getContent();
        //每页有多少条数据
        int numberOfElements = items.getNumberOfElements();

        System.out.println("==========");
    }
}
