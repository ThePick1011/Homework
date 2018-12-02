package org.xupt.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("heroes.txt"), Charset.forName("utf-8"));
        ArrayList<Hero> heroes = new ArrayList<>();
        lines.forEach(str -> {
            String[] array = str.split("\t");
            heroes.add(new Hero(
                    Integer.parseInt(array[0]),
                    array[1], array[2], array[3],
                    Integer.parseInt(array[4]),
                    Integer.parseInt(array[5]),
                    Integer.parseInt(array[6])));
        });
        System.out.println("*****武力前三********");
        heroes.stream().sorted((h1, h2) -> h2.getPower() - h1.getPower()).limit(3).forEach(hero -> {
            System.out.println(hero);
        });
        System.out.println("*****按照出生地分组并统计人数********");
        Map<String, List<Hero>> map = heroes.stream().collect(Collectors.groupingBy((h) -> h.getLoc()));
        for (List<Hero> heroList : map.values()) {
            System.out.println(heroList.size());
            for (Hero hero : heroList) {
                System.out.print("[" + hero.getName() + "," + hero.getLoc() + "]");
            }
            System.out.println("\r\n");
        }
        System.out.println("*****寿命前三********");
        heroes.stream()
                .sorted((h1,h2) -> (h2.getDeath() - h2.getBirth()) - (h1.getDeath() - h1.getBirth())).limit(3)
                .forEach(hero -> {
            System.out.println(hero);
        });
        System.out.println("*****女寿命最高********");
        List<Hero>  women = heroes.stream()
                .collect(Collectors.groupingBy((h) -> h.getSex())).get("女");
        women.stream()
                .sorted((h1,h2) -> (h2.getDeath() - h2.getBirth()) - (h1.getDeath() - h1.getBirth()))
                .limit(1).forEach(hero -> {
            System.out.println(hero);
        });
        System.out.println("*****按照年龄分组********");
        Map<String, List<Hero>> collect = heroes.stream()
                .collect(Collectors
                        .groupingBy(h -> {
                                    if ((h.getDeath() - h.getBirth()) > 0 && (h.getDeath() - h.getBirth()) <= 20)
                                        return "20以下";
                                    else if ((h.getDeath() - h.getBirth()) >= 21 && (h.getDeath() - h.getBirth()) <= 40)
                                        return "21-40";
                                    else if ((h.getDeath() - h.getBirth()) >= 41 && (h.getDeath() - h.getBirth()) <= 60)
                                        return "41-60";
                                    else
                                        return "60以上";
                                }
                        ));
        System.out.println(collect.get("20以下"));
        System.out.println(collect.get("21-40"));
        System.out.println(collect.get("41-60"));
        System.out.println(collect.get("60以上"));
        System.out.println("*****按照武力段分组********");
        Map<String, List<Hero>> listMap = heroes.stream()
                .collect(Collectors
                        .groupingBy(h -> {
                                    if (h.getPower() >= 90)
                                        return "90以上";
                                    else if (h.getPower() >= 80 && h.getPower() <= 89)
                                        return "80-89";
                                    else if (h.getPower() >= 70 && h.getPower() <= 79)
                                        return "70-79";
                                    else
                                        return "70以下";
                                }
                        ));
        System.out.println(listMap.get("90以上"));
        System.out.println(listMap.get("80-89"));
        System.out.println(listMap.get("70-79"));
        System.out.println(listMap.get("70以下"));
    }

}
