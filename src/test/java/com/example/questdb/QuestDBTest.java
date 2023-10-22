package com.example.questdb;

import io.questdb.client.Sender;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class QuestDBTest {
  @Test
  public void test1() {
    try (Sender sender = Sender.builder().address("106.54.225.83:9009").build()) {
      sender.table("test6")
          .stringColumn("end","yes")
          .stringColumn("type", "updat")
          .at(1696726800000000000L);
//      sender.table("inventors")
//          .symbol("born", "USA")
//          .timestampColumn("birthday", Instant.parse("1847-02-11T00:00:00.00Z"))
//          .longColumn("id", 1)
//          .stringColumn("name", "Thomas Alva Edison")
//          .at(System.nanoTime(), ChronoUnit.NANOS);
    }
  }
}
