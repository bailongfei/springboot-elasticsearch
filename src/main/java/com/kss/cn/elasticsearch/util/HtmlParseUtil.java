package com.kss.cn.elasticsearch.util;

import com.kss.cn.elasticsearch.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {

    /*public static void main(String[] args) throws IOException {
        /// 使用前需要联网
        // 请求url
        String url = "http://search.jd.com/search?keyword=java";
        // 1.解析网页(jsoup 解析返回的对象是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 使用document可以使用在js对document的所有操作
        // 2.获取元素（通过id）
        Element j_goodsList = document.getElementById("J_goodsList");
        // 3.获取J_goodsList ul 每一个 li
        Elements lis = j_goodsList.getElementsByTag("li");
        //打印标签内容
        System.out.println(lis);
        // 4.获取li下的 img、price、name
        for (Element li : lis) {
            //String img = li.getElementsByTag("img").eq(0).attr("src");// 获取li下 第一张图片,不会获取到因为：一般图片特别多的网站，所有的图片都是通过延迟加载的
            String img = li.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String name = li.getElementsByClass("p-name").eq(0).text();
            String price = li.getElementsByClass("p-price").eq(0).text();
            System.out.println("=======================");
            System.out.println("img : " + img);
            System.out.println("name : " + name);
            System.out.println("price : " + price);
        }
    }
*/
    public static void main(String[] args) throws IOException {
        System.out.println(parseJD("java"));
    }

    public static List<Content> parseJD(String keyword) throws IOException {
        /// 使用前需要联网
        // 请求url
        String url = "http://search.jd.com/search?keyword=" + keyword;
        // 1.解析网页(jsoup 解析返回的对象是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 使用document可以使用在js对document的所有操作
        // 2.获取元素（通过id）
        Element j_goodsList = document.getElementById("J_goodsList");
        // 3.获取J_goodsList ul 每一个 li
        Elements lis = j_goodsList.getElementsByTag("li");
//        System.out.println(lis);
        // 4.获取li下的 img、price、name
        // list存储所有li下的内容
        List<Content> contents = new ArrayList<Content>();
        for (Element li : lis) {
            // 由于网站图片使用懒加载，将src属性替换为data-lazy-img
            String img = li.getElementsByTag("img").eq(0).attr("data-lazy-img");// 获取li下 第一张图片
            String name = li.getElementsByClass("p-name").eq(0).text();
            String price = li.getElementsByClass("p-price").eq(0).text();
            // 封装为对象
            Content content = new Content(name,img,price);
            // 添加到list中
            contents.add(content);
        }
        //System.out.println(contents);
        // 5.返回 list
        return contents;
    }

}
