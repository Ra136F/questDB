package com.example.questdb;

import com.example.questdb.entity.TbShop;
import com.example.questdb.service.TbShopService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MySQLTest {
  @Resource
  private TbShopService shopService;

  @Test
  public void insert() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    final int INSERT_NUM = 10000000;
    List<TbShop> shopList=new ArrayList<>();
    for (int i = 0; i < INSERT_NUM; i++) {
      TbShop shop=new TbShop();
      shop.setName("星聚会KTV(拱墅区万达店)"+i);
      shop.setTypeId(2L);
      shop.setImages("https://p0.meituan.net/dpmerchantpic/f4cd6d8d4eb1959c3ea826aa05a552c01840451.jpg,https://p0.meituan.net/dpmerchantpic/2efc07aed856a8ab0fc75c86f4b9b0061655777.jpg,https://qcloud.dpfile.com/pc/zWfzzIorCohKT0bFwsfAlHuayWjI6DBEMPHHncmz36EEMU9f48PuD9VxLLDAjdoU_Gd2X_f-v9T8Yj4uLt25Gg.jpg");
      shop.setArea("北部新城");
      shop.setAddress("beij");
      shop.setX(120.128958);
      shop.setY(120.128958);
      shop.setAvgPrice(30L);
      shop.setComments(30);
      shop.setScore(20);
      shop.setSold(10);
      shop.setOpenHours("10:00-22:00");
      shopList.add(shop);
    }
    shopService.saveBatch(shopList,100000);
    stopWatch.stop();
    System.out.println(stopWatch.getTotalTimeMillis());
//    TbShop shop = shopService.getById(1L);
//    System.out.println(shop);
  }
}
