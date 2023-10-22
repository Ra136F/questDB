package com.example.questdb;

import io.questdb.client.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

class QuestDbApplicationTests {

  @Test
  void contextLoads() {
    String filePath = "D:\\杂七杂八\\作业\\1.txt"; // 替换为您的文件路径

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // 使用split方法按照'|'进行拆分
        String[] parts = line.split("\\|");

        // 打印拆分后的结果
        for (String part : parts) {
          System.out.println(part);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  public void updateDate() {
    String inputFilePath = "D:\\杂七杂八\\作业\\bview.20230928.0000.bview.20230928.0000.txt";
    String ouputFilePath = "new.txt";
    try {
      BufferedReader reader=new BufferedReader(new FileReader(inputFilePath));
      BufferedWriter writer=new BufferedWriter(new FileWriter(ouputFilePath));
      String line;

      while((line=reader.readLine())!=null){
        String[] parts = line.split("\\|");
        parts[1]="1695945600";
        String modifyLine=String.join("|",parts);
        writer.write(modifyLine);
        writer.newLine();
      }
      System.out.println("写入成功");
      reader.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  public void test1() {
    String inputFilePath = "D:\\杂七杂八\\作业\\bview.20230928.0000.bview.20230928.0000.txt";
    String ouputFilePath = "new.txt";
    try {
      BufferedReader reader=new BufferedReader(new FileReader(inputFilePath));
      BufferedWriter writer=new BufferedWriter(new FileWriter(ouputFilePath));
      String line;
      Long count=0L;
      while((line=reader.readLine())!=null){
        count++;
      }
      System.out.println("该文件一个有"+count+"行");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  void test2() {
    String inputFilePath = "new.txt"; // 替换为输入文本文件的路径
//    String outputFilePath = "2.csv"; // 替换为输出CSV文件的路径

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
         Sender sender= Sender.builder().address("106.54.225.83:9009").build()) {
      String line;

//      writer.write("\"type\",\"ts\",\"flag\",\"src\",\"srcport\",\"dest\",\"path\",\"por\",\"z1\",\"z2\",\"next\",\"path2\",\"end\"");
//      writer.newLine();

      while ((line = reader.readLine()) != null) {
        // 使用split方法按照'|'进行拆分
        String[] parts = line.split("\\|");
        // 将拆分后的数据写入CSV文件
//        for (String part : parts) {
//          if(part.trim().isEmpty()){
//            writer.write("null");
//          }else {
//            if(parts[1].equals(part)){
//              Date date=new Date(Long.parseLong(part)*1000);
//              part=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//            }
//            writer.write(part);
//          }
//          if(!part.equals("NAG"))
//            writer.write(",");
//        }
        // 写入换行符
//        writer.newLine();
        sender.table("net")
            .stringColumn("type",parts[0])
            .stringColumn("flag",parts[2])
            .stringColumn("src",parts[3])
            .longColumn("srcport",Long.parseLong(parts[4]))
            .stringColumn("dest",parts[5])
            .stringColumn("path",parts[6])
            .stringColumn("por",parts[7])
            .stringColumn("z1",parts[8])
            .longColumn("z2",Long.parseLong(parts[9]))
            .longColumn("next",Long.parseLong(parts[10]))
            .stringColumn("path2",parts[11])
            .stringColumn("end",parts[12])
            .at(Long.parseLong(parts[1])*1000000000);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  public void test3() {
    long timestamp = 1633456800000L; // 示例时间戳
    String formatPattern = "yyyy-MM-dd HH:mm:ss"; // 指定日期格式

    SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
    Date date = new Date(timestamp);

    String formattedDate = dateFormat.format(date);
    System.out.println(formattedDate);
  }
  @Test
  public void test4() {
    String inputFilePath = "new.txt"; // 替换为输入文本文件的路径
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
         Sender sender= Sender.builder().address("localhost:9009").build()) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\|");
        UUID uuid=UUID.randomUUID();
        sender.table("net")
            .symbol("src",parts[3])
            .stringColumn("id",uuid.toString())
            .stringColumn("type",parts[0])
            .stringColumn("flag",parts[2])
            .longColumn("srcport",Long.parseLong(parts[4]))
            .stringColumn("dest",parts[5])
            .stringColumn("path",parts[6])
            .stringColumn("por",parts[7])
            .stringColumn("z1",parts[8])
            .longColumn("z2",Long.parseLong(parts[9]))
            .longColumn("next",Long.parseLong(parts[10]))
            .stringColumn("path2",parts[11])
            .stringColumn("end",parts[12])
            .at(1696118400L *1000000000);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
