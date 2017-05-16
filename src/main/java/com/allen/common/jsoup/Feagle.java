package com.allen.common.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by allen on 2017/5/16.
 */
public class Feagle {
        public static void main(String[] args) {
            String html = "<a href=\"http://phpinfo.me\">Lcy博客</a>";
            Document doc = Jsoup.parse(html);
            Elements link = doc.getElementsByTag("a");
            Element a = link.get(0);
            //Element b = link.get(1);//IndexOutOfBoundsException
            System.out.println(a.attr("href"));
            System.out.println(a.html());
        }
}
